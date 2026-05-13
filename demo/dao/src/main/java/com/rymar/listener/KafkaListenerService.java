package com.rymar.listener;

import com.rymar.common.events.CreateProductEvent;
import com.rymar.common.events.UpdateProductEvent;
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
      topics = "product-events",
      groupId = "orymar-group",
      containerFactory = "kafkaListenerContainerFactory")
  public void listen(ConsumerRecord<String, Object> record) {
      switch (record.value()){
          case CreateProductEvent createProductEvent -> saveProduct(createProductEvent);
          case UpdateProductEvent updateProductEvent -> updateProduct(updateProductEvent);
          default -> throw new IllegalStateException("Unexpected value: " + record.value());
      }

  }

    private void updateProduct(UpdateProductEvent event) {
        var product = productMapper.updateProductDtoToObject(event.updateProductDto);
        productService.saveProduct(product);
        log.info("Product save to db: {}", product);
    }

  private void saveProduct(CreateProductEvent event) {
    var product = productMapper.createProductDtoToObject(event.createProductDto);
    productService.saveProduct(product);
    log.info("Product save to db: {}", product);
  }
}