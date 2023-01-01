package com.example.spring_ioc_test01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_ioc_test01.entity.AddressBook;
import com.example.spring_ioc_test01.mapper.AddressBookMapper;
import com.example.spring_ioc_test01.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImp extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
