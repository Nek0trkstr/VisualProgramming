package il.ac.wis.cs.playgo.playtoolkit.ebridge;

public interface IExecutionCoordinator {

	void register(IExecutionBridge eb);
		
	void instantiateEngine(String engineName, String objectName, String className);

	// PlayGo informs executionEngines on execute event:
	public void activateMethod(final String className, final String objectName, 
			final String methodName, final String... arguments);
	
	// PlayGo informs Execution Engines of property change
	public void setPropertyValue(String className, String objectName,
			String propertyName, String type, String value);
	
	void coordinateAll();

}
