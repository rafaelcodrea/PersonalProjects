package View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class View extends JFrame {
    private JPanel panel;
    private JButton addBtn;
    private JButton multiplyBtn;
    private JTextField polynomial1Tf;
    private JTextField polynomial2Tf;
    private JLabel jlabel1;
    private JLabel jlabel2;
    private JLabel jlabel3;
    private JTextField polynomialRez;
    private JLabel jlabel4;
    private JButton substractionBtn;
    private JButton divBtn;
    private JButton derivBtn;
    private JButton integrBtn;


    public View() {

        panel = new JPanel();
        addBtn = new JButton("+");
        multiplyBtn = new JButton("*");
        polynomial1Tf = new JTextField(5);
        polynomial2Tf = new JTextField(5);
        jlabel1 = new JLabel("Calculator de polinoame");
        jlabel2 = new JLabel("Primul polinom:");
        jlabel3 = new JLabel("Al doilea polinom:");
        polynomialRez = new JTextField(5);
        jlabel4 = new JLabel("Rezultat:");
        substractionBtn = new JButton("-");
        divBtn = new JButton("/");
        derivBtn = new JButton("DERIVARE");
        integrBtn = new JButton("INTEGRARE");

        setPreferredSize(new Dimension(900, 500));
        panel.setLayout(null);



        panel.add(addBtn);
        panel.add(multiplyBtn);
        panel.add(polynomial1Tf);
        panel.add(polynomial2Tf);
        panel.add(jlabel1);
        panel.add(jlabel2);
        panel.add(jlabel3);
        panel.add(polynomialRez);
        panel.add(jlabel4);
        panel.add(substractionBtn);
        panel.add(divBtn);
        panel.add(derivBtn);
        panel.add(integrBtn);



        addBtn.setBounds(170, 280, 140, 45);
        multiplyBtn.setBounds(170, 345, 140, 45);
        polynomial1Tf.setBounds(355, 70, 210, 45);
        polynomial2Tf.setBounds(355, 125, 210, 45);
        jlabel1.setBounds(285, 0, 175, 55);
        jlabel2.setBounds(160, 75, 160, 35);
        jlabel3.setBounds(160, 120, 160, 35);
        polynomialRez.setBounds(355, 185, 210, 45);
        jlabel4.setBounds(165, 180, 130, 40);
        substractionBtn.setBounds(370, 280, 140, 45);
        divBtn.setBounds(370, 345, 140, 45);
        derivBtn.setBounds(600, 265, 105, 60);
        integrBtn.setBounds(600, 335, 105, 60);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setVisible(true);

    }

    public String getPolynomial1Tf() {
        return this.polynomial1Tf.getText();
    }

    public String getPolynomial2Tf() {
        return polynomial2Tf.getText();
    }

    public String getPolynomialRez() {
        return polynomialRez.getText();
    }

    public void setPolynomial1Tf(String txt) {
        this.polynomial1Tf.setText(txt);
    }

    public void setPolynomial2Tf(String txt) {
        this.polynomial2Tf.setText(txt);
    }

    public void setPolynomialRez(String txt) {
        this.polynomialRez.setText(txt);
    }

    public void addAddListener(ActionListener x){
        addBtn.addActionListener(x);
    }
    public void addSubListener(ActionListener x){
        substractionBtn.addActionListener(x);
    }

    public void addMulListener(ActionListener x){
        multiplyBtn.addActionListener(x);
    }
    public void addDerivareListener(ActionListener x){
        derivBtn.addActionListener(x);
    }
    public void addIntegrareListener(ActionListener x){
        integrBtn.addActionListener(x);
    }


}

