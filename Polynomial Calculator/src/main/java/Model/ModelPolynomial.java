package Model;

public class ModelPolynomial {
    Polynomial p1;
    Polynomial p2;
    Operations op;
    public ModelPolynomial(){
        p1 = new Polynomial();
        p2 = new Polynomial();
        op = new Operations();
    }


    public Polynomial adaugare(){
        return op.adunare(this.p1, this.p2);
    }

    public Polynomial scadere(){
        return op.scadere(this.p1, this.p2);
    }

    public Polynomial inmultire(){
        return op.inmultire(this.p1, this.p2);
    }

    public Polynomial derivare(){
        return op.derivare(this.p1);
    }

    public Polynomial integrare(){
        return op.integrare(this.p1);
    }

    public Polynomial getP1() {
        return p1;
    }

    public void setP1(Polynomial p1) {
        this.p1 = p1;
    }

    public Polynomial getP2() {
        return p2;
    }

    public void setP2(Polynomial p2) {
        this.p2 = p2;
    }
}
