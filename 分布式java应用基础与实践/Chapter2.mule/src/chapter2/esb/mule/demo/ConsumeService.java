/**
 * 
 */
package chapter2.esb.mule.demo;

import org.mule.api.MuleContext;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.module.client.MuleClient;

/**
 * @author Administrator
 *
 */
public class ConsumeService {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		MuleContext muleContext = new DefaultMuleContextFactory().createMuleContext("consumeservice.xml"); 
		muleContext.start();
		MuleClient client=new MuleClient();
		client.send("vm://helloworld.queue", "BlueDavy", null);
	}

}
