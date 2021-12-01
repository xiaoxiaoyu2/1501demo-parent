package com.team.est.mapper;

import com.team.est.dto.EvaluateDto;
import com.team.est.entity.Evaluate;
import com.team.est.entity.EvaluateExample;
import java.util.List;

public interface EvaluateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    List<Evaluate> selectByExample(EvaluateExample example);

    Evaluate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);

    //通过评估的编号查询评估休息
    EvaluateDto getEvaluateDtoById(Long id);


}