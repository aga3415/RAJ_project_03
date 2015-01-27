import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 2015-01-27.
 *
 * Singleton.
 */
public class MedicinesRepository {
    private List<Medicine> medicines;
    private static MedicinesRepository repo;

    private MedicinesRepository(){
        medicines = new ArrayList<Medicine>();
        medicines.add(new Medicine("ggg"));
    }

    public static MedicinesRepository getMedicinesRepository(){
        if(repo == null){
            repo = new MedicinesRepository();
        }
        return repo;
    }

    /**
     * Add medicine to MedicinesRepository
     * @param medicine medicine to add
     * @return false - it's been already medicine with the same name or medicine == null; true - medicine has been added
     */
    public boolean addMedicine(Medicine medicine){
        if(medicine == null) return false;
        if(medicines.contains(medicine)){
            return false;
        }
        else{
            medicines.add(medicine);
            return true;
        }
    }

    /**
     * Get medicine for given name.
     * @param name name of medicine
     * @return Medicine or null if there is no medicine with given name.
     */
    public Medicine getMedicine(String name){
        Medicine tmp = new Medicine(name);
        int i = medicines.indexOf(tmp); //if there is no medicine with this name, returns -1
        if(i != -1) return medicines.get(i);
        else return null;
    }

    public List<Medicine> getAllMedicines(){
        return medicines;

    }
}
