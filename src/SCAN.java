import java.util.LinkedList;
import java.util.ListIterator;

public class SCAN extends RequestQueue {
    Direction direction;

    public SCAN(Head head) {
        this.requests = new LinkedList<Request>();
        this.head = head;
    }

    public LinkedList<Request> sort() {
        if(requests.size() <= 1) {
            return requests;
        }

        LinkedList<Request> sorted = new LinkedList<Request>();
        Request recent = requests.pop();

        sorted.add(recent);

        Request discBegin = new Request(0, 0);
        Request discEnd = new Request(0, head.getMaxSection());
        
        calculateDirection();

        if (direction == Direction.UP) {
            sorted.addAll(sortUpwards(recent));
            sorted.add(discEnd);
            sorted.addAll(sortDownwards(recent));
        } else {
            sorted.addAll(sortDownwards(recent));
            sorted.add(discBegin);
            sorted.addAll(sortUpwards(recent));
        }

        return sorted;
    }

    protected LinkedList<Request> sortUpwards(Request startPoint) {
    	ListIterator<Request> it = requests.listIterator();
        LinkedList<Request> sorted = new LinkedList<Request>();
        Request smallest = null;
        Request current;
        LinkedList<Request> requestsCopy = new LinkedList<Request>();

        while (it.hasNext()) {
            requestsCopy.add(it.next().clone());
        }

        for(int n = requestsCopy.size(); n > 0; n--) {
            it = requestsCopy.listIterator();

            while(it.hasNext()) {
                smallest = (Request) it.next();

                if (smallest.compareTo(startPoint) >= 0) {
                    break;
                } else {
                    it.remove();
                    n--;
                    smallest = null;
                }
            }

            while(it.hasNext()) {
                current = (Request) it.next();

                if(current.compareTo(startPoint) >= 0 && current.compareTo(smallest) < 0) {
                    smallest = current;
                }
            }

            if(smallest != null) {
                sorted.add(smallest);
                requestsCopy.remove(smallest);
            }
        }

        return sorted;
    }

    protected LinkedList<Request> sortDownwards(Request startPoint) {
        ListIterator<Request> it = requests.listIterator();
        LinkedList<Request> sorted = new LinkedList<Request>();
        Request largest = null;
        Request current;
        LinkedList<Request> requestsCopy = new LinkedList<Request>();

        while (it.hasNext()) {
            requestsCopy.add(it.next().clone());
        }

        for(int n = requestsCopy.size(); n > 0; n--) {
            it = requestsCopy.listIterator();

            while(it.hasNext()) {
                largest = (Request) it.next();

                if (largest.compareTo(startPoint) <= 0) {
                    break;
                } else {
                    it.remove();
                    n--;
                    largest = null;
                }
            }

            while(it.hasNext()) {
                current = (Request) it.next();

                if(current.compareTo(startPoint) <= 0 && current.compareTo(largest) > 0) {
                    largest = current;
                }
            }

            if(largest != null) {
                sorted.add(largest);
                requestsCopy.remove(largest);
            }
        }

        return sorted;
    }

    protected SCAN calculateDirection() {
        direction = (head.getMaxSection() - requests.peek().discSection >= requests.peek().discSection) ? Direction.UP : Direction.DOWN;

        return this;
    }

    protected SCAN toggleDirection() {
        direction = (direction == Direction.UP) ? Direction.DOWN : Direction.UP;

        return this;
    }
}
