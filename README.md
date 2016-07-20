# PCF Auto Simulator
# 
## Overview
This is an example project that demonstrates how to build applications in a cloud native way relying on tools like [Spring Boot](http://projects.spring.io/spring-boot/) and [Spring Cloud](http://projects.spring.io/spring-cloud/).  This project consists of a set of apps that reference external services, and an application that provides a simulator which consumes car data, and uses it to make decisions about which external services to call.  The car data is based on the [OpenXC](http://openxcplatform.com/) format and the demonstration can use [Spring Cloud Data Flow](http://cloud.spring.io/spring-cloud-dataflow/) to load records into RabbitMQ for consumption be the simulator.  Concepts demonstrated are:

* Spring Boot and Spring Cloud based applications
* External configuration management
* Service registries
* Distributed tracing
* Simple data ingestion with Spring Cloud Data Flow

This project is intended to be run either locally on a laptop or deployed on a Pivotal Cloud Foundry instance.  The applications use Spring Cloud features for configuration management, service registry, and circuit breaker functionality as well as [Zipkin](https://github.com/openzipkin/zipkin) and [Spring Cloud Sleuth](http://cloud.spring.io/spring-cloud-sleuth/) for distributed tracing.  This repo contains a sample registry server and configuration server for use if the deployed locally.  If deployed on PCF the applications can use Spring Cloud Services config server and service registry instances for that functionality.  

## Requirements
The following tools are needed to run this demo:
* [Spring Cloud Data Flow](http://cloud.spring.io/spring-cloud-dataflow/) server for ingesting data
* RabbitMQ either installed locally running with the default configuration or as a service instance in PCF
* [groovy](http://www.groovy-lang.org/) is needed to run the data load scripts 
* An instance of Pivotal Cloud Foundry that can create RabbitMQ and Spring Cloud Services (version 1.1.0) service instances.

## Setup
Data for this demonstration needs to be transformed from the OpenXC format and then loaded into RabbitMQ.  To do so, [download](https://cloud.spring.io/spring-cloud-dataflow/) the SCDF bits and follow the documentation to install a Spring Cloud Data Flow server for the target environment.

Once the server is running create a stream that accepts data on an http port and feeds it to RabbitMQ.  Here is an example stream create script taken from a Spring Cloud Data Flow deployment on Pivotal Cloud Foundry:

```
http --port=8080 | rabbit --exchange=vehicle-data --routing-key=all --password=3k6f6p8tpnilopfh3j9uugg0vd --host=10.68.151.58 --virtual-host=b25035a8-ab44-484c-8e73-7879aa1c0cfd --username=966d3d70-c9bf-4134-9524-c77448f476ff
```

## Ingesting Data
There are two different types of data in the data folder of this repo.  For each type there are two versions:  a full file, and a smaller one consisting of a subset of full data.  The small file can be used to speed up testing or if using a hosted RabbitMQ instance with a free plan.  Within RabbitMQ, set up the following: 

* A queue named ```vehicle-data-queue```.
* An exchange called ```vehicle-data``` bound to the above queue with a routing key of ```all```.

Once RabbitMQ is configured, copy one of the data files to ```/tmp/openxc-input.json```, and then execute the groovy script found in the scripts folder of this repo.

```
groovy OpenXCFileParser.groovy
```
	
