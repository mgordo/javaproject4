package shop.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A Session Bean defining an Item in storage, contains information about the
 * Quantity and Price.
 * @author Miguel & Nikos
 */
@Entity
public class Item implements ItemInterface, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String itemName;
    
    private int itemQuantity;
    private float itemPrice;

    /**
     *  Zero-argument default constructor, as per bean definition.
     */
    public Item() {
    }

    /**
     * Simple Constructor for an Item.
     * @param itemName the name of the Item.
     * @param itemQuantity the quantity of this Item contained in the inventory.
     * @param itemPrice the current sale price of the Item.
     */
    public Item(String itemName, int itemQuantity, float itemPrice){
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
    }
    
    
    @Override
    public String getItemName() {
        return itemName;
    }

    /**
     * Set the name of an Item.
     * @param itemName the new name of the Item.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    @Override
    public int getItemQuantity(){
        return itemQuantity;
    }

    @Override
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    
    @Override
    public float getItemPrice(){
        return itemPrice;
    }

    @Override
    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        return itemName.hashCode();
    }

    /**
     * Compare an Item to an Object. 
     * @param object the other Object.
     * @return true if the other Object is an Item of the same name. false 
     * otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemName.equals(other.getItemName()))) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return itemName;
    }
    
}
