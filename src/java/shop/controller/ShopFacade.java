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
 *
 * @author Miguel
 */
@Stateless
public class ShopFacade {
    @PersistenceContext(unitName = "shopPU")
    private EntityManager entityManager;
    
    public void addItem(Item item) throws Exception {
        entityManager.persist(item);
    }
    
    public void setItemQuantity(String itemName, int quantity) throws Exception {
        ItemInterface item = entityManager.find(Item.class, itemName);
        if (item == null) {
            throw new Exception("No Item with name " + itemName);
        }
        item.setItemQuantity(quantity);
        entityManager.refresh(item);
    }

    public void deleteItem(Item item) throws Exception {
        entityManager.remove(item);
    }

    public List<Item> getItems() throws Exception {
        Query query = entityManager.createQuery("SELECT i from Item i");
        return query.getResultList();
    }
    
    public void addUser(ShopUser user) throws Exception {
        entityManager.persist(user);
    }
    
    public UserInterface getUser(String userName) throws Exception{
        UserInterface user = entityManager.find(ShopUser.class, userName);
        if (user == null) {
            throw new Exception("No User with name " + userName);
        }
        return user;
    }
    
    public void setUserBanned(String userName, boolean banned) throws Exception {
        UserInterface user = entityManager.find(ShopUser.class, userName);
        if (user == null) {
            throw new Exception("No User with name " + userName);
        }
        user.setUserBanned(banned);
        entityManager.refresh(user);
    }

    public void deleteUser(ShopUser user) throws Exception {
        entityManager.remove(user);
    }

    public List<ShopUser> getUsers() throws Exception {
        Query query = entityManager.createQuery("SELECT u from ShopUser u");
        return query.getResultList();
    }
}

/**
 * This class should provide functionality to obtain users and items from the database
 */