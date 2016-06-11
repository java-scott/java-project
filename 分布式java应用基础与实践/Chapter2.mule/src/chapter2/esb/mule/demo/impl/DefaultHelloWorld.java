/**
 * 
 */
package chapter2.esb.mule.demo.impl;

import chapter2.esb.mule.demo.HelloWorld;

/**
 * @author Administrator
 *
 */
public class DefaultHelloWorld implements HelloWorld {

	@Override
	public String sayHello(String name) {
		System.out.println("Server receive: "+name);
		throw new IllegalArgumentException("TEST");
//		return "Server response: Hello "+name;
	}

}
