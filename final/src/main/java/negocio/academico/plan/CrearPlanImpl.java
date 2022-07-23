package negocio.academico.plan;

import excepciones.CrearPlanEx;
import excepciones.ValidarPlanEx;
import modelo.academico.plan.Plan;
import persistencia.BaseDeDatos;


public class CrearPlanImpl implements CrearPlan {

    @Override
    public boolean crear(Plan plan) throws CrearPlanEx {
        
        try {
            if(ValidarPlan.validar(plan, false)) {
                try{
                    BaseDeDatos.planes.add((Plan)plan.clone());
                    return true;
                } catch (CloneNotSupportedException e) {
                    throw new CrearPlanEx(e.getMessage());
                }
            }
        } catch (ValidarPlanEx e) {
            throw new CrearPlanEx(e.getMessage());
        }
        return false;
    }
}