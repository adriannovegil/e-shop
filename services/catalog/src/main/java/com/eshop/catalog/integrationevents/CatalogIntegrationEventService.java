package com.eshop.catalog.integrationevents;

import com.eshop.catalog.shared.IntegrationEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class CatalogIntegrationEventService implements IntegrationEventService {
    private final EntityManager entityManager;
    private final KafkaTemplate<String, IntegrationEvent> kafkaTemplate;
    private final String catalogTopic;

    public CatalogIntegrationEventService(
            EntityManager entityManager,
            KafkaTemplate<String, IntegrationEvent> kafkaTemplate,
            @Value("${spring.kafka.consumer.topic.catalog}") String catalogTopic
    ) {
        this.entityManager = entityManager;
        this.kafkaTemplate = kafkaTemplate;
        this.catalogTopic = catalogTopic;
    }

    @Override
    public void SaveEventAndCatalogContextChanges(IntegrationEvent event) {
        // TODO HD see ResilientTransaction on eShopOnContainers (BuildingBlocks)
        // TODO HD Achieving atomicity between original catalog database operation and the IntegrationEventLog thanks to a local transaction
        // TODO HD save the event in a event log
        // _eventLogService.SaveEventAsync(evt, _catalogContext.Database.CurrentTransaction);
        entityManager.getTransaction().commit();
    }

    @Override
    public void PublishThroughEventBus(IntegrationEvent event) {
        kafkaTemplate.send(catalogTopic, event);
    }
}
