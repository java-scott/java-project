/**
 * �����������ܵĴ��ͷֲ�ʽJavaӦ�á�
 *  ���е�ʾ������
 *  ��Ȩ����   2008---2009
 */
package book.chapter1.springrmi;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ����
 *
 * @author bluedavy 
 * ����ʱ�䣺 2009-2-11
 */
public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		new ClassPathXmlApplicationContext("book/chapter1/springrmi/server.xml");
		System.out.println("Server has been started");
	}

}
