package templetDao.testDaoImpl;

import baseDao.domain.Student;
import templetDao.DaoImpl.BaseDaoImpl;

public class TstStudentDao extends BaseDaoImpl<Student>
{
    public static void main(String[] args)
    {
        TstStudentDao dao = new TstStudentDao();
        //System.out.println(dao.findAll());
        //System.out.println(dao.get(5L));
        Student stu = new Student();
        stu.setName("123");
        stu.setGender("1");
        stu.setAge(55);
        System.out.println(dao.save(stu));
        //System.out.println(dao.delete(1L));
    }
    
}
