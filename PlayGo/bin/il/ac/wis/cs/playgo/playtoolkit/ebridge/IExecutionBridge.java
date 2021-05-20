package il.ac.wis.cs.playgo.playtoolkit.ebridge;



public interface IExecutionBridge {

//	void stop();
	
	void register(IExecutionCoordinator playgo);
	
	IExecutionEngine instantiateEngine(String EEName, String objName, String className);
	
	IExecutionEngine getExecutionEngine(String EEName);
	
	void runCycle();
	
	// PlayGo informs the execution engine on an event:
	void activateMethod(final String className, final String objectName, 
			final String methodName, final String... arguments);
	
	
	// PlayGo informs the execution engine of a change in a variable value:
	void setPropertyValue(final String className, final String objectName, 
			final String propertyName, final String type, final String value);

	// Execution engine retrieve property value from PlayGo (used for initializing the default values, based on the systemmodel default values)
	public Object getPropertyValue(String className, String objectName, String propertyName, String type);

	// Execution engine informs PlayGo on a system event (may be a property change):
	public void systemEvent(String ExecutionEngineId, String sourceClassName, String sourceObjectName, String targetClassName, String targetObjectName, String eventName);
	
	// Execution engine informs PlayGo on a 'self' system event (may be a property change):
	void systemEvent(String ExecutionEngineId, String className, String objectName, String eventName);

	public String getOriginatedFromExecutionEngine();
	
	public void setOriginatedFromExecutionEngine(String executionEngineId);
}

