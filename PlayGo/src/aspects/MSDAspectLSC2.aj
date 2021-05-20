//  The file MSDAspectLSC2.aj was automatically generated using S2A 2.0.0.201512210921.
//  For information see http://www.wisdom.weizmann.ac.il/~maozs/s2a/

package aspects;

import playgo.systemmodel.classes.*;
import playgo.systemmodel.types.*;
import java.util.ArrayList;
import il.ac.wis.cs.s2a.runtime.lib.*;
import il.ac.wis.cs.playgo.playtoolkit.*;


public aspect MSDAspectLSC2 extends MSDAspect
{


	//Constants for instances, locations and variables
	static final int Person_INST_owner = 0;
	static final int House_INST_house = 1;
	static final int Dog_INST_dog = 2;

	static final int String_arg_String_exact_String_arg20 = 0;


	private final String niceName = "LSC2";

	MSDAspectLSC2()
	{
		addMinimalEvent(MSDMethods.Person_House_enter);
		setHotCut(2,2,2);
		setLastCut(2,2,4);
		numberOfLifeLines = 3;
		numberOfInstances = 3;
		numberOfVariables = 1;
		interactionId = "1620994790682";
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

	pointcut Dog_Dog_bring(Dog _dogSource, Object _dog,String String_exact_String_arg20):
		call(* bring(..))
		&& if(_dog instanceof Dog)
		&& if(_dogSource instanceof Dog)
		&& target(_dog) && this(_dogSource)  && args(String_exact_String_arg20);

	after(Dog _dogSource,Object _dog,String String_exact_String_arg20):Dog_Dog_bring(_dogSource,_dog, String_exact_String_arg20)
	{
		ArrayList<Object> args = getArgsList(String_exact_String_arg20);
		changeCutState(MSDMethods.Dog_Dog_bring,
					_dog,_dog,args);
	}

	pointcut Person_House_enter(Person _owner, Object _house):
		call(* enter(..))
		&& if(_house instanceof House)
		&& target(_house) && this(_owner) ;

	after(Person _owner,Object _house):Person_House_enter(_owner,_house)
	{
		changeCutState(MSDMethods.Person_House_enter,
					_owner,_house,null);
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
			case MSDMethods.Dog_Dog_bring:
				if(activeMSD.instancesEquals(Dog_INST_dog,sourceObject)
					&& activeMSD.instancesEquals(Dog_INST_dog,targetObject)
					&& activeMSD.variableEquals(String_arg_String_exact_String_arg20,args.get(0)))
				{
					unification=true;
					if(activeMSD.isInCut(2,2,2))
					{
						activeMSD.setCut(2,2,3);
						if(evaluateCondition(1,activeMSD))
						{
							activeMSD.setCut(2,2,4);
							break;
						}
						break;
					}
				}
				if(!unification)//No unification...
					return;
				break;

			case MSDMethods.Person_House_enter:
				if(activeMSD.instancesEquals(Person_INST_owner,sourceObject)
					&& activeMSD.instancesEquals(House_INST_house,targetObject))
				{
					unification=true;
					if(activeMSD.isInCut(0,0,0))
					{
						activeMSD.setCut(1,1,0);
						if(evaluateCondition(1,activeMSD))
						{
							activeMSD.setCut(2,2,1);
							activeMSD.setCut(2,2,1);
							if(evaluateCondition(4,activeMSD))
							{
								activeMSD.setCut(2,2,2);
								return;
							}
							activeMSD.setCut(2,2,4);
							break;
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
		Person owner = (Person)bindObjectByExpression(activeMSD,Person_INST_owner,null, true);;
		House house = (House)bindObjectByExpression(activeMSD,House_INST_house,null, true);;
		Dog dog = (Dog)bindObjectByExpression(activeMSD,Dog_INST_dog,null, true);;

		String arg20 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg20)!=null){
			arg20 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg20);
		}

		switch (conditionNumber)
		{
			case 1: 
				return true;
			case 4: 
				return dog.getBattery().equals("high");
		}
		return false;
	}


	protected void setCutsExpressions()
	{
		this.setExpressionForCut("2,2,4" , "SYNC");
		this.setExpressionForCut("2,2,1" , "SYNC0");
		this.setExpressionForCut("2,2,2" , "dog.battery == high");

	}


	@SuppressWarnings("unused")
	protected void doBindings(ActiveMSDAspect activeMSD)
	{
		activeMSD.setLineInstance(Person_INST_owner,AppObjects.getObject("owner","Person"));
		activeMSD.setLineInstance(House_INST_house,AppObjects.getObject("house","House"));
		activeMSD.setLineInstance(Dog_INST_dog,AppObjects.getObject("dog","Dog"));
		Person owner = (Person)bindObjectByExpression(activeMSD,Person_INST_owner, null, false );
		House house = (House)bindObjectByExpression(activeMSD,House_INST_house, null, false );
		Dog dog = (Dog)bindObjectByExpression(activeMSD,Dog_INST_dog, null, false );

		String arg20 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg20)!=null){
			arg20 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg20);
		}

		try{
			activeMSD.setPrivateVariable(String_arg_String_exact_String_arg20, (String)"toy");
		}catch(Exception e){}
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
		Person owner = (Person)bindObjectByExpression(activeMSD,Person_INST_owner,null, true);;
		House house = (House)bindObjectByExpression(activeMSD,House_INST_house,null, true);;
		Dog dog = (Dog)bindObjectByExpression(activeMSD,Dog_INST_dog,null, true);;

		String VAR_String_exact_String_arg20 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg20)!=null){
			VAR_String_exact_String_arg20 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg20);
		}

		ArrayList<Object>  args2 = getArgsList(VAR_String_exact_String_arg20);
		MSDMethod MSDm2 = new MSDMethod(dog,dog,
			MSDMethods.Dog_Dog_bring,args2,"Dog:Dog:bring",niceName,activeMSD.getId());// bring() Execute
		MSDMethod MSDm3 = new MSDMethod(owner,house,
			MSDMethods.Person_House_enter,null,"Person:House:enter",niceName,activeMSD.getId());// enter() Monitored

		 if(activeMSD.isInCut(0,0,0))
		{
				ME.add(MSDm3);
				CV.add(MSDm2);
				return;
		}
		 if(activeMSD.isInCut(2,2,2))
		{
				if(MSDm2.sourceInstance!=null && MSDm2.targetInstance!=null){
					EE.add(MSDm2);
				}
				HV.add(MSDm3);
				return;
		}
	}
	
	@SuppressWarnings("unused")
	public Object bindObjectByExpression (ActiveMSDAspect activeMSD,int lifelineIndex, 
			Object obj, boolean retrieveOnly){

		String arg20 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg20)!=null){
			arg20 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg20);
		}

		Object result = null;
		// in case the object is already bounded returning the bounded object
		result = activeMSD.getLineInstance(lifelineIndex);
		if (result == null){
			Person owner = (Person) activeMSD.getLineInstance(Person_INST_owner);
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
