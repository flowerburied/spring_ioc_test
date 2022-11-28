package com.example.spring_ioc_test01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_ioc_test01.entity.Employee;
import com.example.spring_ioc_test01.mapper.EmployeeMapper;
import com.example.spring_ioc_test01.service.EmployeeService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmployeeServiceImplTwo extends
        ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
