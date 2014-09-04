package com.name.projectname.adapter.string;

/**
 * 返回一个字符串
 * 
 * @author selevenguo
 * @date 2014-8-20
 */
public interface IGetString<T> {
	/**
	 * 根据类型返回要生成的字符串
	 * @param t
	 * @return
	 */
	public String getString(T t);
}
