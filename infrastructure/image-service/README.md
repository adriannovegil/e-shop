# Image Service

This service is only used for uploading catalog item images to [Minio](https://github.com/minio/minio).

Only the admin user is allowed to upload images when editing catalog items.

All catalog item images are stored in Mino's **catalog-images** bucket. The images can be accessed via
[imgproxy](https://imgproxy.net/) (ex. http://localhost:8887/insecure/fit/300/200/no/0/plain/s3://catalog-images/adidas-shoes-1.png).

## Optional profiles

- **dev** - to upload test images on start-up.
- **elk** - to enable ELK logging.
- **distributed-tracing** - to enable distributed tracing with Sleuth and Zipking.
- **docker** - used when the service is run with docker.
