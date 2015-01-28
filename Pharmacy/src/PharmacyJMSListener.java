import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.List;


/**
 * Created by AL on 2015-01-28.
 */
public class PharmacyJMSListener implements MessageListener {

    List<Prescription> listOfPrescription;
    @Override
    public void onMessage(Message message) {

        try{
            MapMessage mapMessage = (MapMessage) message;
            listOfPrescription.add((Prescription) ((MapMessage) message).getObject("prescription"));
        }
        catch(JMSException ex){
            throw new IllegalStateException();
        }
    }
}
