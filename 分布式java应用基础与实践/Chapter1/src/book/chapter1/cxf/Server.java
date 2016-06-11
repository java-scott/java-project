/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
package book.chapter1.cxf;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * 描述
 *
 * @author bluedavy 
 * 创建时间： 2009-2-17
 */
public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Business service=new BusinessImpl();
		JaxWsServerFactoryBean svrFactory=new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(Business.class);
		svrFactory.setAddress("http://localhost:9527/business");
		svrFactory.setServiceBean(service);
		svrFactory.create();
	}

}
