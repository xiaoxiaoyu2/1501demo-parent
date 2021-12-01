package com.team.est.mapper;

import com.team.est.dto.ModelDto;
import com.team.est.entity.Model;
import com.team.est.entity.ModelExample;
import java.util.List;
import java.util.Map;

public interface ModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Model record);

    int insertSelective(Model record);

    List<Model> selectByExample(ModelExample example);

    Model selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Model record);

    int updateByPrimaryKey(Model record);

    //查询品牌下的商品
    List<ModelDto> selectModelByBrandId(Map<String ,Object> map);

    //查询商品的dto
    ModelDto getModelDtoById(Long id);

}