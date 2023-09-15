package pizza;

import exceptions.TooManyToppingsException;
import menu.MenuItem;

import pizza.ingredients.Bases;
import pizza.ingredients.Cheeses;
import pizza.ingredients.Sauces;
import pizza.ingredients.Topping;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static pizza.ingredients.Bases.BaseSize.MEDIUM;
import static pizza.ingredients.Sauces.Sauce.TOMATO;
import static pizza.ingredients.Cheeses.Cheese.MOZZARELLA;

import static pizza.ingredients.Topping.PRICE;

/**
 * Pizza combines the required basic elements of a pizza, being the base, sauce
 * and cheese, and up to five additional toppings.
 * <p>
 * This is the pizza template to be extended for more rewarding pizza toppings.
 */
public abstract class Pizza
        implements MenuItem,
                   Bases,
                   Sauces,
                   Cheeses {

    /**
     * name of this pizza
     */
    private String name;

    /**
     * base of this pizza
     */
    private BaseSize size;

    /**
     * sauce of this pizza
     */
    private Sauce sauce;

    /**
     * cheese of this pizza
     */
    private Cheese cheese;

    /**
     * list of the toppings on this pizza
     */
    private List<Topping> toppings = new ArrayList<>();


    /**
     * maximum number of toppings that can be placed on a pizza (5)
     */
    public static final int MAX_TOPPINGS = 5;

    /**
     * Default constructor which creates a medium cheese pizza with tomato sauce.
     * <p>
     * A cheese pizza has a tomato sauce base, mozzarella cheese and no
     * additional toppings.
     * <p>
     * This pizza should be called "Dr Java's Pizza" unless or until another
     * name is set.
     */
    public Pizza() {
        this.size = MEDIUM;
        this.sauce = TOMATO;
        this.cheese = MOZZARELLA;
        setName("Dr Java's Pizza");
        registerMenuItem();
    }

    /**
     * Creating the base requirements of the pizza with no toppings.
     * <p>
     * This pizza should be called "Dr Java's Pizza" unless or until another
     * name is set.
     * @param size size of the pizza base as defined by bases
     * @param sauce sauce on the pizza as defined by sauces
     * @param cheese cheese on the pizza as defined by cheeses
     * @throws IllegalArgumentException if size, sauce or cheese are null
     */
    public Pizza(Bases.BaseSize size,
                 Sauces.Sauce sauce,
                 Cheeses.Cheese cheese)
            throws IllegalArgumentException {
        if (size == null
                | sauce == null
                | cheese == null) {
            throw new IllegalArgumentException();
        } else {
            this.size = size;
            this.sauce = sauce;
            this.cheese = cheese;
            setName("Dr Java's Pizza");
            registerMenuItem();
        }
    }

    /**
     * Creating a Pizza with a set base size, sauce, cheese and list of toppings.
     * <p>
     * This pizza should be called "Dr Java's Pizza" unless or until another
     * name is set.
     * @param size size of the pizza base as defined by bases
     * @param sauce sauce on the pizza as defined by sauces
     * @param cheese cheese on the pizza as defined by cheeses
     * @param toppings list of toppings on the pizza
     * @throws TooManyToppingsException if the number of toppings is greater
     *     than five
     * @throws IllegalArgumentException if size, sauce, cheese or toppings are
     *     null
     */
    public Pizza(Bases.BaseSize size,
                 Sauces.Sauce sauce,
                 Cheeses.Cheese cheese,
                 List<Topping> toppings)
            throws TooManyToppingsException,
            IllegalArgumentException {
        if (size == null
                | sauce == null
                | cheese == null
                | toppings == null) {
            throw new IllegalArgumentException();
        } else if (toppings.size() > MAX_TOPPINGS) {
            throw new TooManyToppingsException(
                    "NUMBER OF TOPPINGS IS GREATER THAN THE MAXIMUM ALLOWABLE "
                            + "NUMBER OF TOPPINGS");
        } else {
            this.size = size;
            this.sauce = sauce;
            this.cheese = cheese;
            this.toppings = toppings;
            setName("Dr Java's Pizza");
            registerMenuItem();
        }
    }

    /**
     * Returns the list of toppings that are on this pizza.
     * <p>
     * Adding or removing elements from the returned list should NOT affect the
     * original list.
     * @return list of the toppings on this pizza
     */
    public List<Topping> getToppings() {
        return new ArrayList<>(toppings);
    }

    /**
     * Returns the number of toppings on this pizza.
     * @return number of toppings on this pizza
     */
    private int getNumberOfToppings() {
        return getToppings().size();
    }

    /**
     * Returns the price of the pizza base size, defined in the base size enum,
     * and adds the price of each topping on the pizza.
     * @return price of the pizza
     */
    public double getTotalPrice() {
        return getSize().getPrice() + getNumberOfToppings() * PRICE;
    }

    /**
     * Returns the name of this pizza.
     * @return string name of this pizza
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the size of this pizza.
     * @return size of this pizza
     */
    private BaseSize getSize() {
        return size;
    }

    /**
     * Returns the sauce of this pizza.
     * @return sauce of this pizza
     */
    private Sauce getSauce() {
        return sauce;
    }

    /**
     * Returns the cheese of this pizza.
     * @return cheese of this pizza
     */
    private Cheese getCheese() {
        return cheese;
    }

    /**
     * Set the name of the pizza.
     * @param name string providing a replacement name of the pizza
     */
    public void setName(String name)
            throws IllegalArgumentException {
        if (name == null
                || name.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    /**
     * Set the size of this pizza base.
     * @param size size of this pizza base
     */
    public void set(Bases.BaseSize size) {
        this.size = size;
    }

    /**
     * Set the sauce on the pizza.
     * @param sauce sauce on this pizza
     */
    public void set(Sauces.Sauce sauce) {
        this.sauce = sauce;
    }

    /**
     * Set the cheese on the pizza.
     * @param cheese cheese on this pizza
     */
    public void set(Cheeses.Cheese cheese) {
        this.cheese = cheese;
    }

    /**
     * Returns the hash code of this pizza.
     * <p>
     * Two pizzas' that are equal according to the equals(Object) method should
     * have the same hash code.
     * @return hash code of this pizza
     */
    @Override
    public int hashCode() {
        return Objects.hash(getSize(),
                getSauce(),
                getCheese(),
                getToppings().stream()
                .sorted((topping1, topping2) -> topping1.toString()
                        .compareTo(topping2.toString()))
                .toList());
    }

    /**
     * Returns true, if the size of this pizza is equal to the size of the other
     * given object.
     * @param other reference object with which to compare
     * @return true, if the size of this pizza is the same as the size of the
     * other argument, otherwise, false
     */
    private boolean equalSize(Object other) {
        if (!(other instanceof Pizza)) {
            return false;
        }
        return this.getSize().equals(((Pizza) other).getSize());
    }

    /**
     * Returns true, if the sauce on this pizza is equal to the sauce on the
     * other given object.
     * @param other reference object with which to compare
     * @return true, if the sauce on this pizza is the same as the size of the
     * other argument, otherwise, false
     */
    private boolean equalSauce(Object other) {
        if (!(other instanceof Pizza)) {
            return false;
        }
        return this.getSauce().equals(((Pizza) other).getSauce());
    }

    /**
     * Returns true, if the cheese on this pizza is equal to the cheese on the
     * other given object.
     * @param other reference object with which to compare
     * @return true, if the cheese on this pizza is the same as the cheese of
     * the other argument, otherwise, false
     */
    private boolean equalCheese(Object other) {
        if (!(other instanceof Pizza)) {
            return false;
        }
        return this.getCheese().equals(((Pizza) other).getCheese());
    }

    /**
     * Returns true, if the number of toppings on this pizza are equal to the
     * number of toppings on the other given object.
     * @param other reference object with which to compare
     * @return true, if the number of toppings on this pizza are the same as the
     * number of toppings on the other argument, otherwise, false
     */
    private boolean equalNumberOfToppings(Object other) {
        if (!(other instanceof Pizza)) {
            return false;
        }
        return this.getNumberOfToppings()
                == ((Pizza) other).getNumberOfToppings();
    }

    /**
     * Returns true, if the toppings on this pizza are equal to the toppings on
     * the other given object.
     * @param other reference object with which to compare
     * @return true, if the toppings on this pizza are the same as the toppings
     * on the other argument, otherwise, false
     */
    private boolean equalToppings(Object other) {
        if (!(other instanceof Pizza)) {
            return false;
        } else if (!equalNumberOfToppings(other)) {
            return false;
        }
        List<Topping> thisToppings = this.getToppings().stream()
                .sorted((topping1, topping2) -> topping1.toString()
                        .compareTo(topping2.toString()))
                .toList();
        List<Topping> otherToppings = ((Pizza) other).getToppings().stream()
                .sorted((topping1, topping2) -> topping1.toString()
                        .compareTo(topping2.toString()))
                .toList();
        return thisToppings.equals(otherToppings);
    }

    /**
     * Returns true, if and only if, this pizza is equal to the other given
     * object.
     * <p>
     * For two pizzas to be equal, they must have the same base size, sauce,
     * cheese and toppings.
     * @param other reference object with which to compare
     * @return true, if the pizza is the same as the other argument,
     *     otherwise, false
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Pizza) {
            return equalSize(other)
                    && equalSauce(other)
                    && equalCheese(other)
                    && equalNumberOfToppings(other)
                    && equalToppings(other);
        }
        return false;
    }

    /**
     * Returns the human-readable string representation of this Pizza.
     * <p>
     * The format of the string to return is,
     *     'Name': is a 'BaseSize' sized base with 'Sauce' sauce and 'Cheese'
     *     cheese 'Toppings' $'Price'
     * where,
     *     'Name' = name of the pizza,
     *     'BaseSize' = base size of the pizza,
     *     'Sauce' = sauce on the pizza,
     *     'Cheese' = cheese on the pizza,
     *     'Toppings' = empty string if there are no toppings OR
     *         " - Toppings: 'Toppings'" if there are toppings,
     *         where,
     *             'Toppings' = string representation of the list of toppings
     *     'Price' = price of the pizza formatted to two decimal places.
     * Example:
     *     Dr Java's Pizza: is a 'MEDIUM' sized base with 'BBQ' sauce and
     *     'MOZZARELLA' cheese $5.00
     * OR,
     *     Dr Java's Pizza: is a 'MEDIUM' sized base with 'BBQ' sauce and
     *     'MOZZARELLA' cheese - Toppings: [BACON, PEPPERONI, HAM] $11.00
     * @return string representation of this pizza
     */
    @Override
    public String toString() {
        return getName() + ": is a '"
                + getSize() + "' sized base with '"
                + getSauce() + "' sauce and '"
                + getCheese() + "' cheese"
                + toStringToppings() + " $"
                + toDollarsAndCents(getTotalPrice());
    }

    /**
     * Returns the string representation of the toppings on this pizza
     * @return string representation of the toppings on this pizza
     */
    private String toStringToppings() {
        StringBuilder toStringToppings = new StringBuilder();

        int pizzaNumber = 1;

        if (!(getToppings().isEmpty())) {
            toStringToppings.append(" - Toppings: [");

            for (Topping topping : getToppings()) {
                toStringToppings.append(topping.toString());
                if (pizzaNumber < getNumberOfToppings()) {
                    toStringToppings.append(", ");
                } else {
                    toStringToppings.append("]");
                }
                pizzaNumber++;
            }
        }
        return toStringToppings.toString();
    }

    /**
     * Returns the given price formatted to two decimal places i.e. to dollars
     * and cents.
     * @param price price to be formatted to dollars and cents
     * @return price in dollars and cents
     */
    private String toDollarsAndCents(double price) {
        return new DecimalFormat("#.00").format(price);
    }

    /**
     * Returns the list of toppings that are on the pizza.
     * <p>
     * This method is used by subclasses to interface with the toppings on this
     * pizza.
     * <p>
     * Adding or removing elements from the returned list should affect the
     * original list.
     * @return list of toppings on this pizza
     */
    protected List<Topping> accessToppings() {
        return toppings;
    }
}