/*******************************************************************************
 * Copyright (C) 2018 Jobsz (zcq@zhucongqi.cn)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/

package vip.e7du.dubbodemo.annopropconfig;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

import vip.e7du.dubbodemo.annotation.action.ServiceAConsumer;
import vip.e7du.dubbodemo.annotation.action.ServiceBConsumer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class ConsumerBootstrap {

    private static AnnotationConfigApplicationContext context;

	public static void main(String[] args) throws InterruptedException {
        context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();

        System.out.println("consumer started!😝");
        ServiceBConsumer b = context.getBean(ServiceBConsumer.class);
        System.out.println(b.sayWorld(" Jobsz "));
        
        ServiceAConsumer a = context.getBean(ServiceAConsumer.class);
        while(true) {
        	String hello = a.helloWorld("Jobsz");
        	System.out.println("result: " + hello);
        	Thread.sleep(1000);
        }
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "vip.e7du.dubbodemo.annotation.action")
    @PropertySource("classpath:/spring/dubbo-consumer.properties")
    @ComponentScan(value = {"vip.e7du.dubbodemo.annotation.action"})
    static class ConsumerConfiguration {

    }
}
