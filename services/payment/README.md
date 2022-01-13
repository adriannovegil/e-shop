# Payment Service

Simulates a simple payment gateway.

## Events

It listens to

- **OrderStatusChangedToStockConfirmedIntegrationEvent**

and produces either

- **OrderPaymentSucceededIntegrationEvent** (70% chance) or
- **OrderPaymentFailedIntegrationEvent** (30% chance).

## Optional profiles:

1. **elk** - to enable ELK logging.
2. **distributed-tracing** - to enable distributed tracing with Sleuth and Zipking.
3. **docker** - used when the service is run with docker.
