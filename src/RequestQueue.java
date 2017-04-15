import java.util.LinkedList;

public abstract class RequestQueue {
    protected Head head;
    protected LinkedList<Request> requests;

    public abstract int countAveragePath();

    public abstract RequestQueue addRequest(Request request) throws OutOfDiscException;
}
