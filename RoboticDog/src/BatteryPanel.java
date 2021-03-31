import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import com.yakindu.core.rx.Observable;

public class BatteryPanel extends JPanel {
	JLabel title;
	JLabel batteryStatus;
	JButton connectChargerButton;
	JButton disconnectChargerButton;
	
	public BatteryPanel(Observable<?> batteryNormal, Observable<?> batteryLow, Observable<?> batteryDrained, Runnable connectChargerFunc, Runnable disconnectChargerFunc)
	{
		// Title
		title = new JLabel();
		title.setText("Battery Status");
		
		// Status
		batteryStatus = new JLabel();
		batteryNormal.subscribe((e) -> batteryStatus.setText("Normal"));
		batteryLow.subscribe((e) -> batteryStatus.setText("Low"));
		batteryDrained.subscribe((e) -> batteryStatus.setText("Drained"));
		
		// Connect Charger
		connectChargerButton = new JButton();
		connectChargerButton.setText("Connect Charger");
		connectChargerButton.addActionListener((e) -> connectChargerFunc.run());
		
		// Disconnect Charger
		disconnectChargerButton = new JButton();
		disconnectChargerButton.setText("Disconnect Charger");
		disconnectChargerButton.addActionListener((e) -> disconnectChargerFunc.run());
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(title);
		this.add(batteryStatus);
		this.add(connectChargerButton);
		this.add(disconnectChargerButton);
	}

}
