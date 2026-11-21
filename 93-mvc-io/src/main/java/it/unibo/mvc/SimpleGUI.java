package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {

    private static JFrame frame = new JFrame();
    private static final int PROPORTION = 20;
    private Controller controller;

    /**
     * The constructor.
     * @param controller
     */

    public SimpleGUI(final SimpleController controller) {
        this.controller = controller;
        final JPanel canvas = new JPanel();
        frame.add(canvas);
        canvas.setLayout(new BorderLayout());
        final JTextField textField = new JTextField();
        canvas.add(textField, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);
        final JPanel south = new JPanel();
        canvas.add(south,BorderLayout.SOUTH);
        final JButton printString = new JButton("Print");
        south.add(printString, BorderLayout.SOUTH);
        final JButton showHistory = new JButton("Show history");
        south.add(showHistory, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        printString.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(final ActionEvent event) {
                SimpleGUI.this.controller.setNextString(textField.getText());
                SimpleGUI.this.controller.currentString();
            }
        });
        showHistory.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(final ActionEvent event) {
                final StringBuilder text = new StringBuilder();
                final List<String> history = SimpleGUI.this.controller.historyStrings();
                for (final String print: history) {
                    text.append(print).append('\n');
                }
                if (!history.isEmpty()) {
                    text.deleteCharAt(text.length() - 1);
                }
                textArea.setText(text.toString());
            }
        });
    }

    /**
     * The method to start the application.
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

    /**
     * The method for the creation of application.
     */
    static void main() {
        new SimpleGUI(new SimpleController()).display();
    }
}
