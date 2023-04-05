import Model.ModelPolynomial;
import Model.Monomial;
import Model.Operations;
import Model.Polynomial;
import View.View;
import Controller.Controller;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        View v = new View();
        ModelPolynomial  m = new ModelPolynomial();
        Controller c=new Controller(m, v);

        Polynomial p = new Polynomial();
        Polynomial q = new Polynomial();

        Monomial m1 = new Monomial(2.0, 1);
        Monomial m2 = new Monomial(1.0, 2);
        Monomial m3 = new Monomial(3.0, 3);
        p.addMon(m1);
        p.addMon(m2);
        p.addMon(m3);

        Monomial m4 = new Monomial(2.0, 4);
        Monomial m5 = new Monomial(3.0, 3);
        Monomial m6 = new Monomial(1.0, 1);

        q.addMon(m4);
        q.addMon(m5);
        q.addMon(m6);
        //Polynomial scadere = Model.Operations.scadere(p, q);
        //x + x^2 +
        //System.out.println(scadere);

       /* Polynomial aux = new Polynomial();
        aux= c.createPolynom("2x^2+3x^1");
        System.out.println(aux);*/

        //2x + x^2 + 3x^3
        //2x^4 +3x^3 + x

        /*Polynomial adunare= Model.Operations.adunare(p, q);
        //2x^4 + 6x^3 + x^2 + 3x^1
        System.out.println(adunare);
        Polynomial scadere = Model.Operations.scadere(p, q);
        //x + x^2 +
        System.out.println(scadere);
       /* Polynomial inmultire = Model.Operations.inmultire(p, q);
        System.out.println(inmultire);
      /* Polynomial derivare = Model.Operations.derivare(p);
        System.out.println(derivare);

        Polynomial integrare = Model.Operations.integrare(p);
        System.out.println(integrare);

        ModelPolynomial m = new ModelPolynomial();
        View v = new View();
        Controller controller = new Controller(m, v);
        controller.init();

        //Operations.test("2x^3+3x");*/
    }
}