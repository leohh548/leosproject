package com.leo.fruitmarket.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.leo.fruitmarket.entity.Fruit;
import com.leo.fruitmarket.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fruit")
public class FruitController {
    @Autowired
    FruitService service;

    @GetMapping("/select")
    public List<Fruit> select() {
        List<Fruit> list = service.list();
        return list;
    }

    public boolean selectNumber(String number) {
        Fruit fruit = service.getById(number);
        if (fruit == null)
            return true;
        else
            return false;
    }

    @PostMapping("/insert")
    public void insert(@RequestBody Fruit fruit, HttpServletResponse response) throws IOException {
        if (fruit.getNumber() != null && fruit.getNumber().length() != 0) {
            if (selectNumber(fruit.getNumber())) {
                if (service.save(fruit)) {
                    response.getWriter().write("ok");
                    System.out.println("新增成功");
                } else {
                    response.getWriter().write("err1");
                    System.out.println("添加失败");
                }
            } else {
                response.getWriter().write("err2");
                System.out.println("水果编号重复");
            }
        } else {
            response.getWriter().write("err3");
            System.out.println("水果编号不能为空");
        }

    }

    @GetMapping("/selectByNumber")
    public Fruit selectByNumber(@RequestParam String number, HttpSession session) {
        Fruit fruit = service.getById(number);
        String num = fruit.getNumber();
        session.setAttribute("number", num);
        return fruit;
    }

    @PostMapping("/updateFruit")
    public void updateFruit(@RequestBody Fruit fruit, HttpServletResponse response, HttpSession session) throws IOException {
        String num = (String) session.getAttribute("number");
        if (fruit.getNumber().equals(num)) {
            boolean res = service.updateById(fruit);
            if (res)
                response.getWriter().write("ok");
            else
                response.getWriter().write("err");
        } else {
            if (selectNumber(fruit.getNumber())) {
                UpdateWrapper<Fruit> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("number", num).set("number", fruit.getNumber());
                boolean res = service.update(fruit, updateWrapper);
                if (res)
                    response.getWriter().write("ok");
                else
                    response.getWriter().write("err");
            } else {
                response.getWriter().write("err1");
                System.out.println("编号已存在");
            }
        }
    }

    //单行删除
    @GetMapping("/deleteRow")
    public void deleteRow(@RequestParam String number, HttpServletResponse response) throws IOException {
        boolean res = service.removeById(number);
        if (res) {
            response.getWriter().write("ok");
        }
    }

    //批量删除
    @GetMapping("/deleteRows")
    public void deleteRows(@RequestParam List<String> numbers, HttpServletResponse response) throws IOException {
        int countRes = 0;
        for (String number : numbers) {
            boolean res = service.removeById(number);
            if (res) {
                countRes++;
            }
        }
        response.getWriter().write(countRes);
        System.out.println(countRes);
    }

    //搜索表单
    @PostMapping("/selectByObject")
    public List<Fruit> selectByFruit(@RequestBody Fruit fruit) {
        //List<Object> listObjs(Wrapper<T> queryWrapper);
        System.out.println(fruit);

        List<Fruit> resList;
        QueryWrapper<Fruit> queryWrapper = new QueryWrapper<>();
        if (fruit.getNumber() == null && fruit.getPrice() == 0 && fruit.getUnit() == null && fruit.getFruitname() == null) {
            List<Fruit> res = select();
            System.out.println(res);
            return res;
        } else {
            queryWrapper.or().eq("number", fruit.getNumber()).or().eq("fruitname", fruit.getFruitname())
                    .or().eq("price", fruit.getPrice()).or().eq("unit", fruit.getUnit());
            resList = service.list(queryWrapper);
            System.out.println(resList);
            return resList;
        }
    }

}
