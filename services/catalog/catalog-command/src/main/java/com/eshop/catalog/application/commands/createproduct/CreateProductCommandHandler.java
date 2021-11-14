package com.eshop.catalog.application.commands.createproduct;

import com.eshop.catalog.application.commandbus.CatalogCommandHandler;
import com.eshop.catalog.application.integrationevents.IntegrationEventPublisher;
import com.eshop.catalog.application.integrationevents.KafkaIntegrationEventPublisher;
import com.eshop.catalog.application.integrationevents.events.CatalogItemCreatedIntegrationEvent;
import com.eshop.catalog.application.models.CatalogItemResponse;
import com.eshop.catalog.domain.catalogitem.*;
import com.eshop.shared.rest.error.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class CreateProductCommandHandler implements CatalogCommandHandler<CatalogItemResponse, CreateProductCommand> {
  private static final Logger logger = LoggerFactory.getLogger(KafkaIntegrationEventPublisher.class);

  private final CatalogItemRepository catalogItemRepository;
  private final CategoryRepository categoryRepository;
  private final BrandRepository brandRepository;

  private final IntegrationEventPublisher integrationEventPublisher;

  @Value("${spring.kafka.consumer.topic.catalogItemCreated}")
  private String catalogItemCreatedTopic;

  @Transactional
  @CommandHandler
  @Override
  public CatalogItemResponse handle(CreateProductCommand command) {

    final var catalogItemAggregate = catalogItemRepository.save(() -> catalogItemOf(command));
    logger.info("Creating CatalogItemCreatedIntegrationEvent for catalog item with id: {}", catalogItemAggregate.identifier());
    var event = new CatalogItemCreatedIntegrationEvent((UUID) catalogItemAggregate.identifier());
    integrationEventPublisher.publish(catalogItemCreatedTopic, event);

    return CatalogItemResponse.builder()
        .productId((UUID) catalogItemAggregate.identifier())
        .version(catalogItemAggregate.version())
        .build();
  }

  private CatalogItem catalogItemOf(CreateProductCommand command) {
    final var category = categoryRepository.findById(command.categoryId())
        .orElseThrow(() -> new BadRequestException("Category does not exist"));
    final var brand = brandRepository.findById(command.brandId())
        .orElseThrow(() -> new BadRequestException("Brand does not exist"));

    return new CatalogItem(
        ProductName.of(command.name()),
        command.description(),
        Price.of(command.price()),
        command.pictureFileName(),
        Units.of(command.availableStock()),
        category,
        brand
    );
  }

}
