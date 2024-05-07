package default_package;

import java.sql.SQLException;

//hacerla GENERICA <T>
public interface IDAO<T> {
	
    public void create(T entity) throws SQLException;
	public void read()throws SQLException;
	public void update(T entity)throws SQLException;
	public void delete(int id)throws SQLException;
	
}
