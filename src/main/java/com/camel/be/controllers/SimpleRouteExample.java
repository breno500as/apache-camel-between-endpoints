package com.camel.be.controllers;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class SimpleRouteExample extends RouteBuilder {
	
	@Value("${url.integracao.defesa.civil}")
	private String defesaCivilUrl = "";

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		restConfiguration()
		.component("servlet")
		.bindingMode(RestBindingMode.json);
		
		// Um exemplo de chamada, as urls obrigatóriamente devem ter o /camel no path, pois é dessa forma
		// que o spring configura o servlet do apache camel.
		// http://localhost:8080/endpoints-test/camel/defesa-civil
		 
		// https://camel.apache.org/uris.html
		rest("/defesa-civil")
		.consumes("application/json")
		.post()
		.route()
		.marshal()
		.json(JsonLibrary.Jackson) 
		.to(this.defesaCivilUrl);
		 
	}
}
