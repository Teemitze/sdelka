package app.sdelka.controller;

import app.sdelka.controller.dto.AdvertDto;
import app.sdelka.service.advert.AdvertServiceMapping;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kafka")
public class KafkaController {

    private final KafkaTemplate<UUID, AdvertDto> kafkaTemplate;
    private final AdvertServiceMapping advertServiceMapping;

    @PostMapping
    public void saveMessage(@RequestBody AdvertDto advertDto) {
        ProducerRecord producerRecord = new ProducerRecord("adverts1", advertDto);
        kafkaTemplate.send(producerRecord);
        kafkaTemplate.flush();
    }


    @KafkaListener(topics = "adverts1", groupId = "firstNode")
    public void getAllMessage(AdvertDto advertDto) {
        advertServiceMapping.save(advertDto);
        System.out.println("Received Message in group foo: " + advertDto.toString());
    }
}
