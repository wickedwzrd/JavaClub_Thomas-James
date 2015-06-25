/*
 * Names: Thomas Nevers/James Milne
 */
package javaclub.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javaclub.User;

public class UserDb {
    JdbcHelper helper = new JdbcHelper();
    ArrayList<Object> params = new ArrayList<>();
    protected String sql;
    boolean connected = 
            helper.connect("jdbc:mysql://localhost/dev", "root", "");
    
    
    public void addUser(User user) {
        params.clear();
        if (!connected) {
            System.err.println("Unable to connect to the database");
            return;
        }
        
        sql = "insert into User values(?, ?, ?, ?, ?)";
        
        params.add(user.getId());
        params.add(user.getPassword());
        params.add(user.getFirstName());
        params.add(user.getLastName());
        params.add(user.getEmail());
        
        int success = helper.update(sql, params);
        
        if (success > 0)
            System.out.println("<p>User added to the database.</p>");
        else System.out.println("<p>User was not added to the database.</p>");
    }
    
    public String getUser(String id) {
        params.clear();
        User user;
        if (!connected) {
            System.err.println("Unable to connect to the database");
        }
        
        try {
            sql = "select * from User where id = ?;";
            params.add(id);

            ResultSet rs = helper.query(sql, params);

            while (rs.next()) {
                user = new User(rs.getString("id"), 
                        rs.getString("password"), rs.getString("firstName"), 
                        rs.getString("lastName"), rs.getString("email"));
                
                return user.getId();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
