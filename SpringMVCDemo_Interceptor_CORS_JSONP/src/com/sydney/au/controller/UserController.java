package com.sydney.au.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sydney.au.model.User;
 
@Controller  
public class UserController {

	//��ȡproperties�ļ���Ϣ
    @Value("${Access-Control-Allow-Origin}")  
    String[] originProperties; 
    
	@RequestMapping(value = "/clientLogin", method = RequestMethod.POST)  
	public String clientLogin(HttpSession session, String username, String password, Model model){  
	      	
	    if(username.equals("eric")&&password.equals("123456")){  
	        //��½�ɹ�  
	    	session.setAttribute("username", "eric");
	    	
	    	User user = new User();
	    	user.setPassword(password);
	    	user.setUsername(username);
	    	model.addAttribute(user); 
	        return "welcome";  
	    }else{  
	        //��½ʧ��  
	        return "login";  
	    }  
	}  
   
	@RequestMapping("/clientLoginOut")  
	public String clientLoginOut(HttpSession httpSession){  
	
		httpSession.invalidate();  
		return "login";  
	}  
	
    @RequestMapping("/showUser")
    @ResponseBody
    public Map<String,String> showUser(){
        
    	HashMap<String,String> map=new HashMap<String,String>();
        map.put("name", "emily");
        map.put("sex", "female");
        map.put("age", "35");
        return map;
    }
    
    //JSONP��ʽʵ�ֿ�������ĺ�̨ʵ�ַ���
    @RequestMapping(value = "getJSONP")
    @ResponseBody
    public String getJSONP(HttpServletRequest request, String callback) throws Exception {
        
    	//����JSONP��ʽ����
    	String type = request.getParameter("type");
    	String mobilePhone = request.getParameter("mobilePhone");
    	String result =  "{'ret':'true'" + ",'type':"+type+",'mobilePhone':"+mobilePhone+"}";
        //���Ϸ��ز���
        result=callback+"("+result+")";
        return result;
    }
}
