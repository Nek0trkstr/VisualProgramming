import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

import com.yakindu.core.rx.Observable;

public class ComponentsStatusContainer extends JPanel {
	private LegStatus legsStatus;
	private VoiceStatus voiceStatus;
	private TailStatus tailStatus;
	private EmotionStatus emotionStatus;
	
	public ComponentsStatusContainer(Observable<?> legsStandingEvent, Observable<?> legsWalkingEvent, Observable<?> legsRunningEvent,
			Observable<?> voiceSilentEvent, Observable<?> voiceBarkingEvent, Observable<?> voiceHowlingEvent,
			Observable<?> tailIdleEvent, Observable<?> tailWaggingEvent,
			Observable<?> emotionNormalEvent, Observable<?> emotionLoveEvent)
	{
		this.setLayout(new FlowLayout());
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		this.setBorder(border);
		
		legsStatus = new LegStatus(legsStandingEvent, legsWalkingEvent, legsRunningEvent);
		this.add(legsStatus);
		
		voiceStatus = new VoiceStatus(voiceSilentEvent, voiceBarkingEvent, voiceHowlingEvent);
		this.add(voiceStatus);
		
		tailStatus = new TailStatus(tailIdleEvent, tailWaggingEvent);
		this.add(tailStatus);
		
		emotionStatus = new EmotionStatus(emotionNormalEvent, emotionLoveEvent);
		this.add(emotionStatus);
	}
}
