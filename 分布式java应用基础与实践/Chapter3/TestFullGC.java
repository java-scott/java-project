import java.util.*;
public class TestFullGC{

   public static void main(String[] args) throws Exception{ 
       List<MemoryObject> objects=new ArrayList<MemoryObject>(6);
       for(int i=0;i<7;i++){
          objects.add(new MemoryObject(1024*1024));
       } 
       //System.gc();
       //System.gc();
       Thread.sleep(2000);
       for(int i=0;i<8;i++){
         if(i==7){
            for(int j=0;j<6;j++){
             objects.remove(0);
            }
            System.out.println("should happen full gc");
            Thread.sleep(2000);
            objects.add(new MemoryObject(1024*512));
         }
         if(i!=7)
          objects.add(new MemoryObject(1024*1024));
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
