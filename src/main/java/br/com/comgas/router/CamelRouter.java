package br.com.comgas.router;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jbpm.JBPMConstants;
import org.apache.camel.model.rest.RestBindingMode;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.RuleServicesClient;
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

		rest("/calcular").description("Calcular Priorização").get("/{Latitude}/{Longitude}/{Distancia}/{Metodo}")
				.route().routeId("calcular-api").to("direct:calcular");

		from("direct:dutos").id("dutos_mysql").description("Consulta de dutos por dados de Geo Localização")
				.streamCaching()
				.to("sql:SELECT * FROM DUTOS WHERE LATITUDE =:#Latitude AND LONGITUDE =:#Longitude?outputType=SelectOne&outputClass=br.com.comgas.beans.Dutos");

		from("direct:calcular").process(new KieClientProcessor() {

			@Override
			public void process(Exchange ex) throws Exception {

				Map<String, Object> params = new HashMap<>();

				params.put("Latitude", ex.getIn().getHeader("Latitude", Double.class));
				params.put("Longitude", ex.getIn().getHeader("Longitude", Double.class));
				params.put("Distancia", ex.getIn().getHeader("Distancia", Integer.class));
				params.put("Metodo", ex.getIn().getHeader("Metodo", String.class));

				Long instanceId = client.startProcess(CONTAINER_ID, PROCESS_ID, params);
				ex.getMessage().setBody(instanceId);

				System.out.println(">>>>> " + instanceId + " <<<<<");
			}
		}).log("${body}").process(new KieClientProcessor() {

			@Override
			public void process(Exchange ex) throws Exception {
				try {
					Object ipc = client.getProcessInstanceVariable(CONTAINER_ID, ex.getMessage().getBody(Long.class),
							VARIABLE_IPC);
					System.out.println(">>>> " + ipc);
				} catch (Exception e) {
					log.debug(e.getMessage());
				}
			}
		});

	}

}