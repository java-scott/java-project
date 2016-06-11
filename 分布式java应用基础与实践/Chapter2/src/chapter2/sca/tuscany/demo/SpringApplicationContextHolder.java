/**
 * 
 */
package chapter2.sca.tuscany.demo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Administrator
 *
 */
public class SpringApplicationContextHolder implements ApplicationContextAware {

	public static ApplicationContext context=null;

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		SpringApplicationContextHolder.context=context;
	}

}
