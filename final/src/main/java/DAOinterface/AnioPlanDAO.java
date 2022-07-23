package DAOinterface;

import modelo.academico.plan.AnioPlan;

import java.util.List;

public interface AnioPlanDAO extends DAO<AnioPlan>{


    public void guardar(AnioPlan a);


    public void eliminar(AnioPlan a);


    public AnioPlan buscar(AnioPlan a);


    public List<AnioPlan> buscarTodos();


}
