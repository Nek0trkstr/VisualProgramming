
package playgo.systemmodel.classes.generated;

import il.ac.wis.cs.systemmodel.SMBaseClass;
import il.ac.wis.cs.systemmodel.SystemModelGen;


/**
 * This file was automatically generated using PlayGo system-model.
 * This class shouldn't be changed. Any change to this class will be overridden!!!
 * 
 */
public class HouseGenerated
    extends SMBaseClass
{

    protected String temperature = ("");

    public HouseGenerated(java.lang.String name) {
        super(name);
    }

    @SystemModelGen
    public String getName() {
        return name;
    }

    @SystemModelGen
    public void enter() {
        if(java.lang.System.getProperty("SYSTEM_MODEL_DEBUG")!=null){
        	java.lang.System.out.println(">>> method call: >>> enter() : void");
        }
        
		//ExecutionBridge handling
        playgo.systemmodel.SystemModelMain.getInstance().
        	activateMethod("House", name, "enter");

        //GUI handling
        playgo.systemmodel.SystemModelMain.getInstance().getPlayable().
        		activateMethod("House", name, "enter");
    }

    @SystemModelGen
    public String getTemperature() {
        return temperature;
    }

    @SystemModelGen
    public void setTemperature(String arg0) {
        this.temperature=arg0;
        if(java.lang.System.getProperty("SYSTEM_MODEL_DEBUG")!=null){
        	java.lang.System.out.println(">>> method call: >>> setTemperature(String) : void");
        }
        //GUI handling
        playgo.systemmodel.SystemModelMain.getInstance().getPlayable().
        	setPropertyValue("House", name, "temperature", arg0);
         
        playgo.systemmodel.SystemModelAgent.getInstance().setPropertyValue("House", name, "temperature", arg0);
        
		//ExecutionBridge handling
        playgo.systemmodel.SystemModelMain.getInstance().
        	setPropertyValue("House", name, "temperature", "String", String.valueOf(arg0));

    }

    @SystemModelGen
    public void pgInitTemperature(String arg0) {
        this.temperature=arg0;
         
        playgo.systemmodel.SystemModelAgent.getInstance().setPropertyValue("House", name, "temperature", arg0);
    }

}
