package negocio.academico.plan;

import modelo.academico.plan.Plan;
import excepciones.BuscarPlanEx;

public interface BuscarPlan {

    // este metodo debe devolver un plan correspondiente al año ingresado 
    //(si este se encuentra en nuestra BD)
    Plan buscar(int anio) throws BuscarPlanEx;
}
