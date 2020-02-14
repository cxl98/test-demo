package com.cxl.strategy.A2;

public class Player {
    private String name;
    private Strategy1 strategy;
    private int winCount;
    private int loseCount;
    private int even;
    private int gameCount;

    public Player(String name, Strategy1 strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public Hand1 nextHand() {
        return strategy.nextHand();
    }

    public void win() {
        strategy.study(true);
        winCount++;
        gameCount++;
    }

    public void lose() {
        strategy.study(false);
        loseCount++;
        gameCount++;
    }

    public void even() {
        even++;
        gameCount++;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", winCount=" + winCount +
                ", loseCount=" + loseCount +
                ", even=" + even +
                ", gameCount=" + gameCount +
                '}';
    }
}
