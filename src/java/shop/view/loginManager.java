
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
 * A Managed Bean, controlling the index.xhtml and inventory.xhtml web pages.
 * All the web funtionality of the project is defined here, as well as a 
 * ultitude of get/set methods as per bean definition.
 * @author Miguel & Nikos
 */
@Named("loginManager")
@ConversationScoped
public class loginManager implements Serializable{
    
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
    
    /**
     * Modify a transient conversation to a long-lived one, as per the 
     * Conversation-Scoped model.
     */
    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }
    
    /**
     * End a long-lived conversation, as per the Conversation-Scoped model.
     */
    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    /**
     * Exceptions raised in any part of the web pages are presented to the
     * customer and optionally logged.
     * @param Exception the exception to present and log.
     */
    private void handleException(Exception e) {
        //stopConversation();
        e.printStackTrace(System.err);
        shopFailure = e;
    }
    
    /**
     * Get the success state of the previous transaction with the customer.
     * @return true if no failure has occurred.
     */
    public boolean getSuccess() {
        return shopFailure == null;
    }
    
    /**
     * Get the last exception produced during the conversation.
     * @return the last exception produced.
     */
    public Exception getException() {
        return shopFailure;
    }
    
    /**
    * Experimental bug fix for issues encountered during development.
    * @return a "Success" float value.
    */   
    private Float jsf22Bugfix() {
        shopFailure = null;
        float res = 0;
        return res;
    }
    
    /**
     * Setter method for the newUsername variable, as per bean definition.
     * @param u newUsername.
     */
    public void setNewUsername(String u){
        newUsername = u;
    }
    
    /**
     * Getter method for the newUsername variable, as per bean definition.
     * @return newUsername.
     */
    public String getNewUsername(){
        return newUsername;
    }
    
    /**
     * Setter method for the bannedUserName variable, as per bean definition.
     * @param u bannedUserName.
     */
    public void setBannedUserName(String u){
        bannedUserName = u;
    }
    
    /**
     * Getter method for the bannedUserName variable, as per bean definition.
     * @return bannedUserName.
     */
    public String getBannedUserName(){
        return bannedUserName;
    }
    
    /**
     * Setter method for the newPassword variable, as per bean definition.
     * @param u newPassword.
     */
    public void setNewPassword(String u){
        newPassword = u;
    }
    
    /**
     * Getter method for the newPassword variable, as per bean definition.
     * @return newPassword.
     */
    public String getNewPassword(){
        return newPassword;
    }
    
    /**
     * Setter method for the temporaryItem variable, as per bean definition.
     * @param u temporaryItem.
     */
    public void setTemporaryItem(String u){
        temporaryItem = u;
    }
    
    /**
     * Getter method for the temporaryItem variable, as per bean definition.
     * @return temporaryItem.
     */
    public String getTemporaryItem(){
        return temporaryItem;
    }
    
    /**
     * Setter method for the temporaryItemAmount variable, as per bean 
     * definition.
     * @param u temporaryItemAmount.
     */
    public void setTemporaryItemAmount(Integer u){
        temporaryItemAmount = u;
    }
    
    /**
     * Getter method for the temporaryItemAmount variable, as per bean 
     * definition.
     * @return temporaryItemAmount.
     */
    public Integer getTemporaryItemAmount(){
        return temporaryItemAmount;
    }
    
    /**
     * Setter method for the newItemPrice variable, as per bean definition.
     * @param u newItemPrice.
     */
    public void setNewItemPrice(Integer u){
        newItemPrice = u;
    }
    
    /**
     * Getter method for the newItemPrice variable, as per bean definition.
     * @return newItemPrice.
     */
    public Integer getNewItemPrice(){
        return newItemPrice;
    }
    
    /**
     * Setter method for the currentUser variable, as per bean definition.
     * @param u currentUser.
     */
    public void setCurrentUser(UserInterface u){
        currentUser = u;
    }
    
    /**
     * Getter method for the currentUser variable, as per bean definition.
     * @return currentUser.
     */
    public UserInterface getCurrentUser(){
        return currentUser;
    }
    
    /**
     * Setter method for the userTotal variable, as per bean definition.
     * @param u userTotal.
     */
    public void setUserTotal(Float u){
        userTotal = u;
    }
    
    /**
     * Getter method for the userTotal variable, as per bean definition.
     * @return userTotal.
     */
    public Float getUserTotal(){
        return userTotal;
    }
    
    /**
     * Setter method for the allItems variable, as per bean definition.
     * @param u allItems.
     */
    public void setAllItems(List<Item> u){
        allItems = u;
    }
    
    /**
     * Getter method for the allItems variable, as per bean definition.
     * @return allItems.
     */
    public List<Item> getAllItems(){
        return allItems;
    }
    
    /**
     * Setter method for the basketItems variable, as per bean definition.
     * @param u basketItems.
     */
    public void setBasketItems(List<Item> u){
        basketItems = u;
    }
    
    /**
     * Getter method for the basketItems variable, as per bean definition.
     * @return basketItems.
     */
    public List<Item> getBasketItems(){
        return basketItems;
    }
    
    /**
     * Setter method for the allUsers variable, as per bean definition.
     * @param u allUsers.
     */
    public void setAllUsers(List<ShopUser> u){
        allUsers = u;
    }
    
    /**
     * Getter method for the allUsers variable, as per bean definition.
     * @return allUsers.
     */
    public List<ShopUser> getAllUsers(){
        return allUsers;
    }
    
    /**
     * Setter method for the newItemName variable, as per bean definition.
     * @param u newItemName.
     */
    public void setNewItemName(String u){
        newItemName = u;
    }
    
    /**
     * Getter method for the newItemName variable, as per bean definition.
     * @return newItemName.
     */
    public String getNewItemName(){
        return newItemName;
    }
    
    /**
     * Setter method for the newItemQuantity variable, as per bean definition.
     * @param u newItemQuantity.
     */
    public void setNewItemQuantity(Integer u){
        newItemQuantity = u;
    }
    
    /**
     * Getter method for the newItemQuantity variable, as per bean definition.
     * @return newItemQuantity.
     */
    public Integer getNewItemQuantity(){
        return newItemQuantity;
    }
    
    /**
     * Setter method for the modifiedItemName variable, as per bean definition.
     * @param u modifiedItemName.
     */
    public void setModifiedItemName(String u){
        modifiedItemName = u;
    }
    
    /**
     * Getter method for the modifiedItemName variable, as per bean definition.
     * @return modifiedItemName.
     */
    public String getModifiedItemName(){
        return modifiedItemName;
    }
    
    /**
     * Setter method for the modifiedItemPrice variable, as per bean definition.
     * @param u modifiedItemPrice.
     */
    public void setModifiedItemPrice(Integer u){
        modifiedItemPrice = u;
    }
    
    /**
     * Getter method for the modifiedItemPrice variable, as per bean definition.
     * @return modifiedItemPrice.
     */
    public Integer getModifiedItemPrice(){
        return modifiedItemPrice;
    }
    
    /**
     * Setter method for the modifiedItemQuantity variable, as per bean 
     * definition.
     * @param u modifiedItemQuantity.
     */
    public void setModifiedItemQuantity(Integer u){
        modifiedItemQuantity = u;
    }
    
    /**
     * Getter method for the modifiedItemQuantity variable, as per bean 
     * definition.
     * @return modifiedItemQuantity.
     */
    public Integer getModifiedItemQuantity(){
        return modifiedItemQuantity;
    }
    
    /**
     * Setter method for the shopFailure variable, as per bean definition.
     * @return shopFailure.
     */
    public Exception setShopFailure(){
        return shopFailure;
    }
    
    /**
     * Getter method for the shopFailure variable, as per bean definition.
     * @return shopFailure.
     */
    public Exception getShopFailure(){
        return shopFailure;
    }
    
    /**
     * METHODS CORRESPONDING TO ACTIONS FROM THE USER
     */
    
    /**
     * Perform a log-in operation for a user. The credentials are 
     * checked against the database, and the cases of wrong credentials, a user
     * having administrative privileges or being banned are handled.
     * @return "login" in case of success, "error" otherwise.
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
    
    /**
     * Perform a registration operation for a user. The credentials are 
     * checked against the database, and the case of an existing username is 
     * handled.
     * @return "login" in case of success, "error" otherwise.
     */
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
    
    /**
     * Clear the contents of the basket of a User without a corresponding sale.
     * @return 0f in case of success, 1f otherwise.
     */
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
    
    /**
     * Perform a sale of the contents of a user Basket. The contained Items are
     * checked to exist in the database, and subsequently subtracted from both 
     * the basket and the inventory.
     * @return 0f in case of success, 1f otherwise.
     */
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
    
    /**
     * Add a specified Item and corresponding quantity to the basket of a User.
     * @return 0f in case of success, 1f otherwise.
     */
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
    
    /**
     * Add a new Item for sale in the shop. This operation is only accessible by
     * users with administrative privileges.
     * @return 0f in case of success, 1f otherwise.
     */
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
    
    /**
     * Remove an existing Item from the shop. This operation is only accessible 
     * by users with administrative privileges.
     * @return 0f in case of success, 1f otherwise.
     */
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
    
    /**
     * Modify the available quantity of an Item in the inventory. This operation
     * is only accessible by users with administrative privileges.
     * @return 0f in case of success, 1f otherwise.
     */
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
    
    /**
     * Modify the sale price of an Item in the inventory. This operation is only
     * accessible by users with administrative privileges.
     * @return 0f in case of success, 1f otherwise.
     */
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
    
    /**
     * Log out from the shop, and end the conversation.
     * @return 0f in case of success, 1f otherwise.
     */
    public String logout(){
        currentUser=null;
        stopConversation();
        return "logout";
    }
    
    /**
     * Ban a user from logging in to the shop. This operation is only accessible
     * by users with administrative privileges.
     * @return 0f in case of success, 1f otherwise.
     */
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
    
    /**
     * Unban a user, allowing them to log in to the shop. This operation is only
     * accessible by users with administrative privileges.
     * @return 0f in case of success, 1f otherwise.
     */
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
