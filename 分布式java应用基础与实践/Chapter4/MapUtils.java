import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class MapUtils {

    static final int HASMAP_TYPE=1;

    static final int TREEMAP_TYPE=2;
    
    static final int CONCURRENTHASHMAP_TYPE=3;

    public static Map<String,String> get(int type){
            switch (type) {
	            case HASMAP_TYPE:
	            	return new HashMap<String,String>();
	            case TREEMAP_TYPE:
	            	return new TreeMap<String,String>();
	            case CONCURRENTHASHMAP_TYPE:
	            	return new ConcurrentHashMap<String,String>();
	            default:
	            	throw new IllegalArgumentException("Unkown type: "+type);
            }
    }

    public static boolean isThreadSafe(int type){
            switch (type) {
	            case HASMAP_TYPE:
	            	return false;
	            case TREEMAP_TYPE:
	            	return false;
	            case CONCURRENTHASHMAP_TYPE:
                    return true;
	            default:
	                    throw new IllegalArgumentException("Unkown type: "+type);
            }
    }

}
