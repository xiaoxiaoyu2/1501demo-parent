    package com.team.est.service;

    import com.team.est.dto.BrandDto;
    import com.team.est.dto.ClassifyDto;
    import com.team.est.dto.ModelDto;
    import com.team.est.mapper.BrandMapper;
    import com.team.est.mapper.ClassifyMapper;
    import com.team.est.mapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.redis.core.RedisTemplate;
    import org.springframework.data.redis.core.ValueOperations;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.concurrent.TimeUnit;

    @RestController
    @RequestMapping("/classify/")
    public class ClassifyServiceImpl {
        @Autowired(required = false)
        private ClassifyMapper classifyMapper;
        @Autowired(required = false)
        private BrandMapper brandMapper;
        @Autowired(required = false)
        private ModelMapper modelMapper;

        //注入RedisTemplate对象即可
        @Autowired(required = false)
        private RedisTemplate redisTemplate;


            @RequestMapping("getClassifyTree")
            public List<ClassifyDto> getClassifyTree(){
                ValueOperations<String,List<ClassifyDto>> vo=redisTemplate.opsForValue();

                List<ClassifyDto> classifyDtos =null;
                //1.判断Redis服务器中是否缓存分类树
                //classTree是redis中的分类树的名称
                if(redisTemplate.hasKey("classTree")){  //存在
                    //3.如果Redis服务中存在分类树的信息
                    // 则从Redis服务器中返回分类树信息
                    classifyDtos=vo.get("classTree");
                    System.out.println("我是从缓存中获取的数据.........");
                }else {   //不存在
                    //2.如果redis服务中不存在，则操作数据获取分类信息并返回
                    //并将分类树的信息保存到Redis中。
                    //从数据中获取分类数据--开始

                    //1.查询所有分类
                    classifyDtos= this.classifyMapper.getAllClassifyDto();
                    //2 .循环每个分类：获取当前分类下的品牌，设置到当前分类的品牌属性中
                    for (ClassifyDto classifyDto:classifyDtos){
                        //循环一次即为一个分类，通过分类查询品牌集合
                        List<BrandDto> brandDtos = this.brandMapper.getBrandByClassifyId(classifyDto.getId());
                        //3.循环每个品牌获取当前品牌下的商品型号，设置到品牌的属性中
                        for (BrandDto brandDto:brandDtos){
                            //循环一次即为一个品牌，通过当前品牌的编号查询商品的集合
                            Map<String,Object> parma=new HashMap<>();
                            parma.put("brandid",brandDto.getId());
                            parma.put("classifyid",classifyDto.getId());
                            List<ModelDto> modelDtos = this.modelMapper.selectModelByBrandId(parma);
                            brandDto.setModelList(modelDtos); //将商品的集合设置品牌实体的商品属性
                        }
                        classifyDto.setBrandList(brandDtos);  //将品牌集合设置到分类实体
                    }
                    //从数据中获取分类数据--结束
                    System.out.println("我是从数据库中获取的数据.........");
                    //将分类树的数据保存到Redis
                    vo.set("classTree",classifyDtos,20, TimeUnit.MINUTES);  //将这个键值保存20分钟
                }
                   return classifyDtos;
            }
        }
