package com.cxl.strategy.A2;

import java.util.Random;

public class ProbStrategy implements Strategy1 {
    private Random random;
    private int prevHandValue=0;
    private int currentHandValue = 0;
    private int[][] history = {
            { 1, 1, 1, },
            { 1, 1, 1, },
            { 1, 1, 1, },
    };

    public ProbStrategy() {
        random=new Random();
    }

    private int getSum(int currentHandValue) {
        int sum=0;
        for (int i = 0; i <3 ; i++) {
            sum+=history[currentHandValue][i];
        }
        return sum;
    }


    public Hand1 nextHand() {
        int bet=random.nextInt(getSum(currentHandValue));
        int handValue;
        if (bet<history[currentHandValue][0]){
            handValue=0;
        }else if (bet<history[currentHandValue][0]+history[currentHandValue][1]){
            handValue=1;
        }else{
            handValue=2;
        }
        prevHandValue=currentHandValue;
        currentHandValue=handValue;
        return Hand1.getHand(handValue);
    }

    public void study(boolean win) {
        if (win) {
            history[prevHandValue][currentHandValue]++;
        }else{
            history[prevHandValue][(currentHandValue+1)%3]++;
            history[prevHandValue][(currentHandValue+2)%3]++;
        }
    }


}
