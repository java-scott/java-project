/**
 * 
 */
package book.chapter1.cxf;

import java.lang.reflect.Method;



/**
 * @author Administrator
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
//		Object object=new Object();
//		int loop=1000000;
//		long beginTime=System.currentTimeMillis();
//		for (int i = 0; i < loop; i++) {
//			object.toString();
//		}
//		long endTime=System.currentTimeMillis();
//		System.out.println("直接调用消耗的时间为："+(endTime-beginTime)+"毫秒");
//		
//		beginTime=System.currentTimeMillis();
//		for (int i = 0; i < loop; i++) {
			Class<?> objectClass=Class.forName("java.lang.Object");
			Method method=objectClass.getMethod("toString", new Class<?>[]{});
			method.invoke(objectClass.newInstance(), new Object[]{});
			while(true){
				Thread.sleep(100000);
				;
			}
//		}
//		endTime=System.currentTimeMillis();
//		System.out.println("不缓存Method，反射调用消耗的时间为："+(endTime-beginTime)+"毫秒");
//		
//		beginTime=System.currentTimeMillis();
//		Method method=Object.class.getMethod("toString", new Class<?>[]{});
//		for (int i = 0; i < loop; i++) {
//			method.invoke(object, new Object[]{});
//		}
//		endTime=System.currentTimeMillis();
//		System.out.println("缓存Method，反射调用消耗的时间为："+(endTime-beginTime)+"毫秒");
	}

}
