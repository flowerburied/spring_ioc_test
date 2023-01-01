package com.example.spring_ioc_test01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.spring_ioc_test01.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
