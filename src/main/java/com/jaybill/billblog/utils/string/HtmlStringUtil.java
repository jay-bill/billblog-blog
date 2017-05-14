package com.jaybill.billblog.utils.string;

import java.util.List;

/**
 * html字符串解析
 * @author jaybill
 *
 */
public interface HtmlStringUtil {
	/**
	 * 返回提取content中的tag标签内容
	 * @param content
	 * @param tag
	 * @return
	 */
	List<String> parseContent(String content,String tag);
	
	/**
	 * 替换tag标签的值
	 * @param content
	 * @param tag
	 * @param target
	 * @return
	 */
	String replaceTagVal(String content,String tag,String target);
}
