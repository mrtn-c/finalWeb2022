package negocio.academico.plan;

import java.util.List;

import excepciones.BorrarPlanEx;
import modelo.academico.plan.Plan;

public class BorrarPlanesImpl implements BorrarPlanes {

    @Override
    public boolean borrar(List<Plan> planes) throws BorrarPlanEx {
        
        BorrarPlan borrarPlan = new BorrarPlanImpl();
          
        for(Plan plan : planes){
            borrarPlan.borrar(plan);
        }  
        return true;
    }
    
}
