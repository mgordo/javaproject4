package shop.model;

import java.util.List;

/**
 * this Interface defines the core methods to modify and use a User
 * @author Miguel & Nikos
 */
public interface UserInterface {
    
    /**
     * Get the name of a User.
     * @return the name of the User.
     */
    public String getUserName();

    /**
     * Get the password of a User.
     * @return the name of the User.
     */
    public String getUserPassword();

    /**
     * Get the administrator state of a User.
     * @return true if the User is an administrator.
     */
    public boolean getUserAdmin();

    /**
     * Get the banned state of a User.
     * @return true if the User is banned.
     */
    public boolean getUserBanned();

    /**
     * Set the banned state of a User.
     * @param banned the new banned state of the User.
     */
    public void setUserBanned(boolean banned);

    /**
     * Get the basket of a User in String format.
     * @return the Basket of the User in String format.
     */
    public String getUserBasket();

    /**
     * Set the basket of a User based on a String format.
     * @param userBasket the new Basket of the User in a String format.
     */
    public void setUserBasket(String userBasket);

    /**
     * Get the basket of a User as a List of Items.
     * @return the basket of the User as a List of Items.
     */
    public List<Item> getUserBasketList();

    /**
     * Add an Item to the basket of a User.
     * @param item the Item to be added to the basket of the User.
     */
    public void addToUserBasket(Item item);
}
