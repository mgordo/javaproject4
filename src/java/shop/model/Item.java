/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Miguel
 */
@Entity
public class Item implements ItemInterface, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String itemName;
    
    private int itemQuantity;
    private float itemPrice;

    public Item() {
    }

    public Item(String itemName, int itemQuantity, float itemPrice){
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
    }
    
    
    @Override
    public String getItemName() {
        return itemName;
    }

    public void setId(String itemName) {
        this.itemName = itemName;
    }
    
    @Override
    public int getItemQuantity(){
        return itemQuantity;
    }

    @Override
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    
    @Override
    public float getItemPrice(){
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        return itemName.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemName.equals(other.getItemName()))) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "shop.model.Item("+itemName+":"+itemQuantity+":"+itemPrice+")";
    }
    
}
