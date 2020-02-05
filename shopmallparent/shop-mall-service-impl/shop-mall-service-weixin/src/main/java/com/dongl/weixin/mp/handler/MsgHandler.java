package com.dongl.weixin.mp.handler;

import com.dongl.core.utils.RegexUtils;
import com.dongl.weixin.mp.builder.TextBuilder;
import com.dongl.weixin.mp.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 *
 */
@Component
public class MsgHandler extends AbstractHandler {

    // 模板消息
    @Value("${dongl.weixin.template.code.message}")
    private String templateMsg ;
    // 默认消息
    @Value("${dongl.weixin.default.template.code.message}")
    private String defaultMsg ;

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

        //TODO 组装回复消息
//        String content = "收到信息内容：" + JsonUtils.toJson(wxMessage);

        String msgType = wxMessage.getMsgType();
        logger.info("公众号回复消息： "+ templateMsg + "  消息类型：" + msgType);
        String content = "你好，请输入 ：您的手机号 ！";

        String msg = wxMessage.getContent();
        logger.info("收到用户发送的消息："+ msg);

        if (RegexUtils.checkMobile(msg)){
            int registCode =  registCode();
            content = templateMsg.replaceAll("templateMsg",registCode+"");
            new TextBuilder().build(content, wxMessage, weixinService);
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
