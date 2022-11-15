package com.leo.fruitmarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leo.fruitmarket.entity.User;
import com.leo.fruitmarket.service.UserService;
import com.leo.fruitmarket.utils.CheckCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/select")
    public List<User> select() {
        List<User> list = userService.list();
        return list;
    }

    private boolean checkUser(String username) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userService.getOne(wrapper);
        if (user == null)
            return true;
        else
            return false;
    }

    private boolean checkUser(String username, String password) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username)
                .eq("password", password);
        User user = userService.getOne(wrapper);
        if (user != null)
            return true;
        else
            return false;
    }

    @PostMapping("/selectByName")
    public void selectByName(@RequestParam String username, HttpServletResponse response) throws IOException {
        boolean flag = checkUser(username);
        if (flag) {
            if (username.length() == 0) {
                flag = false;
                System.out.println("用户名输入值无效");
            } else {
                flag = true;
                System.out.println("用户名合法");
            }
        } else {
            flag = false;
            System.out.println("用户名不合法,请重新输入");
        }

        response.getWriter().write("" + flag);

    }

    @PostMapping("/register")
    public void register(String username, String password, String checkCode, HttpServletResponse response, HttpSession session) throws IOException {

//        System.out.println(username + password + checkCode);
        boolean flag = checkUser(username);
        String info;
        String checkcodesave = (String) session.getAttribute("checkCode");
        if (checkcodesave.equals(checkCode)) {
            if (username == null || username.length() == 0) {
                info = "err4";
                System.out.println("用户名不能为空");
            } else if (password == null || password.length() == 0) {
                info = "err5";
                System.out.println("密码不能为空");
            } else if (flag) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                boolean save = userService.save(user);
                if (save) {
                    info = "ok";
                    System.out.println("注册成功");
                } else {
                    info = "err1";
                    System.out.println("注册失败");
                }
            } else {
                info = "err2";
                System.out.println("用户名已存在");
            }

        } else {
            info = "err3";
            System.out.println("验证码错误");
        }
        response.getWriter().write(info);
    }

    @PostMapping("/login")
    public void login(String username, String password, String checkCode, HttpServletResponse response, HttpSession session) throws IOException {
        String info;
        String checkcodesave = (String) session.getAttribute("checkCode");
        if (checkcodesave.equals(checkCode)) {
            if (checkUser(username, password)) {
                System.out.println("登陆成功");
                User user = new User();
                user.setUsername(username);
                session.setAttribute("user", user);
                info = "ok";
            } else {
                System.out.println("用户名或密码错误！");
                info = "err1";
            }

        } else {
            System.out.println("验证码错误");
            info = "err2";
        }
        response.getWriter().write(info);
    }

    @GetMapping("/checkCodeImg")
    public void checkCodeImage(HttpServletResponse response, HttpSession session) throws IOException {

        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, response.getOutputStream(), 4);
        session.setAttribute("checkCode", checkCode);

    }

    @RequestMapping("/logout")
    public String loginOut(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login.html";
    }

    @RequestMapping("showMain")
    public String showMain() {
        return "redirect:/main.html";
    }

}
