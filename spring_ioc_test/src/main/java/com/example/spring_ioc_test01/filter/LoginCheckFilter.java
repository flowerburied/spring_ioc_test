package com.example.spring_ioc_test01.filter;


import com.alibaba.fastjson.JSON;
import com.example.spring_ioc_test01.common.BaseContext;
import com.example.spring_ioc_test01.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否登录
 */

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器  支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


//        获取本次请求的URL
        String requestURI = request.getRequestURI();

        log.info("拦截到请求：{}", requestURI);

        String[] urls = new String[]{  //定义白名单
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**"

        };

//        判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

//        如果不需要处理，则直接放行
        if (check) {  //判断白名单
            log.info("本次请求{}不需要处理", requestURI);


            filterChain.doFilter(request, response); //放行
            return;
        }
//        判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute("employee") != null) {  //获取登录的缓存
            log.info("用户已登录，用户id为：{}", request.getSession().getAttribute("employee"));

            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
//            long id = Thread.currentThread().getId();
//            log.info("线程id1为:{}", id);

            filterChain.doFilter(request, response); //放行
            return;
        }

//        如果未登录则返回未登录结果,通过输出流方式向客户端响应数据
        log.info("用户未登录");

        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;


//        log.info("拦截到请求：{}", request.getRequestURI());
//        filterChain.doFilter(request, response);
    }


    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {

        for (String url : urls) {
//    匹配路径
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;


    }


}
