package com.pivotal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pivotal.dao.UserDao;
import com.pivotal.model.User;



@Controller
public class UserController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private UserDao dao;

    private static String INSERT_OR_EDIT = "WEB-INF/views/user.jsp";
    private static String LIST_USER = "WEB-INF/views/listUser.jsp";

	
	
    public UserController(){
    	    	    	
    }
	
	@RequestMapping(value = "/")   
	public String home(Model model) {
		return listUser(model);
    }

	   
    
    @RequestMapping(value="/kill")
    public String kill(Model model){
		System.out.println(">>>>> APPLICATION CRASHING...");
		System.exit(-1);    	
    	return LIST_USER;

    }       

    
	@RequestMapping(value = "/listUser")
	public String listUser(Model model) {
		model.addAttribute("users", dao.getAllUsers());
        return LIST_USER;
    }
        
	@RequestMapping(value = "/editUser")
	public String editUser(Model model, @RequestParam("userId") int userId) {
	    User user = dao.getUserById(userId);
	    model.addAttribute("user", user);	
        return INSERT_OR_EDIT;
    }
	
	@RequestMapping(value = "/newUser")
	public String insertUser(Model model) {	   
		User user = new User();
		model.addAttribute("user", user);
        return INSERT_OR_EDIT;
    }

	
	
	
	@RequestMapping(value = "/deleteUser")
	public String deleteUser(Model model, @RequestParam("userId") int userId) {
	    dao.deleteUser(userId);
	    model.addAttribute("users", dao.getAllUsers());	
        return LIST_USER;
    }
	

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(Model model, @ModelAttribute("user")User user) {
	  System.out.println("Received user: "+user+ " with name "+user.getFirstName());
	  if(user.getUserid()==0){
		  dao.addUser(user);
	  }
	  else dao.updateUser(user);
	  
	  model.addAttribute("users", dao.getAllUsers());	
	  return LIST_USER;
	 }
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	    binder.registerCustomEditor(Date.class, editor);
	}	
}
