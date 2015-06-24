/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclub.db;

import java.util.ArrayList;
import javaclub.User;

/**
 *
 * @author thomas
 */
public class UserDb {
    JdbcHelper helper = new JdbcHelper();
    ArrayList<Object> params = new ArrayList<>();
    protected String sql;
    
    public void addUser(User user) {
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
    
    public User getUser() {
        User user = null;
        sql = "select * from user where id = " + user.getId();
        
        return user;
    }
}
