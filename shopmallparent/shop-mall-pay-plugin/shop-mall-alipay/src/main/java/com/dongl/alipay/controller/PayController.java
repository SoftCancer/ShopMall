package com.dongl.alipay.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.dongl.alipay.config.AlipayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Description: 支付功能
 * @author: YaoGuangXun
 * @date: 2020/3/3 15:15
 * @Version: 1.0
 */

@Controller
@RequestMapping("/pay")
public class PayController {

    @RequestMapping("/index")
    public String payIndex() {
        return "index";
    }

    @RequestMapping("/goAlipay")
    public void pay(HttpServletRequest request, HttpServletResponse response) {
//获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        try {
            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //付款金额，必填
            String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
            //订单名称，必填
            String subject = new String(request.getParameter("WIDsubject").getBytes(), "UTF-8");
            //商品描述，可空
            String body = new String(request.getParameter("WIDbody").getBytes(), "UTF-8");

            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"total_amount\":\"" + total_amount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"body\":\"" + body + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

            //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
            //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
            //		+ "\"total_amount\":\""+ total_amount +"\","
            //		+ "\"subject\":\""+ subject +"\","
            //		+ "\"body\":\""+ body +"\","
            //		+ "\"timeout_express\":\"10m\","
            //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

            String head = "<html>\n" +
                    "<head>\n" +
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">";
            //请求
            String result = alipayClient.pageExecute(alipayRequest).getBody();

            String endHtml = "<body></body></html>";
            //输出  WebContent/WEB-INF/lib/commons-logging-1.1.1.jar
            // 解决乱码问题
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println(head + result + endHtml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 订单查询
     * @Author: YaoGuangXun
     * @Date: 2020/3/3 21:39
     **/
    @RequestMapping("/query")
    public String query(HttpServletRequest request, ModelMap map) {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        try {
            //商户订单号，商户网站订单系统中唯一订单号
            String out_trade_no = new String(request.getParameter("WIDTQout_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("WIDTQtrade_no").getBytes("ISO-8859-1"), "UTF-8");
            //请二选一设置

            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no + "\"}");

            //请求
            String result = alipayClient.execute(alipayRequest).getBody();

            //输出
//        out.println(result);
            map.put("result", result);
            map.put("out_trade_no", out_trade_no);
            map.put("data", new Date());
            return "query";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    @RequestMapping("/refund")
    public String refund(HttpServletRequest request, ModelMap map) {
//获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        try {
            //商户订单号，商户网站订单系统中唯一订单号
            String out_trade_no = new String(request.getParameter("WIDTRout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("WIDTRtrade_no").getBytes("ISO-8859-1"), "UTF-8");
            //请二选一设置
            //需要退款的金额，该金额不能大于订单金额，必填
            String refund_amount = new String(request.getParameter("WIDTRrefund_amount").getBytes("ISO-8859-1"), "UTF-8");
            //退款的原因说明
            String refund_reason = new String(request.getParameter("WIDTRrefund_reason").getBytes(), "UTF-8");
            //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
            String out_request_no = new String(request.getParameter("WIDTRout_request_no").getBytes("ISO-8859-1"), "UTF-8");

            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"trade_no\":\"" + trade_no + "\","
                    + "\"refund_amount\":\"" + refund_amount + "\","
                    + "\"refund_reason\":\"" + refund_reason + "\","
                    + "\"out_request_no\":\"" + out_request_no + "\"}");

            //请求
            String result = alipayClient.execute(alipayRequest).getBody();
            map.put("result", result);
            map.put("out_trade_no", out_trade_no);
            map.put("refund_amount", refund_amount);
            map.put("refund_reason", refund_reason);
            map.put("data", new Date());
            return "refund";
        } catch (Exception e) {
            e.printStackTrace();
        }
        //输出
//        out.println(result);
        return "refund";
    }

}
