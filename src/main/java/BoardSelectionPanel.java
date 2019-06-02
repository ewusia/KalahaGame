package mankala.mankala_brzydka_ale_dziala;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * JPanel containing alternate board selection UI. Encapsulates all necessary
 * components and listeners for color selection. Selected buttons are highlighted
 * when clicked and the corresponding MancalaAlter value is stored.
 * 
 * @author Team 7
 * 
 */
public class BoardSelectionPanel extends JPanel {
	private JButton redBtn;
	private JButton grayBtn;
	private JButton yellowBtn;
	private JButton greenBtn;
	private JButton blueBtn;
	private JButton purpleBtn;
	private JButton selectedColorBtn;
	private MancalaAlter startBoardColor;
	
	/**
	 * Initializes a new BoardSelectionPanel with text prompt and
	 * six color selection buttons.
	 */
	public BoardSelectionPanel() {

	}

	/**
	 * Gets the MancalaAlter corresponding to the selected button.
	 * 
	 * @return				MancalaAlter object corresponding to selected button.
	 */
	public MancalaAlter getBoardSelection() {
		return startBoardColor;
	}
}
