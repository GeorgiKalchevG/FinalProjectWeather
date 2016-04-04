package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.dao.IUserDAO;

@Controller
public class UserController {

	 @Autowired
	    private IUserDAO dao;
	//checkIfUsernameAvailable -> returns true if available to jquery function {GET}
	
	//logIn -> returns user object and saves it in the session {POST}
	
	//register -> 	if user is successfully created returns user and saves it in the session else 						{POST}
					//throws exception and sends to error page this would happen if there is a problem with db
					//because we will be sure the username is fine after the first check 
	
	//update ->  uptates user pass and settings {POST}
	
	
	//addFavourite -> adds favourite location {PUT}
	//removeFavourite -> removes favourite location {DELETE}
	
}
