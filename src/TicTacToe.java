import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener {
    JFrame jFrame;
    JButton[] jButtons;
    boolean xTurn = true;
    boolean flag;
    boolean tie;
    public TicTacToe() {
        jFrame = new JFrame("Крестики-нолики");
        jFrame.setSize(400, 400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(new GridLayout(3, 3));
        jFrame.setResizable(false);
        jButtons = new JButton[9];
        for (int i = 0; i < jButtons.length; i++) {
                jButtons[i] = new JButton();
                jButtons[i].setFont(new Font("Arial", Font.PLAIN, 40));
        }
        for (int i = 0; i < jButtons.length; i++) {
                jFrame.add(jButtons[i]);
                jButtons[i].addActionListener(this);
        }
            jFrame.setVisible(true);

    }
    public void isWinner() {
        for (int i = 0; i < jButtons.length-2; i+=3) {
            if ((jButtons[i].getText().equals(jButtons[i+1].getText())) && (jButtons[i].getText().equals(jButtons[i+2].getText())) && !jButtons[i].isEnabled()) {
                JOptionPane.showMessageDialog(jFrame, jButtons[i].getText() + " победили!");
                flag = true;
//если не указать, что кнопки нерабочие, то возникнет проблема: ибо другие кнопки могут быть не зажаты, следовательно,
// значения тоже равны, но это не значит, что игра выиграна: надо, чтобы выиграли или крестики, или нолики, а тут получается, что побеждает "пустота"
            }
        }
        for (int i = 0; i < 3; i++) {
            if ((jButtons[i].getText().equals(jButtons[i+3].getText())) && (jButtons[i].getText().equals(jButtons[i+6].getText())) && !jButtons[i].isEnabled()) {
                JOptionPane.showMessageDialog(jFrame, jButtons[i].getText() + " победили!");
                flag = true;
            }
        }
        if ((jButtons[0].getText().equals(jButtons[4].getText())) && (jButtons[0].getText().equals(jButtons[8].getText())) && !jButtons[0].isEnabled()) {
            JOptionPane.showMessageDialog(jFrame, jButtons[0].getText() + " победили!");
            flag = true;
        }
        if ((jButtons[2].getText().equals(jButtons[4].getText())) && (jButtons[2].getText().equals(jButtons[6].getText())) && !jButtons[2].isEnabled()) {
            JOptionPane.showMessageDialog(jFrame, jButtons[2].getText() + " победили!");
            flag = true;
        }
        isDrawn();
        if (flag) {
            reset();
            flag = false;
        }
    }
    public void isDrawn() {
        for (int i = 0; i < jButtons.length; i++) {
            if (jButtons[i].isEnabled()) {
                tie = false;
                break;
            } else {
                tie = true;
            }
        }
        if (tie && !flag) {
            JOptionPane.showMessageDialog(jFrame, "Ничья");
            flag = true;
        }
    }
    public void reset() {
        for (int i = 0; i < 9; i++) {
            jButtons[i].setText("");
            jButtons[i].setEnabled(true);
        }
        xTurn = true;
    }
    @Override
    public void actionPerformed (ActionEvent e){
        JButton jButton = (JButton) e.getSource();
        if(xTurn) {
            jButton.setText("X");
        } else {
            jButton.setText("O");
        }
        xTurn = !xTurn;
        jButton.setEnabled(false);
        isWinner();
    }
}
