public class Request implements Comparable<Request>, Cloneable {
    public int comeTime;
    public int discSection;

    public Request(int comeTime, int discSection) {
        this.comeTime = comeTime;
        this.discSection = discSection;
    }

    public int compareTo(Request another) {
        if (another.discSection == this.discSection) {
            return 0;
        }
        else if (another.discSection > this.discSection) {
            return -1;
        }
        else {
            return 1;
        }
    }

    public String toString() {
        return "comeTime: " + comeTime + " - discSection: " + discSection;
    }

    public Request clone() {
        return new Request(comeTime, discSection);
    }
}