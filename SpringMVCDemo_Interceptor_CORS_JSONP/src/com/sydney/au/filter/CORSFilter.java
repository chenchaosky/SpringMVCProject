package com.sydney.au.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

//跨域请求的CORS实现方法
public class CORSFilter implements Filter {
	
    String[] originProperties; 

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
    
    //实现向响应头部添加Access-Control-Allow-Origin属性，实现跨域访问。
    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException, ServletException {
        
        //TODO Auto-generated method stub        
        HttpServletResponse response=(HttpServletResponse)sResponse;
        String curOrigin = response.getHeader("Origin");  
        System.out.println("当前访问来源是："+curOrigin);    

        //如果当前访问来源在application.properties的Access-Control-Allow-Origin配置范围内，则允许访问，否则不允许  
        if(curOrigin != null) {  
            for (int i = 0; i < originProperties.length; i++) {  
                //System.out.println("允许跨域访问的来源是："+originProperties[i]);  
                if(curOrigin.equals(originProperties[i])) {  
                	response.setHeader("Access-Control-Allow-Origin", curOrigin);  
                }  
            }  
        } else { 
        	//对于无来源的请求(比如在浏览器地址栏直接输入请求的)，那就只允许我们自己的机器可以吧  
        	response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1");  
        }  
        //Access-Control-Allow-Origin的值为*，代表对所有的域都可以实现跨域访问
        //response.setHeader("Access-Control-Allow-Origin", "*");
        
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT,HEAD"); 
        
        //让Filter按默认方式处理请求和响应，如果没写，那么response里没有body         
        chain.doFilter(sRequest, sResponse);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    	//读取Properties文件获取可跨域执行的域名
    	Properties prop = new Properties();  
    	try{
    		//读取属性文件a.properties
    		InputStream in = CORSFilter.class.getClassLoader().getResourceAsStream("DomainSpan.properties");
    		//加载属性列表
    		prop.load(in);  
    		String tempProperties = prop.getProperty("Access-Control-Allow-Origin");
    		originProperties = tempProperties.split(",");
    	}   
    	catch(Exception e){
    		System.out.println(e);
    	}
    }
}
