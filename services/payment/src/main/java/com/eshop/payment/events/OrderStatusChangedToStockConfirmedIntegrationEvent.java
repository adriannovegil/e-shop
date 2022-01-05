package com.eshop.payment.events;

import com.eshop.shared.eventhandling.IntegrationEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderStatusChangedToStockConfirmedIntegrationEvent extends IntegrationEvent {

    private String orderId;
}
