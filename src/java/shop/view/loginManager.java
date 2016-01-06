
package shop.view;

import shop.controller.ShopFacade;
import shop.model.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Miguel & Nikos
 */
@Named("loginManager")
@ConversationScoped
public class loginManager implements Serializable{
    
    //SerialID?? TO BE CHECKED
    private static final long serialVersionUID = 16247164405L;
    
    @EJB
    private ShopFacade shopfacade;
    
    private UserInterface currentUser;
    
    private String newUsername;
    private String newPassword;
    
    private String temporaryItem;
    private Integer temporaryItemAmount = 0;
    
    private String userBasket;
    private Float userTotal;
    
    private String newItemName;
    private Integer newItemQuantity;
    private Integer newItemPrice;
    
    private String bannedUserName;
    
    private Exception shopFailure;
    
    @Inject
    private Conversation conversation;
    private List<Item> allItems;
    
    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        shopFailure = e;
    }
    
    public boolean getSuccess() {
        return shopFailure == null;
    }
    
    public Exception getException() {
        return shopFailure;
    }
    
    private Float jsf22Bugfix() {
        float res = 0;
        return res;
    }
    
    public void setNewUsername(String u){
        newUsername = u;
    }
    
    public String getNewUsername(){
        return newUsername;
    }
    
    public void setBannedUserName(String u){
        bannedUserName = u;
    }
    
    public String getBannedUserName(){
        return bannedUserName;
    }
    
      
    public void setUserBasket(String u){
        userBasket = u;
    }
    
    public String getUserBasket(){
        return userBasket;
    }
    
    public void setNewPassword(String u){
        newPassword = u;
    }
    
    public String getNewPassword(){
        return newPassword;
    }
    
    public void setTemporaryItem(String u){
        temporaryItem = u;
    }
    
    public String getTemporaryItem(){
        return temporaryItem;
    }
    
    public void setTemporaryItemAmount(Integer u){
        temporaryItemAmount = u;
    }
    
    public Integer getTemporaryItemAmount(){
        return temporaryItemAmount;
    }
    
    public void setNewItemPrice(Integer u){
        newItemPrice = u;
    }
    
    public Integer getNewItemPrice(){
        return newItemPrice;
    }
    
    
    
    
    public void setCurrentUser(UserInterface u){
        currentUser = u;
    }
    
    public UserInterface getCurrentUser(){
        return currentUser;
    }
    
    public void setUserTotal(Float u){
        userTotal = u;
    }
    
    public Float getUserTotal(){
        return userTotal;
    }
    
    public void setAllItems(List<Item> u){
        allItems = u;
    }
    
    public List<Item> getAllItems(){
        return allItems;
    }
    
    
    
    public void setNewItemName(String u){
        newItemName = u;
    }
    
    public String getNewItemName(){
        return newItemName;
    }
    
    public void setNewItemQuantity(Integer u){
        newItemQuantity = u;
    }
    
    public Integer getNewItemQuantity(){
        return newItemQuantity;
    }
    
    public Exception setShopFailure(){
        return shopFailure;
    }
    
    public Exception getShopFailure(){
        return shopFailure;
    }
    
    /**
     * METHODS FOR ACTIONS OF THE USER
     */
    
    
    public String login(){
        try{
            startConversation();
            shopFailure = null;
            UserInterface u = shopfacade.getUser(newUsername);
            if(u==null){//TODO Or if an exception is thrown?
                throw new Exception("Wrong password or username");
            }

            if(!u.getUserPassword().equals(newPassword)){
                throw new Exception("Wrong password or username");
            }
            currentUser = u;
            userBasket = shopfacade.getUserBasketPrintable(newUsername);
            userTotal = shopfacade.getUserBasketTotalPrice(newUsername);
            allItems = shopfacade.getItems();
        }catch(Exception e){
            handleException(e);
            return "error";
        }
        return "login";
        
    }
    
    public String register(){
        try{
            startConversation();
            shopFailure=null;
            if(null!=shopfacade.getUser(newUsername)){
                throw new Exception("User Already Exists");
            }
            if(newUsername.equals("admin")){
                
                ShopUser u = new ShopUser(newUsername,newPassword, true, false,null);
                shopfacade.addUser(u);
            }
            else{
                ShopUser us = new ShopUser(newUsername,newPassword, false, false,null);
                shopfacade.addUser(us);
            }
            currentUser = shopfacade.getUser(newUsername);
            userBasket = shopfacade.getUserBasketPrintable(newUsername);
            userTotal = shopfacade.getUserBasketTotalPrice(newUsername);
            allItems = shopfacade.getItems();
        }catch(Exception e){
            handleException(e);
            return "error";
        }
        return "login";
        
    }
    
    public Float buy(){
        
        try{
            shopfacade.clearUserBasket(currentUser.getUserName());
            
            allItems = shopfacade.getItems();
            userBasket = shopfacade.getUserBasketPrintable(currentUser.getUserName());
            userTotal = shopfacade.getUserBasketTotalPrice(currentUser.getUserName());
        }catch(Exception e){
            handleException(e);
            return 1f;
        }
        return jsf22Bugfix();
        
    }
    
    public Float addToBasket(){
        try{
            ItemInterface it = shopfacade.getItem(temporaryItem);
            if(it.getItemQuantity()<temporaryItemAmount){
                throw new Exception("Cannot buy more units than those available");
            }
            Item item = new Item(temporaryItem,temporaryItemAmount,it.getItemPrice());
            shopfacade.addToUserBasket(currentUser.getUserName(), item);
            userBasket = shopfacade.getUserBasketPrintable(currentUser.getUserName());
            userTotal = shopfacade.getUserBasketTotalPrice(currentUser.getUserName());
            
        }catch(Exception e){
            handleException(e);
            return 1f;
        }
        return jsf22Bugfix();
    }
    
    public Float addItem(){
        try{
            Item it = new Item(newItemName, newItemQuantity, newItemPrice);
            shopfacade.addItem(it);
            allItems = shopfacade.getItems();
        }catch(Exception e){
            handleException(e);
            return 1f;
        }
        return jsf22Bugfix();
    }
    
    public String logout(){
        currentUser=null;
        //stopConversation();
        return "logout";
    }
    
    public Float banUser(){
        try{
            UserInterface u = shopfacade.getUser(bannedUserName);
            if(u==null){
                throw new Exception("No such user in the database");
            }
            u.setUserBanned(true);
        }catch(Exception e){
            handleException(e);
            return 1f;
        }
        return jsf22Bugfix();
    }
    
    public Float unbanUser(){
        try{
            UserInterface u = shopfacade.getUser(bannedUserName);
            if(u==null){
                throw new Exception("No such user in the database");
            }
            u.setUserBanned(false);
        }catch(Exception e){
            handleException(e);
            return 1f;
        }
        return jsf22Bugfix();
    }
}
