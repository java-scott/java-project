/**
 * 
 */
package chapter2.esb.mule.demo;

import org.mule.api.MuleContext;
import org.mule.context.DefaultMuleContextFactory;

/**
 * @author Administrator
 *
 */
public class PublishService {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		MuleContext muleContext = new DefaultMuleContextFactory().createMuleContext("publishservice.xml"); 
		muleContext.start();
		System.out.println("Server Started");
		Object object=new Object();
		synchronized (object) {
			object.wait();
		}
	}

}
