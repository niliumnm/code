package com.ruiji.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruiji.pojo.Employee;
import com.ruiji.service.EmployeeService;
import com.ruiji.service.impl.EmployeeServiceImpl;
import com.ruiji.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeService;

    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request) {
        String username = employee.getUsername();
        String password = employee.getPassword();
        String password_md5 = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Employee::getUsername, username);
        Employee user = employeeService.getOne(lambdaQueryWrapper);
        if (user == null) {
            return R.error("找不到用户");
        } else if (user.getStatus() == 0) {
            return R.error("该用户已禁用");
        }

        if (user.getPassword().equals(password_md5)) {
            System.out.println(user.getId());
            System.out.println(user);

            request.getSession().setAttribute("employee", user.getId());
            return R.success(user);
        } else {
            return R.error("密码错误");
        }
    }


    @RequestMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.removeAttribute("user");
        return R.success("退出成功");
    }
}
