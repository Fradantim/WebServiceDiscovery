# WebServiceDiscovery

Example of three SpringBoot microservices comunicating between themselves and knowing each other location by sharing a common Eureka Server.

### How to start

Launch the following proyects in any order 

(each one is a simple SpringBoot web application, so they have a main class called the same as the project they belong to)

- discovery-server
- movie-catalog-service
- movie-info-service
- rating-data-service

The 5th project, **movie-model** is a shared library between 3 of the before mentioned projects, so they know what type of object an endpoints expects / returns (One big bad practice there... but hey it speeds up things a little)

-F
