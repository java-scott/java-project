import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MapsMultiThreadPerformanceTest {

	private static final String value="";
	
	public static void main(String[] args) throws Exception{
		int mtype=Integer.valueOf(args[0]);
		Map<String,String> map=MapUtils.get(mtype);
        for (int i = 0; i < Integer.valueOf(args[1]); i++) {
        	map.put(("ELE:"+String.valueOf(i)),value);
        }
        // warm up collection add/remove/contains
        Map<String,String> warmupmap=MapUtils.get(mtype);
        for (int i = 0; i < 10000; i++) {
        	warmupmap.put(("WARMUPELE:"+String.valueOf(i)),value);
        	warmupmap.get(("WARMUPELE:"+String.valueOf(i)));
        	warmupmap.remove(("WARMUPELE:"+String.valueOf(i)));
        }
        // warm up testmethod
        for (int i = 0; i < 10; i++) {
        	new MapTestTask(warmupmap, mtype, true).run();
        }
        Thread.sleep(3000);
        int threadCount=Integer.valueOf(args[2]);
        CountDownLatch latch=new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
			new Thread(new MapTestTask(map, mtype,latch)).start();
		}
        latch.await();
    }

}

class MapTestTask implements Runnable{
	
	private static final Random random=new Random();
	
	private int next;
	
	private static final String value="";
	
	private Map<String,String> map;
	
	private int mtype;
	
	private CountDownLatch latch;
	
	private boolean warm=false;
	
	public MapTestTask(Map<String,String> map,int mtype,CountDownLatch latch){
		this.map=map;
		this.mtype=mtype;
		this.latch=latch;
		next=random.nextInt(6);
	}
	
	public MapTestTask(Map<String,String> map,int mtype,boolean warm){
		this.map=map;
		this.mtype=mtype;
		this.warm=warm;
		next=random.nextInt(6);
	}
	
	public void run() {
		if(warm){
			for (int i = 0; i < 10700; i++) {
				;
			}
		}
		String threadName=Thread.currentThread().getName();
		String prefix="TESTELE-"+threadName+"-";
		long beginTime=System.nanoTime();
		if(MapUtils.isThreadSafe(mtype)){
			switch (next) {
				case 0:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                map.put(prefix+i,value);
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                map.containsKey(prefix+i);
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                map.remove(prefix+i);
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 1:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.put(prefix+i,value);
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.remove(prefix+i);
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.containsKey(prefix+i);
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 2:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.containsKey(prefix+i);
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.put(prefix+i,value);
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.remove(prefix+i);
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 3:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.containsKey(prefix+i);
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.remove(prefix+i);
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.put(prefix+i,value);
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 4:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.remove(prefix+i);
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.put(prefix+i,value);
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.containsKey(prefix+i);
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 5:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.remove(prefix+i);
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.containsKey(prefix+i);
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						map.put(prefix+i,value);
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				default:
					break;
			}
		}
		else{
			switch (next) {
				case 0:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.put(prefix+i,value);
						}
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.containsKey(prefix+i);
						}
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.remove(prefix+i);
						}
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 1:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.put(prefix+i,value);
						}
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.remove(prefix+i);
						}
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.containsKey(prefix+i);
						}
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 2:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.containsKey(prefix+i);
						}
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.put(prefix+i,value);
						}
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.remove(prefix+i);
						}
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 3:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.containsKey(prefix+i);
						}
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.remove(prefix+i);
						}
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.put(prefix+i,value);
						}
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 4:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.remove(prefix+i);
						}
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.put(prefix+i,value);
						}
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.containsKey(prefix+i);
						}
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 5:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.remove(prefix+i);
						}
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.containsKey(prefix+i);
						}
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(map){
							map.put(prefix+i,value);
						}
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				default:
					break;
			}
		}
		if(!warm)
			latch.countDown();
	}
	
}
