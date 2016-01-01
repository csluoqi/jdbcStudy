package baseDao.Dao;

import java.io.Serializable;
import java.util.List;

/**
 * 定规则:
 * 实体类名字 |数据库表名
 * Student   | tb_student
 * table的所有字段要和Vo的所有属性对应,字段名要和属性名字一致,提供相应的get,set方法,
 * 无参构造函数构造器,其实也可以用xml文件配置或者用其他的方法
 * @author rocky
 *
 */
public interface BasicDao
{
    /**
     * 根据Id查找对象,返回传入的对象
     * @param clazz 返回查询的对象的类型,
     * @param id 需要查找的id
     * 
     * @return 成功返回Instance,失败,返回null
     * 1.通过class的getName加上tb_前缀,拿到表名
     * 2.发送sql语句,select * from tb_class.getName() where id = ?
     * 3.处理rs:把对象的字段属性通过class 的set方法加到instance里面
     * 返回instantce
     */
    Object get(Class clazz, Serializable id);
    /**
     * 更新object
     * @param object 需要更新的对象
     * @return true.更新成功,false.更新失败
     * 1.通过class的getName加上tb_前缀,拿到表名
     * 2.发送sql语句,update tb_class.getName()set object.getname = f.get(Object)
     */
    boolean update(Object object);
    /**
     * 将Object存到数据库中
     * @param object 要存储的对象
     * @return 成功 返回true,失败,返回false
     * 1.通过class的getName加上tb_前缀,拿到表名
     * 2.发送sql语句,insert into tb_class.getName()(object的所有的属性名) values(object的所有属性之)
     */
    boolean save(Object object);
    /**
     * 根据Object和id删除数据,
     * @param clazz 要删除的对象
     * @param id 要删除的主键
     * @return 成功 返回true,失败 返回false
     */
    boolean delete(Class clazz ,int id);
    /**
     *  查询并返回传入的对象的List集合
     * @param clazz 返回查询的类型在数据库中对应表的集合
     * @return 成功,返回list<?>集合 失败,返回 null
     * 1.通过class的getName加上tb_前缀,拿到表名,
     * 2.发送sql语句,select * from tb_class,getName()
     * 3.处理rs:把对象的字段属性通过class的setfangfa加到instance里面
     * 4.返回List
     */
    List<? extends Object> findAll(Class clazz);
}









