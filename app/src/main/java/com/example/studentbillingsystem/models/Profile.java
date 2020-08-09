package com.example.studentbillingsystem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {
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
    private Details details;

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

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public class Details {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("studentName")
        @Expose
        private String studentName;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("contactNumber")
        @Expose
        private String contactNumber;
        @SerializedName("matrixId")
        @Expose
        private String matrixId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("userId")
        @Expose
        private Integer userId;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("semesterId")
        @Expose
        private Integer semesterId;
        @SerializedName("semesterName")
        @Expose
        private String semesterName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("admissionDTO")
        @Expose
        private AdmissionDTO admissionDTO;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        public String getMatrixId() {
            return matrixId;
        }

        public void setMatrixId(String matrixId) {
            this.matrixId = matrixId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public AdmissionDTO getAdmissionDTO() {
            return admissionDTO;
        }

        public void setAdmissionDTO(AdmissionDTO admissionDTO) {
            this.admissionDTO = admissionDTO;
        }

        public class AdmissionDTO {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("admissionDate")
                @Expose
                private String admissionDate;
                @SerializedName("completionDate")
                @Expose
                private String completionDate;
                @SerializedName("intakeYear")
                @Expose
                private Integer intakeYear;
                @SerializedName("intakeMonth")
                @Expose
                private String intakeMonth;
                @SerializedName("feeAmount")
                @Expose
                private Double feeAmount;
                @SerializedName("scholarshipAmount")
                @Expose
                private Double scholarshipAmount;
                @SerializedName("admissionAmount")
                @Expose
                private Double admissionAmount;
                @SerializedName("studentId")
                @Expose
                private Integer studentId;
                @SerializedName("studentName")
                @Expose
                private String studentName;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public String getAdmissionDate() {
                    return admissionDate;
                }

                public void setAdmissionDate(String admissionDate) {
                    this.admissionDate = admissionDate;
                }

                public String getCompletionDate() {
                    return completionDate;
                }

                public void setCompletionDate(String completionDate) {
                    this.completionDate = completionDate;
                }

                public Integer getIntakeYear() {
                    return intakeYear;
                }

                public void setIntakeYear(Integer intakeYear) {
                    this.intakeYear = intakeYear;
                }

                public String getIntakeMonth() {
                    return intakeMonth;
                }

                public void setIntakeMonth(String intakeMonth) {
                    this.intakeMonth = intakeMonth;
                }

                public Double getFeeAmount() {
                    return feeAmount;
                }

                public void setFeeAmount(Double feeAmount) {
                    this.feeAmount = feeAmount;
                }

                public Double getScholarshipAmount() {
                    return scholarshipAmount;
                }

                public void setScholarshipAmount(Double scholarshipAmount) {
                    this.scholarshipAmount = scholarshipAmount;
                }

                public Double getAdmissionAmount() {
                    return admissionAmount;
                }

                public void setAdmissionAmount(Double admissionAmount) {
                    this.admissionAmount = admissionAmount;
                }

                public Integer getStudentId() {
                    return studentId;
                }

                public void setStudentId(Integer studentId) {
                    this.studentId = studentId;
                }

                public String getStudentName() {
                    return studentName;
                }

                public void setStudentName(String studentName) {
                    this.studentName = studentName;
                }


        }
    }



}
