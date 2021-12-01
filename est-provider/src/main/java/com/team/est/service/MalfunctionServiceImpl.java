package com.team.est.service;

import com.team.est.dto.MalfunctionDto;
import com.team.est.dto.MalfunctionOptionDto;
import com.team.est.mapper.MalfunctionMapper;
import com.team.est.mapper.MalfunctionOptionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/malfunction/")

public class MalfunctionServiceImpl {
    @Autowired(required = false)
    private MalfunctionMapper malfunctionMapper;
    @Autowired(required = false)
    private MalfunctionOptionsMapper malfunctionOptionsMapper;

    //查询评估的维度   getMalfunction?modelId=1
    @RequestMapping("getMalfunction")
    public List<MalfunctionDto> getMalfunction(@RequestParam("modelId") Long modelId){

        //1.查询商品的故障   调用mapper
        List<MalfunctionDto> malfunctionDtos = this.malfunctionMapper.getMalfunctionByModelId(modelId);
        //2.循环所有的故障，通过故障id查询对应的故障明细
      for (MalfunctionDto malfunctionDto:malfunctionDtos){
          //通过故障id查询对应的故障明细,并设置到故障的optionsList属性中
          List<MalfunctionOptionDto> malfunctionOptionDto = this.malfunctionOptionsMapper.getMOptionByMid(malfunctionDto.getId());
          //将明细设置到故障实体中
          malfunctionDto.setOptionsList(malfunctionOptionDto);
      }
       return malfunctionDtos;
    }
}
