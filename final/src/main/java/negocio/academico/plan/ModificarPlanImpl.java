package negocio.academico.plan;

import excepciones.ModificarPlanEx;
import excepciones.ValidarPlanEx;
import modelo.academico.plan.Plan;
import persistencia.BaseDeDatos;

public class ModificarPlanImpl implements ModificarPlan {

	@Override
	public boolean modificar(Plan plan) throws ModificarPlanEx {
        
            try {
                if(ValidarPlan.validar(plan, true)){
                	for(Plan aux : BaseDeDatos.planes) {
                        if(aux.getAnio().equals(plan.getAnio())){
                            try {
                                BaseDeDatos.planes.set(BaseDeDatos.planes.indexOf(aux), (Plan)plan.clone());
                                return true;
                            } catch (CloneNotSupportedException e) {
                                throw new ModificarPlanEx(e.getMessage());
                            }
                        }
                    }
                }
            } catch (ValidarPlanEx e) {
                throw new ModificarPlanEx(e.getMessage());
            }
		return false;
	}
}
