package com.weather.dao;

import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.ResultSetExtractor;

import org.springframework.jdbc.core.RowMapper;

import com.weather.model.User;


public class UserDAO implements IUserDAO {
	private JdbcTemplate jdbcTemplate;
	public UserDAO(DataSource dataSource) {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		String createDB = "CREATE DATABASE IF NOT EXISTS "+DB_NAME;
		
		String createUserTable="CREATE TABLE IF NOT EXISTS "+DB_NAME+"."+USER_TABLE+" (U_ID INT PRIMARY KEY AUTO_INCREMENT, USERNAME VARCHAR(255) UNIQUE NOT NULL, PASSWORD VARCHAR(255) NOT NULL, LANGUAGE VARCHAR(25) NOT NULL, UNIT VARCHAR(25) NOT NULL, ICON VARCHAR(25) NOT NULL);";
		
		String createFavouritesTable="CREATE TABLE IF NOT EXISTS "+DB_NAME+"."+FAVOURITES_TABLE+"(U_ID INT,LOCATION VARCHAR(255),CONSTRAINT pk_favs PRIMARY KEY (U_ID,LOCATION), CONSTRAINT FOREIGN KEY fk_favourites (U_ID) REFERENCES  "+DB_NAME+"."+USER_TABLE+" (U_ID)); ";
		
		System.out.println(createDB);
		jdbcTemplate.execute(createDB);
		System.out.println(createUserTable);
		jdbcTemplate.execute(createUserTable);
		System.out.println(createFavouritesTable);
		jdbcTemplate.execute(createFavouritesTable);
		
	}

	@Override
	public boolean isUsernameAvailable(String username) throws SQLException {
		String sql = "SELECT USERNAME FROM "+DB_NAME+"."+USER_TABLE+";";
		return (boolean) jdbcTemplate.query(sql, new ResultSetExtractor<Boolean>() {
			@Override
			public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()){
					if(rs.getString("USERNAME").equals(username)){
						return false;
					}
				}
				return true;
			}
		});
	}

	@Override
	public User registerUser(String userName, String password, String language, String unit, String icon)
			throws SQLException, NoSuchAlgorithmException {
		String pass = encript(password);
		 String sql = "INSERT INTO "+DB_NAME+"."+USER_TABLE+" (username, password, language, unit,icon)"
                 + " VALUES (?, ?, ?, ?,?)";
		jdbcTemplate.update(sql,userName,pass,language,unit,icon);
		return extractUser(userName);
	}

	private String encript(String password) throws NoSuchAlgorithmException {
		 String sha1 = "";
		    try
		    {
		        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		        crypt.reset();
		        crypt.update(password.getBytes("UTF-8"));
		        sha1 = byteToHex(crypt.digest());
		    }
		    catch(NoSuchAlgorithmException e)
		    {
		        e.printStackTrace();
		    }
		    catch(UnsupportedEncodingException e)
		    {
		        e.printStackTrace();
		    }
		    return sha1;
		}

		private String byteToHex(final byte[] hash)
		{
		    Formatter formatter = new Formatter();
		    for (byte b : hash)
		    {
		        formatter.format("%02x", b);
		    }
		    String result = formatter.toString();
		    formatter.close();
		    return result;
		}

	@Override
	public User logIn(String userName, String password) throws SQLException {
		System.out.println("From log in dao                                                    \n\n\n" +userName+", "+password );
		User user = extractUser(userName);
		
//		System.out.println(user.toString());
		try {
			System.out.println("-------------------------encrypted password "+encript("Qwer123"));
		} catch (NoSuchAlgorithmException e1) {
			return null;
	
		}
		if(user!=null)
			try {
				
				if(user.getPassword().equals(encript(password))){
					System.out.println(user.getPassword()+"            "+encript(password));
					return extractUser(userName);
				}
			} catch (NoSuchAlgorithmException e) {
				return null;
			
			}
		return null;
	}

	@Override
	public void updateSettings(User user) throws SQLException, DataAccessException, NoSuchAlgorithmException {
		String pass=null;
		if(user.getPassword().length()<30){
			pass = encript(user.getPassword());
		}
		else{
			pass = user.getPassword();
		}
		 String sql = "UPDATE "+DB_NAME+"."+USER_TABLE+" SET  password=?, language=?,unit=?,icon=? "
                 + "WHERE U_ID=?";
     jdbcTemplate.update(sql,pass ,user.getLanguage(),user.getUnit(),user.getIcon(),user.getUserId());
		System.out.println("from updateDAO" +user.toString());
	}

	@Override
	public boolean addToFavourites(int userID, String location) {
		String sql = "INSERT INTO "+DB_NAME+"."+FAVOURITES_TABLE+" (U_ID, LOCATION)VALUES (?, ?);";
		
		System.out.println("from add to favourites");
		System.out.println(userID);
		System.out.println(location);
		jdbcTemplate.update(sql, userID,location);
		return false;
	}

	@Override
	public boolean removeFromFavourites(int userID, String location) {
		String sql = "DELETE FROM  "+DB_NAME+"."+FAVOURITES_TABLE+" WHERE U_ID=? AND LOCATION= ?;";
		jdbcTemplate.update(sql, userID,location);
		return false;
	}
	private User extractUser(String userName){
		 String sql = "SELECT  U_ID, username, password, language, unit,icon FROM "+DB_NAME+"."+USER_TABLE+" WHERE username=?;";
		 User user = jdbcTemplate.query(sql,new Object[] {userName}, new ResultSetExtractor<User>() {
			 @Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				User user = new User();
				if(rs.next()){
					user.setUserId(rs.getInt(1));
					user.setUserName(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setLanguage(rs.getString(4));
					user.setUnit(rs.getString(5));
					user.setIcon(rs.getString(6));
					return user;
				}
				
				return null;
			}
		});
		 System.out.println("from extractor");
//		 System.out.println( user.toString());
		 
		 if(user!=null){
			String sqlFav ="SELECT LOCATION FROM  "+DB_NAME+"."+FAVOURITES_TABLE+" WHERE U_ID=?;";
			List<String> favourites = jdbcTemplate.query(sqlFav,new Object[] {user.getUserId()}, new RowMapper<String>() {

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					String location = rs.getString("LOCATION");
					return location;
				}
			});
			 user.setLocations(favourites);
		 }
		
		return user;
		
	}

	@Override
	public boolean checkPassword(String userName, String password) throws NoSuchAlgorithmException {
		User user = extractUser(userName);
		String encripted = encript(password);
		System.out.println(user.toString());
		System.out.println(encripted);
		if(encripted.equals(user.getPassword()))
			return true;
		return false;
	}
}
