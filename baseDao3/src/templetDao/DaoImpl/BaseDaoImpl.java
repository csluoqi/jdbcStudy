package templetDao.DaoImpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import templetDao.Dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T>
{
protected Class entityClass;
public BaseDaoImpl()
{
    super();
    Class clazz = getClass();
    System.out.println("BaseDaoImpl : " + clazz);
    Type type = clazz.getGenericSuperclass();
    System.out.println("type:"+type);
    if(type instanceof ParameterizedType)
    {
        ParameterizedType pType = (ParameterizedType) type;
        Type[] types = pType.getActualTypeArguments();
        entityClass = (Class) types[0];
        System.out.println("entityClass:"+entityClass);
    }
    
    
    }
@Override
public T get(Serializable id)
{
    // TODO Auto-generated method stub
    return null;
}
@Override
public boolean save(T t)
{
    // TODO Auto-generated method stub
    return false;
}
@Override
public boolean delete(int id)
{
    // TODO Auto-generated method stub
    return false;
}
@Override
public List<T> findAll()
{
    // TODO Auto-generated method stub
    return null;
}

}
