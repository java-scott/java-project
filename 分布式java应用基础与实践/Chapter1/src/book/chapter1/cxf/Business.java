/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
package book.chapter1.cxf;

import javax.jws.WebService;

/**
 * 描述
 *
 * @author bluedavy 
 * 创建时间： 2009-2-16
 */
@WebService
public interface Business {

	public String echo(String message);
	
}
