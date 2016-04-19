package com.xiaov.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
 
/**

 * ���������

 * @author shark

 * @update 2015����7:16:26

 * @function

 *

 */
public class EncodeFilter implements Filter { 
	
	@SuppressWarnings("unused") 
	private FilterConfig config = null;
	private ServletContext context = null;
	//ϵͳ����


	private String encode = null;
	public void destroy() { 
		 
	} 
 
 
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//--��Ӧ������ 


		request.setCharacterEncoding(encode);
		response.setContentType("text/html;charset="+encode);
		//--����װ�����ģʽ�ı�request����ͻ�ȡ���������صķ���,�Ӷ������������������� 


		chain.doFilter(new MyHttpServletRequest((HttpServletRequest) request), response);
	} 
 
 
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.config = filterConfig;
		this.context = filterConfig.getServletContext();
		//��ȡϵͳ��������


		this.encode = context.getInitParameter("encode");
	} 
 
 
	/**

	 * HttpServletRequest��װ��

	 * @author shark

	 * @update 2015����7:19:41

	 * @function

	 *

	 */
	private class MyHttpServletRequest extends HttpServletRequestWrapper{ 
		
		private  HttpServletRequest request = null;
		private boolean isNotEncode = true;
		public MyHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		} 
		 
		//��д����


		@Override 
		public Map<String,String[]> getParameterMap() {
			try{ 
				//�жϱ䵭�ύ����


				if(request.getMethod().equalsIgnoreCase("POST")){
					request.setCharacterEncoding(encode);
					return request.getParameterMap();
				}else if(request.getMethod().equalsIgnoreCase("GET")){
					Map<String,String[]> map = request.getParameterMap();
					if(isNotEncode){
						for(Map.Entry<String, String[]> entry : map.entrySet()){
							String [] vs = entry.getValue();
							for(int i=0;i<vs.length;i++){
								
								//��request����Ĳ�������Ӳ����


								vs[i] = new String(vs[i].getBytes("iso8859-1"),encode);
							} 
						} 
						isNotEncode = false;
					} 
					return map;
				}else{ 
					return request.getParameterMap();
				} 
			}catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} 
		} 
		 
		//��д


		@Override 
		public String[] getParameterValues(String name) {
			return getParameterMap().get(name);
		} 
		 
		//��д


		@Override 
		public String getParameter(String name) {
			return getParameterValues(name) == null ? null : getParameterValues(name)[0];
		} 
		 
	}  
} 