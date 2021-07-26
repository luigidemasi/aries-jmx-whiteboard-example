/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package com.redhat.ldemasi.examples;

import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;

import static org.springframework.jms.listener.DefaultMessageListenerContainer.CACHE_AUTO;

@Configuration
@ConfigurationProperties( prefix = "qpid")
public class AppConfiguration {

    private String username;

    private String password;

    private String remoteURI;


    @Bean
    public OrderGenerator orderGenerator() {
        return new OrderGenerator();
    }

    @Bean
    public JmsConnectionFactory amqpConnectionFactory() {
        JmsConnectionFactory jcf = new JmsConnectionFactory(username, password, remoteURI);
        jcf.setReceiveLocalOnly(true);
        return jcf;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory(@Autowired JmsConnectionFactory jcf){
        CachingConnectionFactory ccf = new CachingConnectionFactory();
        ccf.setTargetConnectionFactory(jcf);
        return ccf;
    }

    @Bean
    public JmsConfiguration jmsConfig(@Autowired CachingConnectionFactory ccf) {
        JmsConfiguration jc = new JmsConfiguration();
        jc.setConnectionFactory(ccf);
        jc.setCacheLevel(CACHE_AUTO);
        return jc;
    }

    @Bean
    public AMQPComponent amqp(@Autowired JmsConfiguration jc) {
        AMQPComponent amqp = new AMQPComponent();
        amqp.setConfiguration(jc);
        return amqp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemoteURI() {
        return remoteURI;
    }

    public void setRemoteURI(String remoteURI) {
        this.remoteURI = remoteURI;
    }
}
