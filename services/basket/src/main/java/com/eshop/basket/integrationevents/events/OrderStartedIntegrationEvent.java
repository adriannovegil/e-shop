package com.eshop.basket.integrationevents.events;

import com.eshop.shared.eventhandling.IntegrationEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderStartedIntegrationEvent extends IntegrationEvent {

    private String userId;
}
