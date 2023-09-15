package pizza.ingredients;

/**
 * Cheeses interface for managing the types of cheeses available.
 */
public interface Cheeses {
    /**
     * Enum containing the cheeses available for the pizza cheese type.
     */
    public static enum Cheese {
        /**
         * classic shredded mozzarella cheese
         */
        MOZZARELLA,
        /**
         * special option to represent no cheese
         */
        NONE,
        /**
         * vegan friendly cheese
         */
        VEGAN;
    }

    /**
     * Set cheeses utilises an enum with all the types of cheeses available for
     * the cheese type.
     * <p>
     * Cheese being (MOZZARELLA, NONE, VEGAN)
     * @param cheese enum type depicting the cheeses of each pizza
     */
    void set(Cheeses.Cheese cheese);
}

