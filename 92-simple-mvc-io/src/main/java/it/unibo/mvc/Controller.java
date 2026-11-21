package it.unibo.mvc;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets; 

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private File currentFile;
    public static final String SEP = System.getProperty("file.separator");
    static String defaultName = System.getProperty("user.home") + SEP + "output.txt";
    File dest = new File(System.getProperty("user.home") + File.separator + "output.txt");

    public Controller() {
        this.currentFile = new File(defaultName);
    }

    /**
     * This method sets the value of file for class.
     * 
     * @param file
     */
    public void setFile(final File file) {
        this.currentFile=file;
    }

    /**
     * This method get the current file.
     * 
     * @return the private value of file
     */
    public File getCurrentFile() {
        return this.currentFile;
    }

    /**
     * This method give path of file.
     * 
     * @return the string of path
     */
    public String getPath() {
        return this.currentFile.getPath();
    }

    /**
     * This method is used to write a string in file.
     * 
     * @param string
     * @throws IOException
     */
    public void putString(String string) throws IOException{
        try (final OutputStream ostream = new FileOutputStream(this.getPath());
            final OutputStream bstream = new BufferedOutputStream(ostream);
            final DataOutputStream dstream =new DataOutputStream(bstream)) {
                dstream.writeUTF(string);
            }
    }

    /**
     * Saves some text on the designed file.
     *
     * @param text
     *            the text to save
     * @throws IOException
     *             if the writing fails
     */
    public void save(final String text) throws IOException {
        try (PrintStream out = new PrintStream(this.getCurrentFile(), StandardCharsets.UTF_8)) {
            out.println(text);
        }
    }

    /**
     * Sets a new destination file.
     *
     * @param file
     *            the file where to write
     */
    public void setDestination(final File file) {
        final File parent = file.getParentFile();
        if (parent.exists()) {
            dest = file;
        } else {
            throw new IllegalArgumentException("Cannot save in a non-existing folder.");
        }
    }

    /**
     * Sets a new destination file.
     *
     * @param file
     *            the file where to write
     */
    public void setDestination(final String file) {
        setDestination(new File(file));
    }

}
