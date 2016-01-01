package baseDao.DaoImpl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import jdbcStudy.db.factory.DB;
import baseDao.Dao.BasicDao;
import baseDao.Dao.DataAccessException;

public class BasicDaoImpl implements BasicDao
{
    @Override
    public Object get(Class clazz, Serializable id)
    {
        String simpleName = clazz.getSimpleName();
        System.out.println(simpleName);
        final Field[] fields = clazz.getDeclaredFields();
        // 拼接sql
        final StringBuffer sql = new StringBuffer();
        sql.append(" select ");
        for (Field field : fields)
        {
            sql.append(field.getName()).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" from tb_").append(simpleName);
        sql.append(" where id = ?").append(" ; ");
        System.out.println(sql);

        return template(new CallBack<Object>()
        {
            @Override
            public Object doInCallBack(Connection conn, PreparedStatement pstm,
                    ResultSet rs) throws Throwable
            {
                Object object = clazz.newInstance();
                pstm = conn.prepareStatement(sql.toString());
                pstm.setObject(1, id);
                rs = pstm.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String ColumnName = null;
                boolean next = rs.next();
                Field field = null;
                if (!next)
                {
                    return null;
                }
                for (int i = 1; i <= rsmd.getColumnCount(); i++)
                {
                    ColumnName = rsmd.getColumnName(i);
                    field = clazz.getDeclaredField(ColumnName);
                    if(!field.isAccessible())
                    {
                        field.setAccessible(true);
                    }
                    field.set(object, rs.getObject(ColumnName));
                }
                System.out.println(object);
                return object;
            }
        });
    }

    @Override
    public boolean update(Object object)
    {
        Class clazz = object.getClass();
        String simpleName = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();
        StringBuffer sql = new StringBuffer("update tb_").append(simpleName);
        sql.append(" set ");
        System.out.println("fields: " + fields.length);
        for (Field f : fields)
        {
            if ("id".equals(f.getName()))
            {
                continue;
            }
            System.out.println(f.getName());
            sql.append(f.getName()).append(" = ?,");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" where ").append("id").append(" = ? ;");
        System.out.println(sql);
        return template(new CallBack<Boolean>()
        {
            @Override
            public Boolean doInCallBack(Connection conn,
                    PreparedStatement pstm, ResultSet rs) throws Throwable
            {
                int fieldIndex = 1;
                int affectRow = 0;
                pstm = conn.prepareStatement(sql.toString());
                Object idValue = null;
                for (Field f : fields)
                {
                    if (!f.isAccessible())
                    {
                        f.setAccessible(true);
                    }
                    if (!"id".equals(f.getName()))
                    {
                        System.out.println(f.get(object));
                        pstm.setObject(fieldIndex, f.get(object));
                        fieldIndex++;
                    } else
                    {
                        System.out.println(f.get(object));
                        idValue = f.get(object);
                    }
                }
                pstm.setObject(fieldIndex, idValue);
                affectRow = pstm.executeUpdate();
                return affectRow != 0 ? true : false;
            }
        });
    }

    @Override
    public boolean save(Object object)
    {
        // 是不是应该把id传进来,然后解决不能表里面的主键不是"id"的问题
        Class clazz = object.getClass();
        String simpleName = clazz.getSimpleName();
        System.out.println(simpleName);
        final Field[] fields = clazz.getDeclaredFields();
        // 拼接sql
        final StringBuffer sql = new StringBuffer();
        sql.append("insert into tb_").append(simpleName);
        sql.append(" ( ");
        StringBuffer sbValue = new StringBuffer("values( ");
        for (Field field : fields)
        {
            if (!"id".equals(field.getName()))
            {
                sql.append(field.getName()).append(",");
                sbValue.append("?,");
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sbValue.deleteCharAt(sbValue.length() - 1);
        sql.append(" )");
        sql.append(sbValue.toString());
        sql.append(" )");
        System.out.println("sql--> " + sql);
        return template(new CallBack<Boolean>()
        {
            @Override
            public Boolean doInCallBack(Connection conn,
                    PreparedStatement pstm, ResultSet rs) throws Throwable
            {
                pstm = conn.prepareStatement(sql.toString());
                int i = 1;
                for (Field field : fields)
                {
                    if (!"id".equals(field.getName()))
                    {
                        if (!field.isAccessible())
                        {
                            field.setAccessible(true);
                        }
                        pstm.setObject(i, field.get(object));
                        i++;
                    }
                }
                System.out.println(sql);
                int row = 0;
                row = pstm.executeUpdate();
                return row != 0 ? true : false;
            }
        });
    }

    @Override
    public boolean delete(Class clazz, int id)
    {
        String simpleName = clazz.getSimpleName();
        final StringBuffer sql = new StringBuffer();
        sql.append(" delete from tb_");
        sql.append(simpleName);
        sql.append(" where id = ? ;");
        System.out.println(sql);
        System.out.println(simpleName);
        return template(new CallBack<Boolean>()
        {
            @Override
            public Boolean doInCallBack(Connection conn,
                    PreparedStatement pstm, ResultSet rs) throws Throwable
            {
                pstm = conn.prepareStatement(sql.toString());
                pstm.setObject(1, id);
                int row = pstm.executeUpdate();

                return row != 0 ? true : false;
            }
        });
    }

    @Override
    public List<? extends Object> findAll(Class clazz)
    {
        String simpleName = clazz.getSimpleName();
        System.out.println(simpleName);
        final Field[] fields = clazz.getDeclaredFields();
        // 拼接sql
        final StringBuffer sql = new StringBuffer();
        sql.append(" select ");
        for (Field field : fields)
        {
            sql.append(field.getName()).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" from tb_").append(simpleName);
        // sql.append(" where id = ?").append(" ; ");
        System.out.println(sql);

        return template(new CallBack<List<? extends Object>>()
        {
            @Override
            public List<? extends Object> doInCallBack(Connection conn,
                    PreparedStatement pstm, ResultSet rs) throws Throwable
            {
                List resultList = new ArrayList();
                Object object = null;
                pstm = conn.prepareStatement(sql.toString());
                rs = pstm.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                String columnName = null;
                boolean next = rs.next();
                int ColumnCount = rsmd.getColumnCount();
                Field field = null;
                while (next)
                {
                    object = clazz.newInstance();
                    for (int i = 1; i <= ColumnCount; i++)
                    {
                        columnName = rsmd.getColumnName(i);
                        field = clazz.getDeclaredField(columnName);
                        if(!field.isAccessible())
                        {
                            field.setAccessible(true);
                        }
                        field.set(object, rs.getObject(columnName));
                    }
                    resultList.add(object);
                    next = rs.next();
                }
                System.out.println(object);
                return resultList;
            }
        });
    }

    /**
     * 回调接口
     * 
     * @author rocky
     *
     * @param <E>
     */
    protected interface CallBack<E>
    {
        E doInCallBack(Connection conn, PreparedStatement pstm, ResultSet rs)
                throws Throwable;

    }

    /**
     * 
     * @param callback
     * @return 返回回调方法的返回值
     * @throws DataAccessException
     *             所有回调方法所抛出的方法都会被包转成此运行时异常,该异常extends RuntimeException
     *             {@link CallBack}
     */
    protected <E> E template(CallBack<E> callback) throws DataAccessException
    {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        conn = DB.getDbInstance().getConnection();
        try
        {
            return callback.doInCallBack(conn, pstm, rs);
        } catch (Throwable e)
        {
            throw new DataAccessException(e);
        } finally
        {
            DB.getDbInstance().CloseConnection(conn, pstm, rs);
        }
    }
}
