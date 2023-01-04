package com.example.spring_ioc_test01.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.spring_ioc_test01.common.R;
import com.example.spring_ioc_test01.entity.User;
import com.example.spring_ioc_test01.service.UserService;
import com.example.spring_ioc_test01.utils.SMSUtils;
import com.example.spring_ioc_test01.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;


//测试git
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    UserService userService;


    @Resource
    RedisTemplate redisTemplate;

    @Resource
    CacheManager cacheManager;

    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello world");
        return "OK";
    }

    /**
     * 发送手机验证码
     * CachePut：将方法返回值放入缓存数据
     * value：缓存的名称，每个缓存名称下面可以有多个key
     * key：缓存的key
     *
     * @param user
     * @return
     */
//    @CachePut(value = "userCache",key = "#result.id")
    @CachePut(value = "userCache", key = "#user.phone")
    @PostMapping("/sendMsg")
    public User sendMsg(@RequestBody User user, HttpSession session) {

//        获取手机号
        String phone = user.getPhone();
        User user1 = new User();
        if (StringUtils.isNotEmpty(phone)) {

//        生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}", code);
            //        需要将生成的验证码保存
//            session.setAttribute(phone, code);

//            将生成的验证码缓存到redis中，并且设置有效期5分 钟

            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

//        调用阿里云提供的短信api
//            SMSUtils.sendMessage("验证码短信", "SMS_267145188", phone, code);

            return user1;
        }
        return user1;

    }

    /**
     * 移动端用户登录
     *
     * @param map
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {

        log.info(map.toString());
//        获取手机号
        String phone = map.get("phone").toString();
//        获取验证码
        String code = map.get("code").toString();
//        从Session中获取保存的验证码
//        Object codeInSession = session.getAttribute(phone);
        //        从redis中获取保存的验证码
        Object codeInSession = redisTemplate.opsForValue().get(phone);


//        进行验证码的比较（页面提交的验证码和Seccion中保存的验证码比较）
        if (codeInSession != null && codeInSession.equals(code)) {

            //        如果能对比成功，说明登录成功

            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);

            User user = userService.getOne(queryWrapper);
            log.info("user$={}", user);
            if (user == null) {

                //        判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }

            session.setAttribute("user", user.getId());

//            如果用户登录成功则删除redis中缓存的验证码
            redisTemplate.delete(phone);

            return R.success(user);
        }


        return R.error("登录失败");

    }

}
