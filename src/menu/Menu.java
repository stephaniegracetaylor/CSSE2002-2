package menu;

import pizza.Pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Singleton class which defines a menu that contains items that can be ordered
 * from.
 * <p>
 * All classes that implement MenuItem must be registered with this Menu, which
 * will allow them to be ordered in the simulation.
 * <p>
 * A Menu starts as empty but will grow over the programs run time.
 */
public class Menu {

    /**
     * singleton instance of menu
     */
    private static Menu menu;

    /**
     * list of items on the menu
     */
    private List<MenuItem> menuItems;

    /**
     * Constructs menu which defines a menu that contains menu pizzas from the
     * given pizza menu file.
     */
    private Menu() {
        menuItems = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the menu.
     * @return singleton instance of the menu
     */
    public static Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    /**
     * Returns the items that have been registered with the menu.
     * <p>
     * Adding or removing elements from the returned list should NOT affect the
     * original list.
     * @return items on the menu
     */
    public List<MenuItem> getItems() {
        return new ArrayList<>(menuItems);
    }

    /**
     * Registers a menu item with this menu.
     * <p>
     * The menu will only register items that it has not seen before.
     * <p>
     * The menu has not seen an item if the menu does not contain any item
     * (existingItem) such that Objects.equals(item, existingItem) == true.
     * @param item menu item to be registered to the menu
     */
    public void registerMenuItem(MenuItem item) {
        if (!isExistingMenuItem(item)) {
            menuItems.add(item);
        }
    }

    /**
     * Returns true if the menu item is an existing menu item, otherwise, false
     * @param item menu item to confirm if existing menu item
     * @return boolean for if menu item is an existing menu item
     */
    private boolean isExistingMenuItem(MenuItem item) {
        boolean isExistingMenuItem = false;

        if (getItems() != null) {
            for (MenuItem menuItem : getItems()) {
                if (item.equals(menuItem)) {
                    isExistingMenuItem = true;
                    break;
                }
            }
        }

        return isExistingMenuItem;
    }

    /**
     * Returns a menu item from the list of loaded menu items.
     * @param index index of the item in the list
     * @return item that has been found
     * @throws IndexOutOfBoundsException if index does not exist or array is null
     */
    public MenuItem get(int index)
            throws IndexOutOfBoundsException {
        if (getItems() == null
                | index < 0
                | index >= getItems().size()) {
            throw new IndexOutOfBoundsException();
        } else {
            return getItems().get(index);
        }
    }

    /**
     * Removes all loaded items from the Menu such that getItems() will return a
     * list of size 0.
     */
    public void clear() {
        menuItems = new ArrayList<>();
    }
}