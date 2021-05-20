
package playgo.systemmodel.generated;

import il.ac.wis.cs.playgo.playtoolkit.AppObjects;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import playgo.systemmodel.classes.Dog;
import playgo.systemmodel.classes.House;


/**
 * This file was automatically generated using PlayGo system-model.
 * This class shouldn't be changed. Any change to this class will be overridden!!!
 * 
 */
@Aspect
public class ObjectsInstantiatorAspect {

    private boolean smFirstTime = true;
    private boolean appFirstTime = true;

    @Pointcut("execution(il.ac.wis.cs.playgo.playtoolkit.api.impl.java.GuiJAgent.new(..)) ")
    void systemModelMainInit() {
    }

    @After("systemModelMainInit()")
    public void smInit(JoinPoint thisJoinPoint)
        throws Throwable
    {
        if (smFirstTime) {
        	smFirstTime = false;
        	playgo.systemmodel.SystemModelMain.getInstance();
        }
    }

    @Pointcut("execution(void playgo.systemmodel.SystemModelMain.createSMObjects())")
    void createAppObjects() {
    }

    @After("createAppObjects()")
    public void creObjects(JoinPoint thisJoinPoint)
        throws Throwable
    {
        if (appFirstTime) {
        	appFirstTime = false;
        	instantiateExecutionBridges();
        	createSystemModelObjects();
        }
    }

    private void createSystemModelObjects() {
        try {
            AppObjects.addObject(playgo.systemmodel.classes.Clock.getInstance());
            Object object = null;
            object = new House("house");
            AppObjects.addObject(object);
            object = new playgo.systemmodel.classes.Person("owner");
            AppObjects.addObject(object);
            object = new Dog("dog");
            AppObjects.addObject(object);
            object = new playgo.systemmodel.classes.Person("stranger");
            AppObjects.addObject(object);
            object = new playgo.systemmodel.classes.Env("Env");
            AppObjects.addObject(object);
            object = new playgo.systemmodel.classes.Env("User");
            AppObjects.addObject(object);
        } catch (Exception _x) {
            _x.printStackTrace();
        }
    }

    public void instantiateExecutionBridges() {
    }

}
