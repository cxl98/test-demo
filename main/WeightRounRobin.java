import java.util.ArrayList;
import java.util.List;

public class WeightRounRobin {
    private int currentIndex=-1;
    private int currentWeight=0;
    private int maxWeight;
    private int gcbWeight;

    private int serverCount;
    private List<GetServerWeight> servers=new ArrayList<GetServerWeight>();

    public int greaterCommonDivisor(int a,int b){
        if (a%b==0){
            return b;
        }else{
            return greaterCommonDivisor(b,a%b);
        }
    }
    public int greatestCommonDivisor(List<GetServerWeight> servers){
        int divisor=0;
        for (int index=0,len=servers.size();index<len-1;index++){
           if (index==0){
               divisor=greaterCommonDivisor(   servers.get(index).getWeight(),servers.get(index+1).getWeight());
           }else{
               divisor=greaterCommonDivisor(index,servers.get(index).getWeight());
           }
        }
        return divisor;
    }
    public int getWeight(List<GetServerWeight> servers){
        int weight=0;
        for (GetServerWeight serverWeight :servers){
            if (weight<serverWeight.getWeight()){
                weight=serverWeight.getWeight();
            }
        }
        return weight;
    }
    public GetServerWeight getServers(){
      while(true){
          currentIndex=(currentIndex+1)%serverCount;
          if (currentIndex==0){
              currentWeight-=gcbWeight;
              if (currentWeight<=0){
                  currentWeight=maxWeight;
                  if (currentWeight==0){
                      return null;
                  }
              }
          }
          if (servers.get(currentIndex).getWeight()>=currentWeight){
              return servers.get(currentIndex);
          }
      }
    }
    public void  init(){
        servers.add(new GetServerWeight("192.168.15.1",1));
        servers.add(new GetServerWeight("192.168.15.2",2));
        servers.add(new GetServerWeight("192.168.15.3",4));
        servers.add(new GetServerWeight("192.168.15.4",8));
        maxWeight=getWeight(servers);
        gcbWeight=greatestCommonDivisor(servers);
        serverCount=servers.size();
    }

}
