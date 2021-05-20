
package playgo.systemmodel.classes.generated;

import il.ac.wis.cs.systemmodel.SMBaseClass;
import il.ac.wis.cs.systemmodel.SystemModelGen;


/**
 * This file was automatically generated using PlayGo system-model.
 * This class shouldn't be changed. Any change to this class will be overridden!!!
 * 
 */
public class DogGenerated
    extends SMBaseClass
{

    protected String battery = ("");

    public DogGenerated(java.lang.String name) {
        super(name);
    }

    @SystemModelGen
    public String getName() {
        return name;
    }

    @SystemModelGen
    public String getBattery() {
        return battery;
    }

    @SystemModelGen
    public void setBattery(String arg0) {
        this.battery=arg0;
        if(java.lang.System.getProperty("SYSTEM_MODEL_DEBUG")!=null){
        	java.lang.System.out.println(">>> method call: >>> setBattery(String) : void");
        }
        //GUI handling
        playgo.systemmodel.SystemModelMain.getInstance().getPlayable().
        	setPropertyValue("Dog", name, "battery", arg0);
         
        playgo.systemmodel.SystemModelAgent.getInstance().setPropertyValue("Dog", name, "battery", arg0);
        
		//ExecutionBridge handling
        playgo.systemmodel.SystemModelMain.getInstance().
        	setPropertyValue("Dog", name, "battery", "String", String.valueOf(arg0));

    }

    @SystemModelGen
    public void pgInitBattery(String arg0) {
        this.battery=arg0;
         
        playgo.systemmodel.SystemModelAgent.getInstance().setPropertyValue("Dog", name, "battery", arg0);
    }

    @SystemModelGen
    public void bark() {
        if(java.lang.System.getProperty("SYSTEM_MODEL_DEBUG")!=null){
        	java.lang.System.out.println(">>> method call: >>> bark() : void");
        }
        
		//ExecutionBridge handling
        playgo.systemmodel.SystemModelMain.getInstance().
        	activateMethod("Dog", name, "bark");

        //GUI handling
        playgo.systemmodel.SystemModelMain.getInstance().getPlayable().
        		activateMethod("Dog", name, "bark");
    }

    @SystemModelGen
    public void bring(String arg0) {
        if(java.lang.System.getProperty("SYSTEM_MODEL_DEBUG")!=null){
        	java.lang.System.out.println(">>> method call: >>> bring(String) : void");
        }
        
		//ExecutionBridge handling
        playgo.systemmodel.SystemModelMain.getInstance().
        	activateMethod("Dog", name, "bring");

        //GUI handling
        playgo.systemmodel.SystemModelMain.getInstance().getPlayable().
        	activateMethod("Dog", name, "bring"
        			,arg0
        			);
    }

    @SystemModelGen
    public void wiggle(String arg0) {
        if(java.lang.System.getProperty("SYSTEM_MODEL_DEBUG")!=null){
        	java.lang.System.out.println(">>> method call: >>> wiggle(String) : void");
        }
        
		//ExecutionBridge handling
        playgo.systemmodel.SystemModelMain.getInstance().
        	activateMethod("Dog", name, "wiggle");

        //GUI handling
        playgo.systemmodel.SystemModelMain.getInstance().getPlayable().
        	activateMethod("Dog", name, "wiggle"
        			,arg0
        			);
    }

    @SystemModelGen
    public void run() {
        if(java.lang.System.getProperty("SYSTEM_MODEL_DEBUG")!=null){
        	java.lang.System.out.println(">>> method call: >>> run() : void");
        }
        
		//ExecutionBridge handling
        playgo.systemmodel.SystemModelMain.getInstance().
        	activateMethod("Dog", name, "run");

        //GUI handling
        playgo.systemmodel.SystemModelMain.getInstance().getPlayable().
        		activateMethod("Dog", name, "run");
    }

}
