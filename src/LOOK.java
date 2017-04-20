import java.util.LinkedList;
import java.util.ListIterator;

public class LOOK extends SCAN {

    public LOOK(Head head) {
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
            sorted.addAll(sortUpwards(recent));
            sorted.addAll(sortDownwards(recent));
        } else {
            sorted.addAll(sortDownwards(recent));
            sorted.addAll(sortUpwards(recent));
        }

        return sorted;
    }
}
