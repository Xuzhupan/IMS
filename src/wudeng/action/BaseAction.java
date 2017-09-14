package wudeng.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	public void write(String json){
		try {
			//设置字符编码
			getResponse().setContentType("text/html; charset=utf-8");
			getResponse().setContentType("text/json");
			getResponse().getWriter().write(json);
			getResponse().getWriter().flush();
			getResponse().getWriter().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
