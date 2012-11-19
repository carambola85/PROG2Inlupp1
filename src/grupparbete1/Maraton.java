/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grupparbete1;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Anders
 */
class Maraton extends JFrame {

    Maraton(){
        super("Hej!");
        setLayout(new FlowLayout());
        JLabel lab = new JLabel("Namn:");
        add(lab);
        JTextField fält = new JTextField(8);
        add(fält);
        JButton but = new JButton("Hälsa");
        add(but);
        JLabel häls = new JLabel("Hej");
        add(häls);
        setSize(230, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Maraton();
    }
}
