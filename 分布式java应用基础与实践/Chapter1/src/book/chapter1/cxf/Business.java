/**
 * �����������ܵĴ��ͷֲ�ʽJavaӦ�á�
 *  ���е�ʾ������
 *  ��Ȩ����   2008---2009
 */
package book.chapter1.cxf;

import javax.jws.WebService;

/**
 * ����
 *
 * @author bluedavy 
 * ����ʱ�䣺 2009-2-16
 */
@WebService
public interface Business {

	public String echo(String message);
	
}
