/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
package book.chapter1.tcpnio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * 描述：基于Java NIO实现的tcp服务器端
 *
 * @author bluedavy 
 * 创建时间： 2008-12-2
 */
public class Server {

	public static void main(String[] args) throws Exception{
		int port=9527;
		Selector selector=Selector.open();
		ServerSocketChannel ssc=ServerSocketChannel.open();
		ServerSocket serverSocket=ssc.socket();
		serverSocket.bind(new InetSocketAddress(port));
		System.out.println("Server listen on port: "+port);
		ssc.configureBlocking(false);
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		while(true){
			int nKeys=selector.select(1000);
			if(nKeys>0){
				for (SelectionKey key : selector.selectedKeys()) {
					if(key.isAcceptable()){
						ServerSocketChannel server=(ServerSocketChannel) key.channel();
						SocketChannel sc=server.accept();
						if(sc==null){
							continue;
						}
						sc.configureBlocking(false);
						sc.register(selector, SelectionKey.OP_READ);
					}
					else if(key.isReadable()){
						ByteBuffer buffer=ByteBuffer.allocate(1024);
						SocketChannel sc=(SocketChannel) key.channel();
						int readBytes=0;
						String message=null;
						try{
							int ret;
							try{
								while((ret=sc.read(buffer))>0){
									readBytes+=ret;
								}
							}
							catch(Exception e){
								readBytes=0;
								// IGNORE
							}
							finally{
								buffer.flip();
							}
							if(readBytes>0){
								message=Charset.forName("UTF-8").decode(buffer).toString();
								buffer = null;
							}
						}
						finally{
							if(buffer!=null){
								buffer.clear();
							}
						}
						if(readBytes>0){
							System.out.println("Message from client: "+ message);
							if("quit".equalsIgnoreCase(message.trim())){
								sc.close();
								selector.close();
								System.out.println("Server has been shutdown!");
								System.exit(0);
							}
							String outMessage="Server response："+message;
							sc.write(Charset.forName("UTF-8").encode(outMessage));
						}
					}
				}
				selector.selectedKeys().clear();
			}
		}
	}
	
}
