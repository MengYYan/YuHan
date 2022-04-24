package cn.itcast.domain;

import java.util.List;

/**
 * @author YuHan
 * @date 2022/4/15
 * @apiNote 分页对象
 */
public class PageBean<T> {
    /**
     * 总记录数
     */
    private int totalCount;

    /**
     * 总页码
     */
    private int totalPage;

    /**
     * 每页的数据
     */
    private List<T> list;

    /**
     * 当前页码
     */
    private int currentPage;

    /**
     * 每页显示的记录数
     */
    private int rows;

    public PageBean() {
    }

    public PageBean(int totalCount, int totalPage, List<T> list, int currentPage, int rows) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
        this.currentPage = currentPage;
        this.rows = rows;
    }

    /**
     * 获取
     *
     * @return totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置
     *
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 获取
     *
     * @return totalPage
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 设置
     *
     * @param totalPage
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 获取
     *
     * @return list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 设置
     *
     * @param list
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 获取
     *
     * @return currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置
     *
     * @param currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 获取
     *
     * @return rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * 设置
     *
     * @param rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{totalCount = " + totalCount + ", totalPage = " + totalPage + ", list = " + list + ", currentPage = " + currentPage + ", rows = " + rows + "}";
    }
}
