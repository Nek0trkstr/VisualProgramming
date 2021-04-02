import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.yakindu.core.rx.Observable;

public class VoiceStatus extends JPanel {
	private JLabel voiceStatusTitle;
	private JLabel voiceStatus;
	
	public VoiceStatus(Observable<?> silentEvent, Observable<?> barkEvent, Observable<?> howlEvent)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		voiceStatusTitle = new JLabel();
		voiceStatusTitle.setText("Voice Status");
		this.add(voiceStatusTitle);
		
		voiceStatus = new JLabel();
		this.add(voiceStatus);
		
		// Subscribe to events
		silentEvent.subscribe((e) -> voiceStatus.setText("Silent"));
		barkEvent.subscribe((e) -> voiceStatus.setText("Barking"));
		howlEvent.subscribe((e) -> voiceStatus.setText("Howling"));
	}
}
