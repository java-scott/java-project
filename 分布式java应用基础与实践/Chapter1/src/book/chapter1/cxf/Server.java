/**
 * �����������ܵĴ��ͷֲ�ʽJavaӦ�á�
 *  ���е�ʾ������
 *  ��Ȩ����   2008---2009
 */
package book.chapter1.cxf;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * ����
 *
 * @author bluedavy 
 * ����ʱ�䣺 2009-2-17
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
