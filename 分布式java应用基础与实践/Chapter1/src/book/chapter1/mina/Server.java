/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
package book.chapter1.mina;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.common.IoAcceptor;
import org.apache.mina.common.IoHandler;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.SocketAcceptor;

/**
 * 描述：基于Mina实现的服务器端
 *
 * @author bluedavy 
 * 创建时间： 2008-12-4
 */
public class Server {
	
	public static void main(String[] args) throws Exception{
		int port=9527;
		
		final IoAcceptor acceptor=new SocketAcceptor(Runtime.getRuntime().availableProcessors() + 1,
                							   Executors.newCachedThreadPool());
		acceptor.getFilterChain().addLast("stringserialize", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		IoHandler handler=new IoHandlerAdapter(){

			public void messageReceived(IoSession session, Object message)
					throws Exception {
				if("quit".equalsIgnoreCase(message.toString())){
					acceptor.unbindAll();
					System.out.println("Server has been shutdown!");
					System.exit(0);
				}
				System.out.println("Message from client: "+message);
				session.write("Server response："+message);
			}
			
		};
		acceptor.bind(new InetSocketAddress(port), handler);
		System.out.println("Server listen on port: "+port);
	}

}
