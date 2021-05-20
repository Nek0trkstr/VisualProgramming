package il.ac.wis.cs.playgo.playtoolkit;

import il.ac.wis.cs.playgo.playtoolkit.api.impl.java.SystemModelPlayGo;
import il.ac.wis.cs.s2a.runtime.lib.MSDPlayoutView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Responsible for invoking all messages on a single thread
 * When a specification includes clock, or when running with a TCP agent then there is
 * more than one thread and this class is responsible for invoking all messages on the same thread. 
 */
public class SyncMessageHandler 
{
//	public static final String SM_TYPES = "playgo.systemmodel.types";

	private List<PlayGoMessage> playMessages = null;
	
	private TimerTask messagesHandler;
	private int tickInterval;

	private static SyncMessageHandler instance = null;
	
	//getInstance
	public static SyncMessageHandler getInstance(){
		if(instance==null){
			instance = new SyncMessageHandler();
		}
		return instance;
	}
	
	//CTOR
	private SyncMessageHandler(){
		instance = this;
		playMessages = Collections.synchronizedList(new ArrayList<PlayGoMessage>());
        handleMessages();
	}
	
	//handleMessages
	private void handleMessages()
	{
		messagesHandler = new TimerTask() 
		{
			@Override
			public void run() 
			{
				if(playMessages!=null && playMessages.size()>0){
					synchronized(playMessages){
						//for(PlayGoMessage playMes:playMessages)
						//{
						
							PlayGoMessage playMes = playMessages.remove(0);
						
							if(playMes.getTargetObjectName().equals("Clock") && playMes.getEventName().equals("tick")){
								long currentTime = System.currentTimeMillis();
								long delay = currentTime - playMes.getCreationTime();
								
								if(delay > 2*tickInterval){
									System.out.println("@@@ !!!WARNING: tick accoured in a long delay: " + delay + ", tickInterval: " + tickInterval + "!!! @@@");
								}
						       	invokeMessage(playMes.getTargetObjectName(), playMes.getTargetClassName(),
							       		playMes.getTargetObjectName(), playMes.getTargetClassName(),
							       		playMes.getEventName(), 
							       		playMes.getArgTypes(), playMes.getArgValues());
							}
							else{
								MSDPlayoutView.setDebugLine("External Event[User]", System.currentTimeMillis(), playMes.getEventName());
						       	invokeMessage(playMes.getSourceObjectName(), playMes.getSourceClassName(),
						       		playMes.getTargetObjectName(), playMes.getTargetClassName(),
						       		playMes.getEventName(), 
						       		playMes.getArgTypes(), playMes.getArgValues());
							}
			   	        //}
						//playMessages.clear();
							// advance the flow of all execution engines:
						SystemModelPlayGo.getInstance().coordinateAll();
					}
				}
			}
		};
		Timer timer = new Timer();
        timer.scheduleAtFixedRate(messagesHandler, 10, 100);
	}
	
	//clockTcik
	public void clockTick(int tickInterval)
	{
		this.tickInterval = tickInterval;
		
		PlayGoMessage tickMessage = new PlayGoMessage(
				"Clock", "Clock",
				"Clock", "Clock", 
				"tick",
				null, null, System.currentTimeMillis());
		
		playMessages.add(tickMessage);	
	}
	
	
	//invokeMessage
	public static void invokeMessage(String sourceName, String sourceClassName, 
			String targetName,String targetClassName, String methodName, 
			ArrayList<String> argTypes, ArrayList<String> argValues)
	{
		Method meth=null;
		try {
			Object targetObj = AppObjects.getObject(targetName, targetClassName);
			Object sourceObj = AppObjects.getObject(sourceName, sourceClassName);
			
			if (sourceObj == null) {
				throw new RuntimeException("No object: " + sourceName + " of type: " + sourceClassName + " in the system model.");
			}
			if (targetObj == null){
				throw new RuntimeException("No object: " + targetName + " of type: " + targetClassName + " in the system model.");
			}
			
			//No Args
			if(argValues==null || argValues.size()<1)
			{
				try{
					if(sourceClassName.equals("Env") && methodName.equals("noop")){	//NoOp
						meth = sourceObj.getClass().getMethod(methodName);
						meth.invoke(sourceObj);
						return;
					}else if(sourceClassName.equals("Clock") && methodName.equals("tick")){	//tick
						meth = sourceObj.getClass().getMethod(methodName);
						meth.invoke(sourceObj);
						return;
					}else{
						meth = getMethod(sourceObj.getClass(), targetObj.getClass(), methodName, null, null, true);
					}
					
				} catch (NoSuchMethodException e){
					meth = targetObj.getClass().getMethod(methodName);
					meth.invoke(targetObj);
					meth = null; // already invoked.
//					return;
//					System.out.println("No such method: " + "Wrapper"+methodName+"("+targetObj.getClass()+")");
//					meth = null;
				}
				if (meth != null)
				{
					meth.invoke(sourceObj,targetObj);
				}
			}
			else	//Args exist
			{
				boolean wrapperExists = true;
				
				meth = getMethod(sourceObj.getClass(), targetObj.getClass(), 
						methodName, argTypes, argValues, wrapperExists);
				if(meth==null)
				{
					//we allow setter with no wrapper for cases where the GUI sets a property value and
					//sends the updated value to the behavior and no set message exists in the LSCs
					if(methodName.startsWith("set"))	//setter
					{
						wrapperExists = false;
						//meth = getMethod(sourceObj.getClass(), null, methodName, argTypes, argValues, wrapperExists);
						meth = getMethod(targetObj.getClass(), null, methodName, argTypes, argValues, wrapperExists);
						if(meth==null)
						{
							System.out.println("No such method: "
									+ sourceObj.getClass().getSimpleName() +"."+methodName);
							return;
						}
					}else{								//not a setter
						return;
					}
				}
				//loop vars
				int numOfArgs = argValues.size();
				Object[] values;
				if(wrapperExists){
					values = new Object[numOfArgs+1];
					values[0] = targetObj;
				}else{
					values = new Object[numOfArgs];					
				}
				for(int i=0; i<numOfArgs; i++){
					if(wrapperExists){
						values[i+1]=Utils.getObjectVal(meth.getParameterTypes()[i+1],argValues.get(i));
						meth.invoke(sourceObj,values);
					}else{
						values[i]=Utils.getObjectVal(meth.getParameterTypes()[i],argValues.get(i));
						meth.invoke(targetObj,values);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Method getMethod(Class<?> sourceClass, Class<?> targetClass,
			String methodName, ArrayList<String> argTypes, ArrayList<String> argValues, 
			boolean wrapperExists) throws NoSuchMethodException, SecurityException {
		try{
			if (wrapperExists){
				methodName = "Wrapper" + methodName;
			}
			Method meth = null;
			if (argValues == null){
				meth = sourceClass.getMethod(methodName,targetClass);
				return meth;
			}
			else {
				if (wrapperExists){
					meth = getMethod(sourceClass, targetClass, methodName, argValues.size() + 1, argTypes);
				}
				else{
					meth = getMethod(sourceClass, null, methodName, argValues.size(), argTypes);
				}
				return meth;
			}
		}
		catch (NoSuchMethodException e){
			if (targetClass == null || targetClass.getSuperclass() == null){
				throw new NoSuchMethodException();
			}
			targetClass = targetClass.getSuperclass();
			return getMethod(sourceClass, targetClass, methodName, argTypes, argValues, wrapperExists);
		}
	}

	//getMethod
	private static Method getMethod(Class<?> cls, Class<?> targetCls, 
			String methodName, int numOfArgs, ArrayList<String> argTypes) 
	{
		if(cls ==null){
			return null;
		}
		if(cls==null || cls.getMethods()==null || cls.getMethods().length<1){
			return null;
		}
		Method meth = getMethByNumOfArgs(cls, targetCls, methodName, numOfArgs);
		if(meth!=null){
			return meth;
		}
		for(Method m:cls.getMethods())
		{
			if( ! m.getName().equals(methodName)){
				continue;
			}
			if(m.getParameterTypes()==null || m.getParameterTypes().length != numOfArgs){
				continue;
			}
			if(targetCls != null && targetCls != m.getParameterTypes()[0]){
				continue;				
			}
			if(numOfArgs>0){
				int i=0;
				for(Class<?> methArgTyp:m.getParameterTypes()){
					if( ! methArgTyp.getSimpleName().equals(argTypes.get(i))){
						continue;
					}
					i++;
				}
			}
			return m;
		}
		return null;
	}

	//getMethByNumOfArgs
	private static Method getMethByNumOfArgs(Class<?> cls, Class<?> targetCls,
			String methodName, int numOfArgs) 
	{
		ArrayList<Method> foundMethods = new ArrayList<Method>();
		
		for(Method m:cls.getMethods())
		{
			if( ! m.getName().equals(methodName)){
				continue;
			}
			if(m.getParameterTypes()==null || m.getParameterTypes().length != numOfArgs){
				continue;
			}
			if(targetCls != null && targetCls != m.getParameterTypes()[0]){
				continue;				
			}
			foundMethods.add(m);
		}
		if(foundMethods.size()==1){
			return foundMethods.get(0);
		}
		return null;
	}

	//getObjectVal
//	public static Object getObjectVal(Class<?> argType, String strValue) 
//	{
//		if (strValue == null)
//			return null;
//		
//		Object argVal=null;
//		
//		if(argType == String.class){
//			argVal=strValue;
//		}
//		else if(argType == Integer.class || argType == int.class){
//			argVal=Integer.parseInt(strValue);
//		}
//		else if(argType == Boolean.class || argType == boolean.class){
//			argVal=Boolean.parseBoolean(strValue);
//		}
//		else if(argType == Long.class || argType == long.class){
//			argVal=Long.parseLong(strValue);
//		}
//		else if(argType == Float.class || argType == float.class){
//			argVal=Float.parseFloat(strValue);
//		}
//		else if(argType == Double.class || argType == double.class){
//			argVal=Double.parseDouble(strValue);
//		}
//		else if(argType == Byte.class || argType == byte.class){
//			argVal=Byte.parseByte(strValue);
//		}
//		else if(argType == Short.class || argType == short.class){
//			argVal=Short.parseShort(strValue);
//		}
//		else if(argType == Character.class || argType == char.class){
//			argVal=new String(strValue).charAt(0);
//		}
//		//System Model Enum
//		if(argType.getName().startsWith(SM_TYPES)){
//			for(Object enumConst : argType.getEnumConstants()){
//				if(enumConst.toString().equals(strValue)){
//					return enumConst;
//				}
//			}
//			//argVal = argType.getEnumConstants()[0].toString()
//		}
//		return argVal;
//	}
	
	//invokeMessage
	public static void invokeSetter(String sourceName, String sourceClassName, 
			String targetName, String targetClassName,
			String setterName, Class<?> argType, String strValue)
	{
		ArrayList<String> argTypes = new ArrayList<String>();
		argTypes.add(argType.getSimpleName());
		
		ArrayList<String> parameterValues = new ArrayList<String>();
		parameterValues.add(strValue);
		
		invokeMessage(sourceName, sourceClassName, 
				targetName, targetClassName, 
				setterName, 
				argTypes, 
				parameterValues);
	}

	//invokeMessage
	public static Object invokeGetter(String objectName, String className, String getterName) throws ReflectiveOperationException
	{
		Method meth=null;
		Object targetObj = AppObjects.getObject(objectName, className);
		
		if (targetObj == null){
			throw new RuntimeException("No object: " + objectName + " of type: " + className + " in the system model.");
		}
		
		try{
				meth = getMethod(targetObj.getClass(), targetObj.getClass(), getterName, null, null, true);
			
		} catch (NoSuchMethodException e){
			meth = targetObj.getClass().getMethod(getterName);
			return meth.invoke(targetObj);
		}
		if (meth != null)
		{
			return meth.invoke(targetObj,targetObj);
		}
		
		return null;
	}

	//handleGuiMessage
	public void handleGuiMessage(String className, String objectName,
			String eventName, ArrayList<String> argTypes, ArrayList<String> argValues) 
	{
		//playMessages.add(0, new PlayGoMessage("Env", "User", 
		playMessages.add(new PlayGoMessage("Env", "User",
				className, objectName, eventName, argTypes, argValues, System.currentTimeMillis()));
	}

	//handleGuiMessage
	public void handleGuiMessage(String sourceClassName, String sourceObjectName,
			String targetClassName, String targetObjectName,
			String eventName, ArrayList<String> argTypes, ArrayList<String> argValues) 
	{
		//playMessages.add(0, new PlayGoMessage(sourceClassName, sourceObjectName,
		playMessages.add(new PlayGoMessage(sourceClassName, sourceObjectName,		
				targetClassName, targetObjectName, eventName, argTypes, argValues, System.currentTimeMillis()));
	}

}
