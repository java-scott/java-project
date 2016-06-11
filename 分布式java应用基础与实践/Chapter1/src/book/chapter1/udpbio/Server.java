package book.chapter1.udpbio;
/**
 * �����������ܵĴ��ͷֲ�ʽJavaӦ�á�
 *  ���е�ʾ������
 *  ��Ȩ����   2008---2009
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * ����������javaʵ��UDP/IP+BIO��ʽ������ͨѶʾ������Ϊ�������˴���
 *
 * @author bluedavy
 *  
 * ����ʱ�䣺 2008-11-27
 */
public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		int port=9527;
		int aport=9528;
		DatagramSocket server=new DatagramSocket(port);
		DatagramSocket client=new DatagramSocket();
		InetAddress serverAddress=InetAddress.getByName("localhost");
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
				String response="Server response��"+line;
				byte[] datas=response.getBytes("UTF-8");
				DatagramPacket responsePacket=new DatagramPacket(datas,datas.length,serverAddress,aport);
				client.send(responsePacket);
				Thread.sleep(100);
			}
		}
	}

}
