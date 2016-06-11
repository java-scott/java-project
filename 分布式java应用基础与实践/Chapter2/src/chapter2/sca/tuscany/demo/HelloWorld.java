/**
 * 
 */
package chapter2.sca.tuscany.demo;

import org.osoa.sca.annotations.Remotable;

/**
 * @author Administrator
 *
 */
@Remotable
public interface HelloWorld {

	public String sayHello(String name);
	
}
