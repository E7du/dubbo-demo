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

package vip.e7du.dubbodemo.apiconfig;

import java.io.IOException;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;

import vip.e7du.dubbodemo.api.ServiceA;
import vip.e7du.dubbodemo.apiconfig.impl.ServiceAImpl;

public class ProviderApplication {
    public static void main(String[] args) throws IOException {
        ServiceConfig<ServiceA> service = new ServiceConfig<>();
        ApplicationConfig config = new ApplicationConfig("first-dubbo-provider");
        config.setQosEnable(false);
        service.setApplication(config);
        service.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        service.setInterface(ServiceA.class);
        service.setRef(new ServiceAImpl());
        service.export();
        System.out.println("first-dubbo-provider is running.");
        System.in.read();
    }
}
