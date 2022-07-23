package DAOinterface;

import modelo.academico.plan.Plan;

import java.util.List;

public interface PlanDAO extends DAO<Plan> {

    public void guardar(Plan p);

    public void eliminar(Plan p);

    public Plan buscar(Plan p);

    public List<Plan> buscarTodos();

}
