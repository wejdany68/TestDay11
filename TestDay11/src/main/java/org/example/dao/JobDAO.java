package org.example.dao;


import org.example.dto.JobFilter;
import org.example.models.Jobs;

import java.sql.*;
import java.util.ArrayList;

public class JobDAO
{
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\Desktop\\JavaBasics\\src\\main\\java\\day4\\session3\\hr.db";
    private static final String SELECT_ALL_JOBS = "select * from jobs";
    private static final String SELECT_ALL_JOBS_with_min_salary = "select * from jobs where min_salary = ?";
    private static final String SELECT_ALL_JOBS_with_min_salary_PAGINATION = "select * from jobs where min_salary = ? order by job_id limit ? offset ?";
    private static final String SELECT_ALL_JOBS_with_PAGINATION = "select * from jobs order by job_id limit ? offset ?";
    private static final String SELECT_ONE_JOB = "select * from jobs where job_id = ?";
    private static final String INSERT_JOB = "insert into jobs values (?, ?, ?,?)";
    private static final String UPDATE_JOB = "update jobs set job_title = ?, min_salary = ?, max_salary = ? where job_id = ?";
    private static final String DELETE_JOB = "delete from jobs where job_id = ?";

    public void insertJob(Jobs job) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_JOB);

        st.setInt(1, job.getJob_id());
        st.setString(2, job.getJob_title());
        st.setInt(3, job.getMin_salary());
        st.setInt(4, job.getMax_salary());
        st.executeUpdate();
    }

    public void updateJob(Jobs job) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_JOB);

        st.setInt(4, job.getJob_id());
        st.setString(1, job.getJob_title());
        st.setInt(2, job.getMin_salary());
        st.setInt(3, job.getMax_salary());
        st.executeUpdate();
    }

    public void deleteJob(int JOBId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_JOB);
        st.setInt(1, JOBId);
        st.executeUpdate();
    }

    public Jobs selectJob(int JOBId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_JOB);
        st.setInt(1, JOBId);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Jobs(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Jobs> selectAllJob() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ALL_JOBS);
        ResultSet rs = st.executeQuery();
        ArrayList<Jobs> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new Jobs(rs));
        }

        return jobs;
    }

    public ArrayList<Jobs> selectAllJob(Integer min_salary, Integer limit, int offset) throws SQLException, ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;

        if(min_salary != null && limit != null) {
            st = conn.prepareStatement(SELECT_ALL_JOBS_with_min_salary_PAGINATION);
            st.setInt(1, min_salary);
            st.setInt(2, limit);
            st.setInt(3, offset);
        }
        else if(min_salary != null) {
            st = conn.prepareStatement(SELECT_ALL_JOBS_with_min_salary);
            st.setInt(1, min_salary);
        }
        else if(limit != null) {
            st = conn.prepareStatement(SELECT_ALL_JOBS_with_PAGINATION);
            st.setInt(1, limit);
            st.setInt(2, offset);
        }
        else {
            st = conn.prepareStatement(SELECT_ALL_JOBS);
        }

        ResultSet rs = st.executeQuery();
        ArrayList<Jobs> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new Jobs(rs));
        }
        return jobs;
    }

    public ArrayList<Jobs> selectAllJob(JobFilter jobFilter) throws SQLException, ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;

        if(jobFilter.getMin_salary() != null && jobFilter.getLimit() != null) {
            st = conn.prepareStatement(SELECT_ALL_JOBS_with_min_salary_PAGINATION);
            st.setInt(1, jobFilter.getMin_salary());
            st.setInt(2, jobFilter.getLimit());
            st.setInt(3, jobFilter.getOffset());
        }
        else if(jobFilter.getMin_salary() != null) {
            st = conn.prepareStatement(SELECT_ALL_JOBS_with_min_salary);
            st.setInt(1, jobFilter.getMin_salary());
        }
        else if(jobFilter.getLimit() != null) {
            st = conn.prepareStatement(SELECT_ALL_JOBS_with_PAGINATION);
            st.setInt(1, jobFilter.getLimit());
            st.setInt(2, jobFilter.getOffset());
        }
        else {
            st = conn.prepareStatement(SELECT_ALL_JOBS);
        }

        ResultSet rs = st.executeQuery();
        ArrayList<Jobs> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new Jobs(rs));
        }
        return jobs;
    }

}
