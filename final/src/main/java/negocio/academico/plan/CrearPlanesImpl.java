package negocio.academico.plan;

import java.util.List;

import excepciones.CrearPlanEx;
import modelo.academico.plan.Plan;

public class CrearPlanesImpl implements CrearPlanes {

    @Override
    public boolean crear(List<Plan> planes) throws CrearPlanEx {

        CrearPlanImpl aux = new CrearPlanImpl();
        
        for (Plan plan : planes){
                aux.crear(plan);
        }

        return true;
    } 
    
}
