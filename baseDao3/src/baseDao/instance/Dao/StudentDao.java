package baseDao.instance.Dao;

import java.io.Serializable;
import java.util.List;

import javax.print.attribute.standard.Finishings;

import baseDao.DaoImpl.BasicDaoImpl;
import baseDao.domain.Student;

public class StudentDao extends BasicDaoImpl
{
    public boolean removeId(int id)
    {
        return this.delete(Student.class,1);
    }
    public boolean addStudent(Student stu)
    {
        return this.save(stu);
    }
    public Student getStudent(Long id)
    {
        return (Student) get(Student.class,id);
    }
    public List<Student> findAllStudent()
    {
        return   (List<Student>) findAll(Student.class);
    }
    public boolean updateStudent(Student student)
    {
        return update(student);
    }
    public static void main(String[] args)
    {
        StudentDao studentDao =  new StudentDao();
        //studentDao.removeId(1);
        Student stu = new Student();
        stu.setName("qqfield");
        stu.setGender("female");
        stu.setAge(25);
        stu.setId(3L);
       System.out.println(studentDao.getStudent(2L));
        //System.out.println(studentDao.findAll(Student.class));
        //System.out.println(studentDao.update(stu));
    }
}
