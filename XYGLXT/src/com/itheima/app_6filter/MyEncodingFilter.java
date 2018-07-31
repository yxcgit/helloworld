package com.itheima.app_6filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 处理中文编码问题过滤器
 */
@WebFilter(filterName="Filter",urlPatterns = "/*")
public class MyEncodingFilter implements Filter {

	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest _request = (HttpServletRequest)request;
		//处理响应乱码的问题
		response.setContentType("text/html;charset=utf-8");
		//获取请求方式
		String method = _request.getMethod();
		if ("post".equalsIgnoreCase(method)) {
			//如果是post请求直接编码即可解决
			_request.setCharacterEncoding("utf-8");
			chain.doFilter(_request, response);
		}else{
			//如果是其他方式,采用装饰者模式对_request进行加强
			chain.doFilter(new MyRequest(_request), response);
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
/*
 * 装饰者模式
 */
class MyRequest extends HttpServletRequestWrapper{
	//定义标记,判断是否进行了编码处理,防止多次处理
	private boolean flag = true;
	private HttpServletRequest request;

	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	//需要增强的方法
	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = request.getParameterMap();
		Set<Entry<String,String[]>> set = map.entrySet();
		if (flag) {
			for (Entry<String, String[]> entry : set) {
				String[] values = entry.getValue();
				for (int i = 0; i < values.length; i++) {
					try {
						values[i] = new String(values[i].getBytes("iso8859-1"), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
						throw new RuntimeException("中文乱码过滤器异常...");
					}
				}
			} 
			flag = false;
		}
		return map;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> map = getParameterMap();
		return map.get(name);
	}

	@Override
	public String getParameter(String name) {
		String[] values = getParameterValues(name);
		if (values == null) {
			return null;
		}
		return values[0];
	}
}