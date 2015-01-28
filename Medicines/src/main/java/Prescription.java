import javafx.util.Pair;

import java.util.List;

/**
 * Created by AL on 2015-01-28.
 */
public class Prescription {

    public List<Pair<String, Float>> medicines; //para nazwa leku i % zniżki
    public String pesel;

    public Prescription(String pesel){
        this.pesel = pesel;
    }

    public void addMedicine(String name, Float discount){
        medicines.add(new Pair(name, discount));
    }

    public void removeMedicine(String name, Float discount){
        medicines.remove(new Pair(name, discount));
    }

}
