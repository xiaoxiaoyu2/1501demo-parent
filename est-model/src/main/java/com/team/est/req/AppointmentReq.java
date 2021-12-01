package com.team.est.req;


import java.util.Date;

/**
 * @Description 预约信息入参包装类
 * @Date 2019-09-03 16:51
 * @Author Liujx
 * Version 1.0
 **/
public class AppointmentReq {

    //预约编号
    private String appointCode;
    //用户id
    private Long userId;
    //预约手机号
    private String phone;
    //短信验证码
    private String sms;
    //预约日期
    private Date appintDate;
    //预约时间段（1:上午,2:中午,3:下午,4:晚上）
    private Integer temporalInterval;
    //预约上门维修地址
    private String adress;



    public String getAppointCode() {
        return appointCode;
    }

    public void setAppointCode(String appointCode) {
        this.appointCode = appointCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getAppintDate() {
        return appintDate;
    }

    public void setAppintDate(Date appintDate) {
        this.appintDate = appintDate;
    }

    public Integer getTemporalInterval() {
        return temporalInterval;
    }

    public void setTemporalInterval(Integer temporalInterval) {
        this.temporalInterval = temporalInterval;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    @Override
    public String toString() {
        return "AppointmentReq{" +
                "appointCode='" + appointCode + '\'' +
                ", userId=" + userId +
                ", phone='" + phone + '\'' +
                ", sms='" + sms + '\'' +
                ", appintDate=" + appintDate +
                ", temporalInterval=" + temporalInterval +
                ", adress='" + adress + '\'' +
                '}';
    }
}
