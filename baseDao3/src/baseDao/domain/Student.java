package baseDao.domain;

public class Student
{
    private Long id;
    private String name;
    private String gender;
    private int age;
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getGender()
    {
        return gender;
    }
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public Student()
    {
    }
    @Override
    public String toString()
    {
        return "Student [id=" + id + ", name=" + name + ", gender=" + gender
                + ", age=" + age + "]";
    }
   
}
