/**
 * Project name: Day4
 * Package name: com.team1.filter
 * File name: LoginFilter.java
 * Author: Sanero.
 * Created date: Jan 10, 2019
 * Created time: 8:48:56 AM
 */

package com.team1.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.entity.User;

/*
 * @author Sanero.
 * Created date: Jan 10, 2019
 * Created time: 8:48:56 AM
 * Description: TODO - 
 */
@WebFilter(filterName = "sessionFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

  /* (non-Javadoc)
   * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
   * Author: Sanero.
   * Created date: Jan 10, 2019
   * Created time: 10:13:27 AM
   */
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  /* (non-Javadoc)
   * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
   * Author: Sanero.
   * Created date: Jan 10, 2019
   * Created time: 8:49:40 AM
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("userLogin");
    String currentURL = req.getRequestURI();
    if (user == null) {
      System.out.println("user login null");
      System.out.println(currentURL);
      if (!currentURL.contains("login")
          && !currentURL.contains("favicon.ico")) {
        res.sendRedirect("/login");
      }
      chain.doFilter(req, res);
      return;
    }
//    System.out.println(user.getUsername());
    chain.doFilter(req, res);
  }

}
