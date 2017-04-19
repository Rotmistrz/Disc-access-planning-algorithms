import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RequestList {
	private LinkedList<Request> requests;
	
	public RequestList() {
		this.requests = new LinkedList<Request>();
	}
	
	public RequestList add(Request request) {
		ListIterator<Request> it = requests.listIterator();
		
		Request current;
		
		while(it.hasNext()) {
			current = it.next();
			
			if(request.comeTime < current.comeTime) {
				it.set(request);
				it.add(current);
				
				return this;
			}
		}
		
		it.add(request);
		
		return this;
	}
	
	public Iterator<Request> iterator() {
		return requests.iterator();
	}
	
	public ListIterator<Request> listIterator() {
		return requests.listIterator();
	}
	
	public static RequestList createFromFile(String filename) throws IOException {
		RequestList requestList = new RequestList();
		
		int id;
		int comeTime;
		int discSection;
		
		String line;
		String[] data;
		
		Scanner file = new Scanner(Paths.get(filename), "UTF-8");
		
		try {
			while((line = file.nextLine()) != null) {
				data = Base.split(line, " ");
	
				//id = Integer.parseInt(data[0]);
				//comeTime = Integer.parseInt(data[0]);
				discSection = Integer.parseInt(data[0]);
				
				Request request = new Request(0, discSection);
				
				requestList.add(request);
			}
		} catch(NoSuchElementException e) {

		} catch(Exception e) {
			throw e;
		} finally {
			file.close();
		}
		
		return requestList;
	}
}
