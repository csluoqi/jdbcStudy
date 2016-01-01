

import java.io.Serializable;
import java.util.List;

import baseDao.domain.Bok;


public interface BookDaos
{
    Serializable saveBook(Bok book);
    Serializable updateBook(Bok book);
    Serializable deleteBook(Serializable id);
    List<Bok> findAll();
    Bok findBookById(Serializable id);
    
    
}
