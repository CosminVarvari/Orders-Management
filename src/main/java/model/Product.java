package model;

import bll.ProductBLL;


/**
 * The Class Product.
 */
public class Product extends AbstractModel<ProductBLL> {
    /** The id. */
    protected int id;

    /** The name. */
    private String name;

    /** The quantity. */
    private int quantity;

    /** The unit price. */
    private int unitPrice;

    /**
     * Instantiates a new product.
     */
    public Product() {

    }

    /**
     * Instantiates a new product.
     *
     * @param id
     *            the id
     * @param name
     *            the name
     * @param quantity
     *            the quantity
     * @param unitPrice
     *            the unit price
     */
    public Product(int id, String name, int quantity, int unitPrice) {
        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     *
     * @param quantity
     *            the new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the unit price.
     *
     * @return the unit price
     */
    public int getUnitPrice() {
        return unitPrice;
    }

    /**
     * Sets the unit price.
     *
     * @param unitPrice
     *            the new unit price
     */
    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", quantity=" + quantity + ", unitPrice=" + unitPrice + "]";
    }

}
