package NotePad;

import javax.swing.*;

public class Scroll extends JScrollPane {

    private final String name;

    private  final JTextArea text;

    public Scroll(JTextArea text, String name) {
        super(text);
        this.text = text;
        this.name = name;

    }

    public String getText(){
        return text.getText();
    }

}
