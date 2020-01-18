package com.example.demo.comm.web.constants;


import com.example.demo.comm.enums.ApiStyleEnum;

/**
 * @desc Header的key罗列
 *
 */
public class HeaderConstants {

	/**
	 * 用户的登录token
	 */
	//public static final String X_TOKEN = "X-Token";

	/**
	 * api的版本号
	 */
	public static final String API_VERSION = "api_version";

	/**
	 * app版本号
	 */
	public static final String APP_VERSION = "app_version";

	/**
	 *
	 * 调用来源
	 */
	public static final String CALL_SOURCE = "os_name";

	/**
	 * API的返回格式 {@link ApiStyleEnum}
	 */
	public static final String API_STYLE = "api_style";
}
