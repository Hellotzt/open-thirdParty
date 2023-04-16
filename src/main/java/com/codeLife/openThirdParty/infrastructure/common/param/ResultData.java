package com.codeLife.openThirdParty.infrastructure.common.param;

import lombok.Data;

/**
 * 通用返回结果封装
 */
@Data
public class ResultData<T> {
	
	/**
     *  返回码
     */
    private String code;

    /**
     * 提示信息
     */
    private String msg;
    
    /**
     * 返回时间
     */
    private String timestamp;

    /**
     * 返回数据集合
     */
    private T data;
    
    /**
     * 自定义分页标签
     */
    private Pager pager;


	/**
	 *  成功时候的调用
	 */
	public static ResultData<Object> success(CodeMsg codeMsg){
		return new ResultData<>(codeMsg);
	}

	public static ResultData<Object> success(){
		return new ResultData<>();
	}
	
	public static <T> ResultData<T> success(T data){
		return new ResultData<>(data);
	}

	public static ResultData<Object> fail(){
		return new ResultData<>(CodeMsg.FAIL);
	}

	public static ResultData<Object> fail(CodeMsg codeMsg){
		return new ResultData<>(codeMsg);
	}

	public static <T> ResultData<Object> result(CodeMsg codeMsg, T data, Pager pager){
		return new ResultData<>(codeMsg, data, pager);
	}

	public ResultData() {
		this.code = CodeMsg.SUCCESS.getCode();
		this.msg = CodeMsg.SUCCESS.getMsg();
		this.timestamp = CodeMsg.SUCCESS.getTimestamp();
		this.data = null;
	}
	
	public ResultData(T data) {
		this.code = CodeMsg.SUCCESS.getCode();
		this.msg = CodeMsg.SUCCESS.getMsg();
		this.timestamp = CodeMsg.SUCCESS.getTimestamp();

		this.data = data;
	}

	public ResultData(CodeMsg codeMsg, T data, Pager pager) {
		if(codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
			this.timestamp = codeMsg.getTimestamp();
		}
		this.data = data;
		this.pager = pager;
	}


	public ResultData(CodeMsg codeMsg) {
		if(codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
			this.timestamp = codeMsg.getTimestamp();
		}
	}
	

}