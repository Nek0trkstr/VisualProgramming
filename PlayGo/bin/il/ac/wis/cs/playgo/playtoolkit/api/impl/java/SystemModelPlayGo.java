package il.ac.wis.cs.playgo.playtoolkit.api.impl.java;

import il.ac.wis.cs.playgo.playtoolkit.SyncMessageHandler;
import il.ac.wis.cs.playgo.playtoolkit.api.intf.IPlayGo;
import il.ac.wis.cs.playgo.playtoolkit.api.intf.IPlayable;
import il.ac.wis.cs.playgo.playtoolkit.ebridge.IExecutionBridge;
import il.ac.wis.cs.playgo.playtoolkit.ebridge.IExecutionCoordinator;

import java.util.ArrayList;


public class SystemModelPlayGo implements IPlayGo, IExecutionCoordinator
{
	public String RIGHT_CLICK_EVENT = 	"RightClickEvent";

	private static SystemModelPlayGo instance = null;
	
    //IPlayGo
    public IPlayable playable = null;
    
    public static ArrayList<IExecutionBridge> executionBridges = null;
    
    //CTOR
    protected SystemModelPlayGo(){
    	if (executionBridges == null){
    		executionBridges = new ArrayList<IExecutionBridge>();
    	}
    	instance = this;
    }
    
//    public static boolean informGui=true; 
//    
//    public static boolean informGui(){
//    	return informGui;
//    }
    
//	public String CLICK_EVENT = 		"ClickEvent";
//	public String SWIPE_EVENT = 		"SwipeEvent";
//	public String PUSH_EVENT = 			"PushEvent";
//	public String RELEASE_EVENT = 		"ReleaseEvent";
	
    public static SystemModelPlayGo getInstance() {
        return instance;
    }

	/*
	 * Used by the GUI to inform the behavior that a certain event has occurred in a specific 
	 * object (e.g., click, swipe, push, release, etc.).
	 * It is also used to inform the behavior that a value of some object’s property was changed.
	 * Should it be used to notify PlayGo that a method was called 
	 * (in case one object calls another object within the GUI app, 
	 * not because it was instructed to do so by PlayGo).
	 */
	@Override
	public void objectEvent(final String className, 
			final String objectName, final String eventName, 
			ArrayList<String> argTypes, ArrayList<String> argValues) 
	{
		SyncMessageHandler.getInstance().
			handleGuiMessage("Env", "User", 
					className, objectName, 
					eventName, argTypes, argValues);		
	}
	
	//systemEvent - used for simulation of internal system events via system model call-other-object calls
	public void systemEvent(String sourceClassName, String sourceObjectName,
			String targetClassName, String targetObjectName,
			String eventName, ArrayList<String> argTypes, ArrayList<String> argValues) 
	{
		SyncMessageHandler.getInstance().
			handleGuiMessage(sourceClassName, sourceObjectName,  
       			targetClassName, targetObjectName, 
        		eventName, argTypes, argValues);
	}

	@Override
	public void objectPropertyChanged(final String className, final String objectName,
			final String propertyName, String type, String value) 
	{
		String methodtName = "set" + propertyName.substring(0,1).toUpperCase() +
				propertyName.substring(1);
		
		ArrayList<String> argTypes = new ArrayList<String>();
		argTypes.add(type);
		ArrayList<String> argValues = new ArrayList<String>();
		argValues.add(value);
		
		objectEvent(className, objectName, methodtName, argTypes, argValues); 
	}

	@Override
	public void objectPropertyChanged(String sourceClass, String sourceObject,
			String targetClass, String targetObject, 
			String propertyName, String type, String value) 
	{
		String methodtName = "set" + propertyName.substring(0,1).toUpperCase() +
				propertyName.substring(1);
		
		ArrayList<String> argTypes = new ArrayList<String>();
		argTypes.add(type);
		ArrayList<String> argValues = new ArrayList<String>();
		argValues.add(value);
		
		SyncMessageHandler.getInstance().handleGuiMessage(
				sourceClass, sourceObject,
       			targetClass, targetObject, 
       			methodtName, argTypes, argValues);
	}


	@Override
	public void objectRightClicked(String className, String objectName)
	{
		objectEvent(className, objectName, RIGHT_CLICK_EVENT, null, null);
	}

	@Override
	public void setPlayable(IPlayable playable) {
		this.playable = playable;
	}
	
	@Override
	public IPlayable getPlayable() {
		return playable;
	}

	@Override
	public void register(IExecutionBridge eb) {
		executionBridges.add(eb);
		
	}

	@Override
	public void appIsUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void instantiateEngine(String engineName,
			String objectName, String className) {
		for (IExecutionBridge executionBridge : executionBridges) {
				executionBridge.instantiateEngine(engineName, objectName, className);
		}
	}

	// PlayGo informs executionEngines on execute event:
	@Override
	public void activateMethod(String className, String objectName,
			String methodName, String... arguments) {
		for (IExecutionBridge executionBridge : executionBridges) {
			executionBridge.activateMethod(className, objectName, methodName, arguments);
		}
	}
	
	// PlayGo coordinates the current step in all execution engines:
	@Override
	public void coordinateAll() {
		for (IExecutionBridge executionBridge : executionBridges) {
			executionBridge.runCycle();
		}
	}

	// PlayGo informs executionEngines on property change:
	@Override
	public void setPropertyValue(String className, String objectName,
			String propertyName, String type, String value) {
		for (IExecutionBridge executionBridge : executionBridges) {
			executionBridge.setPropertyValue(className, objectName, propertyName, type, value);
		}
	}
}