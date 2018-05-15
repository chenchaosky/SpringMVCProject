package com.sydney.au.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//�Զ���������
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**  
     * ��ҵ��������������֮ǰ������  
     * �������false  
     *     �ӵ�ǰ������������ִ��������������afterCompletion(),���˳��������� 
     * �������true  
     *    ִ����һ��������,ֱ�����е���������ִ�����  
     *    ��ִ�б����ص�Controller  
     *    Ȼ�������������,  
     *    �����һ������������ִ�����е�postHandle()  
     *    �����ٴ����һ������������ִ�����е�afterCompletion()  
     */    
	@Override    
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  

		System.out.println("==============ִ��˳��: 1��preHandle================");    
		String requestURI = request.getRequestURI();  
		String contextPath = request.getContextPath();  
		String filterPath = contextPath + "/clientLogin";
		
        if(!requestURI.equals(filterPath)){  
        	
        	HttpSession session = request.getSession();  
        	String username = (String) session.getAttribute("username");  
        	if(username!=null){  
        		//��½�ɹ����û�  
        		return true;  
        	}else{  
        		//û�е�½��ת���½����
        		System.out.println("Interceptor����ת��loginҳ�棡");  
        		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);  
        		return false;  
        	}  
        }else{  
        	return true;  
        }  
	}    

	/** 
	 * ��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ�еĶ���    
	 * ����modelAndView�м������ݣ����統ǰʱ�� 
	 */  
	@Override    
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {     
		
		System.out.println("==============ִ��˳��: 2��postHandle================");    
	    if(modelAndView != null){ 
	    	//���뵱ǰʱ��    
	        modelAndView.addObject("var", "����postHandle");    
	    }    
	}    
	
	/**  
	 * ��DispatcherServlet��ȫ����������󱻵���,������������Դ��   
	 *   
	 * �����������׳��쳣ʱ,��ӵ�ǰ����������ִ�����е���������afterCompletion()  
	 */    
	@Override    
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {  
		
		System.out.println("==============ִ��˳��: 3��afterCompletion================");    
	}    

}
