# PCF Auto Simulator
# 
## Overview
This is an example project that demonstrates how to build applications in a cloud native way relying on tools like [Spring Boot](http://projects.spring.io/spring-boot/) and [Spring Cloud](http://projects.spring.io/spring-cloud/).  This project consists of a set of apps that reference external services, and an application that provides a simulator which consumes car data, and uses it to make decisions about which external services to call.  The car data is based on the [OpenXC](http://openxcplatform.com/) format and the demonstration can use [Spring Cloud Data Flow](http://cloud.spring.io/spring-cloud-dataflow/) to load records into RabbitMQ for consumption be the simulator.  Concepts demonstrated are:

* Spring Boot and Spring Cloud based applications
* External configuration management
* Service registries
* Distributed tracing
* Simple data ingestion with Spring Cloud Data Flow

This project is intended to be run either locally on a laptop or deployed on a Pivotal Cloud Foundry instance.  The applications use Spring Cloud features for configuration management, service registry, and circuit breaker functionality as well as [Zipkin](https://github.com/openzipkin/zipkin) and [Spring Cloud Sleuth](http://cloud.spring.io/spring-cloud-sleuth/) for distributed tracing.  This repo contains a sample registry server and configuration server for use if the deployed locally.  If deployed on PCF the applications can use Spring Cloud Services config server and service registry instances for that functionality.  The following diagram depicts the high level architecture of this demo.

![Architecture Diagram](/documentation/diagram.png)

## Requirements
The following tools are needed to run this demo:
* [Spring Cloud Data Flow](http://cloud.spring.io/spring-cloud-dataflow/) server for ingesting data
* RabbitMQ either installed locally running with the default configuration or as a service instance in PCF
* [groovy](http://www.groovy-lang.org/) is needed to run the data load scripts 
* An instance of Pivotal Cloud Foundry that can create RabbitMQ and Spring Cloud Services (version 1.1.0) service instances.

## Setup
Data for this demonstration needs to be transformed from the OpenXC format and then loaded into RabbitMQ.  To do so, [download](https://cloud.spring.io/spring-cloud-dataflow/) the SCDF bits and follow the documentation to install a Spring Cloud Data Flow server for the target environment.

Once the server is running create a stream that accepts data on an http port and feeds it to RabbitMQ.  The stream needs to take this form:

```
http --port=8080 | rabbit --exchange=vehicle-data --routing-key=1ve --username=<rabbit user> --password=<rabbit password> --host=<rabbit host> --virtual-host=<rabbit vhost>
```

Here is an example stream create script taken from a Spring Cloud Data Flow deployment on Pivotal Cloud Foundry:

```
http --port=8080 | rabbit --exchange=vehicle-data --routing-key=1 --username=966d3d70-c9bf-4134-9524-c77448f476ff --password=3k6f6p8tpnilopfh3j9uugg0vd --host=10.68.151.58 --virtual-host=b25035a8-ab44-484c-8e73-7879aa1c0cfd 
```

## Ingesting Data
There are two different types of data in the data folder of this repo.  For each type there are two versions:  a full file, and a smaller one consisting of a subset of full data.  The small file can be used to speed up testing or if using a hosted RabbitMQ instance with a free plan.  Within RabbitMQ, set up the following: 

* A queue named ```vehicle-data-queue```.
* An exchange called ```vehicle-data``` bound to the above queue with a routing key of ```1```.

Once RabbitMQ is configured, copy one of the data files to ```/tmp/openxc-input.json```, and then execute the groovy script found in the scripts folder of this repo.  This script takes the URL of the SCDF HTTP module as an parameter.

```
groovy OpenXCFileParser.groovy <SCDF HTTP module URL>
```

## Running the Demo
To run this demo you will need a Spring Cloud service registry and configuration server.  This repository contains an implementation of each for running the demo locally.  When running on Pivotal Cloud Foundry, Spring Cloud Services should be used to create instances of each.  In either case, the configuration server should be pointed to the config directory of this repo for access to the yaml files each application needs.  

When setting up on PCF, SCS has moved repository configuration to the command line.  Once the config server service instance is created on PCF, run the following command:

```
cf create-service p-config-server standard config-server -c '{"git": { "uri": "<git repo>", "searchPaths": "config" } }'
```

Once the registry server and config server are running, use the manifest in the root of the repo to deploy the various applications below (deploy the auto simulator app last):

* simulator-zipkin
* gas-price-service
* repair-service
* places-service
* dealer-service
* auto-simulator

Each of these services is a separate Spring Boot application that can be built and run separately either locally or on Cloud Foundry.  If run locally, their ports are specified in their configuration yaml file provided by the config server.  If run on PCF create services with the following names:

* config-server - An instance of an SCS config server
* service-registry - An instance of an SCS service registry
* circuit-breaker-dashboard-service - An instance of an SCS circuit breaker dashboard
* rabbitmq-service - An instance of RabbitMQ 

Each service should be bound to each application except the zipkin server.  It only requires the rabbitmq-service.  Once everything is running, open the url for the auto simulator application in a browser.  You should see this screen:

![Main start screen](/documentation/startup-page.png)

To start the demo processing data from the ```vehicle-data-queue``` message queue, press the green start button at the top of the screen:

![Start Button](/documentation/start-button.png)

Once clicked, the demo will take about 10 seconds to start loading data.  Once the first record is pulled from the message queue, the map will refresh with the current location of the vehicle, then the frame on the right should be populated with local gas stations and car dealers.  

![Gas Stations](/documentation/gas_stations.png)

Click the 'Gas Stations' and 'Dealers' tabs to move back and forth and view the data.  Gas stations will only appear if the fuel level indicator is yellow.  Likewise, the dealers will only appear if the vehicle needs service which is indicated by the condition box at the top of the screen.  The thresholds can be adjusted using the fields to the left of the indicators.

![Indicators](/documentation/indicators.png)

To demonstrate the recovery capabilities of PCF, click the kill button at the top right corner of the screen.  This will terminate the app and PCF should restart it automatically.  Additionally, the IP address / instance index is available in order to demonstrate the scaling capabilities of the platform.  Use the bound to Rabbit indicator to demonstrate service binding capabilities.

![Operational Capabilities](/documentation/kill_bind_scale.png)







