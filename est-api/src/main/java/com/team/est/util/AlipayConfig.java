package com.team.est.util;

/**
 * @Classname AlipayConfig
 * @Description TODO
 * @Date 2021/3/14 10:13
 * @Created by Administrator
 */
public class AlipayConfig {
    //商户appid
    public static String APPID = "2021000118604238";
    //私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCMQOJ4ci9udO76ymSww7BDKnjuk5BnOauWnlCCFqQguCbdcfO83pDuvZveVrte8/429oXqyEnrAUDeBN86fUPQ/5xpHLEvhvyf5lOmT0Lm8hE2uy/xl3wjUJj3Foyx2UV1ynkEOAVW3qrKaK1sBmNTocyZPOyPjHYvqbdfhZLefQThPL5rnyR5c86yPrQRahn3IQHAMMC5H8rH6dGxQGV72Qa9xOB7NNrdofLarXNc1lXPYEXSPTikrBL5caRyyuU+3a4r/C8UzRIDaJYJwzJPGx7fhgZ4iWTSzOZFdYYaQQmzmFOzRD2zM6nrJtx0jP5SFMYWhYJ0qi7e6157EvqPAgMBAAECggEAEVl5amLdoTFP2rAssn0SkcxwNzsD0vEcW75rwRvh47ZEAIK3Hvb9QLsty9w4w281VEE8oaVzF2IJTy2VG5whRnpIM+x7sqBlIbaRRHJrmTv6iyWusZkGnyu6MF6Wuu1gnUiVncL8ThmvUOiQA1vq746FS7Nwo/VBLXY2G9ffKixniUGOcHx7NtP6IoSeTsZbQGbvVJ8Hw+Cf3sbo3WBGl6y+5znEt2Sd8sxRcYyc3AsVNopLg5cRH9G6FJXe1YC8JCVL+JMiyu7+I4eFzEy23ZGhP0Kz3je0LeSWRzGlmt+GsQxQqSj4dg09108RNwHRMz3wr5GYyg+u/a8SmFA4+QKBgQDblFiIe0KYPmPEyRFjSZYS86SjKCUJ6U8sXHZCXeLUu/cCYWepzt7XL9bcjOaqhap9msFIm6S3n9wWiVcPSmf/+UEmM4nJvMKY+EHe6/L4RGoax1qK86w/ejX59OSVDWeBRVtTx5BHemRzsq4mo7reBP5TN9BjhOmp933WjCc9UwKBgQCjhD6ANUxLdIcmmWtvLWyUA/Qe0jNM3yY72mwWV7gBOw5931vLXgNNPd+rCeoTT7yOi/r5HJ7cL+OZVl6ehkfAA5mvemrGKKZOZbHuScjtVKmJs/Go5gqzLIvNaDhfZczB+oGISiG82DkIKbpHy/0InbN0fvSEeC8iTpcL+pQqVQKBgF0aTV+xQ89hxNPPnXvH28Y1YH19rBbDwjpBCjOhs2gYOufbfSTW/k2njyb852EuQ9f2IjYzKGhjc+JqYWuuVOKIQUN9XtMXIjvSJ7vpt5/lAkhQduer4kEYBILzf2hjwpthWRPw7hAgqS8VeBoSGk3BOET4yXcpCBp1QfoSeyDZAoGAAkDFN7EETxnY7uA4SbzffR6c03vj9xAwD60PxjMJNwMgTP2x3xh5hSvwFAQ79G4p0/D70Ujiq3gBc9Br11VUUcH68R8Hr5lJyVS/YeAkY5jCCUKwdrpMtXFMr9Tayf22F4ojFMSouFhmWMVyaqDV6LN7kIRbTCP3yko47JJUTk0CgYAe2yBljnrhIR+JKlyFE5pW4K2H5xSKmkDXcxQ52BweEPZRfmYr27ZfZ2cWgzraKQpS8dTMfSHLcYgaJtksljmEdRXi4QKUn1ztARjNPxA4r6smHCczuBwvOtuTPD9Dufyg+SAnRspcDWTklZRFHxiYuo1ciCbndRSLwh/Yq7Nhkg==";
    //这个需要外网，这个地址是支付宝转发到本地系统的 每天24小时内发送8次确认请求
    //服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "";
    //页面跳转同步通知页面路径   这是本地系统转发的请求，支付后自动跳转的
    //需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    //理解为:支付成功后，本系统想做什么....
    public static String return_url = "http://localhost:8080/est/alipayBack";
    //请求网关地址
    public static String URL ="https://openapi.alipaydev.com/gateway.do";
    //编码
    public static String CHARSET = "UTF-8";
    //返回格式
    public static String FORMAT = "json";
    //支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgPVcJFz1ozNI/PImDxVmQAyONWGlb0x6XYeB0zn3jMCFh9DQcUje9bBL1SLBTTpz3AjD9fUgAzzojcd060exwnWWbQfJwY3ZGLGS72SRcmrfKiBRgNVIQ5FlYcVeTP2BkzGQzoNmOTjjBirXez27L7ClJb4s3NS8SHnEIvlyZqzi2dhRSM3ZWiy5I4/JWN641SqvBavSUK+uYwSR55lBM7taAT6yRxe7YeZ+yjSxc4rxjvperqTuyaOFCusYPWDE2/iEVjYrp/o+A/1NP1pOTj/Ehk9KvDYRA2br7WuIeqHN5enjBbaVUuZzG6FvE3Wvhn0MNZMPqR3qtfzFxrv8CwIDAQAB";
    //日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}

