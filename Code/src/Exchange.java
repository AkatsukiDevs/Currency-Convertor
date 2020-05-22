import java.io.*;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Exchange extends JFrame {
    private JButton button1 = new JButton("Convert");
    private JButton button2 = new JButton("ClickMe");
    private JRadioButton radiobutton1 = new JRadioButton("Ruble");
    private JRadioButton radiobutton2 = new JRadioButton("Dollar");
    private JRadioButton radiobutton3 = new JRadioButton("Yen");
    private JRadioButton radiobutton4 = new JRadioButton("Tenge");
    private JTextField input = new JTextField("", 5);
    private JLabel text1 = new JLabel("");
    private JLabel text2 = new JLabel("Enter soms");


    public Exchange() {
        super("Convertor");
        this.setBounds(150, 150, 300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 3, 3, 3));
        container.add(this.text1);
        container.add(this.text2);
        container.add(this.input);
        ButtonGroup group = new ButtonGroup();
        group.add(this.radiobutton1);
        group.add(this.radiobutton2);
        group.add(this.radiobutton3);
        group.add(this.radiobutton4);
        container.add(this.radiobutton1);
        this.radiobutton1.setSelected(true);
        container.add(this.radiobutton2);
        container.add(this.radiobutton3);
        container.add(this.radiobutton4);
        this.button1.addActionListener(new Exchange.ButtonEventListener());
        container.add(this.button1);
        this.button2.addActionListener(new Exchange.ButtonEventListener2());
        container.add(this.button2);
    }

    class ButtonEventListener2 implements ActionListener {
        ButtonEventListener2() {
        }

        public void actionPerformed(ActionEvent e) {

            try {
                File myObj = new File("ClickMe.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String message = myReader.nextLine();
                    System.out.println(message);
                    JOptionPane.showMessageDialog((Component)null, message, "ClickMe", -1);

                }
                myReader.close();
            } catch (FileNotFoundException e3) {
                System.out.println("error");
                e3.printStackTrace();
            }


        }
    }

    class ButtonEventListener implements ActionListener {
        ButtonEventListener() {
        }
        boolean isNumber(String s)
        {
            for (int i = 0; i < s.length(); i++)
                if (!Character.isDigit(s.charAt(i)))
                    return false;

            return true;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                File myObj = new File("output.txt");
                String message = " Exchange will be ";
                String i = Exchange.this.input.getText();

                if(!isNumber(i)){
                    message="Incorrect input!";

                }
                else if(i.equals(" ")){
                    message="Fill in the blank!";}
                else {
                    double input = Integer.parseInt(i);

                    String str;
                    if (Exchange.this.radiobutton1.isSelected()) {
                        input *= 0.95;
                        str = String.valueOf(input);
                        message = message + " " + str + " rubles.";
                    }

                    if (Exchange.this.radiobutton3.isSelected()) {
                        input *= 1.43;
                        str = String.valueOf(input);
                        message = message + " " + str + " yens.";
                    }

                    if (Exchange.this.radiobutton2.isSelected()) {
                        input *= 0.013;
                        str = String.valueOf(input);
                        message = message + " " + str + " dollars.";
                    }

                    if (Exchange.this.radiobutton4.isSelected()) {
                        input *= 5.52;
                        str = String.valueOf(input);
                        message = message + " " + str + " tenges.";
                    }
                }


                JOptionPane.showMessageDialog((Component)null, message, "Convert", -1);
                if (myObj.createNewFile()) {
                    PrintWriter printwr = new PrintWriter(myObj);
                    printwr.println(message);
                    printwr.close();
                }
                else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e2) {
                System.out.println("error.");
                e2.printStackTrace();
            }

        }
    }
}
