package il.ac.wis.cs.playgo.playtoolkit.ebridge;

import il.ac.wis.cs.playgo.playtoolkit.SyncMessageHandler;
import il.ac.wis.cs.playgo.playtoolkit.api.impl.java.SystemModelPlayGo;

import java.util.ArrayList;

public abstract class ExecutionBridge implements IExecutionBridge {

//	protected HashMap<String, IPlayableComponent> allPlayableComponents = new HashMap<String, IPlayableComponent>();
//	protected static IPlayGo guiAgent;

	// hold the name or id of the execution engine that initiated the step (null, when step was initiated by PlayGo):
	protected String originatedFromExecutionEngine = null;
	
	public ExecutionBridge() {
		super();
		register(SystemModelPlayGo.getInstance());
	}

	// Execution engine informs PlayGo on a user event:
	public void objectEvent(String className, String objectName,
			String eventName, ArrayList<String> argTypes, ArrayList<String> argValues) {
		SyncMessageHandler.invokeMessage("User", "Env", className, objectName, eventName, argTypes, argValues);
	}

	
	// Execution engine informs PlayGo on a property change:
	public void objectPropertyChanged(String className, String objectName,
			String propertyName, String type, String value) {

		objectPropertyChanged(className, objectName, className, objectName, propertyName, type, value);
	}

	// Execution engine informs PlayGo on a property change:
	public void objectPropertyChanged(String sourceClassName, String sourceObjectName,
			String targetClassName, String targetObjectName,
			String propertyName, String type, String value) 
	{
		String methodName = "set" + propertyName.substring(0,1).toUpperCase();
		if(propertyName.length()>1){
			methodName = methodName + propertyName.substring(1);
		}
		ArrayList<String> argTypes = new ArrayList<String>();
		argTypes.add(type);
		ArrayList<String> argValues = new ArrayList<String>();
		argValues.add(value);
		
		SyncMessageHandler.invokeMessage(sourceObjectName, sourceClassName,
       			targetObjectName, targetClassName,
       			methodName,
       			argTypes,
       			argValues);
	}
	
	// Execution engine retrieve property value from PlayGo (used for initializing the default values, based on the systemmodel default values)
	public Object getPropertyValue(String objectName, String className, String propertyName, String type){
		String methodName = "get" + propertyName.substring(0,1).toUpperCase();
		if(propertyName.length()>1){
			methodName = methodName + propertyName.substring(1);
		}
				
		try {
			return SyncMessageHandler.invokeGetter(objectName, className,
					methodName);
		} catch (ReflectiveOperationException e) {
			// failed to invoke getter, but this may be OK since variables of the execution engine doents always have a 
			// corresponding property in the system model.
			System.out.println("Failed to invoke method: " + e.getMessage());
			return null;
		}
	}

	// Execution engine informs PlayGo on a system event:
	@Override
	public void systemEvent(String executionEngineId, String sourceClassName, String sourceObjectName, String targetClassName, String targetObjectName, String eventName) {
//		runCycle(); // advance to execution engine before informing PlayGo. // commented out since seems that the statemachine advances itself - makes sense to assume all Execution engines do that?

		setOriginatedFromExecutionEngine(executionEngineId);
		SyncMessageHandler.invokeMessage(sourceObjectName, sourceClassName, targetObjectName, targetClassName, eventName, null, null);
		setOriginatedFromExecutionEngine(null);
	}
		
	// Execution engine informs PlayGo on a 'self' system event:
	@Override
	public void systemEvent(String executionEngineId, String className, String objectName, String eventName) {
		systemEvent(executionEngineId, className, objectName, className, objectName, eventName);
	}
	
//	// Execution engine informs PlayGo on a system event:
//	@Override
//	public void systemEvent(String className, String eventName) {
//		ArrayList<Object> objects = AppObjects.getObjects(className);
//		for (Object object : objects) {
//			String objectName = AppObjects.getObjectName(object);
//			systemEvent(className, objectName, eventName);
//		}
//		
//	}

	
	public String getOriginatedFromExecutionEngine() {
		return originatedFromExecutionEngine;
	}

	public void setOriginatedFromExecutionEngine(
			String executionEngineId) {
		this.originatedFromExecutionEngine = executionEngineId;
	}
	
	@Override
	public void register(IExecutionCoordinator playgo) {
		playgo.register(this);
		
	}

	// PlayGo informs the execution engine on an event:
	@Override
	abstract public void activateMethod(String className, String objectName, String methodName, String... arguments);

	// PlayGo informs the execution engine of a change in a variable value:
	@Override
	abstract public void setPropertyValue(String className, String objectName,
			String propertyName, String type, String value);

}
