package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.User;


public class UserDAO implements IUserDAO {
	private JdbcTemplate jdbcTemplate;
	public UserDAO(DataSource dataSource) {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		String createDB = "CREATE DATABASE IF NOT EXISTS "+DB_NAME;
		
		String createUserTable="CREATE TABLE IF NOT EXISTS "+DB_NAME+"."+USER_TABLE+" (U_ID INT PRIMARY KEY AUTO_INCREMENT, USERNAME VARCHAR(255) NOT NULL, PASSWORD VARCHAR(255) NOT NULL, LANGUAGE VARCHAR(25) NOT NULL, UNIT VARCHAR(25) NOT NULL, ICON VARCHAR(25) NOT NULL);";
		
		String createFavouritesTable="CREATE TABLE IF NOT EXISTS "+DB_NAME+"."+FAVOURITES_TABLE+"(U_ID INT,LOCATION VARCHAR(255),CONSTRAINT pk_favs PRIMARY KEY (U_ID,LOCATION)); ";
		
		System.out.println(createDB);
		jdbcTemplate.execute(createDB);
		System.out.println(createUserTable);
		jdbcTemplate.execute(createUserTable);
		System.out.println(createFavouritesTable);
		jdbcTemplate.execute(createFavouritesTable);
	}

	@Override
	public boolean isUsernameAvailable(String username) throws SQLException {
		String sql = "SELECT "+username+" FROM "+DB_NAME+"."+USER_TABLE+";";
		return (boolean) jdbcTemplate.query(sql, new ResultSetExtractor<Boolean>() {

			@Override
			public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
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
			throws SQLException {
		
		 String sql = "INSERT INTO "+DB_NAME+"."+USER_TABLE+" (username, password, language, unit,icon)"
                 + " VALUES (?, ?, ?, ?,?)";
		jdbcTemplate.update(sql,userName,password,language,unit,icon);
		return extractUser(userName);
	}

	@Override
	public User logIn(String userName, String password) throws SQLException {
		
		return extractUser(userName);
	}

	@Override
	public void updateSettings(User user) throws SQLException {
		 String sql = "UPDATE "+DB_NAME+"."+USER_TABLE+" SET  password=?, language=?,unit=?,icon=? "
                 + "WHERE U_ID=?";
     jdbcTemplate.update(sql, user.getPassword(),user.getLanguage(),user.getUnit(),user.getIcon(),user.getUserId());
		
	}

	@Override
	public boolean addToFavourites(int userID, String location) {
		String sql = "INSERT INTO "+DB_NAME+"."+FAVOURITES_TABLE+" (U_ID, LOCATION)VALUES (?, ?);";
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
		 String sql = "SELECT  U_ID, username, password, language, unit,icon FROM "+DB_NAME+"."+USER_TABLE+" WHERE username='"+userName+"';";
		 User user = jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
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
		 if(user!=null){
			String sqlFav ="SELECT LOCATION FROM  "+DB_NAME+"."+FAVOURITES_TABLE+" WHERE U_ID="+user.getUserId();
			List<String> favourites = jdbcTemplate.query(sqlFav, new RowMapper<String>() {

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					String location = rs.getString(DB_NAME+".LOCATION");
					return location;
				}
			});
			 user.setLocations(favourites);
		 }
		
		return user;
		
	}
}
