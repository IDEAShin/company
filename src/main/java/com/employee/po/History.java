package com.employee.po;

import java.util.Date;

public class History {
    private Integer changeNo;
    private String empNo;
    private Integer deptNo;
    private Integer salary;
    private Date changeDate;
    private String changeReason;
    private Date dimissionDate;
    private String dimissionReason;
    private String empName;
    private String deptName;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getChangeNo() {
        return changeNo;
    }

    public void setChangeNo(Integer changeNo) {
        this.changeNo = changeNo;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public Date getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(Date dimissionDate) {
        this.dimissionDate = dimissionDate;
    }

    public String getDimissionReason() {
        return dimissionReason;
    }

    public void setDimissionReason(String dimissionReason) {
        this.dimissionReason = dimissionReason;
    }
}
