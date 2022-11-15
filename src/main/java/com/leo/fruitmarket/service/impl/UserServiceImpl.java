package com.leo.fruitmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leo.fruitmarket.dao.UserDao;
import com.leo.fruitmarket.entity.User;
import com.leo.fruitmarket.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
