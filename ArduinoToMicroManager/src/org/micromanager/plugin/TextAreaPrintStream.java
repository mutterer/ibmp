/**
 * Class TextAreaPrintStream
 * extends PrintStream.
 * A custom made PrintStream which overrides methods println(String)
 * and print(String).
 * Thus, when the out stream is set as this PrintStream (with System.setOut
 * method), all calls to System.out.println(String) or System.out.print(String)
 * will result in an output stream of characters in the JTextArea given as an
 * argument of the constructor of the class.
 **/

package src.org.micromanager.plugin;

import java.io.*;
import javax.swing.*;

public class TextAreaPrintStream extends PrintStream {

    //The JTextArea to wich the output stream will be redirected.
    private JTextArea textArea;

    public TextAreaPrintStream(JTextArea area, OutputStream out) {
	super(out);
	textArea = area;
    }

    public void println(String string) {
	textArea.append(string+"\n");
    }

    public void print(String string) {
	textArea.append(string);
    }
}