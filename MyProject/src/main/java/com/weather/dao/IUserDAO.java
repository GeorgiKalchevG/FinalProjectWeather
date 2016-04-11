package com.weather.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import com.weather.model.User;

public interface IUserDAO {
	
	
	
	

	String DB_NAME = "weather";
	String USER_TABLE = "users";
	String FAVOURITES_TABLE = "favourites";
	boolean isUsernameAvailable(String username) throws SQLException;
	User registerUser(String userName,String password, String language, String unit, String icon) throws SQLException, NoSuchAlgorithmException;
	User logIn(String username,String password)throws SQLException;
	void updateSettings(User user) throws SQLException, DataAccessException, NoSuchAlgorithmException;
	boolean addToFavourites(int userID, String location);
	boolean removeFromFavourites(int userID, String location);
	boolean checkPassword(String Username,String password) throws NoSuchAlgorithmException;
}
