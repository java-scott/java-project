import java.util.Collection;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CollectionsMultiThreadPerformanceTest {

	public static void main(String[] args) throws Exception{
		int ctype=Integer.valueOf(args[0]);
        Collection<String> collection=CollectionUtils.get(ctype, Integer.valueOf(args[1]));
        for (int i = 0; i < Integer.valueOf(args[1]); i++) {
        	collection.add(("ELE:"+String.valueOf(i)));
        }
        // warm up collection add/remove/contains
        Collection<String> warmupcollection=CollectionUtils.get(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        for (int i = 0; i < 10000; i++) {
            warmupcollection.add(("WARMUPELE:"+String.valueOf(i)));
            warmupcollection.contains(("WARMUPELE:"+String.valueOf(i)));
            warmupcollection.remove(("WARMUPELE:"+String.valueOf(i)));
        }
        // warm up testmethod
        for (int i = 0; i < 10; i++) {
        	new TestTask(warmupcollection, ctype, true).run();
        }
        Thread.sleep(3000);
        int threadCount=Integer.valueOf(args[2]);
        CountDownLatch latch=new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
			new Thread(new TestTask(collection, ctype,latch)).start();
		}
        latch.await();
    }

}

class TestTask implements Runnable{
	
	private static final Random random=new Random();
	
	private int next;
	
	private Collection<String> collection;
	
	private int ctype;
	
	private CountDownLatch latch;
	
	private boolean warm=false;
	
	public TestTask(Collection<String> collection,int ctype,CountDownLatch latch){
		this.collection=collection;
		this.ctype=ctype;
		this.latch=latch;
		next=random.nextInt(6);
	}
	
	public TestTask(Collection<String> collection,int ctype,boolean warm){
		this.collection=collection;
		this.ctype=ctype;
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
		if(CollectionUtils.isThreadSafe(ctype)){
			switch (next) {
				case 0:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.add(prefix+i);
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.contains(prefix+i);
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.remove(prefix+i);
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 1:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.add(prefix+i);
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.remove(prefix+i);
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.contains(prefix+i);
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 2:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.contains(prefix+i);
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.add(prefix+i);
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.remove(prefix+i);
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 3:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.contains(prefix+i);
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.remove(prefix+i);
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.add(prefix+i);
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 4:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.remove(prefix+i);
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.add(prefix+i);
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.contains(prefix+i);
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 5:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.remove(prefix+i);
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.contains(prefix+i);
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
		                collection.add(prefix+i);
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
						synchronized(collection){
							collection.add(prefix+i);
						}
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.contains(prefix+i);
						}
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.remove(prefix+i);
						}
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 1:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.add(prefix+i);
						}
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.remove(prefix+i);
						}
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.contains(prefix+i);
						}
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 2:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.contains(prefix+i);
						}
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.add(prefix+i);
						}
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.remove(prefix+i);
						}
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 3:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.contains(prefix+i);
						}
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.remove(prefix+i);
						}
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.add(prefix+i);
						}
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 4:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.remove(prefix+i);
						}
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.add(prefix+i);
						}
					}
					if(!warm)
						System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.contains(prefix+i);
						}
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					break;
				case 5:
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.remove(prefix+i);
						}
					}
					if(!warm)
						System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.contains(prefix+i);
						}
					}
					if(!warm)
						System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
					beginTime=System.nanoTime();
					for (int i = 0; i < 100; i++) {
						synchronized(collection){
							collection.add(prefix+i);
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
