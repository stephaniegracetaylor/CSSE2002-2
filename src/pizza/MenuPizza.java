package pizza;

import exceptions.TooManyToppingsException;
import menu.MenuItem;

import pizza.ingredients.Bases;
import pizza.ingredients.Sauces;
import pizza.ingredients.Cheeses;
import pizza.ingredients.Topping;

import java.util.List;

/**
 * MenuPizza provides a selection for ordering pre-organised Pizzas from the
 * menu.
 * <p>
 * The menu pizza's toppings can not be changed.
 */
public class MenuPizza
        extends Pizza
        implements MenuItem {

    /**
     * Creating a menu pizza with a set base size, sauce, cheese and list of
     * toppings.
     * @param size size of the pizza base as defined by Bases
     * @param sauce sauce on the pizza as defined by Sauces
     * @param cheese cheese on the pizza as defined by Cheeses
     * @param toppings list of toppings on the pizza
     * @throws TooManyToppingsException if the number of toppings is greater
     *     than the maximum permissible number of toppings
     * @throws IllegalArgumentException if size, sauce or cheese are null
     */
    public MenuPizza(Bases.BaseSize size,
                     Sauces.Sauce sauce,
                     Cheeses.Cheese cheese,
                     List<Topping> toppings)
            throws TooManyToppingsException,
            IllegalArgumentException {
        super(size, sauce, cheese, toppings);
        registerMenuItem();
    }

    /**
     * Returns the human-readable string representation of this Menu Pizza.
     * <p>
     * The format of the string to return is,
     *     [MenuPizza] 'Pizza'
     * where,
     *     'Pizza' = toString of the Pizza superclass
     * Example:
     *     [MenuPizza] Hawaiian: is a 'MEDIUM' sized base with 'TOMATO' sauce
     *     and 'MOZZARELLA' cheese - Toppings: [PINEAPPLE, HAM] $9.00
     * @return string representation of this Menu Pizza
     */
    @Override
    public String toString() {
        return "[MenuPizza] " + super.toString();
    }
}
