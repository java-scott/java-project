package book.chapter1.webservice;
/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

import book.chapter1.webservice.client.Business;
import book.chapter1.webservice.client.BusinessService;

/**
 * 描述：基于Webservice实现的客户端
 *
 * @author bluedavy 
 * 创建时间： 2009-1-4
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		BusinessService businessService=new BusinessService();
		Business business=businessService.getBusinessPort();
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
