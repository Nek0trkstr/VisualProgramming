//  The file MSDAspectLSC1.aj was automatically generated using S2A 2.0.0.201512210921.
//  For information see http://www.wisdom.weizmann.ac.il/~maozs/s2a/

package aspects;

import playgo.systemmodel.classes.*;
import playgo.systemmodel.types.*;
import java.util.ArrayList;
import il.ac.wis.cs.s2a.runtime.lib.*;
import il.ac.wis.cs.playgo.playtoolkit.*;


public aspect MSDAspectLSC1 extends MSDAspect
{


	//Constants for instances, locations and variables
	static final int Person_INST_stranger = 0;
	static final int House_INST_house = 1;
	static final int Dog_INST_dog = 2;



	private final String niceName = "LSC1";

	MSDAspectLSC1()
	{
		addMinimalEvent(MSDMethods.Person_House_enter);
		setHotCut(2,2,1);
		setLastCut(2,2,2);
		numberOfLifeLines = 3;
		numberOfInstances = 3;
		numberOfVariables = 0;
		interactionId = "1620993120463";
		setCutsExpressions();
	}

	//Pointcuts and advices:
	pointcut setCut() : execution(* *.setCut(..));

	after():setCut()
	{
		ArrayList<ActiveMSDAspect> tempActiveArr = new ArrayList<ActiveMSDAspect>();
		tempActiveArr.addAll(getActiveMSDArray());

		for(ActiveMSDAspect liveCopy:tempActiveArr){
			evaluateWaitConditions(liveCopy);	//re-evaluate wait conditions
		}
	}

	//re-evaluate dynamic-universal binding expressions
	pointcut changeActiveMSDCutState() : execution(* *.changeActiveMSDCutState(..));

	after():changeActiveMSDCutState()
	{
		ArrayList<ActiveMSDAspect> tempActiveArr = new ArrayList<ActiveMSDAspect>();
		tempActiveArr.addAll(getActiveMSDArray());

		for(ActiveMSDAspect liveCopy:tempActiveArr){
			if(unboundObjectExists(liveCopy))
			{
				doBindings(liveCopy);				//try to bind objects
				if( ! unboundObjectExists(liveCopy)){
					setUpToDate(false);
				}
			}
		}
		setUpToDate(false);
	}

	private boolean unboundObjectExists(ActiveMSDAspect activeMSD)
	{
	
			return false;
	}

	//this pointcut definition is identical to pointcut Clock_Clock_tick, aspectj does not allow
	//declaring on identical pointcuts, therefore this pointcut must be defined within the method
	after(Object clock):execution(void tick(..))
		&& if(clock instanceof Clock)
		&& target(clock)
	{
		boolean coordinated = false;

		ArrayList<ActiveMSDAspect> tempActiveArr = new ArrayList<ActiveMSDAspect>();
		tempActiveArr.addAll(getActiveMSDArray());

		for(ActiveMSDAspect liveCopy:tempActiveArr){
			evaluateWaitConditions(liveCopy);	//re-evaluate wait conditions
			coordinated = true;
		}

		if (!coordinated) {
			MSDCoordinatorAspect.getInstance().coordinate(null);
		}
	}

	pointcut Dog_Dog_bark(Dog _dogSource, Object _dog):
		call(* bark(..))
		&& if(_dog instanceof Dog)
		&& if(_dogSource instanceof Dog)
		&& target(_dog) && this(_dogSource) ;

	after(Dog _dogSource,Object _dog):Dog_Dog_bark(_dogSource,_dog)
	{
		changeCutState(MSDMethods.Dog_Dog_bark,
					_dog,_dog,null);
	}

	pointcut Person_House_enter(Person _stranger, Object _house):
		call(* enter(..))
		&& if(_house instanceof House)
		&& target(_house) && this(_stranger) ;

	after(Person _stranger,Object _house):Person_House_enter(_stranger,_house)
	{
		changeCutState(MSDMethods.Person_House_enter,
					_stranger,_house,null);
	}

	protected void  setPrivateVariables(int MSDm, Object sourceObject, Object targetObject,ActiveMSDAspect activeMSD,
		ArrayList args)
	{
		switch (MSDm)
		{
		}
	}

	// MSD Logic:
	protected void  changeActiveMSDCutState(int MSDm, Object sourceObject, Object targetObject,ActiveMSDAspect activeMSD,
		ArrayList args)
	{

		boolean unification=false;
		switch (MSDm)
		{
			case MSDMethods.Dog_Dog_bark:
				if(activeMSD.instancesEquals(Dog_INST_dog,sourceObject)
					&& activeMSD.instancesEquals(Dog_INST_dog,targetObject))
				{
					unification=true;
					if(activeMSD.isInCut(2,2,1))
					{
						activeMSD.setCut(2,2,2);
						break;
					}
				}
				if(!unification)//No unification...
					return;
				break;

			case MSDMethods.Person_House_enter:
				if(activeMSD.instancesEquals(Person_INST_stranger,sourceObject)
					&& activeMSD.instancesEquals(House_INST_house,targetObject))
				{
					unification=true;
					if(activeMSD.isInCut(0,0,0))
					{
						activeMSD.setCut(1,1,0);
						if(evaluateCondition(2,activeMSD))
						{
							activeMSD.setCut(2,2,1);
							return;
						}
						break;
					}
				}
				if(!unification)//No unification...
					return;
				break;

			}
		if(activeMSD.checkViolation())
			activeMSD.completion();
	}

	@SuppressWarnings("unused")
	private boolean evaluateCondition(int conditionNumber,ActiveMSDAspect activeMSD)
	{
		Person stranger = (Person)bindObjectByExpression(activeMSD,Person_INST_stranger,null, true);;
		House house = (House)bindObjectByExpression(activeMSD,House_INST_house,null, true);;
		Dog dog = (Dog)bindObjectByExpression(activeMSD,Dog_INST_dog,null, true);;

		switch (conditionNumber)
		{
			case 2: 
				return true;
		}
		return false;
	}


	protected void setCutsExpressions()
	{
		this.setExpressionForCut("2,2,1" , "SYNC0");

	}


	@SuppressWarnings("unused")
	protected void doBindings(ActiveMSDAspect activeMSD)
	{
		activeMSD.setLineInstance(Person_INST_stranger,AppObjects.getObject("stranger","Person"));
		activeMSD.setLineInstance(House_INST_house,AppObjects.getObject("house","House"));
		activeMSD.setLineInstance(Dog_INST_dog,AppObjects.getObject("dog","Dog"));
		Person stranger = (Person)bindObjectByExpression(activeMSD,Person_INST_stranger, null, false );
		House house = (House)bindObjectByExpression(activeMSD,House_INST_house, null, false );
		Dog dog = (Dog)bindObjectByExpression(activeMSD,Dog_INST_dog, null, false );

	}


	protected void  evaluateWaitConditions(ActiveMSDAspect activeMSD)
	{
		for(Integer cond:activeMSD.getWaitingConditions()){
			switch(cond)
			{
			}
		}
	}

	protected void getActiveMSDCutState(MSDMethodSet ME,MSDMethodSet EE,
			MSDMethodSet CV,MSDMethodSet HV,ActiveMSDAspect activeMSD)
	{
		Person stranger = (Person)bindObjectByExpression(activeMSD,Person_INST_stranger,null, true);;
		House house = (House)bindObjectByExpression(activeMSD,House_INST_house,null, true);;
		Dog dog = (Dog)bindObjectByExpression(activeMSD,Dog_INST_dog,null, true);;

		MSDMethod MSDm0 = new MSDMethod(stranger,house,
			MSDMethods.Person_House_enter,null,"Person:House:enter",niceName,activeMSD.getId());// enter() Monitored
		MSDMethod MSDm1 = new MSDMethod(dog,dog,
			MSDMethods.Dog_Dog_bark,null,"Dog:Dog:bark",niceName,activeMSD.getId());// bark() Execute

		 if(activeMSD.isInCut(0,0,0))
		{
				ME.add(MSDm0);
				CV.add(MSDm1);
				return;
		}
		 if(activeMSD.isInCut(2,2,1))
		{
				if(MSDm1.sourceInstance!=null && MSDm1.targetInstance!=null){
					EE.add(MSDm1);
				}
				HV.add(MSDm0);
				return;
		}
	}
	
	@SuppressWarnings("unused")
	public Object bindObjectByExpression (ActiveMSDAspect activeMSD,int lifelineIndex, 
			Object obj, boolean retrieveOnly){

		Object result = null;
		// in case the object is already bounded returning the bounded object
		result = activeMSD.getLineInstance(lifelineIndex);
		if (result == null){
			Person stranger = (Person) activeMSD.getLineInstance(Person_INST_stranger);
			House house = (House) activeMSD.getLineInstance(House_INST_house);
			Dog dog = (Dog) activeMSD.getLineInstance(Dog_INST_dog);
			switch (lifelineIndex){
			}
		}
		return result;
	}
	
	
	public boolean validateBoundedObject(ActiveMSDAspect activeMSD, int lifelineIndex, Object object){
		boolean result = false;
		try{
			Object bounded = bindObjectByExpression(activeMSD, lifelineIndex, object, true);
			if (bounded != null){
				result = bounded.equals(object);
			}else if (object == null){
				result = true;
			}
		}catch(NullPointerException npe){
			result = false; // parent object not bounded yet
		}
		return result;
	}
	
}
