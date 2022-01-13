# Order Notifications Service

This service is used to notify the user in real-time about the order process.

It implements messaging using the [Spring Websocket](https://docs.spring.io/spring-framework/docs/4.3.x/spring-framework-reference/html/websocket.html)

## Events

It basically listens to Kafka topics related to the order status and pushes messages to the appropriate queue.

## Optional profiles

- **elk** - to enable ELK logging.
- **distributed-tracing** - to enable distributed tracing with Sleuth and Zipking.
- **docker** - used when the service is run with docker.
