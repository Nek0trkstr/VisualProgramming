package il.ac.wis.cs.playgo.playtoolkit;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Utils 
{
	public static final String RUNTIME_PROPERTIES_RELATIVE_PATH = "resources/runtime.properties";
	public static final String SYSTEMMODEL_DEBUG_PORT = "systemmodelDebugPort";

	public static final String SM_TYPES = "playgo.systemmodel.types";

	public static boolean isPlayInMode(){
		String playInStr = System.getProperty("playInMode");
		if(playInStr!=null && playInStr.equals("true")){
			return true;
		}
		return false;
	}

	//getPort
	public static int getPort() 
	{
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(getRuntimePropertiesPath()));
			String portStr = properties.getProperty(SYSTEMMODEL_DEBUG_PORT);
			int port = Integer.valueOf(portStr);
			return port;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}		
	}

	//getRuntimePropertiesPath
	private static String getRuntimePropertiesPath(){
		try{
			return new File(RUNTIME_PROPERTIES_RELATIVE_PATH).getAbsolutePath().toString();
		}catch (Exception e) {
			System.out.println(RUNTIME_PROPERTIES_RELATIVE_PATH + " file does not exist");
			return null;
		}
	}
	
	public static Object getObjectVal(Class<?> argType, String strValue) 
	{
		if (strValue == null)
			return null;
		
		Object argVal=null;
		
		if(argType == String.class){
			argVal=strValue;
		}
		else if(argType == Integer.class || argType == int.class){
			argVal=Integer.parseInt(strValue);
		}
		else if(argType == Boolean.class || argType == boolean.class){
			argVal=Boolean.parseBoolean(strValue);
		}
		else if(argType == Long.class || argType == long.class){
			argVal=Long.parseLong(strValue);
		}
		else if(argType == Float.class || argType == float.class){
			argVal=Float.parseFloat(strValue);
		}
		else if(argType == Double.class || argType == double.class){
			argVal=Double.parseDouble(strValue);
		}
		else if(argType == Byte.class || argType == byte.class){
			argVal=Byte.parseByte(strValue);
		}
		else if(argType == Short.class || argType == short.class){
			argVal=Short.parseShort(strValue);
		}
		else if(argType == Character.class || argType == char.class){
			argVal=new String(strValue).charAt(0);
		}
		//System Model Enum
		if(argType.getName().startsWith(SM_TYPES)){
			for(Object enumConst : argType.getEnumConstants()){
				if(enumConst.toString().equals(strValue)){
					return enumConst;
				}
			}
			//argVal = argType.getEnumConstants()[0].toString()
		}
		return argVal;
	}


}
