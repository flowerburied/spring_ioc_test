package com.example.spring_ioc_test01.dto;

import com.example.spring_ioc_test01.entity.Setmeal;
import com.example.spring_ioc_test01.entity.SetmealDish;

import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
