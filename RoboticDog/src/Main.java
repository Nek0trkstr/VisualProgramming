import robotic.dog.Onoff;
import com.yakindu.core.ITimerService;
import com.yakindu.core.TimerService;

public class Main {
	private static final long serialVersionUID = -8909693541678814631L;
	public static void main(String[] args)
	{
		Onoff stateMachine = new Onoff();
		ITimerService timerService = new TimerService();
		stateMachine.setTimerService(timerService);

		new WindowFrame(
				stateMachine::raiseFetchCommand, 
				stateMachine::raiseComeCommand, 
				stateMachine::raiseBarkCommand, 
				stateMachine.battery().getNormal(), 
				stateMachine.battery().getLow(), 
				stateMachine.battery().getDrained(),
				stateMachine::raiseChargerConnected,
				stateMachine::raiseChargerDisconnected,
				stateMachine.legs().getStand(),
				stateMachine.legs().getWalk(),
				stateMachine.legs().getRun(),
				stateMachine.voice().getSilent(),
				stateMachine.voice().getBark(),
				stateMachine.voice().getHowl(),
				stateMachine.tail().getIdle(),
				stateMachine.tail().getWagging()
				);
		stateMachine.enter();
		
		
	}
	
}