package pizza.ingredients;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static pizza.ingredients.Topping.*;

public class ToppingTest {

    @Before
    public void setUp()
            throws IllegalArgumentException {
        resetToppings();
        createTopping("Bacon", false);
        createTopping("Chicken", false);
        createTopping("Prawn", false);
        createTopping("Mushrooms", true);
        createTopping("Pineapple", true);
        createTopping("Olives", true);
        createTopping("Onions", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createToppingNullNameTest()
            throws IllegalArgumentException {
        createTopping(null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createToppingEmptyNameTest()
            throws IllegalArgumentException {
        createTopping("", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createToppingExistingNameTest()
            throws IllegalArgumentException {
        createTopping("Bacon", false);
    }

    @Test
    public void createToppingTest() {
       assertEquals("[BACON, CHICKEN, PRAWN, MUSHROOMS, PINEAPPLE, "
                       + "OLIVES, ONIONS]",
               Arrays.toString(values()));
    }

    @Test
    public void valuesTest() {
        assertEquals("[BACON, CHICKEN, PRAWN, MUSHROOMS, PINEAPPLE, "
                        + "OLIVES, ONIONS]",
                Arrays.toString(values()));
    }

    @Test(expected = NullPointerException.class)
    public void valueOfNullNameTest() {
        valueOf(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void valueOfNonExistingToppingTest() {
        valueOf("Chillies");
    }

    @Test
    public void valueOfTest() {
        assertEquals("BACON", valueOf("BACON").toString());
        assertEquals("BACON", valueOf("bacon").toString());
        assertEquals("BACON", valueOf("Bacon").toString());
        assertEquals("BACON", valueOf("baCON").toString());
    }

    @Test
    public void isVeganTest() {
        // non-vegan toppings
        assertFalse(valueOf("Bacon").isVegan());
        assertFalse(valueOf("Chicken").isVegan());
        assertFalse(valueOf("Prawn").isVegan());
        // vegan toppings
        assertTrue(valueOf("Mushrooms").isVegan());
        assertTrue(valueOf("Pineapple").isVegan());
        assertTrue(valueOf("Olives").isVegan());
        assertTrue(valueOf("Onions").isVegan());
    }

    @Test
    public void resetToppingsTest() {
        assertEquals("[BACON, CHICKEN, PRAWN, MUSHROOMS, PINEAPPLE, "
                        + "OLIVES, ONIONS]",
                Arrays.toString(values()));
        resetToppings();
        assertEquals("[]",
                Arrays.toString(values()));
        createTopping("Chillies", true);
        assertEquals("[CHILLIES]",
                Arrays.toString(values()));
        resetToppings();
        assertEquals("[]",
                Arrays.toString(values()));
    }

    @Test
    public void toStringTest() {
        assertEquals("BACON", valueOf("Bacon").toString());
        assertEquals("CHICKEN", valueOf("Chicken").toString());
        assertEquals("PRAWN", valueOf("Prawn").toString());
        assertEquals("MUSHROOMS", valueOf("Mushrooms").toString());
        assertEquals("PINEAPPLE", valueOf("Pineapple").toString());
        assertEquals("OLIVES", valueOf("Olives").toString());
        assertEquals("ONIONS", valueOf("Onions").toString());
    }
}