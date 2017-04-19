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

        calculateDirection();

        if (direction == Direction.TOP) {
            sorted.addAll(sortUpwards(recent));
            sorted.addAll(sortDownwards(recent));
        } else {
            sorted.addAll(sortDownwards(recent));
            sorted.addAll(sortUpwards(recent));
        }

        return sorted;
    }

    private LinkedList<Request> sortUpwards(Request startPoint) {
        ListIterator<Request> it;
        LinkedList<Request> sorted = new LinkedList<Request>();
        Request smallest = null;
        Request current;
        LinkedList<Request> requestsCopy = (LinkedList<Request>) requests.clone();

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

    private LinkedList<Request> sortDownwards(Request startPoint) {
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

    private SCAN calculateDirection() {
        direction = (head.getMaxSection() - requests.peek().discSection >= requests.peek().discSection) ? Direction.TOP : Direction.BOTTOM;

        return this;
    }

    private SCAN toggleDirection() {
        direction = (direction == Direction.TOP) ? Direction.BOTTOM : Direction.TOP;

        return this;
    }
}
