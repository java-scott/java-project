/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
package book.chapter1.mina;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.common.ConnectFuture;
import org.apache.mina.common.IoHandler;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.SocketConnector;

/**
 * 描述：基于Mina实现的tcp client
 *
 * @author bluedavy 
 * 创建时间： 2008-12-4
 */
public class Client {
	
	public static void main(String[] args) throws Exception{
		int port=9527;
		
		SocketConnector ioConnector = new SocketConnector(Runtime.getRuntime().availableProcessors() + 1,
                Executors.newCachedThreadPool());
        ioConnector.getDefaultConfig().getSessionConfig().setTcpNoDelay(true);
        ioConnector.getFilterChain().addLast("stringserialize", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", port);
        IoHandler handler=new IoHandlerAdapter(){

			public void messageReceived(IoSession session, Object message)
					throws Exception {
				System.out.println(message);
			}        	
        	
        };
        ConnectFuture connectFuture = ioConnector.connect(socketAddress,handler);
        connectFuture.join();
        IoSession session=connectFuture.getSession();
        BufferedReader systemIn=new BufferedReader(new InputStreamReader(System.in));
        while(true){
			String command=systemIn.readLine();
			if(command==null || "quit".equalsIgnoreCase(command.trim())){
				System.out.println("Client quit!");
				session.write("quit");
				session.close();
				System.exit(0);
			}
			session.write(command);
		}
	}

}
