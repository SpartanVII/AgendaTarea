package Vista.clasesPersonalizadas;

import javax.swing.*;

public class Miventana extends JFrame{

    public Miventana() {
        super("Agenda");

        ImageIcon icon =new ImageIcon("src/icono.png");
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(610,700);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
