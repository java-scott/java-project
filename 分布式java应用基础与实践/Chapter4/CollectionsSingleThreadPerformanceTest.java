import java.util.Collection;
public class CollectionsSingleThreadPerformanceTest {

    public static void main(String[] args) throws Exception{
            Collection<String> collection=CollectionUtils.get(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
            for (int i = 0; i < Integer.valueOf(args[1]); i++) {
                    collection.add(("ELE:"+String.valueOf(i)));
            }
            CollectionsSingleThreadPerformanceTest test=new CollectionsSingleThreadPerformanceTest();
            // warm up collection add/remove/contains
            Collection<String> warmupcollection=CollectionUtils.get(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
            for (int i = 0; i < 10000; i++) {
                    warmupcollection.add(("WARMUPELE:"+String.valueOf(i)));
                    warmupcollection.contains(("WARMUPELE:"+String.valueOf(i)));
                    warmupcollection.remove(("WARMUPELE:"+String.valueOf(i)));
            }
            // warm up testmethod
            for (int i = 0; i < 10; i++) {
            	if(i%2==0){
                    test.runAddTest(warmupcollection,true);
                    test.runContainsTest(warmupcollection,true);
                    test.runRemoveTest(warmupcollection,true);
            	}
            	else{
            		test.runAddTest(warmupcollection,false);
                    test.runContainsTest(warmupcollection,false);
                    test.runRemoveTest(warmupcollection,false);
            	}
            }
            Thread.sleep(3000);
            long beginTime=System.nanoTime();
            test.runAddTest(collection,false);
            System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
            beginTime=System.nanoTime();
            test.runContainsTest(collection,false);
            System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
            beginTime=System.nanoTime();
            test.runRemoveTest(collection,false);
            System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
    }

    public void runAddTest(Collection<String> collection,boolean isNeed){
	    	if(isNeed){
	    		for (int i = 0; i < 10700; i++) {
	                    ;
	            }
	        }
            for (int i = 0; i < 100; i++) {
                    collection.add("TESTELE"+i);
            }
    }

    public void runContainsTest(Collection<String> collection,boolean isNeed){
            if(isNeed){
	    		for (int i = 0; i < 10700; i++) {
	                    ;
	            }
            }
            for (int i = 0; i < 100; i++) {
                    collection.contains("TESTELE"+i);
            }
    }

    public void runRemoveTest(Collection<String> collection,boolean isNeed){
	    	if(isNeed){
	    		for (int i = 0; i < 10700; i++) {
	                    ;
	            }
	        }
            for (int i = 0; i < 100; i++) {
                    collection.remove("TESTELE"+i);
            }
    }

}
