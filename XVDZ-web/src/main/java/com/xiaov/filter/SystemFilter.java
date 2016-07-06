package com.xiaov.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaov.utils.StrKit;
import com.xiaov.web.support.AuthenticationCahce;
import com.xiaov.web.support.CookieUtil;

public class SystemFilter implements Filter{
	private HttpServletRequest request;

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		request=(HttpServletRequest) arg0;

		HttpServletResponse response=(HttpServletResponse) arg1;
		
		String url = request.getRequestURL().toString();
		
		if(url.contains("login.html")||url.contains("adminLogin.do")||url.endsWith(".css")||url.endsWith(".js")||url.endsWith(".jpg")||url.endsWith(".png")||url.endsWith(".ico")){
			arg2.doFilter(arg0, arg1);
			return;
			
		}else{
			CookieUtil util=new CookieUtil(request);
			String openId=util.getValue("login", "user.openId", true);
			String userId=util.getValue("login", "user.userId", true);
			String admintoken = util.getValue("login", "user.token", true);
			
			if(url.contains("delete")||url.contains("update")||url.contains("save")||url.contains("index.jsp")){
				if(StrKit.isBlank(admintoken)&&StrKit.isBlank(openId)){
					response.sendRedirect("login.html");
					return;
				}else{
					
					if(admintoken.equals(AuthenticationCahce.get(userId).getToken())){
						arg2.doFilter(arg0, arg1);
						return;
					}else{
						response.sendRedirect("/error/error.html");
						return;
					}
				}
			}/*else{
				if(!StrKit.isBlank(openId)){
					arg2.doFilter(request, response);
				}else{
					if(StrKit.isBlank(admintoken)){
						response.sendRedirect("login.html");
						return;
					}else{
						if(admintoken.equals(AuthenticationCahce.get(userId).getToken())){
							arg2.doFilter(arg0, arg1);
						}else{
							response.sendRedirect("/error/error.html");
						}
						
					}
				}
			}*/
			arg2.doFilter(arg0, arg1);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		
		
	}
	private Map<String, String> getHeadersInfo() {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}
}
