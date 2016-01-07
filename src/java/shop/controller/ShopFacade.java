package shop.controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import shop.model.Item;
import shop.model.ItemInterface;
import shop.model.ShopUser;
import shop.model.UserInterface;

/**
 *  ShopFacade is a Facade pattern EJB, accessing the Java Persistence API in 
 * order to manage the two Session Beans, ShopUser and Item. 
 *  It Provides all the necessary methods to add, remove and modify the 
 * persisted Session Beans for the Managed Bean loginManager.
 * @author Miguel & Nikos
 * 
 */
@Stateless
public class ShopFacade {
    @PersistenceContext(unitName = "shopPU")
    private EntityManager entityManager;
    
    /**
     * Add an Item to the database. 
     * @param item the Item to add.
     * @throws Exception an Exception is thrown if an item of the same name 
     * already exists.
     */
    public void addItem(Item item) throws Exception {
        ItemInterface existing = entityManager.find(Item.class, item.getItemName());
        if (existing != null) {
            throw new Exception("Item with name " + item.getItemName() + "already exists.");
        }
        entityManager.persist(item);
    }
    
    /**
     * Set the stored quantity of an Item to a new value.
     * @param itemName the Item to update.
     * @param quantity the new quantity of the Item.
     * @throws Exception an Exception is thrown if no item identified by that 
     * name exists.
     */
    public void setItemQuantity(String itemName, int quantity) throws Exception {
        ItemInterface item = entityManager.find(Item.class, itemName);
        if (item == null) {
            throw new Exception("No Item with name " + itemName);
        }
        item.setItemQuantity(quantity);
        entityManager.merge(item);
    }
    
    /**
     * Set the stored price of an Item to a new value.
     * @param itemName the Item to update.
     * @param price the new price of the Item.
     * @throws Exception an Exception is thrown if no item identified by that 
     * name exists.
     */
    public void setItemPrice(String itemName, float price) throws Exception {
        ItemInterface item = entityManager.find(Item.class, itemName);
        if (item == null) {
            throw new Exception("No Item with name " + itemName);
        }
        item.setItemPrice(price);
        entityManager.merge(item);
    }

    /**
     * Delete an item from the database.
     * @param itemName the Item to delete.
     * @throws Exception an Exception is thrown if no item identified by that 
     * name exists.
     */
    public void deleteItem(String itemName) throws Exception {
        Item item = entityManager.find(Item.class, itemName);
        if (item == null) {
            throw new Exception("No Item with name " + itemName);
        }
        entityManager.remove(item);
    }

    /**
     * Retrieve an item from the database.
     * @param itemName the Item to retrieve.
     * @return returns the requested Item, or null if no such item is found.
     */
    public ItemInterface getItem(String itemName){
        ItemInterface item = entityManager.find(Item.class, itemName);
        return item;
    }
    
    /**
     * Get all the items that exist in the database.
     * @return returns a list of all the items currently in the inventory.
     */
    public List<Item> getItems() {
        Query query = entityManager.createQuery("SELECT i from Item i");
        return query.getResultList();
    }
    
    /**
     * Add a ShopUser to the database. 
     * @param user the ShopUser to be persisted.
     */
    public void addUser(ShopUser user) {
        entityManager.persist(user);
    }
    
    /**
     * Retrieve a user from the database.
     * @param userName the ShopUser to retrieve.
     * @return the User identified by the username, or null in case none is 
     * found.
     */
    public UserInterface getUser(String userName){
        UserInterface user = entityManager.find(ShopUser.class, userName);
        return user;
    }
    
    /**
     * Change the banned state of a ShopUser in the database.
     * @param userName the username of the ShopUser to be modified.
     * @param banned the new banned state for the ShopUser.
     * @throws Exception an Exception is thrown if no user identified by that 
     * name exists.
     */
    public void setUserBanned(String userName, boolean banned) throws Exception {
        UserInterface user = entityManager.find(ShopUser.class, userName);
        if (user == null) {
            throw new Exception("No ShopUser with name " + userName);
        }
        user.setUserBanned(banned);
        entityManager.merge(user);
    }

    /**
     * Delete a user from the database.
     * @param user the username of the ShopUser to be deleted.
     * @throws Exception an Exception is thrown if no user identified by that 
     * name exists.
     */
    public void deleteUser(ShopUser user) throws Exception {        
        ShopUser storedUser = entityManager.find(ShopUser.class, user.getUserName());
        if (storedUser == null) {
            throw new Exception("No ShopUser with name " + user.getUserName());
        }
        entityManager.remove(user);
    }
    
    /**
     * Retrieve a List of all the ShopUsers currently in the database.
     * @return a List of all the users stored in persistence.
     */
    public List<ShopUser> getUsers() {
        Query query = entityManager.createQuery("SELECT u from ShopUser u");
        return query.getResultList();
    }
    
    /**
     * Add an Item (along with the included quantity) to the database.
     * @param userName the username of the ShopUser to be modified.
     * @param item the item to be added.
     * @throws Exception an Exception is thrown if no user identified by that 
     * name exists.
     */
    public void addToUserBasket(String userName, Item item) throws Exception {
        UserInterface user = entityManager.find(ShopUser.class, userName);
        if (user == null) {
            throw new Exception("No ShopUser with name " + userName);
        }
        user.addToUserBasket(item);
        entityManager.merge(user);
    }
    
    /**
     * Delete the contents of the basket of a ShopUser.
     * @param userName the username of the ShopUser to be modified.
     * @throws Exception an Exception is thrown if no user identified by that 
     * name exists.
     */
    public void clearUserBasket(String userName) throws Exception {
        UserInterface user = entityManager.find(ShopUser.class, userName);
        if (user == null) {
            throw new Exception("No ShopUser with name " + userName);
        }
        user.setUserBasket("");
        entityManager.merge(user);
    }
    
    /**
     * Generate a String representing the contents of the basket of a ShopUser 
     * in a readable format.
     * @param userName the username of the ShopUser whose basket is to be 
     * retrieved.
     * @return the String representation of a user's basket.
     * @throws Exception an Exception is thrown if no user identified by that 
     * name exists.
     */
    public String getUserBasketPrintable(String userName) throws Exception {
        UserInterface user = entityManager.find(ShopUser.class, userName);
        if (user == null) {
            throw new Exception("No ShopUser with name " + userName);
        }
        List<Item> basket = user.getUserBasketList();
        String basketPrintable = "";
        for (Item item:basket){
            ItemInterface realItem = getItem(item.getItemName());
            basketPrintable += item.getItemName()+", "+item.getItemQuantity()+" item(s), total: "+realItem.getItemPrice()*item.getItemQuantity()+"<br>";
        }
        return basketPrintable;
    }
    
    /**
     * Retrieve a List of Item contained in the basket of a ShopUser.
     * @param userName the username of the ShopUser whose basket is to be 
     * retrieved.
     * @return the List of items a user has added to his basket.
     * @throws Exception an Exception is thrown if no user identified by 
     * that name exists.
     */
    public List<Item> getUserBasket(String userName) throws Exception {
        UserInterface user = entityManager.find(ShopUser.class, userName);
        if (user == null) {
            throw new Exception("No ShopUser with name " + userName);
        }
        List<Item> basket = user.getUserBasketList();
        for (Item item:basket){
            ItemInterface realItem = getItem(item.getItemName());
            if (realItem == null)
                realItem = new Item(item.getItemName(), 0, 0);
            basket.get(basket.indexOf(item)).setItemPrice(realItem.getItemPrice()*item.getItemQuantity());
        }
        return basket;
    }
    
    /**
     * Generate the total amount that the Items in the basket of a ShopUser 
     * cost. 
     * @param userName the username of the ShopUser whose basket total is to 
     * be retrieved.
     * @return the total cost of the items in the user's basket.
     * @throws Exception an Exception is thrown if no user identified by that 
     * name exists.
     */
    public float getUserBasketTotalPrice(String userName) throws Exception {
        UserInterface user = entityManager.find(ShopUser.class, userName);
        if (user == null) {
            throw new Exception("No ShopUser with name " + userName);
        }
        List<Item> basket = user.getUserBasketList();
        Float total = 0f;
        for (Item item:basket){
            ItemInterface realItem = getItem(item.getItemName());
            if (realItem == null)
                realItem = new Item(item.getItemName(), 0, 0);
            total += realItem.getItemPrice()*item.getItemQuantity();
        }
        return total;
    }
    
    /**
     * Modify the stored amounts of items in the database according to the ones
     * contained in the basket of a ShopUser, if possible.
     * @param userName the username of the ShopUser whose basket total is to 
     * be retrieved.
     * @throws Exception an Exception is thrown if no user identified by that 
     * name exists, if the items in the basket do not correspond to items in 
     * the database, or if the stored amounts of items are not sufficient to 
     * conclude the sale.
     */
    public void reduceItemQuantitiesByUserBasket(String userName) throws Exception {
        UserInterface user = entityManager.find(ShopUser.class, userName);
        if (user == null) {
            throw new Exception("No ShopUser with name " + userName);
        }
        List<Item> basket = user.getUserBasketList();
        for (Item item:basket){
            ItemInterface realItem = getItem(item.getItemName());
            if (realItem == null){
                throw new Exception("No Item with name " + item.getItemName());
            }
            if (realItem.getItemQuantity() < item.getItemQuantity()) {
                throw new Exception("Not enough "+item.getItemName()+" items in inventory");
            }
        }
        for (Item item:basket){
            ItemInterface realItem = getItem(item.getItemName());
            realItem.setItemQuantity(realItem.getItemQuantity()-item.getItemQuantity());
            entityManager.merge(realItem);
        }
    }
}
