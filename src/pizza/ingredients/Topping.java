package pizza.ingredients;

import java.util.ArrayList;
import java.util.List;

/**
 * Topping class represents possible toppings that can be placed on a pizza.
 * <p>
 * Toppings are created using the createTopping(String, boolean) method which in
 * turn calls a private constructor. A Topping's name should be made uppercase
 * such that: createTopping("baCOn", false) would save the topping with a name
 * of "BACON".
 * <p>
 * Topping class is meant to be a dynamic enum such that given a name the
 * correct Topping is returned by the valueOf(String) method. Similar to
 * createTopping(String, boolean) this method will allow any case of string to
 * match. That is, an input of "bacon", "BaCON", "BACON" would all return the
 * topping with name "BACON".
 * <p>
 * Toppings that are available in the values() method will be any previously
 * created toppings.
 */
public class Topping {
    /**
     * price of any topping is $2.00
     */
    public static final double PRICE = 2.00;

    /**
     * name of the topping
     */
    private final String name;

    /**
     * boolean for vegan friendly topping
     */
    private final boolean isVegan;

    /**
     * list of the toppings that have been defined by this class
     */
    private static List<Topping> toppings = new ArrayList<>();

    /**
     * Creates a new topping with the specified name and vegan boolean state.
     * <p>
     * This new topping is not returned, but rather should be accessed by
     * valueOf(String).
     * @param name name of the topping
     * @param isVegan if the topping is vegan or non-vegan
     * @throws IllegalArgumentException if name is null or topping with that
     *     name has already been created
     */
    public static void createTopping(String name,
                                     boolean isVegan)
            throws IllegalArgumentException {
        if (name == null
                || name.isEmpty()) {
            throw new IllegalArgumentException(
                    "NAME CANNOT BE NULL OR EMPTY");
        } else if (isExistingTopping(name)) {
            throw new IllegalArgumentException(
                    "TOPPING ALREADY EXISTS WITH THE SPECIFIED NAME OF "
                            + name.toUpperCase());
        } else {
            new Topping(name, isVegan);
        }
    }

    /**
     * Constructs a topping with the specified name in uppercase and vegan
     * boolean state.
     * @param name name of this topping
     * @param isVegan if this topping is vegan or non-vegan
     */
    private Topping(String name, boolean isVegan) {
        this.name = name.toUpperCase();
        this.isVegan = isVegan;
        toppings.add(this);
    }

    /**
     * Returns the name of the topping.
     * @return name of this topping
     */
    private String getName() {
        return name;
    }

    /**
     * Returns the vegan boolean property value.
     * @return boolean for vegan friendly item
     */
    public boolean isVegan() {
        return isVegan;
    }

    /**
     * Returns the list of toppings that have been defined by this class.
     * @return list of toppings defined by this class
     */
    private static List<Topping> getToppings() {
        return toppings;
    }

    /**
     * Returns an array containing the toppings that have been defined by this
     * class, in the order they are declared.
     * @return array containing the toppings that have been defined by this
     *     class, in the order they are declared
     */
    public static Topping[] values() {
        return toppings.toArray(new Topping[0]);
    }

    /**
     * Returns the number of toppings defined by this class.
     * @return number of toppings defined by this class
     */
    private static int getNumberOfToppings() {
        return getToppings().size();
    }

    /**
     * Returns a topping that has previously been defined by this class with the
     * specified name.
     * <p>
     * The string must match exactly an identifier used to match the name of a
     * topping.
     * @param name name of the topping to be returned
     * @return topping with the specified name
     * @throws NullPointerException if the argument is null
     * @throws IllegalArgumentException if this class has no topping with the
     *     specified name
     */
    public static Topping valueOf(String name)
            throws NullPointerException,
            IllegalArgumentException {
        if (name == null) {
            throw new NullPointerException(
                    "NAME CANNOT BE NULL");
        } else if (!(isExistingTopping(name))) {
            throw new IllegalArgumentException(
                    "NO TOPPING EXISTS WITH THE SPECIFIED NAME OF "
                            + name.toUpperCase());
        } else {
            return getToppings()
                    .get(indexExistingTopping(name));
        }
    }

    /**
     * Returns true if the topping name is an existing topping name, otherwise,
     * false
     * @param toppingName topping name to confirm if existing topping name
     * @return boolean for if the topping name is an existing topping name
     */
    private static boolean isExistingTopping(String toppingName) {
        boolean isExistingTopping = false;

        for (Topping existingTopping : getToppings()) {
            if (existingTopping.getName()
                    .equals(toppingName.toUpperCase())) {
                isExistingTopping = true;
                break;
            }
        }

        return isExistingTopping;
    }

    /**
     * Returns the index of the existing topping from the toppings that have
     * been defined by this class, based on the topping name provided,
     * otherwise, returns negative one
     * @param name name of the existing topping to index
     * @return index of the existing topping
     */
    private static int indexExistingTopping(String name) {
        for (int index = 0; index < getNumberOfToppings(); index++) {
            if (getToppings().get(index).getName()
                    .equals(name.toUpperCase())) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Resets topping such that values() returns an empty Topping[].
     */
    public static void resetToppings() {
        toppings = new ArrayList<>();
    }

    /**
     * Returns the name of the topping.
     * @return name of this topping
     */
    @Override
    public String toString() {
        return getName();
    }
}
