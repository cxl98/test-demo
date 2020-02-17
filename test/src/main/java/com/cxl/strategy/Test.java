package com.cxl.strategy;


import com.cxl.strategy.A1.Hand;
import com.cxl.strategy.A1.Player;
import com.cxl.strategy.A1.ProbStrategy;
import com.cxl.strategy.A1.WinStrategy;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
//        Scanner scanner=new Scanner(System.in);
//        int seed = scanner.nextInt();
//        int seed1 = scanner.nextInt();
        Player player = new Player("xxx", new WinStrategy());
        Player player1 = new Player("aaa", new ProbStrategy());
        for (int i = 0; i < 10; i++) {
            Hand hand = player.nextHand();
            Hand hand1 = player1.nextHand();
            if ( hand.isWin( hand1)) {
                System.out.println("Winner" + player);
                player.win();
                player1.lose();
            } else if (hand1.isWin( hand)) {
                System.out.println("Winner" + player1);
                player1.win();
                player.lose();
            }else {
                System.out.println("Even....");
                player.even();
                player1.even();
            }
        }
        System.out.println("Total result");
        System.out.println(player.toString());
        System.out.println(player1.toString());



//        Player a=new Player("A",new WinStrategy());
//        Player b=new Player("B",new ProbStrategy());
//        for (int i = 0; i <10 ; i++) {
//            Hand1 hand=a.nextHand();
//            Hand1 hand1=b.nextHand();
//            switch (hand.fight(hand1)){
//                case 0:
//                    System.out.println("Winner:"+a);
//                    a.win();
//                    b.lose();
//                case 1:
//                    System.out.println("Winner："+b);
//                    b.win();
//                    a.lose();
//                case 2:
//                    System.out.println("Even..");
//                    a.even();
//                    b.even();
//                    default:
//                        System.out.println(hand.toString());
//            }
//        }
//        System.out.println("Total result:");
//        System.out.println(a.toString());
//        System.out.println(b.toString());


//        Sort sort=new SelectSort();
//        String[] data={"Almpty", "Bowman", "Barroll", "Elfland", "Alice"};
//        SortAndPrint sortAndPrint=new SortAndPrint(data,sort);
//        sortAndPrint.execute();

    }
}
