package org.lab1.context;

import org.lab1.bean.auth.UserBean;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        UserBean userBean = (UserBean) httpRequest.getSession().getAttribute("userBean");
        if (userBean == null || userBean.getId() == 0) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/views/login.xhtml");
            return;
        }

        chain.doFilter(request, response);
    }
}