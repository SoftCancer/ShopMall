package com.dongl.weixin.mp.handler;

import com.dongl.core.base.BaseResponse;
import com.dongl.core.constants.Constants;
import com.dongl.core.utils.RedisUtil;
import com.dongl.core.utils.RegexUtils;
import com.dongl.weixin.feign.IMemberServiceFeign;
import com.dongl.weixin.mp.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 *
 */
@Component
public class MsgHandler extends AbstractHandler {

    private static String MOBIEL_IS_EXIST = "手机号：m% ,已经被注册。";

    // 模板消息
    @Value("${dongl.weixin.template.code.message}")
    private String templateMsg ;
    // 默认消息
    @Value("${dongl.weixin.default.template.code.message}")
    private String defaultMsg ;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IMemberServiceFeign memberServiceFeign;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                && weixinService.getKefuService().kfOnlineList()
                .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        String content = "你好，请输入 ：您的手机号 ！";

        String formContent = wxMessage.getContent();
        logger.info("收到用户发送的消息："+ formContent);

        // 判断是否为手机号
        if (RegexUtils.checkMobile(formContent)){

            BaseResponse baseResponse = memberServiceFeign.mobileExist(formContent);
            Integer code = baseResponse.getCode();

            // 用户存在返回，手机号被注册
            if (code.equals(Constants.HTTP_RES_CODE_200)){
                return new TextBuilder().build(MOBIEL_IS_EXIST.replaceAll("m%",formContent), wxMessage, weixinService);
            }
            // 用户不为空也不存在，返回异常时的处理
            if (!code.equals(Constants.HTTP_RES_CODE_EXISTMOBILE_203)){
                return new TextBuilder().build(baseResponse.getMsg(), wxMessage, weixinService);
            }
            // 获取随机码
            String registCode =  String.valueOf(registCode());
            // 随机码替换指定字符串
            content = templateMsg.replaceAll("templateMsg",registCode);
            // 把随机码存入redis 中
            redisUtil.setString(Constants.WEIXINCODE_KEY+"_"+formContent,registCode,Constants.WEIXINCODE_TIMEOUT);
           return new TextBuilder().build(content, wxMessage, weixinService);
        }

        return new TextBuilder().build(content, wxMessage, weixinService);

    }

    /**
     *  获取4位随机数 的获取注册码
     * @Author: YaoGuangXun
     * @Date: 2020/2/5 0:52
     **/
    private int registCode() {
        int registCode = (int) (Math.random() * 9000 + 1000);
        return registCode;
    }

}
