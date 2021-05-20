package il.ac.wis.cs.playgo.playtoolkit;

import java.util.ArrayList;

public class PlayGoMessage 
{
	private String sourceClassName;
	private String sourceObjectName;
	private String targetClassName;
	private String targetObjectName;
	
	private String eventName;
	private ArrayList<String> argTypes;
	private ArrayList<String> argValues;
	private long creationTime;
	
	//CTOR
	public PlayGoMessage(String sourceClassName, String sourceObjectName, 
			String targetClassName, String targetObjectName,
			String eventName,
			ArrayList<String> argTypes, ArrayList<String> argValues, long creationTime){
		this.sourceClassName = sourceClassName;
		this.sourceObjectName = sourceObjectName;
		this.targetClassName = targetClassName;
		this.targetObjectName = targetObjectName;
		this.eventName = eventName;
		this.argTypes = argTypes;
		this.argValues = argValues;
		this.creationTime = creationTime;
	}

	
	public String getSourceClassName() {
		return sourceClassName;
	}
	public String getSourceObjectName() {
		return sourceObjectName;
	}
	public String getTargetClassName() {
		return targetClassName;
	}
	public String getTargetObjectName() {
		return targetObjectName;
	}
	public String getEventName() {
		return eventName;
	}
	public ArrayList<String> getArgTypes() {
		return argTypes;
	}
	public ArrayList<String> getArgValues() {
		return argValues;
	}
	public long getCreationTime() {
		return creationTime;
	}
}
