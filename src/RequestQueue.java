import java.util.LinkedList;
import java.util.ListIterator;

public abstract class RequestQueue {
    protected Head head;
    protected LinkedList<Request> requests;

    public int countAveragePath() {
        requests = sort();
        ListIterator<Request> iterator = requests.listIterator();
        head.setTotalPath(0);
        Request current;

        while (iterator.hasNext()) {
            current = iterator.next();
            
            if(!current.systemwideRequest) {
            	head.move(current);
            } else {
            	head.systemMove(current);
            }
            
            //System.out.println(current);
        }

        return head.getTotalPath();
    }

    public RequestQueue addRequest(Request request) throws OutOfDiscException {
        if (request.discSection <= head.getMaxSection()) {
            requests.add(request);
        } else {
            throw new OutOfDiscException();
        }

        return this;
    }
    
    protected abstract LinkedList<Request> sort();
}
