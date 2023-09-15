package menu;

import exceptions.TooManyToppingsException;

import javax.swing.*;

/**
 * Customer Ordering class used for creating multiple types of pizzas, and
 * storing Orders in an order object, to be returned by a calling class. It
 * maintains the order number ID represented by a Universal Unique Identifier
 * (UUID) as described in the Order class. The name, UUID, time and date are
 * taken, and attached to each order. The customer name is required, but not
 * filtered. Such that any string value is accepted for a customer name. But if
 * a value is not provided through the constructor, a name is requested through
 * a GUI prompt in the requestName() method. This calls a simple JOptionPane.
 * See API for details on how to implement an GUI input dialogue. This class
 * will not be tested programmatically but will still be marked for style.
 */
public class CustomerOrder {

    /**
     * customer name for this order
     */
    private String customerName;

    /**
     * order for this customer
     */
    private Order customerOrder;

    /**
     * Default Constructor to create a pizza.
     * <p>
     * Since no name supplied, this constructor sends GUI prompt to the customer
     * for their name through by calling requestName() and passes on the name to
     * the String signature constructor.
     */
    public CustomerOrder() {
        new CustomerOrder(requestName());
    }

    /**
     * Prompts the user for their name, following an attempt to instantiate the
     * order without one. The expectation is to use a JOptionPane to request the
     * name through a GUI medium.
     * @return string from the input field donating the customers name.
     */
    protected String requestName() {
        return JOptionPane.showInputDialog("Name: ");
    }

    /**
     * Constructor taking customer name as the parameter. Creates a unique
     * order/customer ID, stores the customer name gets the current date and
     * time, and calls createOrder().
     * @param customerName string donating the name of the customer
     */
    public CustomerOrder(String customerName) {
        this.customerName = customerName;
        try {
            createOrder();
        } catch (TooManyToppingsException exception) {
            // Empty
        }
    }

    /**
     * Returns the customer name of this order
     * @return name of this order
     */
    private String getCustomerName() {
        return customerName;
    }

    /**
     * Returns the order for this customer.
     * @return order for this customer
     */
    private Order getCustomerOrder() {
        return customerOrder;
    }

    /**
     * Creates a test customer order to connect with a future GUI.
     * <p>
     * This method is not going to be tested, but rather is a proxy you can use
     * instead of the GUI application to test your program.
     * @return order containing the entire order of the customer, which includes
     *     the unique order/customer identifier, data and time.
     * @throws TooManyToppingsException when attempting to add toppings to a
     *     pizza or any class extending pizza
     */
    public Order createOrder()
            throws TooManyToppingsException {
        customerOrder = new Order();
        customerOrder.setName(getCustomerName());
        return customerOrder;
    }

    /**
     * Returns human-readable string representation of a customer order.
     * <p>
     * This is,
     *     Customer Order {'Order'}
     * where,
     *     'Order' = Order.toString().
     * @return string representation of this customer order
     */
    @Override
    public String toString() {
        return "Customer Order {"
                + getCustomerOrder()
                + "}";
    }
}