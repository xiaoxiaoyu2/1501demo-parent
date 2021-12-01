package com.team.est.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.est.dto.ModelDto;
import com.team.est.entity.Model;
import com.team.est.entity.ModelExample;
import com.team.est.mapper.ModelMapper;
import com.team.est.req.ModelReq;
import com.team.est.util.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/model/")
public class ModelServiceImpl {
    @Autowired(required = false)
    private ModelMapper modelMapper;

    //编写服务返回商品列表信息
    //注意:在springcloud的服务提供者中如果方法传递的是对象请使用@RequestBody
    //如果@RequestBody传递参数时，浏览器无法传requestBody的参数需要使用postman进行测试
    @RequestMapping("searchModel")
    public Page<ModelDto> searchModel(@RequestBody ModelReq modelReq){

        //根据方法的参数进行动态查询(利用PageHelper)
        //1.开启分页
        PageHelper.startPage(modelReq.getPageNo(),modelReq.getPageSize());
        //2.通过条件查询所有商品
        ModelExample modelExample=new ModelExample();
        ModelExample.Criteria criteria=modelExample.createCriteria();
        //追加条件
        if(modelReq.getClassifyId()!=null) {
            criteria.andClassifyIdEqualTo(modelReq.getClassifyId());
        }
        if(modelReq.getBrandId()!=null) {
            criteria.andBrandIdEqualTo(modelReq.getBrandId());
        }
        if(!StringUtils.isEmpty(modelReq.getModelName())){
            criteria.andModelNameLike("%"+modelReq.getModelName()+"%");
        }
        List<Model> modelList=this.modelMapper.selectByExample(modelExample);
        PageInfo<Model> pageInfo=new PageInfo<>(modelList);
        //将数据库获取的结果，转化返回返回的DTO
        // int pageNo, int pageSize,Integer total
        Page<ModelDto> modelDtoPage=new Page<ModelDto>(pageInfo.getPageNum(),pageInfo.getPageSize(), (int) pageInfo.getTotal());
        //设置总页
        modelDtoPage.setPages(pageInfo.getPages());
        //将数据的实体集合转化为商品dto的实体集合
        List<ModelDto> modelDtos=new ArrayList<>();
        for (Model model:pageInfo.getList()) {
            //循环一次   一个modelDto
            ModelDto modelDto=new ModelDto(model.getId(),model.getModelName(),model.getFaceImg());
            //将dto添加到集合
            modelDtos.add(modelDto);
        }
        //设置返回数据
        modelDtoPage.setList(modelDtos);
        return modelDtoPage;
    }



    //加载商品的单条
    //http://.../getModel?modelId=1  利用?传参
    //http://..../getModel/1    链接中传入
    @RequestMapping("getModel/{modelId}")
    public ModelDto getModelById(@PathVariable(value = "modelId") Long modelId){
        //调用mapper查询单条信息
        Model model=modelMapper.selectByPrimaryKey(modelId);
        if(model!=null){
            //将表实体转化为DTOW传递数据
            return new ModelDto(model.getId(),model.getModelName(),model.getFaceImg(),model.getContentImg());
        }
        return null;
    }
}
