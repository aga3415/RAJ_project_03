import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


/**
 * Created by Dominik on 2015-01-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MedicinesApp.class)
public class WebServiceTest {

    private RestTemplate restTemplate = new RestTemplate();
    private Medicine med1;
    private Medicine med2;
    private Medicine medGet;

    @Before
    public void setUp(){
        med1 = new Medicine("gripex");
        med2 = new Medicine("kaszelix");
        medGet = new Medicine("coś");
        restTemplate.postForObject("http://localhost:8080/medicines", medGet, Object.class);
    }
    @Test
    public void postMedicines() {
        try {
            restTemplate.postForObject("http://localhost:8080/medicines", med1, Object.class);
            restTemplate.postForObject("http://localhost:8080/medicines", med2, Object.class);
        }
        catch(HttpClientErrorException errorException){
            Assertions.assertThat(errorException.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
    }
    @Test
    public void removeMed3(){
        try{
            Medicine med3 = new Medicine("painKiller");
            restTemplate.postForObject("http://localhost:8080/medicines", med3, Object.class);
            restTemplate.delete("http://localhost:8080/medicines/" + med3.getName());
        }
        catch(HttpClientErrorException errorException){
            Assertions.assertThat(errorException.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
    }
    @Test
    public void getMedGet(){
        Medicine taken = restTemplate.getForObject("http://localhost:8080/medicines/" + medGet.getName(), Medicine.class);
        Assertions.assertThat(taken.equals(medGet));
    }

}



