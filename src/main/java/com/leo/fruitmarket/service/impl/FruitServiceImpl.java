package com.leo.fruitmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leo.fruitmarket.dao.FruitDao;
import com.leo.fruitmarket.entity.Fruit;
import com.leo.fruitmarket.service.FruitService;
import org.springframework.stereotype.Service;

@Service
public class FruitServiceImpl extends ServiceImpl<FruitDao, Fruit> implements FruitService {
}
