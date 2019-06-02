package mankala.mankala_brzydka_ale_dziala;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GUIForm {
    private JFrame mancalaFrame;
    private JPanel btnPnl;
    private JPanel pitPanel;
    private JPanel menuPanel;
    private JPanel gameBoardPanel;
    private PitButton[] pitButtons;
    private JButton playerBScore;
    private JButton playerAScore;
    private JButton endTurn;
    private JButton undo;
    private JButton quit;

    public GUIForm() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        playerAScore.setEnabled(false);
        playerAScore.setFocusable(false);
        playerAScore.setBackground(Color.DARK_GRAY);
        playerAScore.setForeground(Color.BLACK);
        playerAScore.setBorder(new EmptyBorder(10, 10, 10, 10));

        playerBScore.setEnabled(false);
        playerBScore.setFocusable(false);
        playerBScore.setBackground(Color.DARK_GRAY);
        playerBScore.setForeground(Color.BLACK);
        playerBScore.setBorder(new EmptyBorder(10, 10, 10, 10));

        endTurn.setBorder(new EmptyBorder(10, 10, 10, 10));
        undo.setBorder(new EmptyBorder(10, 10, 10, 10));
        quit.setBorder(new EmptyBorder(10, 10, 10, 10));

        pitPanel.setLayout(new GridLayout(2, 6));
        pitPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        pitButtons = new PitButton[12];
        PitButton tmpBtn;
        for(int i = 0; i < pitButtons.length; i++) {
            tmpBtn = new PitButton("3", i);
            if(i < 6)
                tmpBtn.setEnabled(false);
            tmpBtn.setBorderPainted(false);
            tmpBtn.setFocusPainted(false);
            tmpBtn.setBorder(new EmptyBorder(10, 10, 10, 10));
            pitButtons[i] = tmpBtn;
            pitPanel.add(pitButtons[i]);
        }
    }
    public PitButton[] getPits() {
        return pitButtons;
    }

    public JButton getMancalaB() {
        return playerBScore;
    }
    public JButton getMancalaA() {
        return playerAScore;
    }

    public JButton getButton(String btnRequest) {
        if(btnRequest.equals("end"))
            return endTurn;
        else if(btnRequest.equals("undo"))
            return undo;
        else if(btnRequest.equals("quit"))
            return quit;
        else return null;
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Mancala");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //frame.add(gameBoardPanel);
        frame.setContentPane(new GUIForm().gameBoardPanel);
        frame.pack();
        frame.setSize(550, 250);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        DataModel d = new DataModel();
        //Controller c = new Controller(g, d);

        //gameBoardPanel.setVisible(false);
    }

}
