package negocio.academico.plan;

import modelo.academico.plan.Plan;
import persistencia.BaseDeDatos;
import excepciones.BuscarPlanEx;

public class BuscarPlanImpl implements BuscarPlan {

    @Override
    public Plan buscar(int anio) throws BuscarPlanEx {
        
        if(anio <= 1990 || anio >= 2040){
            throw new BuscarPlanEx("El anio esta fuera del rango permitido");
        }
        
        for (Plan plan : BaseDeDatos.planes){
            if(plan.getAnio() == anio){
                return plan;
            }
        }    
        
        System.out.println("Plan con anio: " + anio + " no encontrado!");
        return null;

    }
    
}
