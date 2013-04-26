package org.foodspring.domain;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 4/2/13
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return " " + this.getUsername() + " ";
    }
}
