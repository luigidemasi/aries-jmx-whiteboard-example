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

import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

public class MyCustomImplMBean extends StandardMBean implements MyCustomMBean {

    private final String brokerUrl;

    protected MyCustomImplMBean() throws NotCompliantMBeanException {
        this(null);
    }

    public MyCustomImplMBean(final String brokerUrl)
            throws NotCompliantMBeanException {
        super(MyCustomMBean.class);
        this.brokerUrl =  brokerUrl;
    }

    public String getBrokerUrl() {
        return brokerUrl;
    }
}
