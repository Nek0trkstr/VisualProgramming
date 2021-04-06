import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import robotic.dog.Onoff;

public class TemperaturePanel extends JPanel implements ChangeListener  {
	private JLabel tempValue;
	private JSlider tempSlider;
	private Onoff sm;
	private int currentTemp;
	
	public TemperaturePanel(Onoff stateMachine) {
		sm = stateMachine;
		tempSlider = new JSlider(JSlider.VERTICAL, 0, 100, 28);
		tempSlider.setPaintTicks(true);
		tempSlider.setMinorTickSpacing(10);
		
		tempSlider.setPaintTrack(true);
		tempSlider.setMajorTickSpacing(20);
		tempSlider.setPaintLabels(true);
		tempSlider.addChangeListener(this);
		
		tempValue = new JLabel();
		tempValue.setText("°C = " + tempSlider.getValue());
		
		this.add(tempValue);
		this.add(tempSlider);
	}
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		currentTemp = tempSlider.getValue();
		tempValue.setText("°C = " + currentTemp);
		sm.setCurrentTemp((long)currentTemp );
	}

}
