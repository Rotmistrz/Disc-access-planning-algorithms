import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class FCFS extends RequestQueue {

    public FCFS(Head head) {
        this.head = head;
        this.requests = new LinkedList<Request>();
    }
    
    public LinkedList<Request> sort() {
    	return requests;
    }
}
