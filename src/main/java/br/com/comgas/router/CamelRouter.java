package br.com.comgas.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import br.com.comgas.beans.Dutos;

@Component
public class CamelRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		restConfiguration().apiContextPath("/api-doc")
				.apiProperty("api.title", "Servico de consulta ao registro de Dutos").apiProperty("api.version", "1.0")
				.apiProperty("cors", "true").apiProperty("base.path", "camel/").apiProperty("api.path", "/")
				.apiProperty("host", "").apiContextRouteId("doc-api").component("servlet")
				.bindingMode(RestBindingMode.json);

		rest("/dutos").description("Localização do duto pelo valor de geolocalização Latitude/Longitude")
				.get("/{Latitude}/{Longitude}").outType(Dutos.class).route().routeId("dutos-api").to("direct:dutos");

		from("direct:dutos").id("dutos_mysql").description("Consulta de dutos por dados de Geo Localização").streamCaching()
				.to("sql:SELECT * FROM DUTOS WHERE LATITUDE =:#Latitude AND LONGITUDE =:#Longitude?outputType=SelectOne&outputClass=br.com.comgas.beans.Dutos");
	}

}