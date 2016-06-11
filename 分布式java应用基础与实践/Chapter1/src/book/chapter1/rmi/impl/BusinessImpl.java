/**
 * �����������ܵĴ��ͷֲ�ʽJavaӦ�á�
 *  ���е�ʾ������
 *  ��Ȩ����   2008---2009
 */
package book.chapter1.rmi.impl;

import java.rmi.RemoteException;

import book.chapter1.rmi.Business;

/**
 * ������
 *
 * @author bluedavy 
 * ����ʱ�䣺 2009-1-4
 */
public class BusinessImpl implements Business {

	/* (non-Javadoc)
	 * @see book.chapter1.rmi.Business#echo(java.lang.String)
	 */
	public String echo(String message) throws RemoteException {
		if("quit".equalsIgnoreCase(message.toString())){
			System.out.println("Server will be shutdown!");
			System.exit(0);
		}
		System.out.println("Message from client: "+message);
		return "Server response��"+message;
	}

}
