package pizza.ingredients;

/**
 * Sauces interface for managing the types of sauces available.
 */
public interface Sauces {
    /**
     * Enum containing the sauces available for the pizza sauce type.
     */
    public static enum Sauce {
        /**
         * classic bbq sauce
         */
        BBQ,
        /**
         * strong smelling garlic sauce
         */
        GARLIC,
        /**
         * special option to represent non sauce
         */
        NONE,
        /**
         * rich tomato sauce
         */
        TOMATO;
    }

    /**
     * Set sauce utilises an enum with all the types of cheeses available for
     * the sauce type.
     * <p>
     * Sauce being (BBQ, GARLIC, NONE, TOMATO)
     * @param sauce enum type depicting the sauces of each pizza
     */
    void set(Sauce sauce);
}

