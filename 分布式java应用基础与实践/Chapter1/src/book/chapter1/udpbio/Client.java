/**
 * �����������ܵĴ��ͷֲ�ʽJavaӦ�á�
 *  ���е�ʾ������
 *  ��Ȩ����   2008---2009
 */
package book.chapter1.udpbio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * ����������javaʵ��UDP/IP+BIO��ʽ������ͨѶʾ������Ϊ�ͻ��˴���
 *
 * @author bluedavy
 *  
 * ����ʱ�䣺 2008-11-27
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		int port=9527;
		int aport=9528;
		DatagramSocket serverSocket=new DatagramSocket(aport);
		byte[] buffer=new byte[65507];
		DatagramPacket receivePacket=new DatagramPacket(buffer,buffer.length);
		DatagramSocket socket=new DatagramSocket();
		InetAddress server=InetAddress.getByName("localhost");
		BufferedReader systemIn=new BufferedReader(new InputStreamReader(System.in));
		boolean flag=true;
		while(flag){
			String command=systemIn.readLine();
			byte[] datas=command.getBytes("UTF-8");
			DatagramPacket packet=new DatagramPacket(datas,datas.length,server,port);
			socket.send(packet);
			if(command==null || "quit".equalsIgnoreCase(command.trim())){
				flag=false;
				System.out.println("Client quit!");
				socket.close();
				continue;
			}
			serverSocket.receive(receivePacket);
			String receiveResponse=new String(receivePacket.getData(),0,receivePacket.getLength(),"UTF-8");
			System.out.println(receiveResponse);
		}
	}

}
