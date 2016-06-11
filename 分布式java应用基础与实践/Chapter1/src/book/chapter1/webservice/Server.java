/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
package book.chapter1.webservice;

import javax.xml.ws.Endpoint;

import book.chapter1.webservice.impl.BusinessImpl;

/**
 * 描述：基于Java Webservice实现的服务器端
 *
 * @author bluedavy 
 * 创建时间： 2009-2-11
 */
public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9527/BusinessService", new BusinessImpl());
		System.out.println("Server has beed started");
	}

}
