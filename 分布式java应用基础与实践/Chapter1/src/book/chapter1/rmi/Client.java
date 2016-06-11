package book.chapter1.rmi;
/**
 * �����������ܵĴ��ͷֲ�ʽJavaӦ�á�
 *  ���е�ʾ������
 *  ��Ȩ����   2008---2009
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * ����������RMIʵ�ֵĿͻ���
 *
 * @author bluedavy 
 * ����ʱ�䣺 2009-1-4
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Registry registry=LocateRegistry.getRegistry("localhost");
		String name="BusinessDemo";
		Business business=(Business) registry.lookup(name);
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
