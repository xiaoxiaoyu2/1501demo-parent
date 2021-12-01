package com.team.est.mapper;

import com.team.est.dto.MalfunctionDto;
import com.team.est.entity.Malfunction;
import com.team.est.entity.MalfunctionExample;
import java.util.List;

public interface MalfunctionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Malfunction record);

    int insertSelective(Malfunction record);

    List<Malfunction> selectByExample(MalfunctionExample example);

    Malfunction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Malfunction record);

    int updateByPrimaryKey(Malfunction record);

    //通过商品查询故障
    List<MalfunctionDto> getMalfunctionByModelId(Long id);


}