package presentation;

import bll.AbstractBLL;
import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.AbstractModel;
import model.Client;
import model.Orders;
import model.Product;

import java.util.List;

import javax.swing.JOptionPane;

/**
 * The Class Model. Contains the data retrieved from the database.
 */
public class Model {

    /** The clients. */
    private List<Client> clients;

    /** The products. */
    private List<Product> products;



    /** The orders. */
    private List<Orders> orders;
    /** The product BLL. */
    private ProductBLL productBLL;

    /** The client BLL. */
    private ClientBLL clientBLL;


    /** The order BLL. */
    private OrderBLL orderBLL;

    /**
     * Instantiates a new model. Populates the lists with data from the database
     */
    public Model() {
        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
        orderBLL = new OrderBLL();
        reset();
    }

    /**
     * Gets the clients.
     *
     * @return the clients
     */
    public List<Client> getClients() {
        return clients;
    }

    /**
     * Gets the products.
     *
     * @return the products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Gets the orders.
     *
     * @return the orders
     */
    public List<Orders> getOrders() {
        return orders;
    }

    /**
     * Calculate total number of products(sum of quantities)
     *
     * @return sum of quantites
     */
    public long totalProducts() {
        long sum = 0;
        for (Product product : this.products) {
            sum += product.getQuantity();
        }
        return sum;
    }
    /**
     * Calculate total number of orders(sum of quantities)
     *
     * @return sum of quantities
     */
    public long totalOrders() {
        long sum = 0;
        for (Orders order : this.orders) {
            sum += order.getQuantity();
        }
        return sum;
    }

    /**
     * Creates the corresponding model object according to the state of the view(its
     * TableConent variable)
     *
     * @param content
     *            the content of the view
     * @return the corresponding model object
     */
    public AbstractModel<?> getCorrespondingModelObject(TableContent content) {
        AbstractModel<?> newObject = null;
        switch (content) {
            case CLIENT:
                newObject = new Client();
                break;
            case PRODUCT:
                newObject = new Product();
                break;
            case ORDER:
                newObject = new Orders();
                break;
            default:
                newObject = new Client();
                break;
        }
        return newObject;
    }

    /**
     * Returns the corresponding model list.
     *
     * @param content
     *            the content of the view
     * @return the corresponding model list
     */
    public List<?> getCorrespondingModelList(TableContent content) {
        switch (content) {
            case CLIENT:
                return this.clients;
            case PRODUCT:
                return this.products;
            case ORDER:
                return this.orders;
            default:
                return this.clients;
        }
    }

    /**
     * It is a connection method between the current model object which needs to be
     * updated and the corresponding bll object
     *
     * @param <T>
     *            T is a type of model class
     * @param t
     *             the model object which needs to be updated
     * @param state
     *            the state of the view
     * @return 1 if successfull, return 0 otherwise
     */
    public <T extends AbstractModel<?>> int update(T t, TableContent state) {
        int result = 0;
        AbstractBLL<?, T, ?> genericBLL = (AbstractBLL<?, T, ?>) t.createBLL();
        result = genericBLL.update(t);
        reset();
        return result;
    }

    /**
     *It is a connection method between the current model object which needs to be
     * inserted and the corresponding bll object
     *
     * @param <T>
     *          T is a type of model class
     * @param t
     *            the model object which needs to be updated
     * @param state
     *           the state of the view
     * @return 1 if successfull, return 0 otherwise
     */
    public <T extends AbstractModel<?>> int insert(T t, TableContent state) {
        int result = 0;
        AbstractBLL<?, T, ?> genericBLL = (AbstractBLL<?, T, ?>) t.createBLL();

        if (t.getClass().equals(Orders.class)) {
            // check if the number of products are not to small
            Orders order = (Orders) t;
            ProductBLL productBLL = new ProductBLL();
            Product product = productBLL.findById(order.getIdProduct());
            if (order.getQuantity() <= product.getQuantity()) {// everything right
                // decrement stock
                product.setQuantity(product.getQuantity() - order.getQuantity());
                productBLL.update(product);
            } else {
                View.showMessage("UNDER STOCK", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        }
        result = genericBLL.insert(t);
        reset();
        return result;
    }

    /**
     * It is a connection method between the current model object which needs to be
     * deleted and the corresponding bll object
     *
     * @param <T>
     *            T is a type of model class
     * @param t
     *            the model object which needs to be updated
     * @param state
     *            the state of the view
     * @return 1 if successfull, return 0 otherwise
     */
    public <T extends AbstractModel<?>> int delete(T t, TableContent state) {
        int result = 0;
        AbstractBLL<?, T, ?> genericBLL = (AbstractBLL<?, T, ?>) t.createBLL();
        result = genericBLL.delete(t);
        reset();
        return result;
    }

    /**
     * Resets all the list with the values stored in the database
     */
    public void reset() {
        clients = clientBLL.findAll();
        products = productBLL.findAll();
        orders = orderBLL.findAll();
    }

}
