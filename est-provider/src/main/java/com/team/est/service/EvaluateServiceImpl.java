package com.team.est.service;

import com.team.est.dto.EvaluateDetailDto;
import com.team.est.dto.EvaluateDto;
import com.team.est.dto.ModelDto;
import com.team.est.entity.Evaluate;
import com.team.est.entity.EvaluateDetail;
import com.team.est.entity.Malfunction;
import com.team.est.entity.MalfunctionOptions;
import com.team.est.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/evaluate/")

public class EvaluateServiceImpl {

    @Autowired(required = false)
    private MalfunctionMapper malfunctionMapper;

    @Autowired(required = false)
    private MalfunctionOptionsMapper malfunctionOptionsMapper;

    @Autowired(required = false)
    private EvaluateMapper evaluateMapper;

    @Autowired(required = false)
    private EvaluateDetailMapper evaluateDetailMapper;

    @Autowired(required = false)
    private ModelMapper modelMapper;
    /**
     * 评估
     * @param modelId  商品型号
     * @param optionIds 故障的选项  1,5
     * @param userid  用户id
     */
    @RequestMapping("assess")
    @Transactional
    public EvaluateDto assess(@RequestParam("modelId") Long modelId,
                              @RequestParam("optionIds") String optionIds,
                              @RequestParam("userid") Long userid){

        //1.通过optionIds计算评估价格
        //1.1通过optionIds查询所有故障点
        String[] options =optionIds.split(",");
        List<MalfunctionOptions> malfunctionOptions=malfunctionOptionsMapper.selectOptions(options);
        //1.2循环故障点进行评估金额的累计
        BigDecimal totalPrice=new BigDecimal(0);
        for (MalfunctionOptions option:malfunctionOptions){
            //计算每个故障点的评估价格
            //通过故障细详去查找到对应的故障
            Malfunction malfunction=malfunctionMapper.selectByPrimaryKey(option.getMalfId());
            BigDecimal oprice=malfunction.getMaintainTopPrice().multiply(new BigDecimal(option.getRatio()*0.01d));
            // 累计总价
            totalPrice=totalPrice.add(oprice);
        }
        //级联添加
        //2.保存评估的信息(商品编号、评估价格、订金、用户id...)
        Evaluate evaluate=new Evaluate();
        evaluate.setModelId(modelId);
        //总价
        evaluate.setPrice(totalPrice);
        evaluate.setSubscription(totalPrice.multiply(new BigDecimal(0.1)));
        //用户编号
        evaluate.setCreatedUserId(userid);
        evaluate.setCreatdTime(new Date());
        //保存评估信息
        evaluateMapper.insertSelective(evaluate);
        //注意:添加时，如果想获取添加记录时产生的自动增长列的属性值
        //修改添加持久化操作，设置以下属性即可,将自动增长列的值填充到实体属性中
        //useGeneratedKeys="true" keyProperty="id"
        //System.out.println("获取添加记录，产生的自动编号的id:"+evaluate.getId());
        //3.保存评估的明细(保存选择的故障)
        for (MalfunctionOptions option:malfunctionOptions){
            //创建评估明细记录故障清单
            EvaluateDetail evaluateDetail=new EvaluateDetail();
            //设置评估的id
            evaluateDetail.setEvaluateId(evaluate.getId());
            evaluateDetail.setMalfId(option.getMalfId());
            evaluateDetail.setOptionId(option.getId());
            evaluateDetail.setOptionName(option.getOptionName());
            evaluateDetail.setCreatedUserId(userid);
            evaluateDetail.setCreatdTime(new Date());
            evaluateDetailMapper.insertSelective(evaluateDetail);
        }
        return new EvaluateDto(evaluate.getId(),evaluate.getModelId(),evaluate.getSubscription(),evaluate.getPrice());
    }

      //根据当前评估id获取信息
    @RequestMapping("getEvaluateId/{evaluateId}")
    public EvaluateDto  getEvaluateId(@PathVariable("evaluateId") Long evaluateId){
        //1.通过评估的id获取评估的dto
        EvaluateDto evaluateDto=this.evaluateMapper.getEvaluateDtoById(evaluateId);
        //2.查询评估的商品dto并设置评估dto
        ModelDto modelDto=modelMapper.getModelDtoById(evaluateDto.getModelId());
        evaluateDto.setModel(modelDto);
        //3.查询评估的详情并设置评估dto
        List<EvaluateDetailDto> evaluateDetailDtos=this.evaluateDetailMapper.getEvaluateDetailById(evaluateDto.getId());
        evaluateDto.setDetailList(evaluateDetailDtos);
        return evaluateDto;
    }



}
