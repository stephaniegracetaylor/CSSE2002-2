package io;

import exceptions.PizzaFormatException;
import exceptions.TooManyToppingsException;
import menu.Menu;
import pizza.MenuPizza;
import pizza.ingredients.Topping;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static pizza.ingredients.Bases.BaseSize.MEDIUM;
import static pizza.ingredients.Cheeses.Cheese.MOZZARELLA;
import static pizza.ingredients.Sauces.Sauce.TOMATO;
import static pizza.ingredients.Topping.createTopping;

/**
 * Class to provide management for the loading, saving and parsing of text data
 * retrieved from the PizzaMenu.txt file, stored in the assets package.
 * <p>
 * The loaded file should be called "PizzaMenu.txt".
 * <p>
 * The save function may be revealed later by the GUI team, should this feature
 * be required.
 */
public class MenuLoader {

    /**
     * Provides folder location for text-file PizzaMenu.txt, this can be later
     * improved to search for the file instead of hard coding.
     * <p>
     * This path is,
     *     "./src/assets/".
     */
    public static final String PATH = "./src/assets/";

    /**
     * name of the pizza menu
     */
    private static final String NAME = "PizzaMenu";

    /**
     * number of menu pizzas according to the first line of the pizza menu file
     */
    private static int numberOfPizzas;

    /**
     * Inherited default constructor, not used in this class.
     */
    public MenuLoader() {}

    /**
     * Creates a BufferedReader, utilizing a FileReader to be parsed by the
     * getMenu method.
     * <p>
     * The file to be loaded is stored within a package called assets under the
     * filename PizzaMenu.txt. This is the supplied file name, as other
     * filenames can be used depending on the 'menu-specials' that can be
     * promoted from time to time.
     * <p>
     * If the load method catches a FileNotFoundException, PizzaFormatException,
     * TooManyToppingsException, IndexOutOfBoundsException or a IOException the
     * application should exit with the values 1, 2, 4, 5 and 6 respectively.
     * <p>
     * Given the importance of the menu, within the confines of the pizza
     * company, if the menu loading mechanism experiences a failure in loading
     * or parsing, then the application is to exit, by providing an appropriate
     * exit integer code stored in an inner class within MenuLoader, called
     * Reason. This is not mandatory (and will not be tested) but provides a
     * developer friendly mechanism for debugging.
     * <p>
     * Here is an example for the constant variable names:
     *     COULD_NOT_OPEN_FILE = 1
     *     FILE_FORMAT_ERROR = 2
     *     TOO_MANY_TOPPINGS = 4
     *     MISSING_NUMBER_OF_PIZZAS = 5
     *     CANNOT_READ_LINE = 6
     * <p>
     * These values will assist you with the expected exit codes as defined
     * above.
     * @param filename string representing the file to be read
     * @return a parsed menu type containing the list of pizzas found in the
     * menu txt file
     */
    public static Menu load(String filename) {
        try {
            FileReader fileReader = new FileReader(PATH + filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return getMenu(bufferedReader);
        } catch (FileNotFoundException exception) {
            System.exit(Reason
                    .COULD_NOT_OPEN_FILE);
        } catch (PizzaFormatException exception) {
            System.exit(Reason
                    .FILE_FORMAT_ERROR);
        } catch (TooManyToppingsException exception) {
            System.exit(Reason
                    .TOO_MANY_TOPPINGS);
        } catch (IndexOutOfBoundsException exception) {
            System.exit(Reason
                    .MISSING_NUMBER_OF_PIZZAS);
        } catch (IOException exception) {
            System.exit(Reason
                    .CANNOT_READ_LINE);
        }
        return null;
    }

    /**
     * Inner class within menu loader, providing an appropriate exit integer
     * code.
     */
    private static class Reason {
        /**
         * FileNotFoundException exit code for application
         */
        private static final int COULD_NOT_OPEN_FILE = 1;
        /**
         * PizzaFormatException exit code for application
         */
        private static final int FILE_FORMAT_ERROR = 2;
        /**
         * TooManyToppingsException exit code for application
         */
        private static final int TOO_MANY_TOPPINGS = 4;
        /**
         * IndexOutOfBoundsException exit code for application
         */
        private static final int MISSING_NUMBER_OF_PIZZAS = 5;
        /**
         * IOException exit code for application
         */
        private static final int CANNOT_READ_LINE = 6;
    }



    /**
     * Used by the load method to manage the parsing of the loaded data.
     * @param reader buffered reader used to read file
     * @return menu item that has loaded all the pizzas from the file
     * @throws PizzaFormatException if the given reader is 'null' or empty,
     *     if the name on the first line is not 'PizzaMenu',
     *     if the space is missing after the name,
     *     if the number of pizzas cannot be parsed,
     *     if a blank line does not follow the first line,
     *     if a topping line contains an invalid topping name,
     *     if a blank line does not follow the vegan topping line,
     *     if a pizza line contains an invalid topping (such that, it was not
     *         mentioned in any topping line)
     * @throws TooManyToppingsException if a pizza line has too many toppings
     * @throws IndexOutOfBoundsException if the number of pizza lines given in
     *     the first line, does not match the number of pizza lines present in
     *     the file
     * @throws IOException if an error occurs when trying to read a line
     */
    public static Menu getMenu(BufferedReader reader)
            throws PizzaFormatException,
            TooManyToppingsException,
            IndexOutOfBoundsException,
            IOException {

        String line;
        int lineNumber = 1;

        isReaderNull(reader, lineNumber);
        isReaderEmpty(reader, lineNumber);

        while ((line = reader.readLine()) != null) {
            if (lineNumber == 1) {
                getMenuNameFromLine(line, lineNumber);
                getSpaceAfterMenuNameFromLine(line, lineNumber);
                getNumberOfPizzasFromLine(line, lineNumber);
            } else if (lineNumber == 2
                    || lineNumber == 5) {
                getBlankLine(line, lineNumber);
            } else if (lineNumber == 3
                    || lineNumber == 4) {
                getToppingsFromLine(line, lineNumber);
            } else if (lineNumber > 5) {
                getPizzaFromLine(line, lineNumber);
            }
            lineNumber++;
        }
        isNumberOfPizzasEqualToNumberOfPizzaLines(lineNumber);
        return Menu.getInstance();
    }

    /**
     * Checks if the given ready is null.
     * @param reader buffered reader used to read file
     * @param lineNumber current line number of the given reader
     * @throws PizzaFormatException if the given reader is null
     */
    private static void isReaderNull(BufferedReader reader,
                                     int lineNumber)
            throws PizzaFormatException {
        if (reader == null) {
            throw new PizzaFormatException(
                    "GIVEN READER IS NULL", lineNumber);
        }
    }

    /**
     * Checks if the given ready is empty.
     * @param reader buffered reader used to read file
     * @param lineNumber current line number of the given reader
     * @throws PizzaFormatException if the given reader is empty
     */
    private static void isReaderEmpty(BufferedReader reader,
                                     int lineNumber)
            throws PizzaFormatException,
            IOException {
        if (!reader.ready()) {
            throw new PizzaFormatException(
                    "GIVEN READER IS EMPTY",
                    lineNumber);
        }
    }

    /**
     * Checks if the name on the first line is 'PizzaMenu'.
     * @param line current line of the buffered reader
     * @param lineNumber current line number of the buffered reader
     * @throws PizzaFormatException if the name on the first line is not
     *     'PizzaMenu'
     */
    private static void getMenuNameFromLine(String line,
                                            int lineNumber)
            throws PizzaFormatException {
        int beginIndex = 0;
        int endIndex = NAME.length();

        if (!line.substring(beginIndex, endIndex).equals(NAME)) {
            throw new PizzaFormatException(
                    "NAME ON THE FIRST LINE IS NOT 'PizzaMenu'",
                    lineNumber);
        }
    }

    /**
     * Checks if the space is missing after the name.
     * @param line current line of the buffered reader
     * @param lineNumber current line number of the buffered reader
     * @throws PizzaFormatException if the space is missing after the name
     */
    private static void getSpaceAfterMenuNameFromLine(String line,
                                                      int lineNumber)
            throws PizzaFormatException {
        if (line.charAt(NAME.length()) != ' ') {
            throw new PizzaFormatException(
                    "SPACE IS MISSING AFTER NAME",
                    lineNumber);
        }
    }

    /**
     * Checks if the number of menu pizzas can be parsed and stores the number
     * of menu pizzas accordingly.
     * @param line current line of the buffered reader
     * @param lineNumber current line number of the buffered reader
     * @throws PizzaFormatException if the number of pizzas cannot be parsed
     */
    private static void getNumberOfPizzasFromLine(String line,
                                                  int lineNumber)
            throws PizzaFormatException {
        try {
            numberOfPizzas = parseInt(line.trim().split(" ")[1]);
        } catch (NumberFormatException exception) {
            throw new PizzaFormatException(
                    "NUMBER OF PIZZAS CANNOT BE PARSED",
                    lineNumber);
        }
    }

    /**
     * Returns the number of menu pizzas, according to the first line of the
     * given pizza menu file.
     * @return number of menu pizzas
     */
    private static int getNumberOfPizzas() {
        return numberOfPizzas;
    }

    /**
     * Returns the number of menu pizza lines, according to the given pizza menu
     * file, where,
     *     number of pizza lines = line number,
     *         less, one,
     *             for final increment in while loop,
     *         less, five,
     *             for non-pizza lines above menu pizza lines.
     * @param lineNumber final line number of the buffered reader
     * @return number of menu pizza lines
     */
    private static int getNumberOfPizzaLines(int lineNumber) {
        return lineNumber - 1 - 5;
    }

    /**
     * Checks if the given line is blank or empty.
     * @param line current line of the buffered reader
     * @param lineNumber current line number of the buffered reader
     * @throws PizzaFormatException if a blank line does not follow the first
     *     line, or if a blank line does not follow the vegan topping line
     */
    private static void getBlankLine(String line,
                                     int lineNumber)
            throws PizzaFormatException {
        if (!(line.isEmpty())) {
            throw new PizzaFormatException(
                    "MISSING BLANK LINE",
                    lineNumber);
        }
    }

    /**
     * Checks if a topping line contains an invalid topping name and creates the
     * toppings accordingly.
     * @param line current line of the buffered reader
     * @param lineNumber current line number of the buffered reader
     * @throws PizzaFormatException if a topping line contains an invalid
     * topping name
     */
    private static void getToppingsFromLine(String line,
                                            int lineNumber)
            throws PizzaFormatException {
        boolean isVegan = lineNumber == 4;
        try {
            String[] toppings = line.trim().split(", ");
            for (String topping : toppings)  {
                createTopping(topping, isVegan);
            }
        } catch (IllegalArgumentException exception) {
            throw new PizzaFormatException(
                    "TOPPING LINE CONTAINS AN INVALID TOPPING NAME",
                    lineNumber);
        }
    }

    /**
     * Returns the name of the menu pizza in title format from the given line.
     * @param line current line of the buffered reader
     * @return name of the menu pizza in title format
     */
    private static String getPizzaName(String line) {
        int beginIndex = 0;
        int endIndex = line.indexOf("[") - 1;
        return toTitleFormat(line.substring(beginIndex, endIndex).trim());
    }

    /**
     * Returns the toppings of the menu pizza from the given line.
     * @param line current line of the buffered reader
     * @param lineNumber current line number of the buffered reader
     * @return list of toppings on the menu pizza
     * @throws PizzaFormatException if a pizza line contains an invalid topping,
     *     such that, it was not mentioned in any topping line
     */
    private static List<Topping> getPizzaToppings(String line,
                                                  int lineNumber)
            throws PizzaFormatException {

        List<Topping> toppingsAsList = new ArrayList<>();

        try {
            int beginIndex = line.indexOf("[") + 1;
            int endIndex = line.indexOf("]");

            String[] toppingsAsArray = line.substring(beginIndex, endIndex)
                    .trim().split(", ");

            for (String topping : toppingsAsArray) {
                toppingsAsList.add(Topping.valueOf(topping));
            }
        } catch (NullPointerException
                 | IllegalArgumentException exception) {
            throw new PizzaFormatException(
                    "PIZZA LINE CONTAINS AN INVALID TOPPING NAME, SUCH THAT, "
                            + "IT WAS NOT MENTIONED IN ANY TOPPING LINE",
                    lineNumber);
        }
        return toppingsAsList;
    }

    /**
     * Returns the given title in title format i.e. 'This Is Title Format'
     * @param title title to be returned in title format
     * @return title in title format
     */
    private static String toTitleFormat(String title) {
        StringBuilder toTitleFormat = new StringBuilder();

        List<String> words = asList(title.trim().split(" ", 0));

        for (String word : words) {
            for (int index = 0; index < word.length(); index++) {
                if (index == 0) {
                    toTitleFormat.append(
                            toUpperCase(word.charAt(index)));
                } else {
                    toTitleFormat.append(
                            toLowerCase(word.charAt(index)));
                }
            }
            if (words.indexOf(word) != words.size() - 1) {
                toTitleFormat.append(" ");
            }
        }
        return toTitleFormat.toString();
    }

    /**
     * Creates a new menu pizza with the given name and toppings from a line.
     * @param line current line of the buffered reader
     * @param lineNumber current line number of the buffered reader
     * @throws PizzaFormatException if a pizza line contains an invalid topping,
     *     such that, it was not mentioned in any topping line
     * @throws TooManyToppingsException if a menu pizza has too many toppings
     */
    private static void getPizzaFromLine(String line,
                                             int lineNumber)
            throws PizzaFormatException,
            TooManyToppingsException {
        new MenuPizza(MEDIUM,
                TOMATO,
                MOZZARELLA,
                getPizzaToppings(line, lineNumber))
                .setName(getPizzaName(line));
    }

    /**
     * Checks if the number of menu pizzas given in the first line of the pizza
     * menu file does not match the number of menu pizza lines.
     * @throws IndexOutOfBoundsException if the number of pizza lines given in
     *     the first line does not match the number of pizza lines present in
     *     the file
     */
    private static void isNumberOfPizzasEqualToNumberOfPizzaLines(
            int lineNumber)
            throws IndexOutOfBoundsException {
        if (getNumberOfPizzas() != getNumberOfPizzaLines(lineNumber)) {
            throw new IndexOutOfBoundsException("NUMBER OF PIZZAS IS NOT EQUAL "
                    + "TO NUMBER OF PIZZA LINES");
        }
    }
}