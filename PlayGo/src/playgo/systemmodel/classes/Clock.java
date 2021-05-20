
package playgo.systemmodel.classes;

import playgo.systemmodel.classes.generated.ClockGenerated;


/**
 * This file was automatically generated using PlayGo system-model.
 * This class can be updated. Changes will not be overridden
 * 
 */
public class Clock
    extends ClockGenerated
{

    private static Clock instance = null;

    private Clock(String name) {
        super(name);
    }

    public static Clock getInstance() {
        if ((instance == null)) {
            instance = new Clock("Clock");
        }
        return (instance);
    }

}
