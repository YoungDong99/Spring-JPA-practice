package com.example;

import javax.persistence.*;

@Entity(name="professor_mapping_practice")
@Table(name="pro_info")
public class Professor {

    @Id
    @Column(name="PRO_CODE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int proCode;
    @Column(name="PRO_NAME")
    private String proName;
    @Column(name="DEPT_CODE")
    private String deptCode;
    @Column(name="NUMBER", columnDefinition="varchar(200) default '010-0000-0000'")
    private String number;
    @Column(name="EMAIL", unique=true)
    private String email;
    public Professor() { }

    public Professor(int proCode, String proName, String deptCode, String number, String email) {
        this.proCode = proCode;
        this.proName = proName;
        this.deptCode = deptCode;
        this.number = number;
        this.email = email;
    }

    public int getProCode() {
        return proCode;
    }

    public void setProCode(int proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "proCode=" + proCode +
                ", proName='" + proName + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
