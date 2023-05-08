package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;

public class Main {
    tRI-dANIEL01
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.setBackgroundColor(RED);
        Set<String> playerNames = new HashSet<>();
        playerNames.add("Raya");
        ticTacToe.setWindowSize(new Dimension(800, 800));
        playerNames.add("Daniel");
        ticTacToe.setButtonsColor(YELLOW);
        ticTacToe.setTitle("This is myGame");
        ticTacToe.setButtonForegroundColor(RED);
        ticTacToe.setPlayerNames(playerNames);
        ticTacToe.setButtonForegroundTextSize(40);
        ticTacToe.setTurnLabelText("GO");
        ticTacToe.setTurnLabelTextSize(40);
        ticTacToe.setTurnLabelForegroundColor(Color.WHITE);
        ticTacToe.setVisible(true);
    }
}