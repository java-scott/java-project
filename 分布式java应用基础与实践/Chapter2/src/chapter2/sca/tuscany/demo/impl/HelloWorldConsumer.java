/**
 * 
 */
package chapter2.sca.tuscany.demo.impl;

import chapter2.sca.tuscany.demo.HelloWorld;

/**
 * @author Administrator
 *
 */
public class HelloWorldConsumer {

	private HelloWorld service=null;
	
	public void setService(HelloWorld service){
		this.service=service;
	}
	
	public String execute(String name){
		return service.sayHello(name);
	}
	
}
