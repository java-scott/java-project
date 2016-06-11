/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
package book.chapter1.springrmi;

/**
 * 描述
 *
 * @author bluedavy 
 * 创建时间： 2009-2-11
 */
public interface Business {

	/**
	 * 显示客户端提供的信息，并返回
	 */
	public String echo(String message);
	
}
