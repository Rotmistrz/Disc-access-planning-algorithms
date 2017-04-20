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
        
        int initialHeadSection = head.getCurrentSection();

        calculateDirection();
        
        if (direction == Direction.UP) {
            sorted.addAll(sortUpwards(initialHeadSection, head.getMaxSection()));
            
            Request smallest = getNearest(0);
            smallest.systemwideRequest = true;
            sorted.add(smallest);
            requests.remove(smallest);
            
            sorted.addAll(sortUpwards(0, initialHeadSection - 1));
        } else {
        	sorted.addAll(sortDownwards(0, initialHeadSection));
        	
        	Request largest = getNearest(0);
        	largest.systemwideRequest = true;
            sorted.add(largest);
            requests.remove(largest);
        	
            sorted.addAll(sortDownwards(initialHeadSection + 1, head.getMaxSection()));
        }

        return sorted;
    }
}
