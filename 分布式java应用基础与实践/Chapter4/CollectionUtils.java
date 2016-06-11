import java.util.*;
import java.util.concurrent.*;
public class CollectionUtils {

	static final int ARRAYLIST_TYPE=1;
	
	static final int LINKEDLIST_TYPE=2;
	
	static final int VECTOR_TYPE=3;
	
	static final int STACK_TYPE=4;
	
	static final int HASHSET_TYPE=5;
	
	static final int TREESET_TYPE=6;
	
	static final int COPYONWRITEARRAYLIST_TYPE=7;
	
	public static Collection<String> get(int type,int size){
		switch (type) {
		case ARRAYLIST_TYPE:
			return new ArrayList<String>(size);
		case LINKEDLIST_TYPE:
			return new LinkedList<String>();
		case VECTOR_TYPE:
			return new Vector<String>(size);
		case STACK_TYPE:
			return new Stack<String>();
		case HASHSET_TYPE:
			return new HashSet<String>(size);
		case TREESET_TYPE:
			return new TreeSet<String>();
		case COPYONWRITEARRAYLIST_TYPE:
			return new CopyOnWriteArrayList<String>();
		default:
			throw new IllegalArgumentException("Unkown type: "+type);
		}
	}
	
	public static boolean isThreadSafe(int type){
		switch (type) {
		case ARRAYLIST_TYPE:
			return false;
		case LINKEDLIST_TYPE:
			return false;
		case VECTOR_TYPE:
			return true;
		case STACK_TYPE:
			return true;
		case HASHSET_TYPE:
			return false;
		case TREESET_TYPE:
			return false;
		case COPYONWRITEARRAYLIST_TYPE:
			return true;
		default:
			throw new IllegalArgumentException("Unkown type: "+type);
		}
	}
	
}
