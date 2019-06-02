package mankala.mankala_brzydka_ale_dziala;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Houses listeners for updating view when model state is changed.
 * 
 * @author Team 7
 *
 */
public class Controller implements ChangeListener {
   
	private DataModel dataModel;
    private MancalaGUI gui;
    private StartPanelGUI startPanel;
    private StyleSelectionPanel styleFrame;

    /**
     * Initializes controller with references to the view and data model as well
     * as supporting view objects for the start and style selection menus.
     *
     * @param g		MancalaGUI object reference to the view.
     * @param d		DataModel object reference to the model.
     */
    public Controller(MancalaGUI g, DataModel d) {
        gui = g;
        dataModel = d;
        dataModel.attach(this);
        //Get a reference to the start menu panel
        startPanel = gui.getStartPanel();
        //Get a reference to the style selection panel
        styleFrame = gui.getStylePanel();
        attachControllers();
        //Initializing view with basic game state
        stateChanged(null);
    }
    /**
     * Creates controllers and links them to the model and view.
     */
    public void attachControllers() {
        for(final PitButton btn : gui.getPits())
        	//add actionListener to each pit button
            btn.addActionListener(new ActionListener() {
            	@Override
            	public void actionPerformed(ActionEvent e) {
                    dataModel.replaceState(new State(dataModel));
                    dataModel.clicked(btn.getIndex()); //update index after clicking on the selected pit
                    dataModel.update();
                }
            });
        //add actionListener to undoBtn
        gui.getButton("undo").addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                dataModel.popUndo();
            }
        });
        //add actionListener to endBtn
        gui.getButton("end").addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                dataModel.switchTurn();
            }
        });
        //add actionListener to quitBtn
        gui.getButton("quit").addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                System.out.println("Thank you!\n");
                System.exit(0);
            }
        });

        styleFrame.getDoneBtn().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.changeBoard(styleFrame.getBoardSelection());

			}

        });
        //add actionListener to playBtn
        startPanel.getPlayBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dataModel.setStartingStones(startPanel.getStartStoneCount());
				gui.startGame(startPanel.getStartBoardColor());
			}
		});
    }
    
    /**
     * Updates view with current game state when changes are made to model.
     */
    public void stateChanged(ChangeEvent e) {
        gui.getMancalaA().setText("Gracz I-szy: "+ dataModel.getPlayerAScore());
        gui.getMancalaB().setText("Gracz II-gi: "+ dataModel.getPlayerBScore());
        gui.getButton("undo").setEnabled(!dataModel.getLatestState().isNull() && dataModel.getUndosLeft() > 0);
        gui.getButton("end").setEnabled(dataModel.getSwitch());
        if(dataModel.getPlayerTurn())
            gui.getButton("end").setText("Koniec tury I-go gracza");
        else
            gui.getButton("end").setText("Koniec tury II-go gracza");
        for(int i = 0; i < gui.getPits().length; i++)
            if(i < 6) {
                gui.getPits()[i].setEnabled(!dataModel.getPlayerTurn() && !dataModel.getSwitch() && dataModel.getPitScore(i) > 0);
                gui.getPits()[i].setText("["+dataModel.getPitScore(i)+"]");
            } else {
                gui.getPits()[i].setEnabled(dataModel.getPlayerTurn() && !dataModel.getSwitch() && dataModel.getPitScore(i) > 0);
                gui.getPits()[i].setText("["+dataModel.getPitScore(i)+"]");
            }
    }
}
