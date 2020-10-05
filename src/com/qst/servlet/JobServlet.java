/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: JobServlet
 * Author:   Miao
 * Date:     2020/7/10 21:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.qst.servlet;

import com.qst.bean.Company;
import com.qst.bean.Job;
import com.qst.dao.CompanyDao;
import com.qst.dao.JobDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Miao
 * @create 2020/7/10
 * @since 1.0.0
 */
@WebServlet("/JobServlet")
@MultipartConfig
public class JobServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public JobServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        JobDao jobdao = new JobDao();
        CompanyDao companydao = new CompanyDao();
        if ("list".equals(type)) {
            // 获得所有职位信息
            List<Job> joblist = jobdao.selectAll();
            // 获得所有企业名称和标识信息
            List<Company> companylist = companydao.selectAllCompanyName();
            request.setAttribute("joblist", joblist);
            request.setAttribute("companylist", companylist);
            request.getRequestDispatcher("manage/jobList.jsp").forward(request, response);
        } else if ("JobServlet".equals(type)) {
            int uid = Integer.parseInt(request.getParameter("jobId"));
            JobDao jobDao = new JobDao();
            jobDao.deleteJob(uid);
            response.sendRedirect("manage/jobList.jsp");
        }

    }
}

