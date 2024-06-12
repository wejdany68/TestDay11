package org.example.dto;

import jakarta.ws.rs.QueryParam;

public class JobFilter
{
    private @QueryParam("min_salary") Integer min_salary;
    private @QueryParam("limit") Integer limit;
    private @QueryParam("offset") int offset;

    public void setMin_salary(Integer min_salary) {
        this.min_salary = min_salary;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Integer getMin_salary() {
        return min_salary;
    }

    public Integer getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
