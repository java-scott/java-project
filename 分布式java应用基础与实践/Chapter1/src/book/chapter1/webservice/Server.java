/**
 * �����������ܵĴ��ͷֲ�ʽJavaӦ�á�
 *  ���е�ʾ������
 *  ��Ȩ����   2008---2009
 */
package book.chapter1.webservice;

import javax.xml.ws.Endpoint;

import book.chapter1.webservice.impl.BusinessImpl;

/**
 * ����������Java Webserviceʵ�ֵķ�������
 *
 * @author bluedavy 
 * ����ʱ�䣺 2009-2-11
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
