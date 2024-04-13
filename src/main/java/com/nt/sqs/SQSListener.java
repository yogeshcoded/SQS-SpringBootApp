package com.nt.sqs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.dto.Student;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SQSListener {

    private final ObjectMapper mapper;

    @SqsListener("order")
    public void pullDataFromOrderQueue(String student) throws JsonProcessingException {
        log.info("data revised from order topic {}",student);
        Student student1 = mapper.readValue(student, Student.class);
     log.info("Convert String to Student Object {}",student1);

    }
}
