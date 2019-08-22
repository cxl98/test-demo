public class GetServerWeight {
    private String ip;
    private int weight;

    public GetServerWeight(String ip, int weight) {
        this.ip = ip;
        this.weight = weight;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
