import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.xml.Jaxb2CollectionHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;


/**
 * Created by Dominik on 2015-01-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MedicinesApp.class)
public class AddMedicinesTest {

        //@Autowired

        private RestTemplate restTemplate = new RestTemplate();



    private Medicine med1;
        private Medicine med2;
    private String med1S;

        @Before
        public void setUp(){
            med1 = new Medicine("gripex");
            med2 = new Medicine("kaszelix");
            ObjectMapper mapper = new ObjectMapper();
            try {
                med1S = mapper.writeValueAsString(med1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            restTemplate.getMessageConverters().add(new Jaxb2CollectionHttpMessageConverter<Collection>());
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
    }

