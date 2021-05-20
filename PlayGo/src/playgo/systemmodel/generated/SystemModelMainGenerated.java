
package playgo.systemmodel.generated;

import il.ac.wis.cs.playgo.playtoolkit.api.impl.java.SystemModelPlayGo;


/**
 * This file was automatically generated using PlayGo system-model.
 * This class shouldn't be changed. Any change to this class will be overridden!!!
 * 
 */
public class SystemModelMainGenerated
    extends SystemModelPlayGo
{


    public void runBThreads() {
    }

    @Override
    public void appIsUp() {
        try {
            Object object = null;
            String retval = null;
             
            object = il.ac.wis.cs.playgo.playtoolkit.AppObjects.getObject(
				"house", "House");
             
            retval = playgo.systemmodel.SystemModelMain.getInstance().
				getPlayable().getPropertyValue("House", "house", "temperature");
            if(retval!=null){
            	((playgo.systemmodel.classes.House)object).pgInitTemperature(retval);
            }
            object = il.ac.wis.cs.playgo.playtoolkit.AppObjects.getObject(
				"owner", "Person");
             
            object = il.ac.wis.cs.playgo.playtoolkit.AppObjects.getObject(
				"dog", "Dog");
             
            retval = playgo.systemmodel.SystemModelMain.getInstance().
				getPlayable().getPropertyValue("Dog", "dog", "battery");
            if(retval!=null){
            	((playgo.systemmodel.classes.Dog)object).pgInitBattery(retval);
            }
            object = il.ac.wis.cs.playgo.playtoolkit.AppObjects.getObject(
				"stranger", "Person");
             
            object = il.ac.wis.cs.playgo.playtoolkit.AppObjects.getObject(
				"Env", "Env");
             
            object = il.ac.wis.cs.playgo.playtoolkit.AppObjects.getObject(
				"User", "Env");
             
             
            runBThreads();
             
            //start clock
            playgo.systemmodel.classes.Clock clock = playgo.systemmodel.classes.Clock.getInstance();
            clock.startTimer();
        } catch (Exception _x) {
            _x.printStackTrace();
        }
    }

    @Override
    public void stop() {
    }

}
