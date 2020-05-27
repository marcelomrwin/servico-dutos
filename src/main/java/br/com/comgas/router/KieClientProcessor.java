package br.com.comgas.router;

import org.apache.camel.Processor;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.ProcessServicesClient;

public abstract class KieClientProcessor implements Processor {
	public static final String KIE_URL_BASE = "http://localhost:8080/kie-server/services/rest/server";
	public static final String CONTAINER_ID = "ipc_1.0.0-SNAPSHOT";
	public static final String KIE_USER = "rhpamAdmin";
	public static final String KIE_PASSWD = "rhpamAdmin";
	public static final String PROCESS_ID = "IPC.Processo_Priorizacao";
	public static final String VARIABLE_IPC = "ipc";

	protected ProcessServicesClient client = null;

	public KieClientProcessor() {
		KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(KIE_URL_BASE, KIE_USER, KIE_PASSWD);
		conf.setMarshallingFormat(MarshallingFormat.JSON);
		KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
		client = kieServicesClient.getServicesClient(ProcessServicesClient.class);
	}

}
