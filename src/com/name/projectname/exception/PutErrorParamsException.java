package com.name.projectname.exception;

/**
 * 调用Base中putDataBeforeStartAct时，参数错误时抛异常<br>
 * 
 * @author selevenguo
 * @date 2014-8-8
 */
public class PutErrorParamsException extends RuntimeException {
	private static final long serialVersionUID = -2872246080273079539L;

	public PutErrorParamsException(String detailMessage) {
		super(detailMessage);
	}
}
