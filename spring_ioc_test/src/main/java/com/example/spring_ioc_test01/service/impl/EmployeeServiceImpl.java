package com.example.spring_ioc_test01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_ioc_test01.entity.Employee;
import com.example.spring_ioc_test01.mapper.EmployeeMapper;
import com.example.spring_ioc_test01.service.EmployeeService;
import org.springframework.stereotype.Service;

//实现类 合并mapper和service
@Service
public class EmployeeServiceImpl extends
        ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
