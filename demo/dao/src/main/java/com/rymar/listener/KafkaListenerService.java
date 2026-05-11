package com.rymar.listener;

import com.rymar.common.events.CreateProductEvent;
import com.rymar.entity.Product;
import com.rymar.repo.spring.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaListenerService {

  private final ProductRepo productRepo;

  @KafkaListener(
      topics = "create-product-events",
      groupId = "orymar-group",
      containerFactory = "kafkaListenerContainerFactory")
  public void listen(Object event) {
    if (event instanceof CreateProductEvent) {
      saveProduct((CreateProductEvent) event);
    }
  }

  private void saveProduct(CreateProductEvent event) {
      var obj = event.createProductDto;
      var product = new Product();
      product.name = obj.name();
      product.price = obj.price();
      product.count = obj.count();

      productRepo.save(product);
      log.info(product.toString());
  }
}
