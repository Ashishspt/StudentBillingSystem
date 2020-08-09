package com.example.studentbillingsystem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Subject {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public class Detail {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("subjectName")
        @Expose
        private String subjectName;
        @SerializedName("subjectCode")
        @Expose
        private String subjectCode;
        @SerializedName("subjectCredit")
        @Expose
        private String subjectCredit;
        @SerializedName("semesterId")
        @Expose
        private Integer semesterId;
        @SerializedName("semesterName")
        @Expose
        private String semesterName;
        @SerializedName("status")
        @Expose
        private String status;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getSubjectCode() {
            return subjectCode;
        }

        public void setSubjectCode(String subjectCode) {
            this.subjectCode = subjectCode;
        }

        public String getSubjectCredit() {
            return subjectCredit;
        }

        public void setSubjectCredit(String subjectCredit) {
            this.subjectCredit = subjectCredit;
        }

        public Integer getSemesterId() {
            return semesterId;
        }

        public void setSemesterId(Integer semesterId) {
            this.semesterId = semesterId;
        }

        public String getSemesterName() {
            return semesterName;
        }

        public void setSemesterName(String semesterName) {
            this.semesterName = semesterName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


    }




}
