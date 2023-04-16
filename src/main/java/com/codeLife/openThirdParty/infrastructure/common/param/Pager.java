package com.codeLife.openThirdParty.infrastructure.common.param;

import lombok.Data;

/**
 * 分页结果封装
 */
@Data
public class Pager {
    /**
     * 当前页码
     */
    private long currentNum;
    /**
     * 每页数量
     */
    private long pageSize;
    /**
     * 记录总数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private long totalPages;

	public Pager(long currentNum, long pageSize, long totalSize, long totalPages) {
		this.currentNum = currentNum;
		this.pageSize = pageSize;
		this.totalSize = totalSize;
		this.totalPages = totalPages;
	}
}
