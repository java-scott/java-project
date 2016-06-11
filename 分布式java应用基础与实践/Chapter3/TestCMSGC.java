import java.util.*;
public class TestCMSGC{

   public static void main(String[] args) throws Exception{ 
       List<MemoryObject> objects=new ArrayList<MemoryObject>(6);
       for(int i=0;i<9;i++){
          objects.add(new MemoryObject(1024*1024));
       } 
       //System.gc();
       Thread.sleep(2000);
       objects.remove(0);
       objects.remove(0);
       objects.remove(0);
       for(int i=0;i<20;i++){
          objects.add(new MemoryObject(1024*1024));
          if(i%2==0){
             objects.remove(0);
             //objects.remove(0);        
          }
       }
       Thread.sleep(5000);        
   }

}

class MemoryObject{
        private byte[] bytes;
        public MemoryObject(int objectSize){
                this.bytes=new byte[objectSize];
        }
}
