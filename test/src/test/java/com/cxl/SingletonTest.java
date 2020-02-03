package com.cxl;

import com.cxl.singleton.TicketMaker;
import com.cxl.singleton.Triple;
import com.cxl.singleton.Triple1;

public class SingletonTest {
    public static void main(String[] args) {
//        Singleton singleton=Singleton.getInstance();
//        Singleton instance = Singleton.getInstance();
//        if (singleton.equals(instance)||singleton==instance) {
//            System.out.println("xt");
//        }else{
//            System.out.println("bt");
//        }



//        TicketMaker singleton = TicketMaker.getSingleton();
//        for (int i = 0; i <10 ; i++) {
//            System.out.println(i+":"+singleton.getNextNumber());
//        }


//        for (int i = 0; i <9 ; i++) {
//            Triple instance = Triple.getInstance(i % 3);
//            System.out.println(i+":"+instance);
//        }

        for (int i = 0; i < 9; i++) {
            Triple1 triple1=Triple1.getInstance(i%3);
            System.out.println(i+":"+triple1);
        }

    }
}
