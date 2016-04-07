package com.example.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.IUserDAO;
import com.example.model.User;

@Controller
public class UserController {

	 @Autowired
	    private IUserDAO dao;
	//checkIfUsernameAvailable -> returns true if available to jquery function {GET}
	 @RequestMapping(value="checkUsername")
	 @ResponseBody boolean isAvailable(@RequestParam("username") String username){
		 System.out.println("checking username is free");
		 if(!username.isEmpty())
		 try {
			return dao.isUsernameAvailable(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	 }
	//logIn -> returns user object and saves it in the session {POST}
	@RequestMapping(value="logIn")
	String login(@RequestParam("username") String username, @RequestParam("pass") String password, HttpSession session,HttpServletResponse response ){
		User user = null;
		try {
			user = dao.logIn(username, password);
			if(user==null)
				session.setAttribute("userFail", 1);
			else{
				session.setAttribute("user", user);
				session.setAttribute((String)session.getAttribute("city")+session.getAttribute("language"), null);
				session.setAttribute("language", user.getLanguage());
				if(user.getUnit().equals("F")){
					session.setAttribute("units", "true");
				}
				else{
					session.setAttribute("units", "false");
				}
				HomePageController.ChangeUnits(session);
				session.setAttribute("userFail", 0);
			}
			//System.out.println(user.toString());
		} catch (SQLException e) {
			session.setAttribute("page", "error.jsp");
		}
		return "redirect:index";
	}
	//register -> 	if user is successfully created returns user and saves it in the session else 						{POST}
					//throws exception and sends to error page this would happen if there is a problem with db
					//because we will be sure the username is fine after the first check 
	@RequestMapping(value="register")
	String register(@RequestParam("username") String userName, @RequestParam("pass1") String password,@RequestParam("icon") String icon,@RequestParam("language") String language,@RequestParam("unit") String unit, HttpSession session ){
		
		System.out.println("```````````````````````From register``````````````````````````");
		System.out.println(userName);
		System.out.println(password);
		System.out.println(icon);
		System.out.println(unit);
		
		User user = null;
		try {
			user = dao.registerUser(userName, password, language, unit, icon);
			session.setAttribute("user", user);
			session.setAttribute((String)session.getAttribute("city")+session.getAttribute("language"), null);
			session.setAttribute("language", user.getLanguage());
			if(user.getUnit().equals("F")){
				session.setAttribute("units", "true");
			}
			else{
				session.setAttribute("units", "false");
			}
			HomePageController.ChangeUnits(session);
		} catch (SQLException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:index";
	}
	//update ->  uptates user pass and settings {POST}
	@RequestMapping(value="edit")
	String edit(@RequestParam("pass1") String password,@RequestParam("icon") String icon,@RequestParam("language") String language,@RequestParam("unit") String unit, HttpSession session ){
			
			System.out.println("```````````````````````From edit``````````````````````````");

			/*System.out.println(password);
			System.out.println(icon);
			System.out.println(unit);
			*/
			User user = (User) session.getAttribute("user");
		
			if(password!="")
				user.setPassword(password);
			if(icon!=null)
				user.setIcon(icon);
	
			user.setLanguage(language);
	
			user.setUnit(unit);
			try {
				System.out.println("from update"+user.toString());
				dao.updateSettings(user);
				session.setAttribute("user", user);
				session.setAttribute((String)session.getAttribute("city")+session.getAttribute("language"), null);
				session.setAttribute("language", user.getLanguage());
				if(user.getUnit().equals("F")){
					session.setAttribute("units", "true");
				}
				else{
					session.setAttribute("units", "false");
				}
				HomePageController.ChangeUnits(session);
			} catch (SQLException | DataAccessException | NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:index";
		}
	
	//addFavourite -> adds favourite location {PUT}
	@RequestMapping(value="addFavourite",method = RequestMethod.POST)
	@ResponseBody boolean addFavourite(@RequestParam("location") String location, HttpSession session){
		User user = (User) session.getAttribute("user");
		user.getLocations().add(location);
		dao.addToFavourites(user.getUserId(), location);
		session.setAttribute("user", user);
		return true;
	}
	//removeFavourite -> removes favourite location {DELETE}  removeFavorite
	@RequestMapping(value="removeFavorite",method = RequestMethod.POST)
	@ResponseBody boolean removeFavourite(@RequestParam("location") String location, HttpSession session){
		System.out.println("From remove1: "+location);
		String locationArr[]=location.split(", ");
		location=locationArr[1]+"/"+locationArr[0];
		System.out.println("From remove2: "+location);
		User user = (User) session.getAttribute("user");
		user.getLocations().remove(location);
		
		dao.removeFromFavourites(user.getUserId(), location);
		session.setAttribute("user", user);
		return true;
	}
	//logOut
	@RequestMapping(value="logout")
	String logOut(HttpSession session){
		System.out.println("logging out");
		session.setAttribute("user", null);
		session.setAttribute((String)session.getAttribute("city")+session.getAttribute("language"), null);
		return "redirect:index";
	}
	
}
