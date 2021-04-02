import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.yakindu.core.rx.Observable;

public class ComponentsStatusContainer extends JPanel {
	private LegStatus legsStatus;
	private VoiceStatus voiceStatus;
	private TailStatus tailStatus;
	
	public ComponentsStatusContainer(Observable<?> legsStandingEvent, Observable<?> legsWalkingEvent, Observable<?> legsRunningEvent,
			Observable<?> voiceSilentEvent, Observable<?> voiceBarkingEvent, Observable<?> voiceHowlingEvent,
			Observable<?> tailIdleEvent, Observable<?> tailWaggingEvent)
	{
		this.setLayout(new FlowLayout());
		
		legsStatus = new LegStatus(legsStandingEvent, legsWalkingEvent, legsRunningEvent);
		this.add(legsStatus);
		
		voiceStatus = new VoiceStatus(voiceSilentEvent, voiceBarkingEvent, voiceHowlingEvent);
		this.add(voiceStatus);
		
		tailStatus = new TailStatus(tailIdleEvent, tailWaggingEvent);
		this.add(tailStatus);
	}
}
