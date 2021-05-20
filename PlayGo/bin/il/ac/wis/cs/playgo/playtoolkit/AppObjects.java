package il.ac.wis.cs.playgo.playtoolkit;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class AppObjects {

	private static HashMap<String, Object> objects = new HashMap<String, Object>();
	
	private final static String Env = "Env";
	private final static String Clock = "Clock";
	//private CTOR
	private AppObjects(){
		super();	
	}
	
	//add object to (stubs) pool
	public static void addObject(Object object){
		String key = getObjectName(object) + ":" + object.getClass().getSimpleName();
		if(objects.get(key)==null){
			objects.put(key,object);
//			System.out.println("object added. key: " + key+ ". object: " + object);
		}
//		else{
//			System.out.println(">>>!!!! object with key " + key + 
//					" already exists in ApplicationObjectsPool !!!!<<<");
//		}
	}

	//get object from (stubs) pool
	public static Object getObject(String objectName, String classSimpleName){
		if (classSimpleName == null)
			return null;	
		
		if(classSimpleName.indexOf('.')>0){
			classSimpleName = classSimpleName.substring(
					classSimpleName.lastIndexOf('.')+1);
		}
		if(classSimpleName.equalsIgnoreCase(Env)){
			classSimpleName=Env;
			if(objectName==null || objectName.isEmpty() || objectName.equalsIgnoreCase(Env)){
				objectName=Env;
			}
		}
		else if(classSimpleName.equalsIgnoreCase(Clock)){
			classSimpleName=Clock;
			objectName=Clock;
		}
		String key = objectName + ":" + classSimpleName;
		Object res = objects.get(key);
		if(res!=null){
			if(!classSimpleName.equalsIgnoreCase("user") && 
					!res.getClass().getName().endsWith(classSimpleName)){
				System.out.println("found object is an instance of " + res.getClass().getName()+
					" while it should be an instance of " + classSimpleName);
			}
		}
		return res;
	}
	
	//remove object from pool
	public static Object remove(String objectName, String classSimpleName){
		if(classSimpleName.equalsIgnoreCase(Env)){
			classSimpleName=Env;
			if(objectName==null || objectName.isEmpty() || objectName.equalsIgnoreCase(Env)){
				objectName=Env;
			}
		}
		else if(classSimpleName.equalsIgnoreCase(Clock)){
			classSimpleName=Clock;
			objectName=Clock;
		}
		String key = objectName + ":" + classSimpleName;
		return objects.remove(key);
	}

	public static ArrayList<Object> getKeys(){
		ArrayList<Object> objs = new ArrayList<Object>();
		objs.addAll(objects.keySet());
		return objs;
	}

	public static ArrayList<Object> getObjects(){
		ArrayList<Object> objs = new ArrayList<Object>();
		objs.addAll(objects.values());
		return objs;
	}
	
	//getObjectName
	public static String getObjectName(Object appObject) {
		String name = null;
		try {
			Method mGetName = appObject.getClass().getMethod("getName");
			name = (String) mGetName.invoke(appObject, null);
		} catch (NoSuchMethodException e){
			//if no name exists, use obj.toString() as a name (should be replaced later in the app using setName method)
			name=appObject.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	public static ArrayList<Object> getObjects(String classSimpleName)
	{
		return getObjects(classSimpleName, true);
	}
	
	public static ArrayList<Object> getObjects(String classSimpleName, boolean recursively)
	{
		if (classSimpleName == null || objects==null || objects.size()<1){
			return null;	
		}
		ArrayList<Object> res = new ArrayList<Object>(); 
		
		Class<?> superClass;
		String superClassName;
		
		for(Object appObj:objects.values())
		{
			if(appObj.getClass().getSimpleName().equals(classSimpleName)){
				res.add(appObj);
			}
			else if(recursively)
			{
				superClass = appObj.getClass().getSuperclass();
				superClassName = null;
				
				if(superClass!=null){
					superClassName = superClass.getSimpleName();
				}
				while(superClass!=null && superClassName!=null && superClass.getName().contains("systemmodel"))
				{
					if(superClassName.equals(classSimpleName)){
						res.add(appObj);
						break;
					}
					superClass = superClass.getSuperclass();
					superClassName = null;
					
					if(superClass!=null){
						superClassName = superClass.getSimpleName();
					}
				}
			}
		}
		return res;
	}	

//	public static ArrayList<Object> getObjects(String classSimpleName)
//	{
//		if (classSimpleName == null || objects==null || objects.size()<1){
//			return null;	
//		}
//		ArrayList<Object> res = new ArrayList<Object>(); 
//		
//		String keySimpleName;
//		int ix;
//		for(String key:objects.keySet())
//		{
//			ix = key.indexOf(':');
//			keySimpleName = key.substring(ix+1);
//			
//			if(keySimpleName.equals(classSimpleName)){
//				res.add(objects.get(key));
//			}
//		}
//		return res;
//	}	

}
