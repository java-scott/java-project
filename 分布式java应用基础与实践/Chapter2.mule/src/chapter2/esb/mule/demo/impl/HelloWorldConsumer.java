/**
 * 
 */
package chapter2.esb.mule.demo.impl;

import chapter2.esb.mule.demo.HelloWorld;

/**
 * @author Administrator
 *
 */
public class HelloWorldConsumer{

	private HelloWorld service=null;
	
	public void setService(HelloWorld service){
		this.service=service;
	}
	
	public HelloWorld getService(){
		return service;
	}
	
	public String execute(String name){
		String response=service.sayHello(name);
		System.out.println(response);
		return response;
	}
	
}
