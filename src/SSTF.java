import java.util.LinkedList;
import java.util.ListIterator;

public class SSTF extends RequestQueue {

    public SSTF(Head head) {
        this.requests = new LinkedList<Request>();
        this.head = head;
    }
    
    public LinkedList<Request> sort() {
        if(requests.size() <= 1) {
            return requests;
        }

        LinkedList<Request> sorted = new LinkedList<Request>();
        Request recent = getNearest(head.getCurrentSection());
        ListIterator it;
        Request current;
        Request nearest;

        requests.remove(recent);
        sorted.add(recent);

        for(int n = requests.size(); n > 0; n--) {
            it = requests.listIterator();
            nearest = (Request) it.next();

            while(it.hasNext()) {
                current = (Request) it.next();

                if(Math.abs(recent.discSection - nearest.discSection) > Math.abs(recent.discSection - current.discSection)) {
                    nearest = current;
                }
            }

            sorted.add(nearest);
            requests.remove(nearest);
            recent = nearest;
        }

        return sorted;
    }
}
