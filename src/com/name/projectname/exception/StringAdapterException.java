package com.name.projectname.exception;

/**
 * 调用StringAdapter时传入的数组不是字符串类型但又没有设置IGetString接口<br>
 * 
 * @author selevenguo
 * @date 2014-8-20
 */
public class StringAdapterException extends RuntimeException {
	private static final long serialVersionUID = 4810461359416102933L;

	public StringAdapterException(String detailMessage) {
		super(detailMessage);
	}

}
