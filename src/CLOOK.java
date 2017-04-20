import java.util.LinkedList;
import java.util.ListIterator;

public class CLOOK extends CSCAN {
	
    public CLOOK(Head head) {
    	super(head);
    }

    public LinkedList<Request> sort() {
        if(requests.size() <= 1) {
            return requests;
        }

        LinkedList<Request> sorted = new LinkedList<Request>();
        Request recent = requests.pop();
        
        sorted.add(recent);

        calculateDirection();
        
        if (direction == Direction.UP) {
            sorted.addAll(sortUpwards(recent.discSection, head.getMaxSection()));
            sorted.addAll(sortUpwards(0, recent.discSection - 1));
        } else {
        	sorted.addAll(sortDownwards(0, recent.discSection));
            sorted.addAll(sortDownwards(recent.discSection + 1, head.getMaxSection()));
        }

        return sorted;
    }
}
