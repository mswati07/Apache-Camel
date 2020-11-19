package com.swati.camel;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		System.out.println("Hello from Camel Application");
	}

}
