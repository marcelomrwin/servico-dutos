package com.redhat.comgas.ipc.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.server.api.exception.KieServicesException;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieServiceResponse.ResponseType;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.api.model.instance.VariableInstance;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.RuleServicesClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class KieRestTest {
	public static final String KIE_URL_BASE = "http://localhost:8080/kie-server/services/rest/server";
	public static final String KIE_SESSION_NAME = "kieless";
	public static final String CONTAINER_ID = "ipc_1.0.0-SNAPSHOT";
	public static final String KIE_USER = "rhpamAdmin";
	public static final String KIE_PASSWD = "r3dh4t1!";
	public static final String PROCESS_ID = "IPC.Processo_Priorizacao";
	public static final String VARIABLE_IPC = "ipc";

	protected static ProcessServicesClient processClient = null;
	protected static RuleServicesClient rulesClient;
	protected static KieServicesClient kieServicesClient = null;
	
	static Long instanceId;

	@BeforeClass
	public static void config() {
		KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(KIE_URL_BASE, KIE_USER, KIE_PASSWD);
		conf.setMarshallingFormat(MarshallingFormat.JSON);
		kieServicesClient = KieServicesFactory.newKieServicesClient(conf);

		processClient = kieServicesClient.getServicesClient(ProcessServicesClient.class);
		rulesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
	}

	@Test
//	@Ignore
	public void testInvokeRestClient() {
		Map<String, Object> params = new HashMap<>();

		params.put("Latitude", -23.6189012482);
		params.put("Longitude", -46.7646725256);
		params.put("Distancia", 1);
		params.put("Metodo", "Manual");

		ServiceResponse<KieContainerResource> containerInfo = kieServicesClient.getContainerInfo(CONTAINER_ID);
		KieContainerResource resultInfo = containerInfo.getResult();

		instanceId = processClient.startProcess(resultInfo.getContainerId(), PROCESS_ID, params);

		System.out.println(">>>>> " + instanceId + " <<<<<");
	}
	
	@Test	
//	@Ignore
	public void buscarVariaveisDoProcesso() {
		
		List<VariableInstance> variables = processClient.findVariablesCurrentState(CONTAINER_ID, instanceId);
		String value = null;
		for (VariableInstance variableInstance : variables) {
			if (variableInstance.getVariableName().equalsIgnoreCase("ipc")) {
				value = variableInstance.getValue();
			}
		}
		
		System.out.println(value);
		
	}
	

	@Test
	@Ignore
	public void testInvokeJavaClient() {

		KieCommands commandsFactory = KieServices.Factory.get().getCommands();
		List<Command<?>> commands = new ArrayList<>();

		commands.add(commandsFactory.newInsert(-23.6189012482, "Latitude"));
		commands.add(commandsFactory.newInsert(-46.7646725256, "Longitude"));
		commands.add(commandsFactory.newInsert(1, "Distancia"));
		commands.add(commandsFactory.newInsert("Manual", "Metodo"));

		commands.add(commandsFactory.newStartProcess(PROCESS_ID));
//		commands.add(commandsFactory.newFireAllRules());

		BatchExecutionCommand batchCommand = commandsFactory.newBatchExecution(commands);
		ServiceResponse<ExecutionResults> executeResponse = rulesClient.executeCommandsWithResults(CONTAINER_ID,
				batchCommand);

		if (executeResponse.getType() == ResponseType.SUCCESS) {
			ExecutionResults results = executeResponse.getResult();
			System.out.println(results);
			Object ipc = results.getValue(VARIABLE_IPC);
			System.out.println(ipc);
		} else {
			String message = "Error evaluating ipc. " + executeResponse.getMsg();
			throw new KieServicesException(message);
		}

	}

}

/*


KieCommands commandsFactory = KieServices.Factory.get().getCommands();
				List<Command<?>> commands = new ArrayList<>();

				commands.add(commandsFactory.newInsert(ex.getIn().getHeader("Latitude", Double.class), "Latitude"));
				commands.add(commandsFactory.newInsert(ex.getIn().getHeader("Longitude", Double.class), "Longitude"));
				commands.add(commandsFactory.newInsert(ex.getIn().getHeader("Distancia", Integer.class), "Distancia"));
				commands.add(commandsFactory.newInsert(ex.getIn().getHeader("Metodo", String.class), "Metodo"));

				commands.add(commandsFactory.newStartProcess(PROCESS_ID));
				commands.add(commandsFactory.newFireAllRules());

				BatchExecutionCommand batchCommand = commandsFactory.newBatchExecution(commands);
				ServiceResponse<ExecutionResults> executeResponse = rulesClient.executeCommandsWithResults(CONTAINER_ID,
						batchCommand);

				if (executeResponse.getType() == ResponseType.SUCCESS) {
					ExecutionResults results = executeResponse.getResult();
					System.out.println(results);
					Object ipc = results.getValue(VARIABLE_IPC);
					System.out.println(ipc);
				} else {
					String message = "Error evaluating ipc. " + executeResponse.getMsg();
					throw new KieServicesException(message);
				}


*/