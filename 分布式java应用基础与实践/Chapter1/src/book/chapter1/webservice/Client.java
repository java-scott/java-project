package book.chapter1.webservice;
/**
 * �����������ܵĴ��ͷֲ�ʽJavaӦ�á�
 *  ���е�ʾ������
 *  ��Ȩ����   2008---2009
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

import book.chapter1.webservice.client.Business;
import book.chapter1.webservice.client.BusinessService;

/**
 * ����������Webserviceʵ�ֵĿͻ���
 *
 * @author bluedavy 
 * ����ʱ�䣺 2009-1-4
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
