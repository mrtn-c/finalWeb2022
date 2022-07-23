package DAOinterface;

import modelo.academico.plan.Materia;
import modelo.academico.plan.Plan;

import java.util.List;

public interface MateriaDAO extends DAO<Materia> {

    public void guardar(Materia m);

    public void eliminar(Materia m);

    public Plan buscar(Materia m);

    public List<Materia> buscarTodos();


}
