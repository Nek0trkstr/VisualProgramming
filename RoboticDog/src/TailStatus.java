import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.yakindu.core.rx.Observable;

public class TailStatus extends JPanel{
	private JLabel tailStatusTitle;
	private JLabel tailStatus;
	
	public TailStatus(Observable<?> idleEvent, Observable<?> waggingEvent) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		tailStatusTitle = new JLabel();
		tailStatusTitle.setText("Tail Status");
		this.add(tailStatusTitle);
		
		tailStatus = new JLabel();
		this.add(tailStatus);
		
		// Subscribe to events
		idleEvent.subscribe((e) -> tailStatus.setText("Idle"));
		waggingEvent.subscribe((e) -> tailStatus.setText("Wagging"));
	}
}
