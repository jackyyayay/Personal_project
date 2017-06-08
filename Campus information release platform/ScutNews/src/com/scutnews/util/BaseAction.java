package com.scutnews.util;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements RequestAware,ApplicationAware,ServletRequestAware,ServletContextAware{
	public Map<String,Object> request;
	public Map<String,Object> response;
	public Map<String,Object> cookie;
	public Map<String,Object> application;
	public HttpServletRequest httpRequest;
	public HttpServletResponse httpResponse;
	public ServletContext httpApplication;
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public HttpServletRequest getHttpRequest()
	{
		return httpRequest;
	}

	public void setHttpRequest(HttpServletRequest httpRequest)
	{
		this.httpRequest = httpRequest;
	}

	public HttpServletResponse getHttpResponse()
	{
		return httpResponse;
	}

	public void setHttpResponse(HttpServletResponse httpResponse)
	{
		this.httpResponse = httpResponse;
	}

	public ServletContext getHttpApplication()
	{
		return httpApplication;
	}

	public void setHttpApplication(ServletContext httpApplication)
	{
		this.httpApplication = httpApplication;
	}


	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	public void setServletRequest(HttpServletRequest httpReqeust) {
		this.httpRequest = httpReqeust;
	}

	public void setServletContext(ServletContext httpApplication) {
		this.httpApplication = httpApplication;
	}

}
