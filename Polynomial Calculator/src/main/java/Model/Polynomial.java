package Model;

import Model.Monomial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Polynomial {

    ///public ArrayList<Monomial> list = new ArrayList<Monomial>();


    public HashMap<Integer, Monomial> hashMap = new HashMap<>();
    private Integer key = 0;

    public void addMon(Monomial x){
        ///list.add(x);
        hashMap.put(key, x);
        key++;
    }

    private int getKey(Monomial x){
        for (Map.Entry<Integer,Monomial> entry : hashMap.entrySet())
            if (entry.getValue() == x)
                return entry.getKey();
        return 0;
    }

    public void removeMon(Monomial x){
        ///list.remove(x);
        Integer k = getKey(x);
        hashMap.remove(k);
    }

    public ArrayList<Monomial> getList(){
        ArrayList<Monomial> listRez = new ArrayList<>();
        for (Map.Entry<Integer,Monomial> entry : hashMap.entrySet())
            listRez.add(entry.getValue());
        return listRez;
    }
    public void setList(ArrayList<Monomial> list){

        for (Monomial k : list)
        {
            addMon(k);
        }
    }

    @Override
    public String toString() {
        String polinom = "";
        for (Map.Entry<Integer,Monomial> entry : hashMap.entrySet())
        {
            polinom = polinom + "+" + entry.getValue().toString();
        }
        return polinom;
    }
}


