import java.util.LinkedList;
import java.util.ListIterator;

public class CSCAN extends SCAN {
    Direction direction;

    public CSCAN(Head head) {
        super(head);
    }

    public LinkedList<Request> sort() {
        if(requests.size() <= 1) {
            return requests;
        }

        LinkedList<Request> sorted = new LinkedList<Request>();
        Request recent = requests.pop();
        
        Request discBegin = new Request(0, 0);
        Request discEnd = new Request(0, head.getMaxSection());
        
        sorted.add(recent);

        calculateDirection();
        
        if (direction == Direction.UP) {
            sorted.addAll(sortUpwards(recent.discSection, head.getMaxSection()));
            sorted.add(discEnd);
            
            discBegin.systemwideRequest = true;
            sorted.add(discBegin);
            
            sorted.addAll(sortUpwards(0, recent.discSection));
        } else {
        	sorted.addAll(sortDownwards(0, recent.discSection));
            sorted.add(discBegin);
            
            discEnd.systemwideRequest = true;
            sorted.add(discEnd);
            
            sorted.addAll(sortDownwards(recent.discSection, head.getMaxSection()));
        }

        return sorted;
    }

    private LinkedList<Request> sortUpwards(int bottomLimit, int topLimit) {
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

                if (smallest.discSection > bottomLimit && smallest.discSection < topLimit) {
                    break;
                } else {
                    it.remove();
                    n--;
                    smallest = null;
                }
            }

            while(it.hasNext()) {
                current = (Request) it.next();

                if(smallest.discSection > bottomLimit && smallest.discSection < topLimit && current.compareTo(smallest) < 0) {
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

    private LinkedList<Request> sortDownwards(int bottomLimit, int topLimit) {
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

                if (largest.discSection > bottomLimit && largest.discSection < topLimit) {
                    break;
                } else {
                    it.remove();
                    n--;
                    largest = null;
                }
            }

            while(it.hasNext()) {
                current = (Request) it.next();

                if(largest.discSection > bottomLimit && largest.discSection < topLimit && current.compareTo(largest) > 0) {
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
}
