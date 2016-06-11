/**
 * �����������ܵĴ��ͷֲ�ʽJavaӦ�á�
 *  ���е�ʾ������
 *  ��Ȩ����   2008---2009
 */
package book.chapter1.springrmi;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ����
 *
 * @author bluedavy 
 * ����ʱ�䣺 2009-2-11
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		ApplicationContext ac=new ClassPathXmlApplicationContext("book/chapter1/springrmi/client.xml");
		Business business=(Business) ac.getBean("businessService");
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
