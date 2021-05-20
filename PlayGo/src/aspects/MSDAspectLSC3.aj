//  The file MSDAspectLSC3.aj was automatically generated using S2A 2.0.0.201512210921.
//  For information see http://www.wisdom.weizmann.ac.il/~maozs/s2a/

package aspects;

import playgo.systemmodel.classes.*;
import playgo.systemmodel.types.*;
import java.util.ArrayList;
import il.ac.wis.cs.s2a.runtime.lib.*;
import il.ac.wis.cs.playgo.playtoolkit.*;


public aspect MSDAspectLSC3 extends MSDAspect
{


	//Constants for instances, locations and variables
	static final int Dog_INST_dog = 0;
	static final int Person_INST_owner = 1;

	static final int String_arg_String_exact_String_arg00 = 0;
	static final int String_arg_String_exact_String_arg11 = 1;
	static final int String_arg_String_exact_String_arg32 = 2;


	private final String niceName = "LSC3";

	MSDAspectLSC3()
	{
		addMinimalEvent(MSDMethods.Dog_Dog_bring);
		setHotCut(4,3);
		setLastCut(5,3);
		numberOfLifeLines = 2;
		numberOfInstances = 2;
		numberOfVariables = 3;
		interactionId = "1620996067144";
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

	pointcut Dog_Dog_wiggle(Dog _dogSource, Object _dog,String String_exact_String_arg11):
		call(* wiggle(..))
		&& if(_dog instanceof Dog)
		&& if(_dogSource instanceof Dog)
		&& target(_dog) && this(_dogSource)  && args(String_exact_String_arg11);

	after(Dog _dogSource,Object _dog,String String_exact_String_arg11):Dog_Dog_wiggle(_dogSource,_dog, String_exact_String_arg11)
	{
		ArrayList<Object> args = getArgsList(String_exact_String_arg11);
		changeCutState(MSDMethods.Dog_Dog_wiggle,
					_dog,_dog,args);
	}

	pointcut Dog_Dog_bring(Dog _dogSource, Object _dog,String String_exact_String_arg00):
		call(* bring(..))
		&& if(_dog instanceof Dog)
		&& if(_dogSource instanceof Dog)
		&& target(_dog) && this(_dogSource)  && args(String_exact_String_arg00);

	after(Dog _dogSource,Object _dog,String String_exact_String_arg00):Dog_Dog_bring(_dogSource,_dog, String_exact_String_arg00)
	{
		ArrayList<Object> args = getArgsList(String_exact_String_arg00);
		changeCutState(MSDMethods.Dog_Dog_bring,
					_dog,_dog,args);
	}

	pointcut Person_Person_say(Person _ownerSource, Object _owner,String String_exact_String_arg32):
		call(* say(..))
		&& if(_owner instanceof Person)
		&& if(_ownerSource instanceof Person)
		&& target(_owner) && this(_ownerSource)  && args(String_exact_String_arg32);

	after(Person _ownerSource,Object _owner,String String_exact_String_arg32):Person_Person_say(_ownerSource,_owner, String_exact_String_arg32)
	{
		ArrayList<Object> args = getArgsList(String_exact_String_arg32);
		changeCutState(MSDMethods.Person_Person_say,
					_owner,_owner,args);
	}

	pointcut Dog_Dog_run(Dog _dogSource, Object _dog):
		call(* run(..))
		&& if(_dog instanceof Dog)
		&& if(_dogSource instanceof Dog)
		&& target(_dog) && this(_dogSource) ;

	after(Dog _dogSource,Object _dog):Dog_Dog_run(_dogSource,_dog)
	{
		changeCutState(MSDMethods.Dog_Dog_run,
					_dog,_dog,null);
	}

	protected void  setPrivateVariables(int MSDm, Object sourceObject, Object targetObject,ActiveMSDAspect activeMSD,
		ArrayList args)
	{
		switch (MSDm)
		{
			case MSDMethods.Dog_Dog_wiggle:
						activeMSD.setPrivateVariable(String_arg_String_exact_String_arg11,args.get(0));

				break;

			case MSDMethods.Dog_Dog_bring:
						activeMSD.setPrivateVariable(String_arg_String_exact_String_arg00,args.get(0));

				break;

			case MSDMethods.Person_Person_say:
						activeMSD.setPrivateVariable(String_arg_String_exact_String_arg32,args.get(0));

				break;

		}
	}

	// MSD Logic:
	protected void  changeActiveMSDCutState(int MSDm, Object sourceObject, Object targetObject,ActiveMSDAspect activeMSD,
		ArrayList args)
	{

		boolean unification=false;
		switch (MSDm)
		{
			case MSDMethods.Dog_Dog_wiggle:
				if(activeMSD.instancesEquals(Dog_INST_dog,sourceObject)
					&& activeMSD.instancesEquals(Dog_INST_dog,targetObject)
					&& activeMSD.variableEquals(String_arg_String_exact_String_arg11,args.get(0)))
				{
					unification=true;
					if(activeMSD.isInCut(1,0))
					{
						activeMSD.setCut(2,0);
						if(evaluateCondition(1,activeMSD))
						{
							activeMSD.setCut(3,1);
							return;
						}
						break;
					}
				}
				if(!unification)//No unification...
					return;
				break;

			case MSDMethods.Dog_Dog_bring:
				if(activeMSD.instancesEquals(Dog_INST_dog,sourceObject)
					&& activeMSD.instancesEquals(Dog_INST_dog,targetObject)
					&& activeMSD.variableEquals(String_arg_String_exact_String_arg00,args.get(0)))
				{
					unification=true;
					if(activeMSD.isInCut(0,0))
					{
						activeMSD.setCut(1,0);
						return;
					}
				}
				if(!unification)//No unification...
					return;
				break;

			case MSDMethods.Person_Person_say:
				if(activeMSD.instancesEquals(Person_INST_owner,sourceObject)
					&& activeMSD.instancesEquals(Person_INST_owner,targetObject)
					&& activeMSD.variableEquals(String_arg_String_exact_String_arg32,args.get(0)))
				{
					unification=true;
					if(activeMSD.isInCut(3,1))
					{
						activeMSD.setCut(3,2);
						if(evaluateCondition(1,activeMSD))
						{
							activeMSD.setCut(4,3);
							return;
						}
						break;
					}
				}
				if(!unification)//No unification...
					return;
				break;

			case MSDMethods.Dog_Dog_run:
				if(activeMSD.instancesEquals(Dog_INST_dog,sourceObject)
					&& activeMSD.instancesEquals(Dog_INST_dog,targetObject))
				{
					unification=true;
					if(activeMSD.isInCut(4,3))
					{
						activeMSD.setCut(5,3);
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
		Dog dog = (Dog)bindObjectByExpression(activeMSD,Dog_INST_dog,null, true);;
		Person owner = (Person)bindObjectByExpression(activeMSD,Person_INST_owner,null, true);;

		String arg11 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg11)!=null){
			arg11 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg11);
		}

		String arg00 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg00)!=null){
			arg00 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg00);
		}

		String arg32 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg32)!=null){
			arg32 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg32);
		}

		switch (conditionNumber)
		{
			case 1: 
				return true;
		}
		return false;
	}


	protected void setCutsExpressions()
	{
		this.setExpressionForCut("3,1" , "SYNC2");
		this.setExpressionForCut("4,3" , "SYNC4");

	}


	@SuppressWarnings("unused")
	protected void doBindings(ActiveMSDAspect activeMSD)
	{
		activeMSD.setLineInstance(Dog_INST_dog,AppObjects.getObject("dog","Dog"));
		activeMSD.setLineInstance(Person_INST_owner,AppObjects.getObject("owner","Person"));
		Dog dog = (Dog)bindObjectByExpression(activeMSD,Dog_INST_dog, null, false );
		Person owner = (Person)bindObjectByExpression(activeMSD,Person_INST_owner, null, false );

		String arg11 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg11)!=null){
			arg11 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg11);
		}

		String arg00 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg00)!=null){
			arg00 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg00);
		}

		String arg32 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg32)!=null){
			arg32 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg32);
		}

		try{
			activeMSD.setPrivateVariable(String_arg_String_exact_String_arg00, (String)"toy");
		}catch(Exception e){}
		try{
			activeMSD.setPrivateVariable(String_arg_String_exact_String_arg11, (String)"tail");
		}catch(Exception e){}
		try{
			activeMSD.setPrivateVariable(String_arg_String_exact_String_arg32, (String)"fetch");
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
		Dog dog = (Dog)bindObjectByExpression(activeMSD,Dog_INST_dog,null, true);;
		Person owner = (Person)bindObjectByExpression(activeMSD,Person_INST_owner,null, true);;

		String VAR_String_exact_String_arg11 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg11)!=null){
			VAR_String_exact_String_arg11 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg11);
		}

		String VAR_String_exact_String_arg00 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg00)!=null){
			VAR_String_exact_String_arg00 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg00);
		}

		String VAR_String_exact_String_arg32 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg32)!=null){
			VAR_String_exact_String_arg32 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg32);
		}

		ArrayList<Object>  args4 = getArgsList(VAR_String_exact_String_arg00);
		MSDMethod MSDm4 = new MSDMethod(dog,dog,
			MSDMethods.Dog_Dog_bring,args4,"Dog:Dog:bring",niceName,activeMSD.getId());// bring() Monitored
		MSDMethod MSDm5 = new MSDMethod(dog,dog,
			MSDMethods.Dog_Dog_run,null,"Dog:Dog:run",niceName,activeMSD.getId());// run() Execute
		ArrayList<Object>  args6 = getArgsList(VAR_String_exact_String_arg11);
		MSDMethod MSDm6 = new MSDMethod(dog,dog,
			MSDMethods.Dog_Dog_wiggle,args6,"Dog:Dog:wiggle",niceName,activeMSD.getId());// wiggle() Monitored
		ArrayList<Object>  args7 = getArgsList(VAR_String_exact_String_arg32);
		MSDMethod MSDm7 = new MSDMethod(owner,owner,
			MSDMethods.Person_Person_say,args7,"Person:Person:say",niceName,activeMSD.getId());// say() Monitored

		 if(activeMSD.isInCut(0,0))
		{
				ME.add(MSDm4);
				CV.add(MSDm5,MSDm6,MSDm7);
				return;
		}
		 if(activeMSD.isInCut(1,0))
		{
				ME.add(MSDm6);
				CV.add(MSDm5,MSDm4,MSDm7);
				return;
		}
		 if(activeMSD.isInCut(3,1))
		{
				ME.add(MSDm7);
				CV.add(MSDm5,MSDm6,MSDm4);
				return;
		}
		 if(activeMSD.isInCut(4,3))
		{
				if(MSDm5.sourceInstance!=null && MSDm5.targetInstance!=null){
					EE.add(MSDm5);
				}
				HV.add(MSDm6,MSDm4,MSDm7);
				return;
		}
	}
	
	@SuppressWarnings("unused")
	public Object bindObjectByExpression (ActiveMSDAspect activeMSD,int lifelineIndex, 
			Object obj, boolean retrieveOnly){

		String arg11 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg11)!=null){
			arg11 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg11);
		}

		String arg00 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg00)!=null){
			arg00 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg00);
		}

		String arg32 = null;
		if(activeMSD.getPrivateVariable(String_arg_String_exact_String_arg32)!=null){
			arg32 = (String) activeMSD.getPrivateVariable(String_arg_String_exact_String_arg32);
		}

		Object result = null;
		// in case the object is already bounded returning the bounded object
		result = activeMSD.getLineInstance(lifelineIndex);
		if (result == null){
			Dog dog = (Dog) activeMSD.getLineInstance(Dog_INST_dog);
			Person owner = (Person) activeMSD.getLineInstance(Person_INST_owner);
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
