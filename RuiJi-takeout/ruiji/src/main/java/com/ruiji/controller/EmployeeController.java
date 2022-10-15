package com.ruiji.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruiji.pojo.Employee;
import com.ruiji.service.impl.EmployeeServiceImpl;
import com.ruiji.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeService;

    /**
     * 登录
     *
     * @param employee
     * @param request
     * @return
     */
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


    /**
     * 注销
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.removeAttribute("user");
        return R.success("退出成功");
    }

    //http://localhost:8080/employee/page?page=1&pageSize=10

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> addUser(HttpServletRequest request, @RequestBody Employee employee) {
        employee.setPassword("123456");
        String password_md5 = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        employee.setPassword(password_md5);
        employeeService.save(employee);
        return R.success("新增员工成功");
    }

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> getPage(@RequestParam Integer page,
                           @RequestParam Integer pageSize,
                           String name) {

        Page pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //模糊查询
        queryWrapper.like(name != null, Employee::getUsername, name);
        //降序排列
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo, queryWrapper);
        System.out.println(pageInfo);

        return R.success(pageInfo);
    }

    /**
     * 根据ID修改员工信息
     *
     * @param employee
     * @return
     */
    @PutMapping
    public R update(HttpServletRequest request, @RequestBody Employee employee) {
        long id = Thread.currentThread().getId();
        log.info("线程ID为: {}"+id);

        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateUser(empId);
        employee.setUpdateTime(new Date(System.currentTimeMillis()));
        employeeService.saveOrUpdate(employee);
        return R.success("更新成功");
    }
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id) {
        log.info("根据id查询员工信息");
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return R.success(employee);
        }
        return R.error("没有查询到对应的员工信息");
    }
}
