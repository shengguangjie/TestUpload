package com.ysd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ysd.dao.StudentRepository;
import com.ysd.entity.OtherStudent;
import com.ysd.entity.Student;
import com.ysd.entity.StudentFild;
import com.ysd.utils.JpaObjectsToEntity;

@RestController
public class StudentController {
	
	@Autowired
	private StudentRepository stuRepository;
	/**
	 *  http://localhost:8080/addStu?studentName=刘一&studentAge=100&studentBirthday=2018-08-13 11:21:30
	 * 添加
	 * @param student
	 * @return
	 */
	@RequestMapping("/addStu")
	public Object addStudent(Student student) {
		return stuRepository.save(student);
	}
	
	/**
	 * http://localhost:8080/findOne?id=1
	 * 根据id查询
	 */
	@RequestMapping("/findOne")
	public Object findOne(Integer id) {
		
		return stuRepository.findById(id);
	}
	
	/**
	 * http://localhost:8080/findByStudentName?name=刘
	 * 模糊查询
	 */
	@RequestMapping("/findByStudentName")
	public Object findByStudentName(String name) {
		
		return stuRepository.findByStudentNameContaining(name);
	}
	
	/**
	 * 根据id删除
	 */
	public void delete(Integer id) {	
		try {
			stuRepository.deleteById(id);		
		} catch (Exception e) {
			
			e.printStackTrace();		
		}
	}
	
	/**
	 * 利用jpql进行操作
	 * http://localhost:8080/insertStudent
	 */
	@RequestMapping("/insertStudent")
	public void insertStudent() {		
		int n=stuRepository.insertStudent("王五", 20);
	}
	
	
	
	/**
	 * http://localhost:8080/getStudentFild
	 * 自定义映射
	 */
	@RequestMapping("/getStudentFild")
	public Object getStudentFild() {
		List<StudentFild> list=stuRepository.getStudentFild();
		return list;
	}
	
	/**
	 * http://localhost:8080/queryOtherStudent
	 * 反射机制映射
	 */
	@RequestMapping("/queryOtherStudent")
	public Object queryOtherStudent() {
		List<Object[]> list=stuRepository.queryOtherStudent();
		List<OtherStudent> otherStudent=(List<OtherStudent>) JpaObjectsToEntity.jpaResultToObjectList(list, OtherStudent.class);
		return otherStudent;
	}

}
