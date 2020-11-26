package com.swati.camelspring;

import java.util.StringTokenizer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		from("file:E:/source")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				String message = exchange.getIn().getBody(String.class);
				StringTokenizer str = new StringTokenizer(message,",");
				
				String eid = str.nextToken();
				String ename = str.nextToken();
				String esal = str.nextToken();
				
				String outMessage = "{eid:"+eid+", ename:"+ename+", esal:"+esal+"}";
				
				exchange.getIn().setBody(outMessage);
					
			}
		})
		.to("file:E:/dest?fileName=file.json");
	}

}
