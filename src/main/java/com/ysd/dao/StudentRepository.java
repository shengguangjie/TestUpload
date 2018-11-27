package com.ysd.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ysd.entity.Student;
import com.ysd.entity.StudentFild;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	/**
	 * 模糊查询
	 */
	public List<Student> findByStudentNameContaining(String name);

	/*********** JPQL使用 ***********/
	// 查询
	@Query(" from Student s where s.studentName=?1")
	public List<Student> findStudentByName(String name);

	// 修改
	@Modifying
	@Transactional
	@Query("update Student s set s.studentName=?1 where s.studentId=?2")
	public int updateStudent(String name, int id);

	// 删除
	@Modifying
	@Transactional
	@Query("delete from Student s where s.studentId=?1")
	public int deleteStudent(int id);
	

	/*********** 原生SQL ***********/
	// 添加
	@Modifying
	@Transactional
	@Query(value = "insert into studenttb (student_name,student_age) VALUES(:name,:age)", nativeQuery = true)
	public int insertStudent(@Param("name") String name, @Param("age") int age);
	
	
	/*********** 自定义投影映射 ***********/
	//自定义投影映射JPQL中字段必须起和[属性名]一致的别名
	@Query("select s.studentName AS studentName, s.studentAge AS studentAge from Student s")
	public List<StudentFild> getStudentFild();
	
	
	/*********** 反射机制映射 ***********/
	//使用自定义工具类转换时原生SQL结果集与实体类只需要字段顺序一致即可.不需要字段名称一致
	@Query(value=" SELECT student_name AS a,student_age AS b FROM studenttb ",nativeQuery=true)
	public List<Object[]> queryOtherStudent();

}
