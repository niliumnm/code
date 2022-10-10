package com.ruiji.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruiji.pojo.Employee;
import com.ruiji.service.EmployeeService;
import com.ruiji.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【employee(员工信息)】的数据库操作Service实现
* @createDate 2022-10-09 21:35:29
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}




