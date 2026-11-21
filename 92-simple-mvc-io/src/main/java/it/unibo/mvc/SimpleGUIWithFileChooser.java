package it.unibo.mvc;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    final JFrame frame = new JFrame("My Second Graphical Interface");
    private static final int PROPORTION = 20;

    public SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel canvas1 = new JPanel();
        frame.add(canvas1);
        canvas1.setLayout(new BorderLayout());
        final JPanel canvas2 = new JPanel();
        canvas2.setLayout(new BorderLayout());
        canvas1.add(canvas2,BorderLayout.NORTH);
        final JTextField textField = new JTextField(PROPORTION);
        textField.setText(controller.getPath());
        //non è modificabile il file
        textField.setEditable(false);
        canvas2.add(textField, BorderLayout.CENTER);
        final JButton browse = new JButton("Browse...");
        canvas2.add(browse, BorderLayout.EAST);
        final JButton save = new JButton("SAVE");
        canvas1.add(save,BorderLayout.SOUTH);
        save.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(final ActionEvent event) {
                try {
                    controller.save(textField.getText());
                } catch(IOException e) {
                    JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); // NOPMD: allowed as this is just an exercise
                }
            }
        });
        browse.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(final ActionEvent event) {
                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile( controller.getCurrentFile());
                final int result = fileChooser.showSaveDialog(frame);
                switch (result) {
                case JFileChooser.APPROVE_OPTION:
                    final File newDest = fileChooser.getSelectedFile();
                    controller.setDestination(newDest);
                    textField.setText(newDest.getPath());
                    break;
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, result, "Meh!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used for starting the application.
     */
    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main() {
        new SimpleGUIWithFileChooser(new Controller()).display();
    }
}
