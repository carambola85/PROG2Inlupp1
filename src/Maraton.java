

import static javax.swing.JOptionPane.*; 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class Window extends JFrame{
    static ArrayList<Runner> runners = new ArrayList<Runner>();
    static ArrayList<Integer> numberlist = new ArrayList<Integer>();
    static int currentSNr = 0;
    
    JRadioButton startnr, name, age, time;
    JButton bnew, bshow, btime;
    JTextArea text;
    Window(){   
        super("Maratonlöpare");
     
     
     setLayout(new BorderLayout());
     
     // NORTH
     JPanel north = new JPanel();
     JLabel titel = new JLabel("DSV Maraton");
     north.add(titel);
     add(north, BorderLayout.NORTH);
     
     // CENTER
     text = new JTextArea();
     add(text, BorderLayout.CENTER);
     JScrollPane scroll = new JScrollPane(text);
     add(scroll);
     
     // EAST
     JPanel east = new JPanel();
     east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
     JLabel sort = new JLabel("Sortering");
     
     // RadioButtons
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
    
    // SOUTH
    JPanel south = new JPanel();
    bnew = new JButton("Ny");
    bshow = new JButton("Visa");
    btime = new JButton("Tid");    
    
    bnew.addActionListener(new BnewListener());
    bshow.addActionListener(new BshowListener());
    btime.addActionListener(new BtimeListener());
    south.add(bnew);
    south.add(bshow);
    south.add(btime);
    add(south, BorderLayout.SOUTH);
    
    setVisible(true);
    setSize(500,600);
    setLocation(400,130);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
       }
    
    class NewRunnerForm extends JPanel{
        private JTextField nameField, countryField, ageField;
        
        public NewRunnerForm(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JPanel rad0 = new JPanel();
            rad0.add(new JLabel ("Startnummer:"));
            rad0.add(new JLabel(Integer.toString(numberlist.size() +1 )));
            add(rad0);
            
	    JPanel rad1 = new JPanel();
	    rad1.add(new JLabel("Namn:"));
	    nameField = new JTextField(10);
	    rad1.add(nameField);
	    add(rad1);
            
            JPanel rad2 = new JPanel();
	    rad2.add(new JLabel("Land:"));
	    countryField = new JTextField(10);
	    rad2.add(countryField);
	    add(rad2);
            
            JPanel rad3 = new JPanel();
	    rad3.add(new JLabel("Ålder:"));
	    ageField = new JTextField(10);
	    rad3.add(ageField);
	    add(rad3);
        }
        
        
        
        public String getName(){
            return nameField.getText();
        }
        public String getCountry(){
            return countryField.getText();
        }
        public int getAge(){
            return Integer.parseInt(ageField.getText());
        }
        
    } 
    
    class NewTimeForm extends JPanel{
        private JTextField startnr, time;
        
        public NewTimeForm() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            JPanel rad1 = new JPanel();
            JPanel rad2 = new JPanel();
            JLabel rad1text = new JLabel("Startnummer: ");
            JLabel rad2text = new JLabel("Tid: ");
            startnr = new JTextField(10);
            time = new JTextField(10);
            
            rad1.add(rad1text);
            rad1.add(startnr);
            rad2.add(rad2text);
            rad2.add(time);
            
            add(rad1);
            add(rad2);
    }
    }
    
            
    class BnewListener implements ActionListener{
        public void actionPerformed(ActionEvent ave){
           NewRunnerForm form = new NewRunnerForm();
           
           while(true){
               try{
               int svar = showConfirmDialog(Window.this, form);
               if(svar != OK_OPTION)                   
                    return;
               
               String name = form.getName();
               String country = form.getCountry();
               int age = form.getAge();
               if(!name.equals("") && !country.equals("")){
                    currentSNr++;
                    Runner r = new Runner(name, country, age, currentSNr);
                    runners.add(r);
                    numberlist.add(currentSNr);
                    return;
               }
               else{
                   showMessageDialog(Window.this, "Du måste fylla i alla fält");
               }
               }catch(NumberFormatException e){
                   showMessageDialog(Window.this, "Du har fyllt i fel någonstans. Var god rätta till detta.");
               }
           }
           
        }
        
    }
    class BshowListener implements ActionListener{
        public void actionPerformed(ActionEvent ave){
            text.setText("");
            for(Runner r : runners){
                
                text.append(r.getName()+ " " + r.getCountry() + " " + r.getAge() + "\n");
            }
        }
    }
    class BtimeListener implements ActionListener{
        public void actionPerformed(ActionEvent ave){
           NewTimeForm form = new NewTimeForm();
           
           
           int svar = showConfirmDialog(Window.this, form);
        }
    }
    public void addRunners(){
        
    }
    
    public static void main(String[]args){
    new Window();
    
    }
}
