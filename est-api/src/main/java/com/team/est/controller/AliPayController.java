package com.team.est.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.team.est.util.AlipayConfig;
import feign.RequestLine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * @Classname AliPayController
 * @Description TODO
 * @Created by Administrator
 */
@Controller
public class AliPayController {
    //进入页面
    @RequestMapping("/goIndex")
    public String goIndex(){
        return "index";
    }

    //去支付的接口
    @RequestMapping("/goPay")
    public void goPay(
                         HttpServletRequest request,
                       HttpServletResponse response,
                       String orderid,
                       String subject,
                       String money){
        //1.获取支付的相关参数:如订单的编号、金额
        //2.向支付宝发起支付的请求
        //2.1.创建支付客户端对象
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
        //2.2创建支付交易的请求
        AlipayTradePagePayRequest alipay_request=new AlipayTradePagePayRequest();  //扫码支付请求
        //封装交易数据
        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
        model.setOutTradeNo(orderid);  //交易的订单号
        model.setSubject(subject);   //订单的名称..
        model.setTotalAmount(money);  //订单的金额
        //model.setBody(body);
        model.setTimeoutExpress("2m");  //超时时间 可空
        //model.setProductCode("QUICK_WAP_WAY");  //销售产品码 必填
        model.setProductCode("FAST_INSTANT_TRADE_PAY"); //必需设置为此参数，网站扫描支付与AlipayTradePagePayRequest一起使用

        alipay_request.setBizModel(model);
        // 设置异步通知地址
        alipay_request.setNotifyUrl(AlipayConfig.notify_url);
        // 设置同步地址
        alipay_request.setReturnUrl(AlipayConfig.return_url);

        // form表单生产
        String form = "";
        // 调用SDK生成表单
        try {
            form = client.pageExecute(alipay_request).getBody();
            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //2.支付成功后的回调请求
    @RequestMapping("/alipayBack")
    @ResponseBody
    public String alipayBack(HttpServletRequest request,
                             HttpServletResponse response) throws  Exception {
        PrintWriter out=response.getWriter();
        //1.获取返回的参数
        //2.验签操作
        Map<String,String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
        if(verify_result){
            //进行后期支付成功的处理:如修改订单的状态为已支付
            System.out.println("进行后期业务的编写");
            return "paySuccess";
        }
        else {
            return  "payFail";
        }
    }

}
