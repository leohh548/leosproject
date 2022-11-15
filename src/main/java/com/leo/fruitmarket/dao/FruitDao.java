package com.leo.fruitmarket.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.fruitmarket.entity.Fruit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FruitDao extends BaseMapper<Fruit> {
}
