/**
 * �����������ܵĴ��ͷֲ�ʽJavaӦ�á�
 *  ���е�ʾ������
 *  ��Ȩ����   2008---2009
 */
package book.chapter1.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * �������������˵�ҵ������
 *
 * @author bluedavy 
 * ����ʱ�䣺 2009-1-4
 */
public interface Business extends Remote{

	/**
	 * ��ʾ�ͻ����ṩ����Ϣ��������
	 */
	public String echo(String message) throws RemoteException;
	
}
