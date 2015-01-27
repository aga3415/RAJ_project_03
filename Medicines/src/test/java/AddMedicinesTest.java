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

        @Before
        public void setUp(){
            med1 = new Medicine("gripex");
            med2 = new Medicine("kaszelix");
            restTemplate.getMessageConverters().add(new Jaxb2CollectionHttpMessageConverter<Collection>());
        }

        @Test
        public void postMedicines() {
               restTemplate.postForObject("http://localhost:8080/medicines", med1, Object.class);
               // restTemplate.postForObject("http://localhost:8080/medicines", med2, Object.class);
                Assertions.assertThat(true);

        }
    }

