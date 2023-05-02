package org.example;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static java.awt.Color.RED;

public class Main {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.setBackgroundColor(RED);
        Set<String> playerNames = new HashSet<>();
        playerNames.add("Aixend");
        playerNames.add("Daniel");
        ticTacToe.setTitle("This is myGame");
        ticTacToe.setButtonForegroundColor(Color.BLACK);
        ticTacToe.setPlayerNames(playerNames);
        ticTacToe.setTurnLabelText("GO");
        ticTacToe.setTurnLabelForegroundColor(Color.WHITE);
        ticTacToe.setVisible(true);
    }
}