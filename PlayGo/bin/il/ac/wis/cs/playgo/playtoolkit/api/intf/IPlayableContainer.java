package il.ac.wis.cs.playgo.playtoolkit.api.intf;

import java.util.ArrayList;


public interface IPlayableContainer
{
	void add(IPlayableComponent comp, String compName); // also sets the comp name to be compName
	
	/*
	 * 	Used by the GUI to inform PlayGo that a certain event has occurred in a specific object 
	 * (e.g., click, swipe, push, release, etc.).
	 * It is also used to inform PlayGo that a value of some object?s property was changed.
	 * Should it be used to notify PlayGo that a method was called 
	 * (in case one object calls another object within the GUI app, 
	 * not because it was instructed to do so by PlayGo).
	 */
	void objectEvent(String className, String objectName, String eventName, 
			ArrayList<String> argTypes, ArrayList<String> argValues);
	
	/*
	 * Used by the GUI to inform PlayGo that a certain object was right-clicked. 
	 * This is a special event since it is used by PlayGo as part of the play-in mechanism.
	 */
	void objectRightClicked(String className, String objectName);
	
	void objectPropertyChanged(String sourceClass, String sourceObject, String targetClass, 
			String targetObject, String propertyName, String type, String value);
	
	//when no source class+object is sent, User is assumed to be the source
	void objectPropertyChanged(String className, String objectName, String propertyName, 
			String type, String value); 
	
	void containerRepaint(); 

}
