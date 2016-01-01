package templetDao.Dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T>
{
    /**
     * 通过Id查找对象,返回传入的对象
     * @param id 表的主见的ID
     * @return Instance
     */
    T get(Serializable id);
    boolean save(T t);
    boolean delete(Serializable id);
    List<T> findAll();
    
}
