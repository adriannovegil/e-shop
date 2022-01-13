# Basket Service

The Basket service is a simple CRUD data-driven microservice that manages user baskets.

All endpoints are available only for all users who own a JWT token that contains "basket" scope and "basket-service" audience.

# Events

In case of basket checkout, it publishes **UserCheckoutAcceptedIntegrationEvent** to initiate the order process.

It also handles two integration events:

1. **OrderStartedIntegrationEvent**

    To delete user's basket when the order process has started.

2. **ProductPriceChangedIntegrationEventHandler**

    To update the price of the product in the basket.

The Basket service uses Redis for persistence.

## Optional profiles

- **elk** - to enable ELK logging.
- **distributed-tracing** - to enable distributed tracing with Sleuth and Zipking.
- **docker** - used when the service is run with docker.
