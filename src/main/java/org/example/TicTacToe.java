package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private final JButton[][] buttons;
    private final JLabel turnLabel;
    private int turnCount;
    private final int BOARD_SIZE = 3;
    private final String PLAYER_ONE = "X";
    private final String PLAYER_TWO = "O";
    private boolean gameOver;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(BOARD_SIZE + 1, BOARD_SIZE));

        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }

        turnLabel = new JLabel("Turn: " + PLAYER_ONE);
        add(turnLabel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            return;
        }

        JButton button = (JButton) e.getSource();

        if (button.getText().equals("")) {
            if (turnCount % 2 == 0) {
                button.setText(PLAYER_ONE);
                turnLabel.setText("Turn: " + PLAYER_TWO);
            } else {
                button.setText(PLAYER_TWO);
                turnLabel.setText("Turn: " + PLAYER_ONE);
            }

            turnCount++;

            if (checkForWin()) {
                turnLabel.setText("Game over! " + getCurrentPlayer() + " wins!");
                gameOver = true;
            } else if (turnCount == BOARD_SIZE * BOARD_SIZE) {
                turnLabel.setText("Game over! It's a tie!");
                gameOver = true;
            }
        }
    }

    private boolean checkForWin() {
        // check rows
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (checkLine(buttons[row][0], buttons[row][1], buttons[row][2])) {
                return true;
            }
        }

        // check columns
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (checkLine(buttons[0][col], buttons[1][col], buttons[2][col])) {
                return true;
            }
        }

        // check diagonals
        if (checkLine(buttons[0][0], buttons[1][1], buttons[2][2])) {
            return true;
        }

        return checkLine(buttons[0][2], buttons[1][1], buttons[2][0]);
    }

    private boolean checkLine(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals("") && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }
    private String getCurrentPlayer() {
        return turnCount % 2 == 0 ? PLAYER_ONE : PLAYER_TWO;
    }
}