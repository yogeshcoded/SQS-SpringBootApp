package com.nt.sqs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.dto.Student;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SQSProducer {


    private final ObjectMapper mapper;
    private final SqsTemplate sqsTemplate;


    public void sendData(Student student){
        log.info("Data send to order topic {}",student);
        sqsTemplate.send(to -> {
            try {
                to.queue("order").payload(mapper.writeValueAsString(student));
                log.info("send data to order topic successfully");
            } catch (JsonProcessingException e) {
             log.error("Error while sending message to queue");
            }
        });

    }
}
