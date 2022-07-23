package negocio.academico.plan;
import excepciones.BorrarPlanEx;
import modelo.academico.plan.Plan;
import persistencia.BaseDeDatos;

public class BorrarPlanImpl implements BorrarPlan {

    @Override
    public boolean borrar(Plan plan) throws BorrarPlanEx {
        
        if(plan == null)  { 
            throw new BorrarPlanEx("el plan recibido es nulo");
        }
        if (plan.getAnio() == null) {
            throw new BorrarPlanEx("el plan recibido no tiene año definido"); 
        }
  
        for(Plan aux : BaseDeDatos.planes){
            if(aux.getAnio() == plan.getAnio()){
                if(aux.isEstadoBorrador()){
                    System.out.println("Plan " + aux.getAnio() + " eliminado de la BD");
                    BaseDeDatos.planes.remove(aux);
                    return true;
                } else {
                    System.out.println("Plan " + aux.getAnio() + " seteado a NOACTIVO");
                    aux.setEstadoNoActivo();
                }
            }
        }
        return false;       
    }
    
}
