package com.qst.dao;

import com.qst.bean.Company;
import com.qst.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao {
    /**
     * add by tim 20200704:select company
     *
     * @param company
     */
    public void save(Company company) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO TB_COMPANY("
                + "COMPANY_NAME,COMPANY_AREA,COMPANY_SIZE,COMPANY_TYPE,COMPANY_BRIEF,COMPANY_STATE,COMPANY_SORT,COMPANY_VIEWNUM,COMPANY_PIC"
                + ") VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, company.getCompanyName());
            pstmt.setString(2, company.getCompanyArea());
            pstmt.setString(3, company.getCompanySize());
            pstmt.setString(4, company.getCompanyType());
            pstmt.setString(5, company.getCompanyBrief());
            pstmt.setInt(6, company.getCompanyState());
            pstmt.setInt(7, company.getCompanySort());
            pstmt.setInt(8, company.getCompanyViewnum());
            pstmt.setString(9, company.getCompanyPic());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
    }


    /**
     * add by tim 20200704:update company
     *
     * @param company
     */
    public void update(Company company) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String sql = "UPDATE tb_company "
                + "SET company_name=?,company_area=?,company_size=?,company_type=?,company_brief=?,company_state=?,company_sort=?," +
                "company_viewnum=?,company_pic=? WHERE company_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, company.getCompanyName());
            pstmt.setString(2, company.getCompanyArea());
            pstmt.setString(3, company.getCompanySize());
            pstmt.setString(4, company.getCompanyType());
            pstmt.setString(5, company.getCompanyBrief());
            pstmt.setInt(6, company.getCompanyState());
            pstmt.setInt(7, company.getCompanySort());
            pstmt.setInt(8, company.getCompanyViewnum());
            pstmt.setString(9, company.getCompanyPic());
            pstmt.setInt(10, company.getCompanyId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
    }

    /**
     * add by tim 20200704:select by companyid
     *
     * @param companyId
     * @return
     */
    public Company selectById(int companyId) {
        Company company = new Company();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tb_company WHERE company_id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, companyId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                company.setCompanyId(rs.getInt(1));
                company.setCompanyName(rs.getString(2));
                company.setCompanyArea(rs.getString(3));
                company.setCompanySize(rs.getString(4));
                company.setCompanyType(rs.getString(5));
                company.setCompanyBrief(rs.getString(6));
                company.setCompanyState(rs.getInt(7));
                company.setCompanySort(rs.getInt(8));
                company.setCompanyViewnum(rs.getInt(9));
                company.setCompanyPic(rs.getString(10));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(rs, pstmt, conn);
        }
        return company;
    }

    /**
     * add by tim 20200704:delete company
     *
     * @param companyId
     * @return
     */
    public static int deleteCompany(int companyId) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            String sql = "DELETE FROM tb_company WHERE COMPANY_ID=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, companyId);
            pstmt.executeUpdate();
            DBUtil.closeJDBC(null, pstmt, conn);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return 0;
    }


    /**
     * 企业列表查询
     * add by Miao 20200709
     *
     * @return
     */
    public List<Company> selectAll() {
        List<Company> list = new ArrayList<Company>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT COMPANY_ID,COMPANY_NAME,COMPANY_AREA,COMPANY_SIZE,COMPANY_TYPE,COMPANY_STATE,COMPANY_SORT,COMPANY_VIEWNUM "
                + "FROM TB_COMPANY ORDER BY COMPANY_ID DESC";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Company company = new Company();
                company.setCompanyId(rs.getInt(1));
                company.setCompanyName(rs.getString(2));
                company.setCompanyArea(rs.getString(3));
                company.setCompanySize(rs.getString(4));
                company.setCompanyType(rs.getString(5));
                company.setCompanyState(rs.getInt(6));
                company.setCompanySort(rs.getInt(7));
                company.setCompanyViewnum(rs.getInt(8));
                list.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * 查询所有企业的名称和标识
     *
     * @return
     */
    public List<Company> selectAllCompanyName() {
        List<Company> list = new ArrayList<Company>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT COMPANY_ID,COMPANY_NAME FROM TB_COMPANY ORDER BY COMPANY_ID DESC";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Company company = new Company();
                company.setCompanyId(rs.getInt(1));
                company.setCompanyName(rs.getString(2));
                list.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(rs, pstmt, conn);
        }
        return list;
    }

    /**
     * add by tim 20200711:page
     *
     * @param page
     * @param pageSize
     * @return
     */
    public static List<Company> getRecords(int page, int pageSize) {
        List<Company> list = new ArrayList<Company>();
        int start = 0;
        if (page > 0) {
            start = (page - 1) * pageSize;
        }
        try {
            Connection con = DBUtil.getConnection();
            String sql = "SELECT COMPANY_ID,COMPANY_NAME,COMPANY_AREA,COMPANY_SIZE,COMPANY_TYPE,COMPANY_STATE,COMPANY_SORT,COMPANY_VIEWNUM" +
                    " FROM TB_COMPANY LIMIT " + start + "," + pageSize;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Company company = new Company();
                company.setCompanyId(rs.getInt(1));
                company.setCompanyName(rs.getString(2));
                company.setCompanyArea(rs.getString(3));
                company.setCompanySize(rs.getString(4));
                company.setCompanyType(rs.getString(5));
                company.setCompanyState(rs.getInt(6));
                company.setCompanySort(rs.getInt(7));
                company.setCompanyViewnum(rs.getInt(8));
                list.add(company);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    // 表中的记录总数
    public static int getTotal() {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT count(*) AS total FROM TB_COMPANY ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    // 总页数
    public static int totalPages(int pageSize) {
        int total = getTotal();
        int totalPages = (total % pageSize == 0) ? (total / pageSize) : (total / pageSize) + 1;
        return totalPages;
    }
}
