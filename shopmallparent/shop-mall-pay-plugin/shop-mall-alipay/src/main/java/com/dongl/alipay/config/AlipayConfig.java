package com.dongl.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101800714984";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQ028SI201Rg7dAd3c/Y2Ld278BCEjdsYyRlvhjkRjsQy/IVloKvnJrrdMPvtLf6b7MlSFh9XHe/89CyJVMUWvlIg0RfV85P3iMtEktEwToNjWmCBbGT0EOIScvh6uT/Myv52+mGwsDMkAuBdS3YbAunHE7yxN5samczLpAPlAZ6KmOhBgpJ/Ac5mKsHYjLU+9gahwCY+Z3I4nDpkOuVJTAgoLfemrmmzt+viyXH/VKzMbas8lM6bIpn6T/DPMYpf+/ImrLsnkmk4LsFd/muWeu3GECFYpt3h9bNTCVwC0y0ROhvh3Z9ZeZBTDqfGO0q7UjvQO9Cba9X5CN8UhaQKpAgMBAAECggEAaxjv+inYiJzWQPkwaEvYcFpR6GvlVaRvzJ3bNGFQkahMFMtEopOlRE+/nNK2Yo5znS8K8fQRUwOPLEUfjoBxV/YvAvj5IPL2DGAn6UyrNZiswYSd6MAxLv3ck0zEw4K8BbtNm7R7yddqjO2V0eGf1MYLe+aijLksDcsCyuu9ZrIdqM097wGYFjG+EUq3szWw60U9XKVMPTNNhGH1KxNvKAWFOLZJVV4mPM5Rj/npVnE5A5LTpQkPLsSjJEDMxKuIoPW39BUKT6WKM4lZDWsbGQt/DgaZrWEzcFV6BAF67oz+7a2vpRqR6a/FdNVuo/n+0WLhLlSz1/+lSbnf1epgAQKBgQDazkx2Uol/jLeYaW4B0xZjb4cLyMAqE+wu6stk3+Ttz/ocOJxxeUx/EqC/ngauJD6kZt5cCoTHGf2Q0xfUOjr3i1mL/F5xOBHjwNzIH4qQAnRhfw4kPFSYs8UG4jHkPAKSP6cqjfcXqR1TwXhlheLwQGpgX3qgTDOXFb4pMzc96QKBgQCpccZZwgszAhJF6QKDMtxU2KNCzXla3GbOq/7y3hI0XI0kLVZCotc09jReRo/VJ4d6kKayMkfTeHb4L8G3WsCzO5xvpMW0r/+zRDcjaYH2wt3hp3jtyT2DfN4X8kFYZMvWAXQ6q2+b8wyG1kWkv51+yxHM+yuXDkYveGAI34TmwQKBgDfXquAP1JO/ACRf0/mXPAGItziktZaHmyGmPt6x3aVsDPMi55p2ULWltWzyEdZj3bDrqBhpftWppnNNzKkXa3E+GU6GzDutCbPMxww0MJLsbXNsj9ljpFuMld5Z4E3VmvpkUG+l58gcGyYDHI0cjHywk/WgqJU8nXYT0IKHVbPxAoGAE1eKdcqBrsPoxBQyFjnjDshf8osz9Bw+nA7kHmMdHt8FuYB3XUnrWP9VwQE6//DwNRx3+tVkf447vl7tNPnmD0+ZruDXNKX2gUcjKCauEDX0UALm+fXD0NObIo6jyOxQnFA0LVJOfjXDU0z4TDMXTlbt5EEzm52kg1yxUbH6IMECgYEAzZ8astjjwcsLrhSJ4rpczbK5JtbZw+bpQ0a9B1K/An67zSDqp6n3jOqF+Fng/PMSFefDKhDl5xzHjeK0UiCtvd9MiURHQK/tqX+sTnoG7KK7C3v7cUQPQhY7JVJ3RcctRWstipFfJxd0nvQcfoqJ2A1qmszGAJL/P4lRQB60VgQ=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjBhFC7FSC9pGGjhd159C8zVNQfhMSF/MGB3IXgT4bkRc/47KBzaTihdASNjh9Vmk2pFM+zJPP/NgxWGQmki7Cm8bZJCGyujhO5xde7n7hBQrxBdP+0/8Dk5OVI/2DKAqZ7exxLE6bNmgK/RXHIBs5HhBtwqG56X74XAoBlcoylhYRrWB+czw1OhEnOHuZsuj1ooqlUo7X6yfehqb/meApHWp/h5h6yyxenb+7AGmdedSBMo12gBEnWlFkftSJo/QSPnwZ80wU4gptG/Pc46EdpPJFZ/ujr17FJILh2P0/sDhyuQRg9bap8orWoInd7gctuL97EK+gJ5UHh5oxJYA+QIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://dongl.natapp1.cc/callback/notify";
    //	外网映射：http://dongl.natapp1.cc
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://dongl.natapp1.cc/callback/return";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	// 支付宝网关 正式环境
//	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
    // 支付宝网关 沙箱环境
	public static String gatewayUrl = " https://openapi.alipaydev.com/gateway.do";

	// 日志路径
	public static String log_path = "D:\\AliPay_logs";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

