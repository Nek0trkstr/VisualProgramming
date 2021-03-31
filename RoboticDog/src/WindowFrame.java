import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import com.yakindu.core.rx.Observable;

public class WindowFrame extends JFrame { 
	private static final long serialVersionUID = 1L;
	private CommandPanel commandPanel;
	private BatteryPanel batteryPanel;

	public WindowFrame(Runnable raiseFetchCommand, Runnable raiseComeCommand, Runnable raiseBarkCommand, Observable batteryNormal, Observable batteryLow, Observable batteryDrained, Runnable connectCharger, Runnable disconnectCharger)
	{		
		commandPanel = new CommandPanel(raiseFetchCommand, raiseComeCommand, raiseBarkCommand);
		this.add(commandPanel, BorderLayout.NORTH);
		batteryPanel = new BatteryPanel(batteryNormal, batteryLow, batteryDrained, connectCharger, disconnectCharger);
		this.add(batteryPanel, BorderLayout.EAST);
		
		ImageIcon image = new ImageIcon("icon.png");
		this.setIconImage(image.getImage());
		
		this.setTitle("Test Title");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(740,420);
		this.setVisible(true);
	}
	
}
