/**
 * 
 */
package chapter2.sca.tuscany.demo;

import org.apache.tuscany.sca.host.embedded.SCADomain;

/**
 * @author Administrator
 *
 */
public class PublishService {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		SCADomain.newInstance("publishservice.composite");
		System.out.println("Server Started");
		Object object=new Object();
		synchronized (object) {
			object.wait();
		}
	}

}
