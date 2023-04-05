package Model;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operations {

    public static Polynomial adunare(Polynomial p, Polynomial q) {


        Polynomial rez = new Polynomial();
        for(Monomial n: p.getList()){
            rez.addMon(n);
        }
        //rez = p + q
        //p x^3 + 2x + 1
        //q x^2 + 3x
        //rez = x^3 + 2x + 1 + x^2

        for(Monomial n: q.getList()){
            int ok = 0;
            for(Monomial m : rez.getList()){
                if(m.getPutere() == n.getPutere()){
                    m.setCoeficient(m.getCoeficient() + n.getCoeficient());
                    ok = 1;
                }
            }
            if(ok==0)
                rez.addMon(n);
        }


        Collections.sort(rez.getList(), Monomial::compareTo);
        return rez;
    }

    public static Polynomial scadere(Polynomial p, Polynomial q) {

        Polynomial rez = new Polynomial();
        for(Monomial n: p.getList()){
            rez.addMon(n);
        }
        //rez = p - q
        //p x^3 + 2x + 1
        //q x^2 + 3x
        //rez = x^3 + 2x + 1 - x^2

        for(Monomial n: q.getList()){
            int ok = 0;
            for(Monomial m : rez.getList()){
                if(m.getPutere() == n.getPutere()){
                    m.setCoeficient(m.getCoeficient() - n.getCoeficient());
                    if(m.getCoeficient() == 0)
                    {
                        rez.removeMon(m);
                    }
                    ok = 1;
                }
            }

            if(ok==0) {
                n.setCoeficient(n.getCoeficient()* (-1));
                rez.addMon(n);
            }
        }

        Collections.sort(rez.getList(), Monomial::compareTo);
        return rez;
    }

    public static Polynomial inmultire(Polynomial p, Polynomial q) {

        Polynomial rez = new Polynomial();

        for(Monomial n : p.getList())
        {
            for(Monomial m : q.getList()){
                Monomial k= new Monomial(0.0, 0);
                k.setCoeficient(m.getCoeficient() * n.getCoeficient());
                k.setPutere(m.getPutere() + n.getPutere());
                rez.addMon(k);
            }
        }

        Collections.sort(rez.getList(), Monomial::compareTo);
        return rez;

    }


    public static Polynomial derivare(Polynomial p) {
        Polynomial  rez = new Polynomial();

        for(Monomial n: p.getList()) {
            if(n.getPutere() > 0)
            {
                Monomial k = new Monomial(0.0,0);
                k.setCoeficient(n.getCoeficient() * n.getPutere());
                k.setPutere( n.getPutere() - 1);
                rez.addMon(k);
            }

        }
        Collections.sort(rez.getList(), Monomial::compareTo);
        return rez;
    }

    public static Polynomial integrare(Polynomial p) {

        Polynomial rez = new Polynomial();
        for(Monomial n: p.getList())
        {
            Monomial k = new Monomial(0.0, 0);
            k.setCoeficient(n.getCoeficient() / (n.getPutere() + 1));
            k.setPutere(n.getPutere() + 1);
            rez.addMon(k);
        }
        Collections.sort(rez.getList(), Monomial::compareTo);
        return rez;
    }
}


