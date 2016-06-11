/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
package book.chapter1.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 描述：服务器端的业务功能类
 *
 * @author bluedavy 
 * 创建时间： 2009-1-4
 */
public interface Business extends Remote{

	/**
	 * 显示客户端提供的信息，并返回
	 */
	public String echo(String message) throws RemoteException;
	
}
