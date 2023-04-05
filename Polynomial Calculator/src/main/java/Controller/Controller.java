package Controller;

import Model.ModelPolynomial;
import Model.Monomial;
import Model.Polynomial;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Controller {

    ModelPolynomial model;
    View view;

    public Controller(ModelPolynomial modelPolynomial, View view){
        this.model = modelPolynomial;
        this.view = view;

        view.addAddListener(new Addlistener());
        view.addSubListener(new Difflistener());
        view.addMulListener(new Mulistener());
        view.addDerivareListener(new Derivlistener());
        view.addIntegrareListener(new Integrareistener());
    }

    void modelInit(){

        String p1 = view.getPolynomial1Tf();
        String p2 = view.getPolynomial2Tf();
        System.out.println(p1.toString());
        Polynomial auxP1 = Controller.createPolynom(p1);
        Polynomial auxP2 = Controller.createPolynom(p2);

        model.setP1(auxP1);
        model.setP2(auxP2);

    }
    void modelInit2(){

        String p1 = view.getPolynomial1Tf();
        System.out.println(p1.toString());
        Polynomial auxP1 = Controller.createPolynom(p1);
        model.setP1(auxP1);

    }

    public static Polynomial createPolynom(String p)
    {
        Polynomial rez = new Polynomial();

        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(p);
        int k = 0;
        ArrayList<Monomial> list = new ArrayList<Monomial>();

        while (matcher.find()) {


            Monomial monom = new Monomial();

            String s = matcher.group(1);
            int l = s.length();

            double coeficient = 0;
            int putere = 0;

            int i = s.indexOf("x");

            if(i == -1) {
                coeficient = Double.parseDouble(s);
                putere = 0;
            }

            else {
                if(i == 0) {
                    coeficient = 1;
                    putere = Integer.parseInt(s.substring(i + 2, l));
                }
                else {
                    coeficient = Double.parseDouble(s.substring(0, i));
                    putere = Integer.parseInt(s.substring(i + 2, l));
                }
            }

            monom.setCoeficient(coeficient);
            monom.setPutere(putere);
            list.add(monom);
            k++;
        }

        rez.setList(list);
        return rez;

    }

    class Addlistener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            modelInit();
            Polynomial rez = model.adaugare();
            view.setPolynomialRez(rez.toString());
        }
    }

    class Difflistener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            modelInit();
            Polynomial rez = model.scadere();
            view.setPolynomialRez(rez.toString());
        }
    }

    class Mulistener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            modelInit();
            Polynomial rez = model.inmultire();
            view.setPolynomialRez(rez.toString());
        }
    }

    class Derivlistener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            modelInit2();
            Polynomial rez = model.derivare();
            view.setPolynomialRez(rez.toString());
        }
    }

    class Integrareistener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            modelInit2();
            Polynomial rez = model.integrare();
            view.setPolynomialRez(rez.toString());
        }
    }
}

