package book.chapter1.tcpbio;
/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 描述：基于java实现TCP/IP+BIO方式的网络通讯示例，此为客户端代码
 *
 * @author bluedavy 
 * 
 * 创建时间： 2008-11-27
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String host="127.0.0.1";
		int port=9527;
		Socket socket=new Socket(host,port);
		BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
		BufferedReader systemIn=new BufferedReader(new InputStreamReader(System.in));
		boolean flag=true;
		while(flag){
			String command=systemIn.readLine();
			if(command==null || "quit".equalsIgnoreCase(command.trim())){
				flag=false;
				System.out.println("Client quit!");
				out.println("quit");
				out.close();
				in.close();
				socket.close();
				continue;
			}
			out.println(command);
			String response=in.readLine();
			System.out.println(response);
		}
	}
	
}
