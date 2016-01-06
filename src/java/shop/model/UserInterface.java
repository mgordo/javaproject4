/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.model;

import java.util.List;

/**
 *
 * @author trabladorr
 */
public interface UserInterface {
    
    public String getUserName();
    public String getUserPassword();
    public boolean getUserAdmin();
    public boolean getUserBanned();
    public void setUserBanned(boolean banned);
    public String getUserBasket();
    public void setUserBasket(String userBasket);
    public List<Item> getUserBasketList();
    public void addToUserBasket(Item item);
}
