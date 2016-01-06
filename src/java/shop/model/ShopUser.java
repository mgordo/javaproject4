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
 *
 * @author Miguel
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
    
    public ShopUser(){
        
    }
    
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
    
    public void setUserName(String userName) {
         this.userName = userName;
    }
    
    @Override
    public String getUserPassword(){
        return userPassword;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    @Override
    public boolean getUserAdmin() {
        return userAdmin;
    }
    
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
        List<Item> basket = parseBasketString(userBasket);
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
        
        userBasket = createBasketString(basket);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        return userName.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShopUser)) {
            return false;
        }
        ShopUser other = (ShopUser) object;
        if ((this.userName.equals(other.getUserName()))) {
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shop.model.ShopUser("+userName+":"+userPassword+":"+userAdmin+":"+userBanned+")";
    }
    
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
