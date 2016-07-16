package com.xiaov.filter;

import com.xiaov.dao.InnerSessionDao;
import com.xiaov.model.InnerSession;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SystemFilter implements Filter{


	private HttpServletRequest request;
	private InnerSessionDao dao;


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

			Session session= dao.getSessionFactory().openSession();
			String token=request.getParameter("auth_token");
			if(token==null){
				response.sendRedirect("http://localhost/xvstore/index.php?page=account");
				return;
			}
			InnerSession innersion = (InnerSession) session.get(InnerSession.class, token);

			if(innersion!=null){


				if(innersion.getId().equals(token)){
					//判断是否超时
					if((System.currentTimeMillis()-innersion.getBegin())<innersion.getTime()){

						innersion.setBegin(System.currentTimeMillis());
						session.update(innersion);
					}else{
						response.sendRedirect("http://localhost/xvstore/index.php?page=account");
						return;
					}

				}else{
					response.sendRedirect("http://localhost/xvstore/index.php?page=account");
					return;
				}
				arg2.doFilter(request,response);
				return;
			}else{
				response.sendRedirect("http://localhost/xvstore/index.php?page=account");
				return;
			}

			//先查数据库是否已经登录


			//已登录刷新时间

			//未登录跳转到登录(返回未登录消息)


			/*CookieUtil util=new CookieUtil(request);
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
				}*/
			/*}else{
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
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
	public InnerSessionDao getDao() {
		return dao;
	}
	public void setDao(InnerSessionDao dao) {
		this.dao = dao;
	}

	public static void close(Session session){

		if(session!=null){
			session.close();
		}
	}
}
