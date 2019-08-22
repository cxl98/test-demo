public class TestWeightRounRobin {
    public static void main(String[] args)  {
        WeightRounRobin weightRounRobin=Factorys.getInstance();
        weightRounRobin.init();
        for (int i=0;i<15;i++){
            GetServerWeight serverWeight=weightRounRobin.getServers();
            System.out.println("server: "+serverWeight.getIp()+" : "+"weight: "+serverWeight.getWeight());
        }
    }


}
