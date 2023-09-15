package pizza.ingredients;

/**
 * Base interface for managing the types of base sizes available.
 */
public interface Bases {
    /**
     * Enum containing the sizes available for the pizza bases type.
     */
    public static enum BaseSize {
        /**
         * large base size, $7.00
         */
        LARGE(7.00),
        /**
         * medium base size, $5.00
         */
        MEDIUM(5.00),
        /**
         * small base size, $3.00
         */
        SMALL(3.00);

        /**
         * price of this pizza base size
         */
        private final double price;

        /**
         * Creates BaseSize for the specified price
         * @param price price of this pizza base size
         */
        BaseSize(double price) {
            this.price = price;
        }

        /**
         * Returns the price of this pizza base
         * @return price of this pizza base
         */
        public double getPrice() {
            return price;
        }
    }

    /**
     * Set base size utilises an enum with all the types of bases available for
     * the base size type.
     * <p>
     * Base size being (SMALL, MEDIUM, LARGE)
     * @param size enum type depicting the base sizes of each pizza
     */
    void set(BaseSize size);
}
