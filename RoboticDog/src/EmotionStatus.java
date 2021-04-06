import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.yakindu.core.rx.Observable;

public class EmotionStatus extends JPanel{
	private JLabel emotionStatus;
	private JLabel emotionStatusTitle;
	
	public EmotionStatus(Observable<?> normalEvent, Observable<?> loveEvent) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		emotionStatusTitle = new JLabel();
		emotionStatusTitle.setText("Emotion Status");
		this.add(emotionStatusTitle);
		
		emotionStatus = new JLabel();
		this.add(emotionStatus);
		
		// Subscribe to events
		normalEvent.subscribe((e) -> emotionStatus.setText("Normal"));
		loveEvent.subscribe((e) -> emotionStatus.setText("Love"));
	}
}
