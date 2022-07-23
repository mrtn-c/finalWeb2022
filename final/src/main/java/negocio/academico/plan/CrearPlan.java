package negocio.academico.plan;

import excepciones.CrearPlanEx;
import modelo.academico.plan.Plan;

public interface CrearPlan {

    /*
        Este metodo requiere que se guarde en la base de datos un plan
        Se debe validar y retornar false si:
            -ver reglas de validación en la clase ValidarPlan() 
    */
    public boolean crear(Plan plan) throws CrearPlanEx;    
    
}
