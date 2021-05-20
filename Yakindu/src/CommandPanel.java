import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class CommandPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	protected JButton fetchCommand;
	
	public CommandPanel(Runnable raiseFetchCommand, Runnable raiseComeCommand, Runnable raiseBarkCommand, Runnable raisePet) {
		this.setLayout(new FlowLayout());
		
		JButton fetchCommand = new JButton();
		fetchCommand.addActionListener((e) -> raiseFetchCommand.run());
		fetchCommand.setText("Fetch");
		
		JButton comeCommand = new JButton();
		comeCommand.addActionListener((e) -> raiseComeCommand.run());
		comeCommand.setText("Come");
		
		JButton barkCommand = new JButton();
		barkCommand.addActionListener((e) -> raiseBarkCommand.run());
		barkCommand.setText("Bark");
		
		JButton pet = new JButton();
		pet.addActionListener((e) -> raisePet.run());
		pet.setText("Give Pets");
		
		this.add(fetchCommand);
		this.add(comeCommand);
		this.add(barkCommand);
		this.add(pet);
	}
	
}
