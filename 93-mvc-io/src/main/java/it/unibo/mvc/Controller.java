package it.unibo.mvc;

import java.util.List;

/**
 * This interface represents a controller in the MVC pattern,
 * responsible for managing the flow of data between the model and the view.
 */
public interface Controller {

    /**
     * A method to set the next string to print.
     * 
     * @param string the input string.
     * @throws NullPointerException throws exception.
     */
    void setNextString(String string) throws NullPointerException;

    /**
     * A method to get the next string to print.
     * 
     * @return string
     */
    String getNextString();

    /**
     * A method to get the all strings printed.
     * 
     * @return list of all strings
     */
    List<String> historyStrings();

    /**
     * A method that print the current string.
     * 
     * @throws IllegalStateException if the string is not set 
     */
    void currentString() throws IllegalStateException;

}
