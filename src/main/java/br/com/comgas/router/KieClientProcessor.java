package br.com.comgas.router;

import org.apache.camel.Processor;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.RuleServicesClient;

public abstract class KieClientProcessor implements Processor {
	public static final String KIE_URL_BASE = "http://localhost:8080/kie-server/services/rest/server";
	public static final String KIE_SESSION_NAME = "kieless";
	public static final String CONTAINER_ID = "ipc_1.0.0-SNAPSHOT";
	public static final String KIE_USER = "rhpamAdmin";
	public static final String KIE_PASSWD = "r3dh4t1!";
	public static final String PROCESS_ID = "IPC.Processo_Priorizacao";
	public static final String VARIABLE_IPC = "ipc";

	protected ProcessServicesClient processClient = null;
	protected RuleServicesClient rulesClient;

	public KieClientProcessor() {
		KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(KIE_URL_BASE, KIE_USER, KIE_PASSWD);
		conf.setMarshallingFormat(MarshallingFormat.JSON);
		KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
		
		processClient = kieServicesClient.getServicesClient(ProcessServicesClient.class);
		rulesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
	}

}
