package com.jaybill.billblog.utils.string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

/**
 * 博客内容解析
 * @author jaybill
 *
 */
@Component
public class BlogHtmlStringUtil implements HtmlStringUtil {

	@Override
	public List<String> parseContent(String content, String tag) {
		//去除>符号
		tag = tag.substring(0,tag.length()-1);
		String suf = tag.charAt(0)+"/"+tag.substring(1)+">";
		Pattern p = null;
		if(suf.contains("img")){
			p = Pattern.compile(tag+"[^<]+"+"(>|"+suf+"|/>)");
		}else{
			p = Pattern.compile(tag+".+"+"(/>|"+suf+")$");
		}		
		Matcher m = p.matcher(content);
		List<String> list = new ArrayList<String>();
		while(m.find()){
			list.add(m.group());	
		}		
		return list;
	}

	/**
	 * 替换
	 */
	@Override
	public String replaceTagVal(String content, String tag, String target) {		
		return content.replace(tag, target);
	}

}
