package org.example.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.ResultSet;
import java.sql.SQLException;

@XmlRootElement
public class Jobs
{
    private int job_id;
    private String job_title;
    private int min_salary;
    private int max_salary;

    public Jobs(ResultSet resultSet) throws SQLException
    {
        job_id = resultSet.getInt("job_id");
        job_title = resultSet.getString("job_title");
        min_salary = resultSet.getInt("min_salary");
        max_salary = resultSet.getInt("max_salary");
    }

    public Jobs(int job_id, String job_title, int min_salary, int max_salary) {
        this.job_id = job_id;
        this.job_title = job_title;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
    }

    public Jobs() {

    }


    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public void setMin_salary(int min_salary) {
        this.min_salary = min_salary;
    }

    @XmlElement
    public int getJob_id() {
        return job_id;
    }

    @XmlElement
    public String getJob_title() {
        return job_title;
    }

    @XmlElement
    public int getMin_salary() {
        return min_salary;
    }

    @XmlElement
    public int getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(int max_salary) {
        this.max_salary = max_salary;
    }

    @Override
    public String toString()
    {
        return "Job ID: "+job_id+", Title: "+job_title+", min_Salary: "+min_salary+", max_salary: "+max_salary;
    }

}
