package com.team.est.util;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SendSmsUtil {
    private static String regionId="cn-hangzhou";
    private static String accesskeyId="LTAIGghp21U9II4B";
    private static String secret="yTXOEpo1FBl9s2GJlQkH0u7zIg5nnd";
    private static MethodType methodType=MethodType.POST;
    private static String sysDomain="dysmsapi.aliyuncs.com";
    private static String sysVersion="2017-05-25";
    private static String sysAction="SendSms";
    private static String SignName="宏鹏";

    /**
     * 发送短信的方法
     * @param PhoneNumbers  接收短信的手机号，多人用逗号隔开
     *  @param templateId  短信内容的模板id
     * @param contentParam  短信内容的参数  格式为json 例如:{"code":123}
     *                      如果没有参数传递null
     * @return
     */
    public static boolean SendMsg(String PhoneNumbers,String templateId,String contentParam) {
        //1.创建接口调用对象
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accesskeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);
        //2.设置请求参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(methodType);
        request.setSysDomain(sysDomain); //服务接口
        request.setSysVersion(sysVersion);  //版本
        request.setSysAction(sysAction);  //类型
        request.putQueryParameter("RegionId", regionId);  //服务器
        //以下是变化的
        //设置接收短信的手机号，多个用逗号隔开
        request.putQueryParameter("PhoneNumbers", PhoneNumbers);
        //设置签名
        request.putQueryParameter("SignName", SignName);
        //设置模板
        request.putQueryParameter("TemplateCode", templateId);
        //设置模板的参数   参数的格式为json
        if(contentParam!=null) {
            request.putQueryParameter("TemplateParam", contentParam);
        }
        try {
            //3.发送请求，获取返回数所据
            CommonResponse response = client.getCommonResponse(request);
            //System.out.println(response.getData());
            return true;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
