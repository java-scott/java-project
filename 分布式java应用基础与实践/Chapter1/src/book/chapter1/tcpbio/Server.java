package book.chapter1.tcpbio;
/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 描述：基于java实现TCP/IP方式的网络通讯示例，此为服务器端代码
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
		ServerSocket ss=new ServerSocket(port);
		System.out.println("Server listen on port: "+port);
		Socket socket=ss.accept();
		BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
		while(true){
			String line=in.readLine();
			if(line==null){
				Thread.sleep(100);
				continue;
			}
			if("quit".equalsIgnoreCase(line.trim())){
				in.close();
				out.close();
				ss.close();
				System.out.println("Server has been shutdown!");
				System.exit(0);
			}
			else{
				System.out.println("Message from client: "+ line);
				out.println("Server response："+line);
				Thread.sleep(100);
			}
		}
	}
	
}
