package book.chapter1.rmi;
/**
 * �����������ܵĴ��ͷֲ�ʽJavaӦ�á�
 *  ���е�ʾ������
 *  ��Ȩ����   2008---2009
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import book.chapter1.rmi.impl.BusinessImpl;

/**
 * ����������RMIʵ�ֵķ�������
 *
 * @author bluedavy 
 * ����ʱ�䣺 2009-1-4
 */
public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		int port=9527;
		String name="BusinessDemo";
		Business business=new BusinessImpl();
		UnicastRemoteObject.exportObject(business, port);
		Registry registry=LocateRegistry.createRegistry(1099);
		registry.rebind(name, business);
	}

}
