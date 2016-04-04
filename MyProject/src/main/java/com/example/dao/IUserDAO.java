package com.example.dao;

import java.sql.SQLException;

import com.example.model.User;

public interface IUserDAO {
	String DB_NAME = "weather";
	String USER_TABLE = "users";
	String FAVOURITES_TABLE = "favourites";
	boolean isUsernameAvailable(String username) throws SQLException;
	User registerUser(String userName,String password, String language, String unit, String icon) throws SQLException;
	User logIn(String username,String password)throws SQLException;
	void updateSettings(User user) throws SQLException;
	boolean addToFavourites(int userID, String location);
	boolean removeFromFavourites(int userID, String location);
}
