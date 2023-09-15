package pizza;

import exceptions.TooManyToppingsException;
import menu.Menu;
import org.junit.Before;
import org.junit.Test;
import pizza.ingredients.Bases;
import pizza.ingredients.Cheeses;
import pizza.ingredients.Sauces;
import pizza.ingredients.Topping;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static pizza.ingredients.Topping.*;

public class CustomPizzaTest {
    private CustomPizza customPizza1;
    private CustomPizza customPizza2;
    private CustomPizza customPizza3;
    private CustomPizza customPizza4;
    private CustomPizza customPizza5;

    private List<Topping> toppings1;
    private List<Topping> toppings2;
    private List<Topping> toppings3;

    @Before
    public void setUp() {
        Menu.getInstance().clear();

        customPizza1 = new CustomPizza();
        customPizza2 = new CustomPizza(Bases.BaseSize.SMALL,
                Sauces.Sauce.BBQ,
                Cheeses.Cheese.MOZZARELLA);
        customPizza3 = new CustomPizza(Bases.BaseSize.MEDIUM,
                Sauces.Sauce.GARLIC,
                Cheeses.Cheese.NONE);
        customPizza4 = new CustomPizza(Bases.BaseSize.LARGE,
                Sauces.Sauce.NONE,
                Cheeses.Cheese.VEGAN);
        customPizza5 = new CustomPizza(Bases.BaseSize.LARGE,
                Sauces.Sauce.NONE,
                Cheeses.Cheese.VEGAN);

        resetToppings();
        createTopping("Bacon", false);
        createTopping("Chicken", false);
        createTopping("Mushrooms", true);
        createTopping("Pineapple", true);
        createTopping("Olives", true);
        createTopping("Onions", true);

        // less than the maximum allowable number of toppings
        toppings1 = new ArrayList<>();
        toppings1.add(valueOf("BACON"));
        toppings1.add(valueOf("CHICKEN"));
        toppings1.add(valueOf("MUSHROOMS"));

        // equal to the maximum allowable number of toppings
        toppings2 = new ArrayList<>();
        toppings2.add(valueOf("BACON"));
        toppings2.add(valueOf("CHICKEN"));
        toppings2.add(valueOf("MUSHROOMS"));
        toppings2.add(valueOf("OLIVES"));
        toppings2.add(valueOf("ONIONS"));

        // greater than the maximum allowable number of toppings
        toppings3 = new ArrayList<>();
        toppings3.add(valueOf("BACON"));
        toppings3.add(valueOf("CHICKEN"));
        toppings3.add(valueOf("CHICKEN"));
        toppings3.add(valueOf("MUSHROOMS"));
        toppings3.add(valueOf("PINEAPPLE"));
        toppings3.add(valueOf("OLIVES"));
        toppings3.add(valueOf("ONIONS"));
    }

    @Test
    public void createCustomPizzaDefaultTest() {
        assertEquals("Custom Pizza", customPizza1.getName());
        assertEquals("Custom Pizza: is a 'MEDIUM' sized base with " +
                        "'TOMATO' sauce and 'MOZZARELLA' cheese $5.00",
                Menu.getInstance().get(0).toString());
    }

    @Test
    public void createCustomPizzaNonDefaultTest() {
        assertEquals("Custom Pizza", customPizza2.getName());
        assertEquals("Custom Pizza", customPizza3.getName());
        assertEquals("Custom Pizza", customPizza4.getName());
        assertEquals("Custom Pizza: is a 'SMALL' sized base with " +
                        "'BBQ' sauce and 'MOZZARELLA' cheese $3.00",
                Menu.getInstance().get(1).toString());
        assertEquals("Custom Pizza: is a 'MEDIUM' sized base with " +
                        "'GARLIC' sauce and 'NONE' cheese $5.00",
                Menu.getInstance().get(2).toString());
        assertEquals("Custom Pizza: is a 'LARGE' sized base with " +
                        "'NONE' sauce and 'VEGAN' cheese $7.00",
                Menu.getInstance().get(3).toString());
    }

    @Test
    public void createCustomPizzaExistingMenuItemTest() {
        // customerPizza1, customerPizza2, customerPizza3, customerPizza4
        // customerPizza4 == customerPizza5
        assertEquals(4, Menu.getInstance().getItems().size());
    }

    // Toppings.add(Topping topping)

    @Test(expected = TooManyToppingsException.class)
    public void addToppingGreaterThanAllowableMaximumToppingsTest()
            throws TooManyToppingsException {
        customPizza1.add(Topping.valueOf("Mushrooms"));
        customPizza1.add(Topping.valueOf("Mushrooms"));
        customPizza1.add(Topping.valueOf("Mushrooms"));
        customPizza1.add(Topping.valueOf("Pineapple"));
        customPizza1.add(Topping.valueOf("Pineapple"));
        customPizza1.add(Topping.valueOf("Pineapple"));
    }

    @Test
    public void addToppingEqualToAllowableMaximumToppingsTest()
            throws TooManyToppingsException {
        customPizza2.add(Topping.valueOf("Bacon"));
        customPizza2.add(Topping.valueOf("Chicken"));
        customPizza2.add(Topping.valueOf("Mushrooms"));
        customPizza2.add(Topping.valueOf("Pineapple"));
        customPizza2.add(Topping.valueOf("Onions"));
        assertEquals("[BACON, CHICKEN, MUSHROOMS, PINEAPPLE, ONIONS]",
                customPizza2.getToppings().toString());
    }

    @Test
    public void addToppingLessThanAllowableMaximumToppingsTest()
            throws TooManyToppingsException {
        customPizza3.add(Topping.valueOf("Mushrooms"));
        customPizza3.add(Topping.valueOf("Pineapple"));
        assertEquals("[MUSHROOMS, PINEAPPLE]",
                customPizza3.getToppings().toString());
    }

    // Toppings.add(List<Topping> toppings)

    @Test
    public void addToppingsLessThanMaximumAllowableToppingsTest()
            throws TooManyToppingsException {
        customPizza1.add(toppings1);
        assertEquals("[BACON, CHICKEN, MUSHROOMS]",
                customPizza1.getToppings().toString());
    }

    @Test
    public void addToppingsEqualToMaximumAllowableToppingsTest()
            throws TooManyToppingsException {
        customPizza2.add(toppings2);
        assertEquals("[BACON, CHICKEN, MUSHROOMS, OLIVES, ONIONS]",
                customPizza2.getToppings().toString());
    }

    @Test(expected = TooManyToppingsException.class)
    public void addToppingsGreaterThanMaximumAllowableToppingsTest()
            throws TooManyToppingsException {
        customPizza3.add(toppings3);
    }

    @Test
    public void removeToppingTest()
            throws TooManyToppingsException {
        customPizza1.add(Topping.valueOf("Mushrooms"));
        customPizza1.add(Topping.valueOf("Pineapple"));
        customPizza1.remove(Topping.valueOf("Pineapple"));
        assertEquals("[MUSHROOMS]",
                customPizza1.getToppings().toString());
    }

    @Test
    public void getTotalPriceTest()
            throws TooManyToppingsException {
        customPizza1.add(valueOf("Bacon"));
        customPizza1.add(valueOf("Chicken"));
        assertTrue(9.00 - customPizza1.getTotalPrice() < 0.001);
    }

    @Test
    public void getNameTest() {
        assertEquals("Custom Pizza", customPizza1.getName());
        assertEquals("Custom Pizza", customPizza2.getName());
        assertEquals("Custom Pizza", customPizza3.getName());
        assertEquals("Custom Pizza", customPizza4.getName());
        assertEquals("Custom Pizza", customPizza5.getName());
    }

    @Test
    public void setNameTest() {
        customPizza1.setName("Best Pizza");
        assertEquals("Best Pizza", customPizza1.getName());
    }

    @Test
    public void setSizeTest() {
        customPizza1.set(Bases.BaseSize.LARGE);
        assertEquals("Custom Pizza: is a 'LARGE' sized base with " +
                        "'TOMATO' sauce and 'MOZZARELLA' cheese $7.00",
                customPizza1.toString());
    }

    @Test
    public void setSauceTest() {
        customPizza1.set(Sauces.Sauce.BBQ);
        assertEquals("Custom Pizza: is a 'MEDIUM' sized base with " +
                        "'BBQ' sauce and 'MOZZARELLA' cheese $5.00",
                customPizza1.toString());
    }

    @Test
    public void setCheeseTest() {
        customPizza1.set(Cheeses.Cheese.VEGAN);
        assertEquals("Custom Pizza: is a 'MEDIUM' sized base with " +
                        "'TOMATO' sauce and 'VEGAN' cheese $5.00",
                customPizza1.toString());
    }

    @Test
    public void hashcodeEqualTest()
            throws TooManyToppingsException{
        customPizza4.add(Topping.valueOf("Bacon"));
        customPizza4.add(Topping.valueOf("Chicken"));
        customPizza5.add(Topping.valueOf("Chicken"));
        customPizza5.add(Topping.valueOf("Bacon"));
        assertEquals(customPizza4.hashCode(), customPizza5.hashCode());
    }

    @Test
    public void hashcodeNotEqualTest()
            throws TooManyToppingsException{
        customPizza4.add(Topping.valueOf("Bacon"));
        customPizza4.add(Topping.valueOf("Chicken"));
        customPizza5.add(Topping.valueOf("Bacon"));
        customPizza5.add(Topping.valueOf("Mushrooms"));
        assertNotEquals(customPizza5.hashCode(), customPizza4.hashCode());
    }

    @Test
    public void equalsEqualTest()
            throws TooManyToppingsException {
        customPizza4.add(Topping.valueOf("Bacon"));
        customPizza4.add(Topping.valueOf("Chicken"));
        customPizza5.add(Topping.valueOf("Chicken"));
        customPizza5.add(Topping.valueOf("Bacon"));
        assertEquals(customPizza4, customPizza5);
    }

    @Test
    public void equalsNotEqualTest()
            throws TooManyToppingsException{
        customPizza4.add(Topping.valueOf("Bacon"));
        customPizza4.add(Topping.valueOf("Chicken"));
        customPizza5.add(Topping.valueOf("Bacon"));
        customPizza5.add(Topping.valueOf("Mushrooms"));
        assertNotEquals(customPizza4, customPizza5);
    }

    @Test
    public void toStringTest()
            throws TooManyToppingsException {
        customPizza1.add(Topping.valueOf("Bacon"));
        customPizza1.add(Topping.valueOf("Chicken"));
        customPizza4.add(Topping.valueOf("Mushrooms"));
        customPizza4.add(Topping.valueOf("Pineapple"));
        assertEquals("Custom Pizza: is a 'MEDIUM' sized base with " +
                        "'TOMATO' sauce and 'MOZZARELLA' cheese " +
                        "- Toppings: [BACON, CHICKEN] $9.00",
                customPizza1.toString());
        assertEquals("Custom Pizza: is a 'SMALL' sized base with " +
                        "'BBQ' sauce and 'MOZZARELLA' cheese $3.00",
                customPizza2.toString());
        assertEquals("Custom Pizza: is a 'MEDIUM' sized base with " +
                        "'GARLIC' sauce and 'NONE' cheese $5.00",
                customPizza3.toString());
        assertEquals("Custom Pizza: is a 'LARGE' sized base with " +
                        "'NONE' sauce and 'VEGAN' cheese " +
                        "- Toppings: [MUSHROOMS, PINEAPPLE] $11.00",
                customPizza4.toString());
        assertEquals("Custom Pizza: is a 'LARGE' sized base with " +
                        "'NONE' sauce and 'VEGAN' cheese $7.00",
                customPizza5.toString());
    }
}