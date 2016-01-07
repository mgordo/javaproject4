package shop.model;

/**
 * this Interface defines the core methods to modify and use an Item
 * @author Miguel & Nikos
 */
public interface ItemInterface {
    
    /**
     * Get the name of an Item.
     * @return the name of the Item.
     */
    public String getItemName();

    /**
     * Get the quantity of an Item.
     * @return the quantity of the Item.
     */
    public int getItemQuantity();

    /**
     * Set the quantity of an Item.
     * @param itemQuantity the new quantity of the Item.
     */
    public void setItemQuantity(int itemQuantity);

    /**
     * Set the price of an Item.
     * @param itemPrice the new price of the Item.
     */
    public void setItemPrice(float itemPrice);

    /**
     * Get the price of an Item.
     * @return the price of the Item.
     */
    public float getItemPrice();
}
