package com.team.est.mapper;

import com.team.est.dto.MalfunctionOptionDto;
import com.team.est.entity.MalfunctionOptions;
import com.team.est.entity.MalfunctionOptionsExample;
import java.util.List;

public interface MalfunctionOptionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MalfunctionOptions record);

    int insertSelective(MalfunctionOptions record);

    List<MalfunctionOptions> selectByExample(MalfunctionOptionsExample example);

    MalfunctionOptions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MalfunctionOptions record);

    int updateByPrimaryKey(MalfunctionOptions record);

    //通过故障的编号查询故障的明细
    public List<MalfunctionOptionDto> getMOptionByMid(Long malid);

    //通过一组故障明细的id，查找信息
    public List<MalfunctionOptions> selectOptions(String [] ids);

}