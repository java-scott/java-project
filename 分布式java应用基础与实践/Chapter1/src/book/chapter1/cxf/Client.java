/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
package book.chapter1.cxf;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;


/**
 * 描述
 *
 * @author bluedavy 
 * 创建时间： 2009-2-16
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		JaxWsProxyFactoryBean factory=new JaxWsProxyFactoryBean();
		factory.setServiceClass(Business.class);
		factory.setAddress("http://localhost:9527/business");
		Business business=(Business)factory.create();
		BufferedReader systemIn=new BufferedReader(new InputStreamReader(System.in));
        while(true){
			String command=systemIn.readLine();
			if(command==null || "quit".equalsIgnoreCase(command.trim())){
				System.out.println("Client quit!");
				try{
					business.echo(command);
				}
				catch(Exception e){
					// IGNORE
				}
				System.exit(0);
			}
			System.out.println(business.echo(command));
		}
	}

}
