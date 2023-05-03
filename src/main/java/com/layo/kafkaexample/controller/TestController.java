package com.layo.kafkaexample.controller;

import com.layo.kafkaexample.engine.KafkaProducerExample;
import com.layo.kafkaexample.model.Message;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final KafkaProducerExample senderExample;

    @GetMapping("/test")
    public String test(){
        return "test is working";
    }

    @GetMapping("/sendMessage/{message}")
    public void sendMessage(@PathVariable("message") String message) throws ExecutionException, InterruptedException{

        Message messageNew = new Message();
        messageNew.setName("Ivan");
        messageNew.setMessage(message);

        senderExample.sendMessageObjectMessage(messageNew, "topic-message");

        senderExample.sendMessageTemplate(message, "topic-2");

        senderExample.sendMessageRouting(message, "topic-bytes");
        logger.info("Sended message: " + message);
    }

}
