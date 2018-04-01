import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class CodePanel extends JPanel {

	private static final long serialVersionUID = -8598904166396947414L;

	public CodePanel() {
		this.setPreferredSize(new Dimension(500, 600));
		this.setBackground(new Color(230, 230, 230));
		this.setName("Code Panel");
	}
}
