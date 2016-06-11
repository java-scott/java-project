/**
 * 
 */
package chapter2.sca.tuscany.demo.impl;

import chapter2.sca.tuscany.demo.HelloWorld;

/**
 * @author Administrator
 *
 */
public class DefaultHelloWorld implements HelloWorld {

	/* (non-Javadoc)
	 * @see chapter2.sca.tuscany.demo.HelloWorld#sayHello(java.lang.String)
	 */
	@Override
	public String sayHello(String name) {
		System.out.println("Server receive: "+name);
		throw new IllegalArgumentException("TEST");
		// return "Server response: Hello "+name;
	}

}
