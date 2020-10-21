package rentalService;

import org.springframework.beans.factory.annotation.Autowired;
import rentalService.config.kafka.KafkaProcessor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }


    @Autowired
    CollectRepository CollectRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReturnCanceled_CollectCanced(@Payload ReturnCanceled returnCanceled){

        if(returnCanceled.isMe()){
            Collect collect = new Collect();
            collect.setRentalId(returnCanceled.getRentalId());
            collect.setStatus("Collected_Canceled");

            CollectRepository.save(collect);
        }

        ///


    }


}
