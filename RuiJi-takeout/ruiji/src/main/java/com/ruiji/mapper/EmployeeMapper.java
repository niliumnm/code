package com.ruiji.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ruiji.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Administrator
* @description 针对表【employee(员工信息)】的数据库操作Mapper
* @createDate 2022-10-09 21:38:07
* @Entity com.ruiji.pojo.Employee
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    int insertSelective(Employee employee);

    List<Employee> selectById(@Param("id") Long id);

}




