package DAOinterface;

import java.util.List;

public interface DAO<T> {

     public void guardar(T p);

     public void eliminar(T p);
     public Object buscar(T p);

     public List<T> buscarTodos();

}//end interface DAO


