package com.rory.filter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
             System.out.println("Filter init....");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String str= request.getRequestURI();
        System.out.println(" Filter doFilter...."+str);
    }

    @Override
    public void destroy() {
        System.out.println("Filter destroy....");
    }
}
