import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import robotic.dog.Onoff;

import com.yakindu.core.rx.Observable;

public class WindowFrame extends JFrame { 
	private static final long serialVersionUID = 1L;
	private CommandPanel commandPanel;
	private BatteryPanel batteryPanel;
	private ComponentsStatusContainer componentsStatus;
	private TemperaturePanel tempPanel;

	public WindowFrame(Runnable raiseFetchCommand, Runnable raiseComeCommand, Runnable raiseBarkCommand, Runnable raisePet,
			Observable<?> batteryNormal, Observable<?> batteryLow, Observable<?> batteryDrained, Runnable connectCharger, Runnable disconnectCharger, 
			Observable<?> legsStandingEvent, Observable<?> legsWalkingEvent, Observable<?> legsRunningEvent,
			Observable<?> voiceSilentEvent, Observable<?> voiceBarkingEvent, Observable<?> voiceHowlingEvent,
			Observable<?> tailIdleEvent, Observable<?> tailWaggingEvent,
			Observable<?> emotionNormalEvent, Observable<?> emotionLoveEvent, Onoff stateMachine)
	{		
		commandPanel = new CommandPanel(raiseFetchCommand, raiseComeCommand, raiseBarkCommand, raisePet);
		this.add(commandPanel, BorderLayout.NORTH);
		batteryPanel = new BatteryPanel(batteryNormal, batteryLow, batteryDrained, connectCharger, disconnectCharger);
		this.add(batteryPanel, BorderLayout.SOUTH);
		componentsStatus = new ComponentsStatusContainer(legsStandingEvent, legsWalkingEvent, legsRunningEvent,
				voiceSilentEvent, voiceBarkingEvent, voiceHowlingEvent,
				tailIdleEvent, tailWaggingEvent,
				emotionNormalEvent, emotionLoveEvent);
		this.add(componentsStatus, BorderLayout.CENTER);
		tempPanel = new TemperaturePanel(stateMachine);
		this.add(tempPanel, BorderLayout.WEST);
		
		ImageIcon image = new ImageIcon("icon.png");
		this.setIconImage(image.getImage());
		
		this.setTitle("Test Title");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(740,420);
		this.setVisible(true);
	}
	
}
