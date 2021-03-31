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
		
		// Subscribe to events
		stateMachine.legs().getRun().subscribe((e) -> System.out.println("Legs: Running"));
		stateMachine.legs().getStand().subscribe((e) -> System.out.println("Legs: Standing"));
		stateMachine.legs().getWalk().subscribe((e) -> System.out.println("Legs: Walking"));
		stateMachine.voice().getBark().subscribe((e) -> System.out.println("Voice: Bark Bark"));
		stateMachine.voice().getHowl().subscribe((e) -> System.out.println("Voice: Howl"));
		stateMachine.voice().getSilent().subscribe((e) -> System.out.println("Voice: Silent"));
		stateMachine.tail().getIdle().subscribe((e) -> System.out.println("Tail: Idle"));
		stateMachine.tail().getWagging().subscribe((e) -> System.out.println("Tail: Wagging"));

		new WindowFrame(
				stateMachine::raiseFetchCommand, 
				stateMachine::raiseComeCommand, 
				stateMachine::raiseComeCommand, 
				stateMachine.battery().getNormal(), 
				stateMachine.battery().getLow(), 
				stateMachine.battery().getDrained(),
				stateMachine::raiseChargerConnected,
				stateMachine::raiseChargerDisconnected
				);
		stateMachine.enter();
		
		
	}
	
}