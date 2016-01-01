package templetDao.DaoImpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import templetDao.Dao.BaseDao;
import templetDao.templet.JdbcTemplate;

public class BaseDaoImpl<T> implements BaseDao<T>
{
    // 实际会调用JDBCTemplate的底层的T的底层方法
    private JdbcTemplate<T> jdbcTemplate = new JdbcTemplate<T>();

    // 定义一个Class对象,代表传递进来的T的class
    // 确定一个Class
    protected Class entityClass;

    public BaseDaoImpl()
    {
        super();
        // 获取当前类的CLass
        Class clazz = this.getClass();
        // 打印当前类的Class
        System.out.println("BaseDaoImpl : " + clazz);
        // 返回表示此Class所表示的实体(类,接口,基本类型或者Void)的
        // 直接超类的Type.
        Type type = clazz.getGenericSuperclass();

        System.out.println("type:" + type);
        // ParameterizedType 表示参数化类型，如 Collection<String>。这里可以理解为泛型被实现的具体的对象的类
        if (type instanceof ParameterizedType)
        {
            ParameterizedType pType = (ParameterizedType) type;
            // 返回表示此类型实际参数的type对象的数组
            Type[] types = pType.getActualTypeArguments();
            // 拿到泛型的具体类型
            entityClass = (Class) types[0];
            System.out.println("types" + Arrays.toString(types));
            System.out.println("entityClass:" + entityClass);
        }

    }

    @Override
    public T get(Serializable id)
    {
        // TODO Auto-generated method stub
        return jdbcTemplate.get(entityClass, id);
    }

    @Override
    public boolean save(T t)
    {
        // TODO Auto-generated method stub
        return jdbcTemplate.save(t);
    }

    @Override
    public boolean delete(Serializable id)
    {
        // TODO Auto-generated method stub
        return jdbcTemplate.delete(entityClass, id);
    }

    @Override
    public List<T> findAll()
    {
        // TODO Auto-generated method stub
        return jdbcTemplate.findAll(entityClass);
    }

}
