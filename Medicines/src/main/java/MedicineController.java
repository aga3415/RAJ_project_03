import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 2015-01-27.
 */

@RestController
@RequestMapping(value = "/medicines")
public class MedicineController {
    private final MedicinesRepository repo = MedicinesRepository.getMedicinesRepository();

    @RequestMapping(method = RequestMethod.GET)
    public List<Medicine> getAllMedicines(){
        return repo.getAllMedicines();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void putBook(@RequestBody Medicine med){
        boolean successful = repo.addMedicine(med);
    }




}
