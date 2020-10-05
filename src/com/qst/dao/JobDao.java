/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: JobDao
 * Author:   Miao
 * Date:     2020/7/10 21:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.qst.dao;

import com.qst.bean.Company;
import com.qst.bean.Job;
import com.qst.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Miao
 * @create 2020/7/10
 * @since 1.0.0
 */
public class JobDao {
    public List<Job> selectAll() {
        List<Job> list = new ArrayList<Job>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            String sql = "SELECT tb_job.*,company_name "
                    + "FROM tb_job "
                    + "INNER JOIN tb_company on tb_job.company_id =  tb_company.company_id ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Job job = new Job();
                // 查询某个职位的申请人数
                String sql2 = "SELECT COUNT(*) FROM tb_jobapply WHERE job_id = ?";
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setInt(1, rs.getInt("job_id"));
                rs2 = pstmt2.executeQuery();
                if (rs2.next())
                    job.setApplyNum(rs2.getInt(1));
                else
                    job.setApplyNum(0);
                job.setJobId(rs.getInt("job_id"));
                job.setJobName(rs.getString("job_name"));
                job.setJobHiringnum(rs.getInt("job_hiringnum"));
                job.setJobEnddate(rs.getTimestamp("job_endtime"));
                job.setJobState(rs.getInt("job_state"));
                Company company = new Company();
                company.setCompanyId(rs.getInt("company_id"));
                company.setCompanyName(rs.getString("company_name"));
                job.setCompany(company);
                list.add(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(rs, pstmt, conn);
            DBUtil.closeJDBC(rs2, pstmt2, conn);
        }
        return list;
    }

    /**
     * 删除职位
     *
     * @param uid
     */
    public static void deleteJob(int uid) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            String sql = "DELETE FROM tb_job WHERE JOB_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, uid);
            pstmt.executeUpdate();
            DBUtil.closeJDBC(null, pstmt, conn);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        } finally {
        }
    }

}

