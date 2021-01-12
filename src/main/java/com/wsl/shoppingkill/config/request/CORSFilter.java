package com.wsl.shoppingkill.config.request;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : WangShiLei
 * @date : 2020/12/26 11:39 上午
 **/
@WebFilter("/*")
public class CORSFilter implements Filter {
    public CORSFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //设置跨域请求
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String[] allowDomains = {"http://oss.wslhome.top",
                "http://static.wslhome.top",
                "http://admin.wslhome.top",
                "http://www.wslhome.top",
                "http://test.wslhome.top",
                "http://kill.wslhome.top",
                "https://oss.wslhome.top",
                "https://static.wslhome.top",
                "https://admin.wslhome.top",
                "https://www.wslhome.top",
                "https://test.wslhome.top",
                "https://kill.wslhome.top"};
        Set allowOrigins = new HashSet(Arrays.asList(allowDomains));
        String originHeads = request.getHeader("Origin");
        if(allowOrigins.contains(originHeads)){
            response.setHeader("Access-Control-Allow-Origin", originHeads);
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,HEAD,PUT,PATCH");
            response.setHeader("Access-Control-Max-Age", "36000");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization,authorization");
            response.setHeader("Access-Control-Allow-Credentials","true");
        }
        chain.doFilter(req, response);
    }

        @Override
        public void init(FilterConfig fConfig) throws ServletException {
        }
    }