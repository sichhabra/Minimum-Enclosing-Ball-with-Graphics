import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CreateUI extends JPanel {

	private static final long serialVersionUID = 2188556985055181317L;

	public CreateUI() {

		JFrame frame = new JFrame();
		frame.setTitle("Smallest Circle from points");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1200, 700));

		this.add(new CirclePanel());
		this.add(new CodePanel());
		this.add(new PointListPanel());
		this.add(new NotificationPanel());

		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new CreateUI();
	}

}
