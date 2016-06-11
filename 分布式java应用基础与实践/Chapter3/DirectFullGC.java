public class DirectFullGC{


    public static void main(String[] args) throws Exception{
        byte[] bytes=new byte[1024*1024*2];
        byte[] bytes2=new byte[1024*1024*2];
        byte[] bytes3=new byte[1024*1024*2];
        System.out.println("ready to happen one minor gc,if parallel gc,then should one full gc");
        byte[] bytes4=new byte[1024*1024*2];
        Thread.sleep(3000);
        System.out.println("minor gc end");
        byte[] bytes5=new byte[1024*1024*2];
        byte[] bytes6=new byte[1024*1024*2];
        System.out.println("minor gc again ,and should direct full gc");
        byte[] bytes7=new byte[1024*1024*2];
        Thread.sleep(3000);    
    }

}
