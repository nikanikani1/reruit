package com.nika.recruit.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author ht
 */
public class PageResult<T> {

    private List<T> list;

    private Integer total;


    private Integer current;


    private Integer pageSize;

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public PageResult(List<T> list, Integer total, Integer current, Integer pageSize) {
        this.list = list;
        this.total = total;
        this.current = current;
        this.pageSize = pageSize;
    }

    public PageResult(Page<T> page) {
        this.total = Math.toIntExact(page.getTotal());
        this.current = Math.toIntExact(page.getCurrent());
        this.pageSize = Math.toIntExact(page.getSize());
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getCurrent() {
        return current;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
