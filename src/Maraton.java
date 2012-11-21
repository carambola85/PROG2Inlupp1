
import java.util.List;
import static javax.swing.JOptionPane.*; 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class Window extends JFrame{
    
    static CompareSNr compS = new CompareSNr();
    static CompareAge compA = new CompareAge();
    static CompareName compN = new CompareName();
    static CompareTime compT = new CompareTime();
    
    static ArrayList<Runner> runnersSNr = new ArrayList<Runner>();
    static ArrayList<Runner> runnersName = new ArrayList<Runner>();
    static ArrayList<Runner> runnersAge = new ArrayList<Runner>();
    static ArrayList<Runner> runnersTime = new ArrayList<Runner>();
    static int currentSNr = 1;
    
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
     text.setEditable(false);
     scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
     
     // EAST
     JPanel east = new JPanel();
     east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
     JLabel sort = new JLabel("Sortering");
     
     // RadioButtons
     ArrayList<JRadioButton> JRbuttons = new ArrayList<JRadioButton>();
     
     startnr = new JRadioButton("Startnr");
     name = new JRadioButton("Namn");
     age = new JRadioButton("Ålder");
     time = new JRadioButton("Tid");
    
     JRbuttons.add(startnr);
     JRbuttons.add(name);
     JRbuttons.add(age);
     JRbuttons.add(time);
     
     east.add(sort);
     east.add(startnr);
     east.add(name);
     east.add(time);
     east.add(age);
     add(east, BorderLayout.EAST);
     startnr.setSelected(true);
     
    ButtonGroup bg = new ButtonGroup();
    bg.add(startnr);
    bg.add(name);
    bg.add(time);
    bg.add(age);
    startnr.setName("startnummer");
    System.out.println(startnr.getName());
    // ;SOUTH
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
    
    void addBinary(List<Runner> lista, Runner r, Comparator comp){
        int pos = Collections.binarySearch(lista, r, comp);
        if (pos<0){
                pos=-pos-1;
		lista.add(pos,r);
                
        }
    }
    
    
    class NewRunnerForm extends JPanel{
        private JTextField nameField, countryField, ageField;
        
        public NewRunnerForm(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
           
            JPanel rad0 = new JPanel();
            rad0.add(new JLabel ("Startnummer:"));
            rad0.add(new JLabel(Integer.toString(currentSNr)));
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
        public int getSNr(){
            return Integer.parseInt(startnr.getText());
    }
        public double getTime(){
            return Double.parseDouble(time.getText());
        }
    }
    
            
    class BnewListener implements ActionListener{
        public void actionPerformed(ActionEvent ave){
           NewRunnerForm form = new NewRunnerForm();
           
           while(true){
               try{
               int svar = showConfirmDialog(Window.this, form, "Skapa ny löpare", JOptionPane.YES_NO_CANCEL_OPTION);
               
               if(svar != OK_OPTION)                   
                    return;
               
               String name = form.getName();
               String country = form.getCountry();
               int age = form.getAge();
               if(!name.equals("") && !country.equals("")){
                    Runner r = new Runner(name, country, age, currentSNr++);
                    addBinary(runnersAge, r, compA);
                    addBinary(runnersName, r, compN);
                    runnersSNr.add(r);
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
        List<Runner> lst = runnersSNr;
        public void actionPerformed(ActionEvent ave){
            text.setText("");
            if(startnr.isSelected())
                lst = runnersSNr;
            else if(name.isSelected())
                lst = runnersName;
            else if (age.isSelected())
                lst = runnersAge;
            else if (time.isSelected())
                lst = runnersTime;
            for(Runner r : lst){
                
                text.append(r.getSNr() + " " + r.getName()+ " " + r.getCountry() + " " + r.getAge() + " " + r.getTime() + "\n");
            }
            
        }
    }
    class BtimeListener implements ActionListener{
        public void actionPerformed(ActionEvent ave){
           NewTimeForm form = new NewTimeForm();
           
           while(true){
               try{
               int svar = showConfirmDialog(Window.this, form, "Tid för målgång", JOptionPane.OK_CANCEL_OPTION);
               if(svar != OK_OPTION)                   
                    return;
               
               double rTime = form.getTime();
               int rSNr = form.getSNr();
               
               if(rSNr <= runnersSNr.size()){
                    for (Runner r : runnersSNr){
                        if (r.getSNr() == rSNr){
                            r.setTime(rTime);
                            if (r.getHasTime()){
                                Collections.sort(runnersTime, compT);
                                return;
                            }else{
                                addBinary(runnersTime, r, compT);
                                r.setHasTime(true);
                            }
                            
                        }
                    }
               
               }
               else{
                   showMessageDialog(Window.this, "Du måste ange ett giltigt startnummer. Antalet registrerade tävlande är " + runnersSNr.size() + ".");
               }
               return;
               }catch(NumberFormatException e){
                   showMessageDialog(Window.this, "Du måste skriva siffror.");
               }
        }
    }
    }
   
    
    public static void main(String[]args){
    new Window();
    
    }
};

class CompareSNr implements Comparator<Runner>{
    public int compare(Runner r1, Runner r2){
        return r1.getSNr() - r2.getSNr();
    }
};

class CompareAge implements Comparator<Runner>{
    public int compare(Runner r1, Runner r2){
        return r1.getAge() - r2.getAge();
    }
}
class CompareTime implements Comparator<Runner>{
    public int compare(Runner r1, Runner r2){
        int result = 0;
        if(r1.getTime() < r2.getTime())
            result = -1;
        else if (r1.getTime() > r2.getTime())
            result = 1;
        else
            result = 0;
        return result;
    }
}
class CompareName implements Comparator<Runner>{
    public int compare(Runner r1, Runner r2){
        String n1 = r1.getName();
        String n2 = r2.getName();
        return n1.compareTo(n2);
    }
}
