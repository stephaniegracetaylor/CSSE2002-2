package menu;

/**
 * Represents an item that can be ordered from the menu.
 */
public interface MenuItem {
    /**
     * A functional interface to allow a lambda function to be passed to an Order.
     */
    @FunctionalInterface
    public static interface Discount {
        /**
         * Apply a discount to the given price.
         * @param price input price
         * @return discounted price
         */
        double applyDiscount(double price);
    }

    /**
     * Returns the price of a menu item.
     * @return price of the menu item
     */
    double getTotalPrice();

    /**
     * Registers this item with the Menu singleton class.
     * <p>
     * This method should be called in the constructor of each implementing class.
     */
    default void registerMenuItem() {
        Menu.getInstance().registerMenuItem(this);
    }

    /**
     * Returns the name of this menu item.
     * @return name of this menu item
     */
    String getName();
}