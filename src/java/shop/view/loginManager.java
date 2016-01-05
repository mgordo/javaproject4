
package shop.view;

import shop.controller.ShopFacade;

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
    
    private Username currentUsername;
    
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
    
    public void setCurrentUsername(Username u){
        currentUsername = u;
    }
    
    public Username getCurrentUsername(){
        return currentUsername;
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
    
    
    public void login(){
        startConversation();
        shopFailure = null;
    }
    
    public void register(){
        startConversation();
        shopFailure=null;
    }
    
    public void buy(){
        
    }
    
    public void addToBasket(){
        
    }
    
    public void addItem(){
        
    }
}
