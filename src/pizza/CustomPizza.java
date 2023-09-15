package pizza;

import exceptions.TooManyToppingsException;
import menu.MenuItem;

import pizza.ingredients.Bases;
import pizza.ingredients.Cheeses;
import pizza.ingredients.Sauces;
import pizza.ingredients.Topping;

import java.util.List;

/**
 * Custom Pizza allows the addition of extra toppings for a more delicious Pizza.
 */
public class CustomPizza
        extends Pizza
        implements MenuItem {

    /**
     * Default constructor which creates a medium cheese pizza.
     * <p>
     * A medium cheese pizza has a tomato sauce base, mozzarella cheese and no
     * additional toppings.
     * <p>
     * This pizza should be called "Custom Pizza" until another name is set.
     */
    public CustomPizza() {
        super();
        setName("Custom Pizza");
        registerMenuItem();
    }

    /**
     * Creating the default requirements of a pizza with no additional toppings.
     * <p>
     * This pizza should be called "Custom Pizza" until another name is set.
     * @param size size of the pizza base as defined by bases
     * @param sauce sauce on the pizza as defined by sauces
     * @param cheese cheese on the pizza as defined by cheeses
     * @throws IllegalArgumentException if size, sauce or cheese is null
     */
    public CustomPizza(Bases.BaseSize size,
                       Sauces.Sauce sauce,
                       Cheeses.Cheese cheese)
            throws IllegalArgumentException {
        super(size, sauce, cheese);
        setName("Custom Pizza");
        registerMenuItem();
    }

    /**
     * The add method allows toppings to be added to the pizza, limited to the
     * maximum permissible amount of five.
     * <p>
     * This method will only add toppings to the pizza if ALL given toppings can
     * be added. If an exception is thrown then the list of toppings should
     * remain unchanged.
     * @param toppings list of toppings to be added to the pizza
     * @throws TooManyToppingsException when attempting to add toppings to Pizza
     *     or any class extending Pizza
     * @throws IllegalArgumentException if toppings is null
     */
    public void add(List<Topping> toppings)
            throws TooManyToppingsException,
            IllegalArgumentException {
        if (toppings == null) {
            throw new IllegalArgumentException();
        } else if (getToppings().size() + toppings.size() > MAX_TOPPINGS) {
            throw new TooManyToppingsException(
                    "NUMBER OF TOPPINGS IS GREATER THAN THE MAXIMUM ALLOWABLE "
                            + "NUMBER OF TOPPINGS");
        } else {
            accessToppings().addAll(toppings);
        }
    }

    /**
     * The add method allows a single topping to be added to the pizza, limited
     * to the maximum permissible amount of 5.
     * @param topping topping to be added to the pizza
     * @throws TooManyToppingsException if adding the new topping causes the
     *     number of toppings to exceed the limit of 5
     * @throws IllegalArgumentException if topping is null
     */
    public void add(Topping topping)
            throws TooManyToppingsException,
            IllegalArgumentException {
        if (topping == null) {
            throw new IllegalArgumentException();
        } else if (getToppings().size() + 1 > MAX_TOPPINGS) {
            throw new TooManyToppingsException(
                    "NUMBER OF TOPPINGS IS GREATER THAN THE MAXIMUM ALLOWABLE "
                            + "NUMBER OF TOPPINGS");
        } else {
            accessToppings().add(topping);
        }
    }

    /**
     * The remove method removes the first occurrence of the specified topping
     * from this pizza, if it is present.
     * <p>
     * If the topping is not on the pizza, it is unchanged.
     * @param topping topping to be removed from the pizza
     */
    public void remove(Topping topping) {
        accessToppings().remove(topping);
    }
}