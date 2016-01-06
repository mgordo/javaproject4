/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.model;

/**
 *
 * @author trabladorr
 */
public interface ItemInterface {
    
    public String getItemName();
    public int getItemQuantity();
    public void setItemQuantity(int itemQuantity);
    public void setItemPrice(float itemPrice);
    public float getItemPrice();
}
