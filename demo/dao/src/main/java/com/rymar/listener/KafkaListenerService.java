package com.rymar.listener;

import com.rymar.common.events.CreateProductEvent;
import com.rymar.mapper.ProductMapper;
import com.rymar.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaListenerService {

  private final ProductService productService;
  private final ProductMapper productMapper;

  @KafkaListener(
      topics = "create-product-event",
      groupId = "orymar-group",
      containerFactory = "kafkaListenerContainerFactory")
  public void listen(ConsumerRecord<String, CreateProductEvent> record) {
    CreateProductEvent event = record.value();
    saveProduct(event);
  }

  private void saveProduct(CreateProductEvent event) {
    var obj = event.createProductDto;
    log.info(obj.toString());
    var product = productMapper.dtoToObject(obj);
    log.info(product.toString());
    productService.saveProduct(product);
    log.info("Product save to db: {}", product);
  }
}