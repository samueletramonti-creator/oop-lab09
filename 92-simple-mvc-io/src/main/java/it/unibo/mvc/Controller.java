package it.unibo.mvc;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.OutputStream; 

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private File currentFile;
    public static final String SEP = File.separator;
    static String defaultName=System.getProperty (" user.home ") + SEP + "output.txt";

    public Controller(File currentFile) {
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
}
