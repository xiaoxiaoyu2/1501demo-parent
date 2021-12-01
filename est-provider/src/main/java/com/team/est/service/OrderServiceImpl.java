package com.team.est.service;

import com.team.est.dto.EvaluateDto;
import com.team.est.entity.Appointment;
import com.team.est.entity.MaintainOrder;
import com.team.est.entity.MaintainOrderFlow;
import com.team.est.mapper.AppointmentMapper;
import com.team.est.mapper.EvaluateMapper;
import com.team.est.mapper.MaintainOrderFlowMapper;
import com.team.est.mapper.MaintainOrderMapper;
import com.team.est.req.AppointmentReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/order/")

public class OrderServiceImpl {
    @Autowired(required = false)
    private AppointmentMapper appointmentMapper;
    @Autowired(required = false)
    private MaintainOrderMapper maintainOrderMapper;
    @Autowired(required = false)
    private MaintainOrderFlowMapper maintainOrderFlowMapper;
    @Autowired(required = false)
    private EvaluateMapper evaluateMapper;

    /**
     * 实现预约下单
     * @param appointmentReq   预约实体
     * @param evaluateId  评估id
     * @param userId  用户编号
     * @return
     */
    @RequestMapping("submit")
    @Transactional
    public String submit(
            @RequestBody AppointmentReq appointmentReq,
            @RequestParam("evaluateId") Long evaluateId,
            @RequestParam("userId") Long userId){
        //1.添加预约信息
        Appointment appointment=new Appointment();
        appointment.setUserId(userId);
        appointment.setPhone(appointmentReq.getPhone());
        appointment.setAppintDate(appointmentReq.getAppintDate());
        appointment.setTemporalInterval(appointmentReq.getTemporalInterval());
        appointment.setAdress(appointmentReq.getAdress());
        appointment.setStatus(1); //状态
        appointment.setCreatedUserId(userId);
        appointment.setCreatdTime(new Date());
        appointment.setIsDelete(0);
        appointmentMapper.insertSelective(appointment);  //入库
        //2.添加订单
        //通过评估的编号找评估的实体
        EvaluateDto evaluateDto=evaluateMapper.getEvaluateDtoById(evaluateId);
        String orderNo=this.getOrderNo();  //获取编号
        MaintainOrder maintainOrder=new MaintainOrder();
        maintainOrder.setOrderNo(orderNo);  //重点  订单编号
        maintainOrder.setUserId(userId);
        maintainOrder.setModelId(evaluateDto.getModelId());
        maintainOrder.setEvaluateId(evaluateDto.getId());
        maintainOrder.setSubscription(evaluateDto.getSubscription());
        maintainOrder.setPrice(evaluateDto.getPrice());
        maintainOrder.setCreatedUserId(userId);
        maintainOrder.setStatus(1);
        maintainOrder.setCreatdTime(new Date());
        maintainOrderMapper.insertSelective(maintainOrder);
        //3.添加订单流水  状态
        MaintainOrderFlow maintainOrderFlow=new MaintainOrderFlow();
        maintainOrderFlow.setOrderNo(orderNo);
        maintainOrderFlow.setOrderStatus(1);
        maintainOrderFlow.setOrderStatusDesc("正常预约");
        maintainOrderFlow.setCreatdTime(new Date());
        maintainOrderFlowMapper.insertSelective(maintainOrderFlow);
        return orderNo;
    }
    //获取订单编号
    public String getOrderNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return  sdf.format(new Date())+ System.currentTimeMillis();
    }


}
