package com.example.spring_ioc_test01.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.spring_ioc_test01.common.R;
import com.example.spring_ioc_test01.entity.Employee;
import com.example.spring_ioc_test01.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    EmployeeService employeeService;


    private static final String msg = "123";

    /**
     * 员工登录
     *
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
//    json形式加注解requestbody
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {

//将页面提交的密码password进行MD5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

//        根据用户名来查数据库

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());

        Employee emp = employeeService.getOne(queryWrapper);

//        如果没有查询到则返回登录失败结果
        if (emp == null) {
            return R.error("登录失败");
        }
//密码比对，如果不一致则返回登录失败结果
        if (!emp.getPassword().equals(password)) {
            return R.error("登录失败");
        }
//        查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (emp.getStatus() == 0) {
            return R.error("账号已禁用");
        }
//登录成功，将员工id存入Session并返回登录成功结果

        request.getSession().setAttribute("employee", emp.getId());

        return R.success(emp);
    }

    /**
     * 员工退出
     *
     * @param request
     * @return
     */
    @PostMapping("logout")
    public R<String> logout(HttpServletRequest request) {

        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    @PostMapping
//    json形式需要添加注解 @RequestBody
//            HttpServletRequest request获取当前登录用户
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工，员工信息：{}", employee);

        //设置初始密码，md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
//        设置当前系统时间
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//获取当前登录用户的id
//        getAttribute()  方法返回的都是Object类型需要 (Long) 强转
//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);

        employeeService.save(employee);  //存入数据库

        return R.success("新增员工成功");
    }

    /**
     * 员工信息分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {

        log.info("page={}，pageSize={}，name={}", page, pageSize, name);

        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
//        添加过滤条件 eq等值查询 like模糊查询
//        StringUtils.isNotEmpty(name) 判断是否等于空
        queryWrapper.eq(StringUtils.isNotEmpty(name), Employee::getName, name);
//        添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
        employeeService.page(pageInfo, queryWrapper);

//        return R.success(   //高阶写法 有可能有bug
//                employeeService.lambdaQuery()
//                        .eq(StringUtils.isNotEmpty(name), Employee::getName, name)
//                        .page(new Page(page, pageSize))
//        );


        return R.success(pageInfo);
    }

    /**
     * 根据id修改员工信息
     *
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {

        log.info(employee.toString());

        long id = Thread.currentThread().getId();
        log.info("线程id2为:{}", id);
//        Long empId = (Long) request.getSession().getAttribute("employee");


//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(empId);
        employeeService.updateById(employee);
        return R.success("员工信息修改成功");

    }

    /**
     * 根据id查询员工信息
     *
     * @param id
     * @return
     */
    //    @PathVariable  路径传值
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id) {

        log.info("根据id查询员工信息");

        Employee employee = employeeService.getById(id);

        if (employee != null) {
            return R.success(employee);
        }

        return R.error("没有查询到对应员工");
    }

}
