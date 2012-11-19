


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Window extends JFrame{
    JRadioButton startnr, name, age, time;
    JButton bnew, bshow, btime;
    Window(){   
        super("Maratonlöpare");
     setLayout(new BorderLayout());
     
     //Top
     JPanel north = new JPanel();
     JLabel titel = new JLabel("DSV Maraton");
     north.add(titel);
     add(north, BorderLayout.NORTH);
     
     //Center
     JTextArea text = new JTextArea();
     add(text, BorderLayout.CENTER);
     
     //Höger
     
     
     JPanel east = new JPanel();
     east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
     JLabel sort = new JLabel("Sortering");
     
     
     startnr = new JRadioButton("Startnr");
     name = new JRadioButton("Namn");
     age = new JRadioButton("Ålder");
     time = new JRadioButton("Tid");
     east.add(sort);
     east.add(startnr);
     east.add(name);
     east.add(time);
     east.add(age);
     add(east, BorderLayout.EAST);
     
    ButtonGroup bg = new ButtonGroup();
    bg.add(startnr);
    bg.add(name);
    bg.add(time);
    bg.add(age);
    
    JPanel south = new JPanel();
    bnew = new JButton("Ny");
    bshow = new JButton("Visa");
    btime = new JButton("Tid");    
    
    south.add(bnew);
    south.add(bshow);
    south.add(btime);
    add(south, BorderLayout.SOUTH);
    
        setVisible(true);
        setSize(500,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[]args){
    new Window();
    }
}
