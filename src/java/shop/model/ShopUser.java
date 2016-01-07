package shop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A Session Bean defining a User in storage, contains the login credentials, 
 * the administrative and banned states, and the current basket of the User.
 * @author Miguel & Nikos
 */
@Entity
public class ShopUser implements UserInterface, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userName;
    
    private String userPassword;
    private boolean userAdmin;
    private boolean userBanned;
    private String userBasket;
    
    /**
     * Zero-argument default constructor, as per bean definition.
     */
    public ShopUser(){
        
    }
    
    /**
     * Simple Constructor for a ShopUser.
     * @param userName the username of the ShopUser.
     * @param userPassword the password of the ShopUser.
     * @param userAdmin the administrative state of the ShopUser.
     * @param userBanned the banned state of the ShopUser.
     * @param userBasket the basket of the ShopUser in String format.
     */
    public ShopUser(String userName, String userPassword, boolean userAdmin, boolean userBanned, String userBasket){
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAdmin = userAdmin;
        this.userBanned = userBanned;
        this.userBasket = userBasket;
    }

    
    @Override
    public String getUserName() {
        return userName;
    }
    
    /**
     * Set the name of a ShopUser.
     * @param userName the new name of the ShopUser.
     */
    public void setUserName(String userName) {
         this.userName = userName;
    }
    
    @Override
    public String getUserPassword(){
        return userPassword;
    }
    
    /**
     * Set the password of a ShopUser.
     * @param userPassword the new password of the ShopUser.
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    @Override
    public boolean getUserAdmin() {
        return userAdmin;
    }
    
    /**
     * Set the administrative state of a ShopUser.
     * @param userAdmin the new administrative state of the ShopUser.
     */
    public void setUserAdmin(boolean userAdmin) {
         this.userAdmin = userAdmin;
    }
    
    @Override
    public boolean getUserBanned(){
        return userBanned;
    }
    
    @Override
    public void setUserBanned(boolean userBanned) {
         this.userBanned = userBanned;
    }
    
    @Override
    public String getUserBasket(){
        return userBasket;
    }
    
    @Override
    public List<Item> getUserBasketList(){
        return parseBasketString(userBasket);
    }
    
    @Override
    public void setUserBasket(String userBasket){
        this.userBasket = userBasket;
    }
        
    @Override
    public void addToUserBasket(Item item){
        List<Item> basket = parseBasketString(this.userBasket);
        if (basket.contains(item)){
            Item prev = basket.get(basket.indexOf(item));
            prev.setItemQuantity(prev.getItemQuantity()+item.getItemQuantity());
        }
        else
            basket.add(item);
        Collections.sort(basket, new Comparator<Item>() {
            @Override
            public int compare(Item c1, Item c2) {
                return c1.getItemName().compareTo(c2.getItemName());
            }});
        
        this.userBasket = createBasketString(basket);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        return userName.hashCode();
    }

    /**
     * Compare a ShopUser to an Object. 
     * @param object the other Object.
     * @return true if the other Object is a ShopUser of the same name. false 
     * otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ShopUser)) {
            return false;
        }
        ShopUser other = (ShopUser) object;
        if ((this.userName.equals(other.getUserName()))) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "shop.model.ShopUser("+userName+":"+userPassword+":"+userAdmin+":"+userBanned+")";
    }
    
    /**
     * Convert a basket in String format to a List of Items.
     * @param userBasket a basket in String format.
     * @return a basket as a List of Items.
     */
    private static List<Item> parseBasketString(String userBasket){
        List<Item> basket = new ArrayList<Item>();
        if (userBasket == null || userBasket.isEmpty())
            return basket;
        String basketItems[] = userBasket.split(":");
        for (String basketItem: basketItems){
            String basketDetails[] = basketItem.split("\\*");
            basket.add(new Item(basketDetails[0], Integer.parseInt(basketDetails[1]), 0));
        }
        return basket;
    }
        
    /**
     * Convert a basket as a List of Items to a String format.
     * @param userBasket a basket as a List of Items.
     * @return a basket in String format. 
     */
    private static String createBasketString(List<Item> basket){
        String basketString = "";
        if (basket.isEmpty())
            return basketString;
        for (Item basketItem: basket)
           basketString += basketItem.getItemName()+"*"+basketItem.getItemQuantity()+":";
        basketString = basketString.substring(0, basketString.length()-1); //remove last ":"
        return basketString;
    }
    
}
