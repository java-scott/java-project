/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
package book.chapter1.webservice.impl;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


/**
 * 描述：以webservice方式对外暴露的服务
 *
 * @author bluedavy 
 * 创建时间： 2009-2-11
 */
@WebService(name="Business",serviceName="BusinessService",targetNamespace="http://webservice.chapter1.book/client")
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class BusinessImpl implements Business {

	/* (non-Javadoc)
	 * @see book.chapter1.webservice.Business#echo(java.lang.String)
	 */
	public String echo(String message) {
		if("quit".equalsIgnoreCase(message.toString())){
			System.out.println("Server will be shutdown!");
			System.exit(0);
		}
		System.out.println("Message from client: "+message);
		return "Server response："+message;
	}

}
