package il.ac.wis.cs.playgo.playtoolkit.ebridge;


public interface IExecutionEngine {

	// initializes the execution engine
	void initEE();
	
	void setExecutionBridge(IExecutionBridge eb);
	
	// PlayGo informs the execution engine on an event:
	void activateMethod(final String className, final String objectName, 
			final String methodName, final String... arguments);
	
	
	// PlayGo informs the execution engine of a change in a variable value:
	void setPropertyValue(final String className, final String objectName, 
			final String propertyName, final String value);

	// Execution engine informs PlayGo on a system event (may be a property change):
	void systemEvent(String className, String objectName, String eventName);
	
	// Execution engine retrieve property value from PlayGo (used for initializing the default values, based on the systemmodel default values)
	public Object getPropertyValue(String className, String objectName, String propertyName, String type);

}

