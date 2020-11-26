package com.swati.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class CamelTimer {

	public static void main(String[] args) throws Exception {
		
		MyBean myBean = new MyBean();
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("bean1", myBean);
		//registry.bind("bean1",MyBean.class,myBean); // if the above stmt doesnt work try registry.bind
		
		CamelContext camel = new DefaultCamelContext(registry);
		camel.addRoutes(new RouteBuilder() {
			
			@Override	//configure the routes
			public void configure() throws Exception {
				//timer is a component
				from("timer:myTimer?period=1000")
				.setBody(simple("Hello From Camel at ${header.firedTime}"))
					.to("stream:out")
					.to("bean:bean1?method=display")
					.bean(new MyBean(),"display");
			}
		});
		
			camel.start();
			Thread.sleep(2000);
	    
}
	
}
