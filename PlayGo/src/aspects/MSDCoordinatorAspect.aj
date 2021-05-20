//  The file MSDCoordinatorAspect.aj was automatically generated using S2A 2.0.0.201512210921.
//  For information see http://www.wisdom.weizmann.ac.il/~maozs/s2a/

package aspects;

import playgo.systemmodel.classes.*;
import playgo.systemmodel.types.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Stack;
import il.ac.wis.cs.s2a.runtime.Utils;
import il.ac.wis.cs.s2a.runtime.lib.*;


public aspect MSDCoordinatorAspect extends MSDCoordinator
{
	declare precedence: MSDCoordinatorAspect, *;
	ICoordinatorStrategy strategy = null;

	TraceVisHandler traceVisHandler = null;

	private static final int ENV_MESSAGE_ID = 777;
	private Vector<MSDMethod> history;

	private boolean lock = false;
	private static Stack<MSDMethod> coordinateStack = new Stack<MSDMethod>();
	private static boolean firstTime = true;
	private static MSDCoordinatorAspect instance = null;

		public static MSDCoordinatorAspect getInstance(){
		return instance;
	}

	pointcut strategyInit()
		: (execution(playgo.systemmodel.SystemModelMain.new()) && if(firstTime));

	before() : strategyInit()
	{
		instance=this;

		firstTime=false;

		new AdditionalInfo(Utils.getModelFilesPaths(),
				MSDSPOStaticInfo.getInteractionLifelines(),
				MSDSPOStaticInfo.getInteractionVariables(),
				Utils.getRuntimePropertiesPath(),
				Utils.isConcreteLSc());

		traceVisHandler = TraceVisHandler.getSingleton();

		if(strategy == null){
			strategy = StrategyFactory.createStrategy(
				Utils.getRuntimePropertiesPath());
			history = new Vector<MSDMethod>();

		}

		boolean initSucceed = strategy.init();
		if (!initSucceed)
		{
			MSDPlayoutView.setStrategyInitFailed();
		}
	}

	pointcut EnvNoop():
		execution(void il.ac.wis.cs.systemmodel.SMEnvBaseClass+.noop());

	after (): EnvNoop(){

		MSDMethod envMeth = new MSDMethod(
			null,	//src (env)
			null,	//tgt (env)
			-1,		//messageId (not in use here)
			null);	//args

		envMeth.messageIdStr="env:env:noop";
		history.add(envMeth);

		coordinate(envMeth);
	}

	public void Dog.Wrapperwiggle(Dog _dog,String String_exact_String_arg11)
	{
		_dog.wiggle(String_exact_String_arg11);
	}

	public void Dog.Wrapperbark(Dog _dog)
	{
		_dog.bark();
	}

	public void Dog.Wrapperbring(Dog _dog,String String_exact_String_arg20)
	{
		_dog.bring(String_exact_String_arg20);
	}

	public void Person.Wrappersay(Person _person,String String_exact_String_arg32)
	{
		_person.say(String_exact_String_arg32);
	}

	public void Dog.Wrapperrun(Dog _dog)
	{
		_dog.run();
	}

	public void Person.Wrapperenter(House _house)
	{
		_house.enter();
	}

	pointcut MSDMessage():
	call(* run(..))
	|| call(* bark(..))
	|| call(* say(..))
	|| call(* wiggle(..))
	|| call(* enter(..))
	|| call(* bring(..));


	after (): MSDMessage()
	{
		ArrayList<Object> args = null;
		if(thisJoinPoint.getArgs()!=null){
			args = new ArrayList<Object>();
			for(int i=0;i<thisJoinPoint.getArgs().length;i++){
				args.add(thisJoinPoint.getArgs()[i]);
			}	
		}
		MSDMethod envMeth = new MSDMethod(
				null,	//src (env)
				thisJoinPoint.getTarget(),
				ENV_MESSAGE_ID, //messageId (not in use here)
				args);

		envMeth.messageIdStr="env:"+
			thisJoinPoint.getTarget().getClass().getSimpleName()+":"+
			thisJoinPoint.getSignature().getName();

		if(history==null)return;	//called before strategyInit, probably via irrelevant caught call
		history.add(envMeth);

		MSDAspect.handlingEvent(thisJoinPoint);
		coordinate(envMeth);
	}

	public void coordinate(MSDMethod envMeth)
	{
		if (lock)
		{
			coordinateStack.push(envMeth);
			return;
		}
		else
		{
			lock = true;
			afterImpl(envMeth);
			lock = false;
		}
		if(coordinateStack.size() > 0)
		{
			coordinate(coordinateStack.pop());
		}
	}

	private void afterImpl(MSDMethod envMeth) 
	{
		ArrayList<MSDAspect> aspects = new ArrayList<MSDAspect>();

		MSDMethodSet monitoringEnabled = new MSDMethodSet();
		MSDMethodSet executingEnabled = new MSDMethodSet();
		MSDMethodSet coldViolation = new MSDMethodSet();
		MSDMethodSet hotViolation = new MSDMethodSet();

		MSDAspectLSC1.aspectOf().getCutState(monitoringEnabled, executingEnabled, coldViolation, hotViolation);
		aspects.add(MSDAspectLSC1.aspectOf());

		MSDAspectLSC2.aspectOf().getCutState(monitoringEnabled, executingEnabled, coldViolation, hotViolation);
		aspects.add(MSDAspectLSC2.aspectOf());

		MSDAspectLSC3.aspectOf().getCutState(monitoringEnabled, executingEnabled, coldViolation, hotViolation);
		aspects.add(MSDAspectLSC3.aspectOf());

		MSDAspectLSC4.aspectOf().getCutState(monitoringEnabled, executingEnabled, coldViolation, hotViolation);
		aspects.add(MSDAspectLSC4.aspectOf());

		 // Coordinate all execution engines:
		playgo.systemmodel.SystemModelMain.getInstance().coordinateAll();

		MSDMethod MSDm = strategy.chooseMethod(monitoringEnabled, executingEnabled, coldViolation, hotViolation
				,history,aspects);

		history.add(MSDm);
		dealWithTraceVis(MSDm, envMeth);
		wrapperCallPoint(MSDm);
	}

	private void dealWithTraceVis(MSDMethod MSDm, MSDMethod envMeth) {
		TraceVisMSDMethodSet monitoringEnabled = new TraceVisMSDMethodSet();
		TraceVisMSDMethodSet executingEnabled = new TraceVisMSDMethodSet();
		TraceVisMSDMethodSet coldViolation = new TraceVisMSDMethodSet();
		TraceVisMSDMethodSet hotViolation = new TraceVisMSDMethodSet();

		MSDAspectLSC1.aspectOf().getCutState(monitoringEnabled, executingEnabled, coldViolation, hotViolation);
		MSDAspectLSC2.aspectOf().getCutState(monitoringEnabled, executingEnabled, coldViolation, hotViolation);
		MSDAspectLSC3.aspectOf().getCutState(monitoringEnabled, executingEnabled, coldViolation, hotViolation);
		MSDAspectLSC4.aspectOf().getCutState(monitoringEnabled, executingEnabled, coldViolation, hotViolation);

	if (envMeth != null && MSDm != null && envMeth.messageID == ENV_MESSAGE_ID) {
		envMeth = null;
	}

		traceVisHandler.notify(monitoringEnabled, executingEnabled, coldViolation, hotViolation, MSDm, envMeth);
	}


	protected void wrapperCall(MSDMethod MSDm)
	{
		if(MSDm==null){
			return;
		}
		switch (MSDm.messageID)
		{
			case MSDMethods.Dog_Dog_bark:
			 ((Dog)MSDm.sourceInstance).Wrapperbark(
				(Dog)MSDm.targetInstance);
			break;
			case MSDMethods.Dog_Dog_bring:
			 ((Dog)MSDm.sourceInstance).Wrapperbring(
				(Dog)MSDm.targetInstance
				,(String)MSDm.args.get(0));
			break;
			case MSDMethods.Dog_Dog_run:
			 ((Dog)MSDm.sourceInstance).Wrapperrun(
				(Dog)MSDm.targetInstance);
			break;
		}
	}

}

