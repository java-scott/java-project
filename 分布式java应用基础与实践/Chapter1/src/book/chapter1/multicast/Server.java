package book.chapter1.multicast;
/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * 描述：基于java实现UDP/IP+BIO方式的网络通讯示例，此为服务器端代码
 *
 * @author bluedavy
 *  
 * 创建时间： 2008-11-27
 */
public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		int port=9527;
		int aport=9528;
		InetAddress groupAddress=InetAddress.getByName("224.1.1.1");
		
		MulticastSocket server=new MulticastSocket(port);
		server.joinGroup(groupAddress);
		
		MulticastSocket client=new MulticastSocket();
		client.joinGroup(groupAddress);
		
		byte[] buffer=new byte[65507];
		DatagramPacket packet=new DatagramPacket(buffer,buffer.length);
		while(true){
			server.receive(packet);
			String line=new String(packet.getData(),0,packet.getLength(),"UTF-8");
			if("quit".equalsIgnoreCase(line.trim())){
				server.close();
				System.exit(0);
			}
			else{
				System.out.println("Message from client: "+ line);
				packet.setLength(buffer.length);
				String response="Server response："+line;
				byte[] datas=response.getBytes("UTF-8");
				DatagramPacket responsePacket=new DatagramPacket(datas,datas.length,groupAddress,aport);
				client.send(responsePacket);
				Thread.sleep(100);
			}
		}
	}

}
