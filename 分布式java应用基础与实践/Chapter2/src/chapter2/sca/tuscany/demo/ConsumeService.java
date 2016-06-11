/**
 * 
 */
package chapter2.sca.tuscany.demo;

import org.apache.tuscany.sca.host.embedded.SCADomain;

import chapter2.sca.tuscany.demo.impl.HelloWorldConsumer;

/**
 * @author Administrator
 *
 */
public class ConsumeService {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		SCADomain.newInstance("consumeservice.composite");
		System.out.println("Client Started");
		HelloWorldConsumer consumer=(HelloWorldConsumer) SpringApplicationContextHolder.context.getBean("HelloWorldConsumer");
		System.out.println(consumer.execute("BlueDavy"));
	}

}
