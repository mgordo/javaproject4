
package shop.view;

import shop.controller.ShopFacade;
import shop.model.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    @EJB
    
    private ShopFacade shopfacade;
    
    private UserInterface currentUser;
    
    private String newUsername;
    private String newPassword;
    
    private String temporaryItem;
    private Integer temporaryItemAmount;
    
    private String newItemName;
    private Integer newItemQuantity;
    
    private Exception shopFailure;
    
    @Inject
    private Conversation conversation;
    
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
    
    public void setTemporaryItemAmount(int u){
        temporaryItemAmount = u;
    }
    
    public int getTemporaryItemAmount(){
        return temporaryItemAmount;
    }
    
    public void setCurrentUser(UserInterface u){
        currentUser = u;
    }
    
    public UserInterface getCurrentUser(){
        return currentUser;
    }
    
    public void setNewItemName(String u){
        newItemName = u;
    }
    
    public String getNewItemName(){
        return newItemName;
    }
    
    public void setNewItemQuantity(int u){
        newItemQuantity = u;
    }
    
    public int getNewItemQuantity(){
        return newItemQuantity;
    }
    
    
    /**
     * METHODS FOR ACTIONS OF THE USER
     */
    
    
    public Float login(){
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
        }catch(Exception e){
            handleException(e);
        }
        return jsf22Bugfix();
        
    }
    
    public Float register(){
        try{
            startConversation();
            shopFailure=null;
            if(null!=shopfacade.getUser(newUsername)){
                throw new Exception("User Already Exists");
            }
            if(newUsername.equals("admin")){
                ShopUser u = new ShopUser(newUsername,newPassword, true, false);
                shopfacade.addUser(u);
            }
            else{
                ShopUser u = new ShopUser(newUsername,newPassword, false, false);
                shopfacade.addUser(u);
            }
            currentUser = shopfacade.getUser(newUsername);
        }catch(Exception e){
            handleException(e);
        }
        return jsf22Bugfix();
        
    }
    
    public Float buy(){
        
    }
    
    public Float addToBasket(){
        try{
            currentUser.addToBasket(temporaryItem,temporaryItemAmount);
            
        }catch(Exception e){
            handleException(e);
        }
        return jsf22Bugfix();
    }
    
    public Float addItem(){
        try{
            shopfacade.addItem(newItemName);
            
        }catch(Exception e){
            handleException(e);
        }
        return jsf22Bugfix();
    }
    
    public Float logout(){
        
    }
}
