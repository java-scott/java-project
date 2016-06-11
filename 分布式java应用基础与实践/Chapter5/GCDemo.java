import java.util.*;
public class GCDemo {

	public static void main(String[] args) throws Exception{
		Thread.sleep(10000);
		List<GCDataObject> oldGenObjects=new ArrayList<GCDataObject>();
	        for (int I = 0; I < 51200; I++) {
		  oldGenObjects.add(new GCDataObject(2));
		}
		System.gc();
		oldGenObjects.size();
		oldGenObjects=null;
		Thread.sleep(5000);
		List<GCDataObject> tmpObjects=new ArrayList<GCDataObject>();
		for (int I = 0; I < 3200; I++) {
			tmpObjects.add(new GCDataObject(5));
		}
tmpObjects.size();
		tmpObjects=null;
	}

}

class GCDataObject{
	
	byte[] bytes=null;
	
	RefObject object=null;
	
	public GCDataObject(int factor){
		// create object in kb
		bytes=new byte[factor*1024];
		object=new RefObject();
	}
	
}

class RefObject{
	
	RefChildObject object;
	
	public RefObject(){
		object=new RefChildObject();
	}
	
}

class RefChildObject{
	
	public RefChildObject(){
		;
	}
	
}

