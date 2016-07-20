# PCF Auto Simulator
# 
## Overview
This is an example project that demonstrates how to build applications in a cloud native way relying on tools like ([Spring Boot](http://projects.spring.io/spring-boot/)) and ([Spring Cloud](http://projects.spring.io/spring-cloud/)).  This project consists of a set of apps that reference external services, and an application that provides a simulator which tracks movements of a car, consumes car data, and uses it to make decisions about which services to call.  The car data is based on the ([OpenXC](http://openxcplatform.com/)) format and the demonstration can use ([Spring Cloud Data Flow] http://cloud.spring.io/spring-cloud-dataflow/)) to load records into RabbitMQ for consumption be the simulator.  Concepts demonstrated are:

* Spring Boot and Spring Cloud based applications
* External configuration management
* Service registries
* Distributed tracing
* Simple data ingestion with Spring Cloud Data Flow

This project is intended to be run either locally on a laptop or deployed on a Pivotal Cloud Foundry instance.  The applications use Spring Cloud features for configuration management, service registry, circuit breaker functionality.  This repo contains a sample registry server and configuration server for use if the deployed locally.  If deployed on PCF the applications can use Spring Cloud Services config server and service registry instances for that functionality.  

## Setup
Data for this demonstration needs to be transformed from the OpenXC format and then loaded into RabbitMQ.  To do so, ([download](https://cloud.spring.io/spring-cloud-dataflow/)) and follow the documentation to install Spring Cloud Data Flow server for the target environment.

Once the server is running create a stream that accepts data on an http port and feeds it to RabbitMQ.  Here is an example stream create script taken from a Spring Cloud Data Flow deployment on Pivotal Cloud Foundry:

  http --port=8080 | rabbit --exchange=vehicle-data --routing-key=all --password=3k6f6p8tpnilopfh3j9uugg0vd --host=10.68.151.58 --virtual-host=b25035a8-ab44-484c-8e73-7879aa1c0cfd --username=966d3d70-c9bf-4134-9524-c77448f476ff	

## Requirements
For the immediate future, this can be done locally on a laptop, from an image capable of running Spring XD.  Spring XD should target a RabbitMQ queue either running locally, or in a hosted solution such as [CloudAMQP] (http://www.cloudamqp.com).  

* Spring XD 1.1.0 or later ([Instructions](https://github.com/SpringSource/spring-xd/wiki/Getting-Started))
* CloudAMQP account using a free plan ([Instructions] (https://www.cloudamqp.com/plans.html))
  * Alternatively, choose the free CloudAMQP plan within the [Pivotal Web Services] (http://run.pivotal.io) marketplace to use for this demo.

## Ingesting Data
Data that contains route and vehicle information needs to be ingested from a source.  For this demo, use the sample data in this project.  There are two version of each data file, a full file, and a smaller one consisting of a subset of data for testing.  This speeds up testing and is necessary if using a hosted RabbitMQ instance with a free plan. 

Create a queue within RabbitMQ name `vehicle-data-queue`.

Once you have Spring XD installed and a RabbitMQ destination configured, start a *Spring XD single-node instance*.

	xd/bin>$ ./xd-singlenode

Now start the *Spring XD Shell* in a separate window:

	shell/bin>$ ./xd-shell
	
In the *Spring XD shell*, create a new stream that reads data in `/tmp/openxc-input.json` performs a filter, and writes the results to your RabbitMQ queue:

	stream create --name openxc-ingest --definition "http | rabbit --addresses='<Rabbit URL>' --vhost='<Rabbit Virtual Host>' --username='<Rabbit Username>' --password='<Rabbit Password>' --routingKey='\"vehicle-data-queue\"'" --deploy
  
Once the stream is created, copy one of the sample input files to `/tmp/openxc-input.json`. Then, run the groovy script, OpenXCFileParser.groovy, found in apps/VehicleSimulator/scripts folder
