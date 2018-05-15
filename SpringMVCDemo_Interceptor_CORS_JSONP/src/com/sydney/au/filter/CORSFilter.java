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

//���������CORSʵ�ַ���
public class CORSFilter implements Filter {
	
    String[] originProperties; 

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
    
    //ʵ������Ӧͷ�����Access-Control-Allow-Origin���ԣ�ʵ�ֿ�����ʡ�
    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException, ServletException {
        
        //TODO Auto-generated method stub        
        HttpServletResponse response=(HttpServletResponse)sResponse;
        String curOrigin = response.getHeader("Origin");  
        System.out.println("��ǰ������Դ�ǣ�"+curOrigin);    

        //�����ǰ������Դ��application.properties��Access-Control-Allow-Origin���÷�Χ�ڣ���������ʣ���������  
        if(curOrigin != null) {  
            for (int i = 0; i < originProperties.length; i++) {  
                //System.out.println("���������ʵ���Դ�ǣ�"+originProperties[i]);  
                if(curOrigin.equals(originProperties[i])) {  
                	response.setHeader("Access-Control-Allow-Origin", curOrigin);  
                }  
            }  
        } else { 
        	//��������Դ������(�������������ַ��ֱ�����������)���Ǿ�ֻ���������Լ��Ļ������԰�  
        	response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1");  
        }  
        //Access-Control-Allow-Origin��ֵΪ*����������е��򶼿���ʵ�ֿ������
        //response.setHeader("Access-Control-Allow-Origin", "*");
        
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT,HEAD"); 
        
        //��Filter��Ĭ�Ϸ�ʽ�����������Ӧ�����ûд����ôresponse��û��body         
        chain.doFilter(sRequest, sResponse);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    	//��ȡProperties�ļ���ȡ�ɿ���ִ�е�����
    	Properties prop = new Properties();  
    	try{
    		//��ȡ�����ļ�a.properties
    		InputStream in = CORSFilter.class.getClassLoader().getResourceAsStream("DomainSpan.properties");
    		//���������б�
    		prop.load(in);  
    		String tempProperties = prop.getProperty("Access-Control-Allow-Origin");
    		originProperties = tempProperties.split(",");
    	}   
    	catch(Exception e){
    		System.out.println(e);
    	}
    }
}
