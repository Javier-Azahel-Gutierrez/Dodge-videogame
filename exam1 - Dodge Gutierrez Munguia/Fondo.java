import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fondo extends JLabel {
    // atributos
    private String url;
    private ImageIcon icon;

    public Fondo(String url) {
        this.url = url;

        // Icon
        icon = new ImageIcon(this.getClass().getResource(url));

        setIcon(icon);
    }

}
