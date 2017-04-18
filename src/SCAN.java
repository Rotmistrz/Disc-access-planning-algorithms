import java.util.LinkedList;
import java.util.ListIterator;

public class SCAN extends RequestQueue {
	Direction direction;
	
    public SCAN(Head head) {
        this.requests = new LinkedList<Request>();
        this.head = head;
    }

    @Override
    public int countAveragePath() {
    	requests = sort();
        ListIterator<Request> iterator = requests.listIterator();
        head.setTotalPath(0);
        Request current;

        while (iterator.hasNext()) {
            current = iterator.next();
            head.move(current);
            System.out.println(current);
        }

        return head.getTotalPath()/requests.size();
    }

    @Override
    public RequestQueue addRequest(Request request) throws OutOfDiscException {
        if (request.discSection <= head.getMaxSection()) {
            requests.add(request);
        } else {
            throw new OutOfDiscException();
        }

        return this;
    }
    
    private LinkedList<Request> sort() {
    	if(requests.size() <= 1) {
    		return requests;
    	}
    	
    	LinkedList<Request> sorted = new LinkedList<Request>();
    	Request recent = requests.pop();
    	ListIterator<Request> it;
    	Request current;
    	Request nearest;
    	
    	sorted.add(recent);
    	
    	// calculating direction
    	
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
