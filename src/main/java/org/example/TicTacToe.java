package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private final JLabel turnLabel;
    private int turnCount;
    private int BOARD_SIZE = 3;

    private Dimension windowSize;
    private Color winningLineColor;
    private String PLAYER_ONE = "X";
    private String PLAYER_TWO = "O";
    private boolean gameOver;

    private Color turnLabelBackgroundColor = Color.WHITE;
    private Color turnLabelForegroundColor = Color.BLACK;
    private Color buttonForegroundColor = Color.BLACK;
    private Set<String> playerNames = new HashSet<>();

    private int playerOneNameSize = 16;
    private int playerTwoNameSize = 16;

    private int turnLabelTextSize = 16;


    public TicTacToe() {
        setTitle("Tic Tac Toe");
        this.windowSize = new Dimension(600, 600);
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

    public void setPlayerNames(Set<String> names) {
        playerNames = names;
        if (names.size() > 0) {
            PLAYER_ONE = names.toArray(new String[0])[0];
            PLAYER_TWO = names.toArray(new String[0])[1];
        }
        // Set the font size for each player name
        turnLabel.setText("<html><font size='" + playerOneNameSize + "'>" + PLAYER_ONE +
                "</font> vs. <font size='" + playerTwoNameSize + "'>" + PLAYER_TWO + "</font></html>");
    }


    public void setTurnLabelText(String text) {
        turnLabel.setText(text);
    }

    public void setButtonText(int row, int col, String text) {
        buttons[row][col].setText(text);
    }

    public void setTurnLabelBackgroundColor(Color color) {
        turnLabelBackgroundColor = color;
        turnLabel.setBackground(color);
    }

    public void setTurnLabelForegroundColor(Color color) {
        turnLabelForegroundColor = color;
        turnLabel.setForeground(color);
    }

    public void setBackgroundColor(Color color) {
        getContentPane().setBackground(color);
    }

    public void setButtonForegroundColor(Color color) {
        buttonForegroundColor = color;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                buttons[row][col].setForeground(color);
            }
        }
    }

    public void setButtonColor(String name, Color color) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton button = buttons[row][col];
                if (button.getText().equals(name)) {
                    button.setForeground(color);
                }
            }
        }
    }

    public void setButtonsColor(Color color) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton button = buttons[row][col];
                button.setBackground(color);
            }
        }
    }

    public void setBoardSize(int size) {
        BOARD_SIZE = size;
        setSize(size * 100, size * 100);
        setLayout(new GridLayout(size + 1, size));
        buttons = new JButton[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            return;
        }

        JButton button = (JButton) e.getSource();

        if (button.getText().equals("")) {
            if (turnCount % 2 == 0) {
                button.setText(PLAYER_ONE);
                setButtonColor(PLAYER_ONE, buttonForegroundColor);
                setTurnLabelText("Turn: " + PLAYER_TWO);
            } else {
                button.setText(PLAYER_TWO);
                setButtonColor(PLAYER_TWO, buttonForegroundColor);
                setTurnLabelText("Turn: " + PLAYER_ONE);
            }
            turnCount++;

            checkForWinner();
        }
    }

    private void checkForWinner() {
        // Check rows
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                    buttons[row][0].getText().equals(buttons[row][2].getText()) &&
                    !buttons[row][0].getText().equals("")) {
                endGame(buttons[row][0].getText() + " wins!");
                return;
            }
        }


        for (int col = 0; col < BOARD_SIZE; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                    buttons[0][col].getText().equals(buttons[2][col].getText()) &&
                    !buttons[0][col].getText().equals("")) {
                endGame(buttons[0][col].getText() + " wins!");
                return;
            }
        }


        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) {
            endGame(buttons[0][0].getText() + " wins!");
            return;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals("")) {
            endGame(buttons[0][2].getText() + " wins!");
            return;
        }


        if (turnCount == BOARD_SIZE * BOARD_SIZE) {
            endGame("Tie game!");
        }
    }

    public void setWindowSize(Dimension size) {
        this.windowSize = size;
        setSize(size);
    }

    public void setTurnLabelTextSize(int fontSize) {
        turnLabelTextSize = fontSize;
        Font font = turnLabel.getFont();
        turnLabel.setFont(new Font(font.getName(), font.getStyle(), turnLabelTextSize));
    }

    public void setButtonForegroundTextSize(int size) {
        Font font = new Font(buttons[0][0].getFont().getName(), buttons[0][0].getFont().getStyle(), size);
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                buttons[row][col].setFont(font);
            }
        }
    }


    private void endGame(String message) {
        setTurnLabelText(message);
        JOptionPane.showMessageDialog(null, "Thank you for coming to the master class", "Thanks for playing!", JOptionPane.INFORMATION_MESSAGE);
        gameOver = true;
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
    }
}