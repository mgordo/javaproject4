
package shop.view;

import shop.controller.ShopFacade;
import shop.model.*;
import java.io.Serializable;
import java.util.List;
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
    
    private Float userTotal;
    
    private String modifiedItemName;
    private Integer modifiedItemQuantity;
    private Integer modifiedItemPrice;
    
    private String newItemName;
    private Integer newItemQuantity;
    private Integer newItemPrice;
    
    private String bannedUserName;
    
    private Exception shopFailure;
    
    @Inject
    private Conversation conversation;
    private List<Item> allItems;
    private List<Item> basketItems;
    private List<ShopUser> allUsers;
    
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
        //stopConversation();
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
        shopFailure = null;
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
    
    public void setBasketItems(List<Item> u){
        basketItems = u;
    }
    
    public List<Item> getBasketItems(){
        return basketItems;
    }
    
    public void setAllUsers(List<ShopUser> u){
        allUsers = u;
    }
    
    public List<ShopUser> getAllUsers(){
        return allUsers;
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
    
    public void setModifiedItemName(String u){
        modifiedItemName = u;
    }
    
    public String getModifiedItemName(){
        return modifiedItemName;
    }
    
    public void setModifiedItemPrice(Integer u){
        modifiedItemPrice = u;
    }
    
    public Integer getModifiedItemPrice(){
        return modifiedItemPrice;
    }
    
    public void setModifiedItemQuantity(Integer u){
        modifiedItemQuantity = u;
    }
    
    public Integer getModifiedItemQuantity(){
        return modifiedItemQuantity;
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
            if(u.getUserBanned()){
                throw new Exception("You have been banned from the system");
            }
            currentUser = u;
            basketItems = shopfacade.getUserBasket(newUsername);
            userTotal = shopfacade.getUserBasketTotalPrice(newUsername);
            allItems = shopfacade.getItems();
            allUsers = shopfacade.getUsers();
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
            basketItems = shopfacade.getUserBasket(newUsername);
            userTotal = shopfacade.getUserBasketTotalPrice(newUsername);
            allItems = shopfacade.getItems();
            allUsers = shopfacade.getUsers();
        }catch(Exception e){
            handleException(e);
            return "error";
        }
        return "login";
        
    }
    
    public Float clearBasket(){
        
        try{
            shopfacade.clearUserBasket(currentUser.getUserName());
            
            allItems = shopfacade.getItems();
            allUsers = shopfacade.getUsers();
            basketItems = shopfacade.getUserBasket(currentUser.getUserName());
            userTotal = shopfacade.getUserBasketTotalPrice(currentUser.getUserName());
        }catch(Exception e){
            handleException(e);
            return 1f;
        }
        return jsf22Bugfix();
    }
    
    public Float buy(){
        
        try{
            shopfacade.reduceItemQuantitiesByUserBasket(currentUser.getUserName());
            shopfacade.clearUserBasket(currentUser.getUserName());
            
            allItems = shopfacade.getItems();
            allUsers = shopfacade.getUsers();
            basketItems = shopfacade.getUserBasket(currentUser.getUserName());
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
            basketItems = shopfacade.getUserBasket(currentUser.getUserName());
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
            basketItems = shopfacade.getUserBasket(currentUser.getUserName());
            userTotal = shopfacade.getUserBasketTotalPrice(currentUser.getUserName());
            allItems = shopfacade.getItems();
            allUsers = shopfacade.getUsers();
        }catch(Exception e){
            handleException(e);
            return 1f;
        }
        return jsf22Bugfix();
    }
    
    public Float removeItem(){
        try{
            shopfacade.deleteItem(newItemName);
            basketItems = shopfacade.getUserBasket(currentUser.getUserName());
            userTotal = shopfacade.getUserBasketTotalPrice(currentUser.getUserName());
            allItems = shopfacade.getItems();
            allUsers = shopfacade.getUsers();
        }catch(Exception e){
            handleException(e);
            return 1f;
        }
        return jsf22Bugfix();
    }
    
    public Float setItemQuantity(){
        try{
            shopfacade.setItemQuantity(modifiedItemName, modifiedItemQuantity);
            allItems = shopfacade.getItems();
            allUsers = shopfacade.getUsers();
        }catch(Exception e){
            handleException(e);
            return 1f;
        }
        return jsf22Bugfix();
    }
    
    public Float setItemPrice(){
        try{
            shopfacade.setItemPrice(modifiedItemName, modifiedItemPrice);
            allItems = shopfacade.getItems();
            allUsers = shopfacade.getUsers();
        }catch(Exception e){
            handleException(e);
            return 1f;
        }
        return jsf22Bugfix();
    }
    
    public String logout(){
        currentUser=null;
        stopConversation();
        return "logout";
    }
    
    public Float banUser(){
        try{
            UserInterface u = shopfacade.getUser(bannedUserName);
            if(u==null){
                throw new Exception("No such user in the database");
            }
            shopfacade.setUserBanned(bannedUserName, true);
            allUsers = shopfacade.getUsers();
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
            shopfacade.setUserBanned(bannedUserName, false);
            allUsers = shopfacade.getUsers();
        }catch(Exception e){
            handleException(e);
            return 1f;
        }
        return jsf22Bugfix();
    }
}
