package Model;

public class Monomial {
    private Double coeficient;
    private Integer putere;

    public Monomial(Double coef, Integer exp){
        this.coeficient = coef;
        this.putere = exp;
    }

    public Monomial(Monomial m){
        this.coeficient = m.getCoeficient();
        this.putere = m.getPutere();
    }

    public  Monomial(){
        super();
    }

    public Double getCoeficient() {
        return this.coeficient;
    }

    public Integer getPutere() {
        return this.putere;
    }

    public void setCoeficient(Double coef) {
        this.coeficient = coef;
    }

    public void setPutere(Integer putere){
        this.putere = putere;
    }

    @Override
    public String toString(){
        return coeficient + "X^" + putere + " ";
    }

    public int compareTo(Monomial o){
        return this.getPutere() - o.getPutere();
    }

}
