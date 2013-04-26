package org.foodspring.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/22/13
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Meal implements Serializable {

    private int id;
    private String items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

}
