import javax.swing.JLabel;
import javax.swing.JPanel;

import com.yakindu.core.rx.Observable;

import javax.swing.BoxLayout;

public class LegStatus extends JPanel {
	private JLabel legStatus;
	private JLabel legStatusTitle;
	
	public LegStatus(Observable<?> standingEvent, Observable<?> walkingEvent, Observable<?> runningEvent)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		legStatusTitle = new JLabel();
		legStatusTitle.setText("Legs Status");
		this.add(legStatusTitle);
		
		legStatus = new JLabel();
		this.add(legStatus);
		
		// Subscribe to events
		standingEvent.subscribe((e) -> legStatus.setText("Standing"));
		walkingEvent.subscribe((e) -> legStatus.setText("Walking"));
		runningEvent.subscribe((e) -> legStatus.setText("Running"));
	}
}
