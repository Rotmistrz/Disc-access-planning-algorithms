import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class FCFS extends RequestQueue {

    public FCFS(Head head) {
        this.head = head;
        this.requests = new LinkedList<Request>();
    }

    @Override
    public int countAveragePath() {
        ListIterator<Request> iterator = requests.listIterator();
        head.setTotalPath(0);
        Request current;

        while (iterator.hasNext()) {
            current = iterator.next();
            head.move(current);
            System.out.println(current);
        }


        return head.getTotalPath();
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
}
