package com.liutao.demo.util.response;

import java.util.HashMap;

public class RespBody extends HashMap<String, Object> {
	private static final long serialVersionUID = 3538526127311229622L;
	
	/**
	 * 通用成功返回消息
	 *
	 * @param data
	 * @return
	 */
	public static RespBody success(Object data) {
		RespBody body = new RespBody();
		body.put("code", ResponseCodeEnum.SUCCESS.getValue());
		body.put("message", ResponseCodeEnum.SUCCESS.getText());
		body.put("res", data);
		return body;
	}

	public static RespBody success() {
		return success(null);
	}

	/**
	 * 失败返回消息
	 *
	 * @param msg
	 * @return
	 */
	public static RespBody failure(String msg) {
		RespBody body = new RespBody();
		body.put("code", ResponseCodeEnum.FAILURE.getValue());
		body.put("message", msg);

		return body;
	}

	public static RespBody paramErr(String msg) {
		RespBody body = new RespBody();
		body.put("code", ResponseCodeEnum.PARAM_ERR.getValue());
		body.put("message", msg);

		return body;
	}

	/**
	 * 通用无数据返回消息
	 *
	 * @return
	 */
	public static RespBody noData() {
		RespBody body = new RespBody();
		body.put("code", ResponseCodeEnum.NODATA.getValue());
		body.put("message", ResponseCodeEnum.NODATA.getText());

		return body;
	}

	/**
	 * 服务器异常
	 *
	 * @return
	 */
	public static RespBody serverErr() {
		RespBody body = new RespBody();
		body.put("code", ResponseCodeEnum.SERVER_ERR.getValue());
		body.put("message", ResponseCodeEnum.SERVER_ERR.getText());

		return body;
	}

	/**
	 * 服务器异常
	 *
	 * @return
	 */
	public static RespBody illegalRequest() {
		RespBody body = new RespBody();
		body.put("code", ResponseCodeEnum.ILLEGAL_REQUEST.getValue());
		body.put("message", ResponseCodeEnum.ILLEGAL_REQUEST.getText());

		return body;
	}

	public static RespBody notFound() {
		RespBody body = new RespBody();
		body.put("code", ResponseCodeEnum.NOT_FOUND.getValue());
		body.put("message", ResponseCodeEnum.NOT_FOUND.getText());

		return body;
	}

	/**
	 * 反射异常
	 *
	 * @return
	 */
	public static RespBody reflectErr() {
		RespBody body = new RespBody();
		body.put("code", ResponseCodeEnum.REFLECT_ERR.getValue());
		body.put("message", ResponseCodeEnum.REFLECT_ERR.getText());

		return body;
	}

	/**
	 * 帐号验证错误
	 *
	 * @return
	 */
	public static RespBody authFailure(String msg) {
		RespBody CommonResponse = new RespBody();
		CommonResponse.put("code", ResponseCodeEnum.AUTH_FAILURE.getValue());
		CommonResponse.put("message", null == msg ? ResponseCodeEnum.AUTH_FAILURE.getText() : msg);

		return CommonResponse;
	}

	public static RespBody authConflict() {
		RespBody body = new RespBody();
		body.put("code", ResponseCodeEnum.AUTH_CONFLICT.getValue());
		body.put("message", ResponseCodeEnum.AUTH_CONFLICT.getText());

		return body;
	}

}
