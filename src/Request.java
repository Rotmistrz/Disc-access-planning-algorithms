public class Request {
    public int comeTime;
    public int discSection;

    public Request(int comeTime, int discSection) {
        this.comeTime = comeTime;
        this.discSection = discSection;
    }

    public String toString() {
        return "comeTime: " + comeTime + " - discSection: " + discSection;
    }
}