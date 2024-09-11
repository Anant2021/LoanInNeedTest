package com.bank.fintech.Model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "clientDetail")
public class ClientLoanDetail {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
    private String loanNo;
    private String firstName;
    private String lastName;
    private String panNo ;
    private String mobileNo;
    private String status;
    private LocalDate statusDate;
    private String branchName;
    private String loanDisbursed;
    private String roiPerDay;
    private String noOfDays;
    private String realDays;
    private int penaltyDays;
    private String penaltyInterest;
    private String penaltyCharge;
    private Double paidAmount;
    private double repaymentAmount;
    private LocalDate repaymentDate;
    private LocalDate disbursementDate;
    private double adminAndGstRate;
    private double dueDaterepaymentAmount;
    private double realInterest;

    public double getRealInterest() {
        return realInterest;
    }

    public void setRealInterest(double realInterest) {
        this.realInterest = realInterest;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public LocalDate getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(LocalDate statusDate) {
        this.statusDate = statusDate;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getLoanDisbursed() {
        return loanDisbursed;
    }

    public void setLoanDisbursed(String loanDisbursed) {
        this.loanDisbursed = loanDisbursed;
    }

    public String getRoiPerDay() {
        return roiPerDay;
    }

    public void setRoiPerDay(String roiPerDay) {
        this.roiPerDay = roiPerDay;
    }

    public String getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getRealDays() {
        return realDays;
    }

    public void setRealDays(String realDays) {
        this.realDays = realDays;
    }

    public int getPenaltyDays() {
        return penaltyDays;
    }

    public void setPenaltyDays(int penaltyDays) {
        this.penaltyDays = penaltyDays;
    }

    public String getPenaltyInterest() {
        return penaltyInterest;
    }

    public void setPenaltyInterest(String penaltyInterest) {
        this.penaltyInterest = penaltyInterest;
    }

    public String getPenaltyCharge() {
        return penaltyCharge;
    }

    public void setPenaltyCharge(String penaltyCharge) {
        this.penaltyCharge = penaltyCharge;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(double repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public LocalDate getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(LocalDate repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public LocalDate getDisbursementDate() {
        return disbursementDate;
    }

    public void setDisbursementDate(LocalDate disbursementDate) {
        this.disbursementDate = disbursementDate;
    }

    public double getAdminAndGstRate() {
        return adminAndGstRate;
    }

    public void setAdminAndGstRate(double adminAndGstRate) {
        this.adminAndGstRate = adminAndGstRate;
    }

    public double getDueDaterepaymentAmount() {
        return dueDaterepaymentAmount;
    }

    public void setDueDaterepaymentAmount(double dueDaterepaymentAmount) {
        this.dueDaterepaymentAmount = dueDaterepaymentAmount;
    }


}
