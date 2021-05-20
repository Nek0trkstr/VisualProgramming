
package playgo.systemmodel.classes.generated;

import il.ac.wis.cs.systemmodel.SMBaseClass;
import il.ac.wis.cs.systemmodel.SystemModelGen;


/**
 * This file was automatically generated using PlayGo system-model.
 * This class shouldn't be changed. Any change to this class will be overridden!!!
 * 
 */
public class PersonGenerated
    extends SMBaseClass
{


    public PersonGenerated(java.lang.String name) {
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
        	activateMethod("Person", name, "enter");

        //GUI handling
        playgo.systemmodel.SystemModelMain.getInstance().getPlayable().
        		activateMethod("Person", name, "enter");
    }

    @SystemModelGen
    public void say(String arg0) {
        if(java.lang.System.getProperty("SYSTEM_MODEL_DEBUG")!=null){
        	java.lang.System.out.println(">>> method call: >>> say(String) : void");
        }
        
		//ExecutionBridge handling
        playgo.systemmodel.SystemModelMain.getInstance().
        	activateMethod("Person", name, "say");

        //GUI handling
        playgo.systemmodel.SystemModelMain.getInstance().getPlayable().
        	activateMethod("Person", name, "say"
        			,arg0
        			);
    }

}
