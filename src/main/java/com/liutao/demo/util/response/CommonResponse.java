package com.liutao.demo.util.response;

import java.util.HashMap;

public class CommonResponse extends HashMap<String, Object> {
	private static final long serialVersionUID = 3538526127311229622L;
	
	/**
	 * 通用成功返回消息
	 *
	 * @param data
	 * @return
	 */
	public static CommonResponse success(Object data) {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.put("code", ResponseCodeEnum.SUCCESS.getValue());
		commonResponse.put("message", ResponseCodeEnum.SUCCESS.getText());
		commonResponse.put("res", data);
		return commonResponse;
	}

	public static CommonResponse success() {
		return success(null);
	}

	/**
	 * 失败返回消息
	 *
	 * @param msg
	 * @return
	 */
	public static CommonResponse failure(String msg) {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.put("code", ResponseCodeEnum.FAILURE.getValue());
		commonResponse.put("message", msg);

		return commonResponse;
	}

	public static CommonResponse paramErr(String msg) {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.put("code", ResponseCodeEnum.PARAM_ERR.getValue());
		commonResponse.put("message", msg);

		return commonResponse;
	}

	/**
	 * 通用无数据返回消息
	 *
	 * @return
	 */
	public static CommonResponse noData() {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.put("code", ResponseCodeEnum.NODATA.getValue());
		commonResponse.put("message", ResponseCodeEnum.NODATA.getText());

		return commonResponse;
	}

	/**
	 * 服务器异常
	 *
	 * @return
	 */
	public static CommonResponse serverErr() {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.put("code", ResponseCodeEnum.SERVER_ERR.getValue());
		commonResponse.put("message", ResponseCodeEnum.SERVER_ERR.getText());

		return commonResponse;
	}

	/**
	 * 服务器异常
	 *
	 * @return
	 */
	public static CommonResponse illegalRequest() {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.put("code", ResponseCodeEnum.ILLEGAL_REQUEST.getValue());
		commonResponse.put("message", ResponseCodeEnum.ILLEGAL_REQUEST.getText());

		return commonResponse;
	}

	public static CommonResponse notFound() {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.put("code", ResponseCodeEnum.NOT_FOUND.getValue());
		commonResponse.put("message", ResponseCodeEnum.NOT_FOUND.getText());

		return commonResponse;
	}

	/**
	 * 反射异常
	 *
	 * @return
	 */
	public static CommonResponse reflectErr() {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.put("code", ResponseCodeEnum.REFLECT_ERR.getValue());
		commonResponse.put("message", ResponseCodeEnum.REFLECT_ERR.getText());

		return commonResponse;
	}

	/**
	 * 帐号验证错误
	 *
	 * @return
	 */
	public static CommonResponse authFailure(String msg) {
		CommonResponse CommonResponse = new CommonResponse();
		CommonResponse.put("code", ResponseCodeEnum.AUTH_FAILURE.getValue());
		CommonResponse.put("message", null == msg ? ResponseCodeEnum.AUTH_FAILURE.getText() : msg);

		return CommonResponse;
	}

	public static CommonResponse authConflict() {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.put("code", ResponseCodeEnum.AUTH_CONFLICT.getValue());
		commonResponse.put("message", ResponseCodeEnum.AUTH_CONFLICT.getText());

		return commonResponse;
	}

}
