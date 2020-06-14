package com.rt.modules.sys.dto.query;

/**
 * @Auther: edeis
 * @Date: 2019/1/23 11:38
 * @Description:
 */
public class PageQueryDTO<T> {

    /**
     * 按创建时间倒序排序
     */
    public static final String ORDER_BY_CREATE_TIME_DESC = "create_time desc";

    private int pageNumber = 1;

    private int pageSize = 10;

    private String orderBy;

    private T condition;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    public PageQueryDTO() {
    }

    public PageQueryDTO(int pageNum, int pageSize) {
        super();
        this.pageNumber = pageNum;
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return (this.pageNumber - 1) * this.pageSize;
    }

}