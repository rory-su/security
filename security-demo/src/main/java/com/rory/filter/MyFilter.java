package com.rory.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/*

 */
//@Component
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
             System.out.println("Filter init....");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String str= request.getRequestURI();
        System.out.println(" Filter doFilter start...."+str);
        Long start=new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("myfiler 耗时"+(new Date().getTime()-start));
        System.out.println(" Filter doFilter end...."+str);
    }

    @Override
    public void destroy() {
        System.out.println("Filter destroy....");
    }
}
