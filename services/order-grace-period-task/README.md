# Order Grace Period Task

Currently, this microservice is implemented as a spring boot web application, but it should be re-implement as
[Spring Cloud Task](https://spring.io/projects/spring-cloud-task) that will be run with [Spring Cloud Data Flow](https://spring.io/projects/spring-cloud-dataflow).

# Events

Order grace period task runs every 20 seconds, and it looks for confirmed orders in the orderdb.

It takes every order that has been submitted and for each one of those orders it publishes

- **GracePeriodConfirmedIntegrationEvent**.

## Optional profiles

- **elk** - to enable ELK logging.
- **distributed-tracing** - to enable distributed tracing with Sleuth and Zipking.
- **docker** - used when the service is run with docker.
