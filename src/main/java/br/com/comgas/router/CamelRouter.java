package br.com.comgas.router;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import br.com.comgas.beans.*;


/**
 * A simple Camel REST DSL route that implements the greetings service.
 * 
 */
@Component
public class CamelRouter extends RouteBuilder {

    

    @Override
    public void configure() throws Exception {

        // @formatter:off
        restConfiguration()
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Servico de consulta ao registro de Dutos")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiProperty("base.path", "camel/")
                .apiProperty("api.path", "/")
                .apiProperty("host", "")
                .apiContextRouteId("doc-api")
            .component("servlet")
            .bindingMode(RestBindingMode.json);
        
        rest("/dutos").description("Localização do duto pelo valor de geolocalização Latitude/Longitude")
            .get("/{Latitude}/{Longitude}").outType(Dutos.class)
                .route().routeId("dutos-api")
                .to("direct:dutos");

        
        from("direct:dutos").description("Consulta de dutos por dados de Geo Localização")
        .log("{body.Latitude}")
        .streamCaching()
        .setBody(simple("SELECT * FROM RHPAM.REDE_DE_TRANSMISSAO WHERE LATITUDE =#Latitude AND LONGITUDE = #Longitude"))
       .to("sql:datasource")
        .to("log:{body}");   
        // @formatter:on
    }

}