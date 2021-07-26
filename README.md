# Fuse Karaf Custom MBean Example

This example demonstrates how to expose a custom MBean with Red Hat Fuse on Spring Boot. 

### Prerequisites
* Maven
* An instance of A-MQ server that expose an AMQP endpoint


### Running the example

##### Build the project and run
```bash
mvn clean install spring-boot:run
```


Access the Fuse console at http://localhost:10001/hawtio, go to the JMX tab and you'll see the Mbean exposed
under com.redhat.ldemasi.examples.config.amqp: 

![mbean](img/mbean1.png)

You can retrieve the jolokia url clicking on the attribute:

![jolokia url](img/mbean2.png)
