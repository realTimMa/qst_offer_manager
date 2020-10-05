/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: UserPageBean
 * Author:   Miao
 * Date:     2020/7/8 18:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.qst.bean;

import com.qst.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Miao
 * @create 2020/7/8
 * @since 1.0.0
 */
public class UserPageBean {
    // 每页显示记录数
    private int pageSize;
    // 当前页码
    private int pageNo;
    // 总页数
    private int totalPages;
    // 每页数据记录集合
    private List<User> pageData = new ArrayList<User>();
    // 是否有下一页
    private boolean hasNextPage;
    // 是否有上一页
    private boolean hasPreviousPage;

    public UserPageBean() {
    }

    public UserPageBean(int pageSize, int pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPages() {
        // 获取总记录数
        UserDao dao = new UserDao();
        int recordCount = dao.getRecordCount();
        // 获取并返回总页数
        return (recordCount + pageSize - 1) / pageSize;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<User> getPageData() {
        // 查询当页记录
        UserDao dao = new UserDao();
        List<User> list = dao.getUserPageList(pageNo, pageSize);
        return list;
    }

    public void setPageData(List<User> pageData) {
        this.pageData = pageData;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }
}

