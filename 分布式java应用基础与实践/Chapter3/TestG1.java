import java.util.*;
public class TestG1{
    public static void main(String[] args) throws Exception{
        List<MemoryObject> objects=new ArrayList<MemoryObject>();
        for(int i=0;i<20;i++){
           objects.add(new MemoryObject(1024*1024));
           if(i%3==0){
               objects.remove(0);
           }
        }
        Thread.sleep(2000);
    }
}

