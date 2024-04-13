package com.nt.api;

import com.nt.dto.Student;
import com.nt.sqs.SQSProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final SQSProducer sqsProducer;


    @PostMapping("/")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        log.info("Request coming to controller {}", student);
        sqsProducer.sendData(student);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
