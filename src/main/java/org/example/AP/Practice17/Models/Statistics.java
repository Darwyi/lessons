package org.example.AP.Practice17.Models;

import java.time.LocalDateTime;

public class Statistics {
    public String winner;
    public LocalDateTime date;
    public int fieldSize;
    public String playerX;
    public String playerO;

    public Statistics(String winner, int fieldSize, String playerX, String playerO) {
        this.winner = winner;
        this.date = LocalDateTime.now();
        this.fieldSize = fieldSize;
        this.playerX = playerX;
        this.playerO = playerO;
    }

    //читав тут https://www.examclouds.com/ru/java/java-core-russian/method-tostring
    // про @Override -https://javarush.com/groups/posts/1975-kak-ustroen-mekhanizm-pereopredelenija-metodov-
    // https://proglang.su/java/overriding
    @Override
    public String toString() {
        return date + ", Winner: " + winner + ", Size: " + fieldSize + ", " + playerX + " vs " + playerO;
    }
}
