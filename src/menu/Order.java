package menu;

import pizza.Pizza;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Object model to manage an individual order.
 * <p>
 * An order will have an associated date, time and customer name, to make it
 * easy to identify order's they are also given a UUID.
 */
public class Order {

    /**
     * A constant lambda functional interface to apply a 10% discount.
     */
    public static final MenuItem.Discount DISCOUNT_10 = price -> 0.90 * price;

    /**
     * A constant lambda functional interface to apply a 25% discount.
     */
    public static final MenuItem.Discount DISCOUNT_25 = price -> 0.75 * price;

    /**
     * list of pizzas in this order
     */
    private List<Pizza> pizzasInOrder;

    /**
     * name for this order
     */
    private String name;

    /**
     * universally unique identifier for this order
     */
    private UUID uuid;

    /**
     * date of this order
     */
    private LocalDate date;

    /**
     * time of this order
     */
    private LocalTime time;

    /**
     * boolean for if the order is discounted
     */
    private boolean isDiscounted = false;

    /**
     * price of this order with discounts applied
     */
    private double orderPriceWithDiscount;

    /**
     * Creates an order initialising any member variables, as required.
     */
    public Order() {
        setName("Not Given");
        setUUID(UUID.randomUUID());
        setDate(LocalDate.now());
        setTime(LocalTime.now());
    }

    /**
     * Returns the name of this order.
     * @return name of this order
     */
    private String getName() {
        return name;
    }

    /**
     * Returns the uuid of this order.
     * @return uuid of this order
     */
    private UUID getUUID() {
        return uuid;
    }

    /**
     * Returns the date of this order.
     * @return date of this order
     */
    private LocalDate getDate() {
        return date;
    }

    /**
     * Returns the time of this order.
     * @return time of this order
     */
    private LocalTime getTime() {
        return time;
    }

    /**
     * Returns the price of this order without discounts.
     * @return price of this order without discounts
     */
    private double getOrderPriceWithoutDiscount() {
        double orderPriceWithoutDiscount = 0;
        if (pizzasInOrder != null) {
            for (Pizza pizza : pizzasInOrder) {
                orderPriceWithoutDiscount += pizza.getTotalPrice();
            }
        }
        return orderPriceWithoutDiscount;
    }

    /**
     * Returns the price of this order with discounts.
     * @return price of this order with discounts
     */
    private double getOrderPriceWithDiscount() {
        return orderPriceWithDiscount;
    }

    /**
     * Returns the number of pizzas in this order.
     * @return number of pizzas in this order
     */
    private int getNumberOfPizzas() {
        return pizzasInOrder.size();
    }

    /**
     * Mutator method to modify the customer name.
     * @param name string
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutator method to modify the orders UUID.
     * @param uuid UUID
     */
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Mutator method to modify the order's creation date.
     * @param date date of the order
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Mutator method to modify the order's creation time.
     * @param time time of the order
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Adds a completed pizza to the order list.
     * <p>
     * If there are 3 or more pizzas in the order then a 10% discount should be
     * applied.
     * <p>
     * If there are 6 or more pizzas in the order then a 25% discount should be
     * applied.
     * <p>
     * This supersedes the previous 10% discount.
     * @param pizza pizza to add to this order
     */
    public void add(Pizza pizza) {
        if (pizzasInOrder == null) {
            pizzasInOrder = new ArrayList<>();
        }
        pizzasInOrder.add(pizza);

        if (getNumberOfPizzas() > 5) {
            orderPriceWithDiscount = DISCOUNT_25
                    .applyDiscount(getOrderPriceWithoutDiscount());
            isDiscounted = true;
        } else if (getNumberOfPizzas() > 2) {
            orderPriceWithDiscount = DISCOUNT_10
                    .applyDiscount(getOrderPriceWithoutDiscount());
            isDiscounted = true;
        }
    }

    /**
     * Object.toString() providing a complete synopsis of the order class
     * including the current assigned date and time, formatted as shown. The
     * time is formatted as ISO-8601, with only the hour and minute.
     * <p>
     * Example:
     *     Date: 2022-10-12
     *     Time: 14:37
     *     Customer: John Smith
     *     Order number: e6ef5932-7f6a-46ff-a81e-856a6afabc3c
     *     Order:
     *     1 - Custom Pizza: is a 'MEDIUM' sized base with 'BBQ' sauce and
     *         'MOZZARELLA' cheese - Toppings: [BACON, PEPPERONI, HAM] $11.00
     *     2 - [MenuPizza] Hawaiian: is a 'MEDIUM' sized base with 'TOMATO'
     *         sauce and 'MOZZARELLA' cheese - Toppings: [PINEAPPLE, HAM] $9.00
     *     3 - [MenuPizza] Spicy Italian: is a 'LARGE' sized base with 'GARLIC'
     *         sauce and 'MOZZARELLA' cheese - Toppings: [PEPPERONI, JALAPENO,
     *         PEPPERS] $13.00
     *     4 - Custom Pizza: is a 'SMALL' sized base with 'GARLIC' sauce and
     *         'VEGAN' cheese $3.00
     *     <p>
     *     Multi item discount applied of $36.00 applied, new Total: $32.40
     * @return string representing the instantiated class
     */
    @Override
    public String toString() {
        return "Date: "
                + getDate() + "\n"
                + "Time: "
                + getTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n"
                + "Customer: "
                + getName() + "\n"
                + "Order number: "
                + getUUID() + "\n"
                + "Order:" + "\n"
                + toStringPizzasInOrder() + "\n"
                + toStringTotal() + "\n";
    }

    /**
     * Returns the string representation of the list of pizzas in this order.
     * @return string representation of the list of pizzas in this order
     */
    private String toStringPizzasInOrder() {
        StringBuilder toStringPizzasInOrder = new StringBuilder();

        int pizzaNumber = 1;

        if (pizzasInOrder != null) {
            for (Pizza pizza : pizzasInOrder) {
                toStringPizzasInOrder.append(pizzaNumber);
                toStringPizzasInOrder.append(" - ");
                toStringPizzasInOrder.append(pizza.toString());
                toStringPizzasInOrder.append("\n");
                pizzaNumber++;
            }
        }

        return toStringPizzasInOrder.toString();
    }

    /**
     * Returns the string representation of the total including discounts
     * applied to this order accordingly.
     * @return string representation of the total for this order
     */
    private String toStringTotal() {
        String toStringTotal = "";
        if (isDiscounted) {
            toStringTotal += "Multi item discount applied of $"
                    + toDollarsAndCents(getOrderPriceWithoutDiscount())
                    + " applied, new Total: $"
                    + toDollarsAndCents(getOrderPriceWithDiscount());
        } else {
            toStringTotal += "Total: $"
                    + toDollarsAndCents(getOrderPriceWithoutDiscount());
        }
        return toStringTotal;
    }

    /**
     * Returns the given price formatted to two decimal places i.e. to dollars
     * and cents.
     * @param price price to be formatted to dollars and cents
     * @return price in dollars and cents
     */
    private String toDollarsAndCents(double price) {
        return new DecimalFormat("0.00").format(price);
    }
}
