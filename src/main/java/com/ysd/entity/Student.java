package com.ysd.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "studenttb")
public class Student {

	@Id
	@GeneratedValue
	private Integer studentId;//学生编号
	
	private String studentName;// 学生姓名 
	private Integer studentAge;//学生年龄 
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Date studentBirthday;//学生出生年月
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
	public Integer getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}
	public Date getStudentBirthday() {
		return studentBirthday;
	}
	public void setStudentBirthday(Date studentBirthday) {
		this.studentBirthday = studentBirthday;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentAge=" + studentAge
				+ ", studentBirthday=" + studentBirthday + "]";
	}
	
	

}
