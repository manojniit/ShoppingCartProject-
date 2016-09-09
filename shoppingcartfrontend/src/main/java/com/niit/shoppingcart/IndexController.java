package com.niit.shoppingcart;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.UserDetailsDAO;
import com.niit.shoppingcart.model.Category;
import com.niit.shoppingcart.model.UserDetails;

@Controller("/index")
public class IndexController {
	@Autowired
	UserDetails userDetails;
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private UserDetailsDAO userDetailsDAO;
	@Autowired
	private Category category;
	@RequestMapping("/")
	@Autowired
	public ModelAndView onLoad(HttpSession session)
	{
		ModelAndView mv=new ModelAndView("/home");
		session.setAttribute("category",category);
			session.setAttribute("categoryList", categoryDAO.list());
			System.out.println("size:"+categoryDAO.list());
			return mv;
	}
	@RequestMapping(value="user/register",method=RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute UserDetails userDetails)
	{

		ModelAndView mv=new ModelAndView("/home");
		if (userDetailsDAO.get(userDetails.getId())==null) {
			userDetailsDAO.save(userDetails);
			mv.addObject("msg","you are successfully registered");
		} else {
			mv.addObject("msg","no user exist with this id");
		}
	
		return mv;
	}
	@RequestMapping("/signup")
	public ModelAndView signup()
	{

		ModelAndView mv=new ModelAndView("/home");
		mv.addObject("userDetails",userDetails);
		mv.addObject("user clicked signup here","true");
		return mv;
	}
	@RequestMapping("/login")
	public ModelAndView login()
	{

		ModelAndView mv=new ModelAndView("/home");
		mv.addObject("userDetails",userDetails);
		mv.addObject("user clicked login here","true");
		return mv;
}
}
