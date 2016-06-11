import java.util.*;
public class MapsSingleThreadPerformanceTest {

	private static final String value="";
	
    public static void main(String[] args) throws Exception{
            Map<String,String> map=MapUtils.get(Integer.valueOf(args[0]));
            for (int i = 0; i < Integer.valueOf(args[1]); i++) {
                    map.put(("ELE:"+String.valueOf(i)),value);
            }
            MapsSingleThreadPerformanceTest test=new MapsSingleThreadPerformanceTest();
            // warm up collection add/remove/contains
            Map<String,String> warmupmap=MapUtils.get(Integer.valueOf(args[0]));
            for (int i = 0; i < 10000; i++) {
            	warmupmap.put(("WARMUPELE:"+String.valueOf(i)),value);
            	warmupmap.get(("WARMUPELE:"+String.valueOf(i)));
            	warmupmap.remove(("WARMUPELE:"+String.valueOf(i)));
            }
            // warm up testmethod
            for (int i = 0; i < 10; i++) {
            	if(i%2==0){
                    test.runAddTest(warmupmap,true);
                    test.runContainsTest(warmupmap,true);
                    test.runRemoveTest(warmupmap,true);
            	}
            	else{
            		test.runAddTest(warmupmap,false);
                    test.runContainsTest(warmupmap,false);
                    test.runRemoveTest(warmupmap,false);
            	}
            }
            Thread.sleep(3000);
            long beginTime=System.nanoTime();
            test.runAddTest(map,false);
            System.out.println("add "+(System.nanoTime()-beginTime)/100+" ns");
            beginTime=System.nanoTime();
            test.runContainsTest(map,false);
            System.out.println("find "+(System.nanoTime()-beginTime)/100+" ns");
            beginTime=System.nanoTime();
            test.runRemoveTest(map,false);
            System.out.println("remove "+(System.nanoTime()-beginTime)/100+" ns");
    }

    public void runAddTest(Map<String,String> map,boolean isNeed){
    		if(isNeed){
	            for (int i = 0; i < 10700; i++) {
	                    ;
	            }
    		}
            for (int i = 0; i < 100; i++) {
            	map.put("TESTELE"+i,value);
            }
    }

    public void runContainsTest(Map<String,String> map,boolean isNeed){
	    	if(isNeed){
	            for (int i = 0; i < 10700; i++) {
	                    ;
	            }
			}
            for (int i = 0; i < 100; i++) {
            	map.get("TESTELE"+i);
            }
    }

    public void runRemoveTest(Map<String,String> map,boolean isNeed){
	    	if(isNeed){
	            for (int i = 0; i < 10700; i++) {
	                    ;
	            }
			}
            for (int i = 0; i < 100; i++) {
            	map.remove("TESTELE"+i);
            }
    }

}
