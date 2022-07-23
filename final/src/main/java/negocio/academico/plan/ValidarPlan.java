package negocio.academico.plan;

import java.util.ArrayList;
import java.util.List;

import excepciones.ValidarPlanEx;
import modelo.academico.plan.AnioPlan;
import modelo.academico.plan.Materia;
import modelo.academico.plan.Plan;
import persistencia.BaseDeDatos;

public class ValidarPlan {

    /*
        Este metodo requiere se valide un plan.
        Debe retornar false si:
            - plan no puede ser null
            - No se permite recibir un plan sin estado
            - No se permite un plan con año null, excepto si el estado es BORRADOR
            - No se permite un plan con año < 1990 y > 2040
            - No se permite un listado de años null o vacio, excepto si el estado del plan es BORRADOR
            - No se permite un año con el att plan != del plan que se recibe [ej. plan.getAnios().get(0).getPlan().equals(plan) == false, es error ]
            - No se permite un año con el att numero <= 0, recordar que si el estado del plan es BORRADOR, este att puede ser null, pero nunca <= 0
            - Los numeros de los años en el listado de años debe ser secuancial, comenzando en 1
            - No se puede repetir años con el mismo numero, en el mismo plan
            - No se permite que un año del plan no tenga nombre, excepto si el estado del plan es BORRADOR 
            - No se permiten años con un listado de materias null o vacio, excepto si el estado del plan es BORRADOR
            - No se permite una materia con el att anioPlan != del anioPlan al q pertenece [ej. anioPlan.getMaterias().get(0).getAnioPlan().equals(anioPlan) == false, es error ]
            - No se permite una materia con el att codigo <= 0, recordar que si el estado del plan es BORRADOR, este att puede ser null, pero nunca <= 0 
            - Los numeros de las materias en el listado de materias de un año debe ser secuancial, comenzando en 1
            - No se permiten materias con el mismo codigo, en el mismo plan
            - No se permite que una materia no tenga nombre, excepto si el estado del plan es BORRADOR 
            - No se permite que una materia no tenga carga horaria, excepto si el estado del plan es BORRADOR 
            - No se permite que una materias tenga carga horaria < 0, recordar que si el estado del plan es BORRADOR, este att puede ser null, pero nunca <= 0 
    */

    public static boolean validar(Plan plan, boolean modificacion) throws ValidarPlanEx {

        List <Integer> auxNumAnio = new ArrayList<>();
        
        if (plan == null) {
            throw new ValidarPlanEx("plan es null");
        }

        if(plan.getAnio() == null){
            throw new ValidarPlanEx("El plan no posee anio");
        }

        if (!plan.hasEstado()){ //comprobamos que tenga estado asignado.
            //return false;
            throw new ValidarPlanEx("El plan no posee estado");
        }
        
        boolean borrador = plan.isEstadoBorrador() ? true : false;
         
        if(!modificacion) {
            for (Plan auxPlan : BaseDeDatos.planes) {
                if (auxPlan.getAnio().equals(plan.getAnio())) {
                    throw new ValidarPlanEx("El anio del plan ya existe");
                }
            }
        }

        if(plan.getAnio() <= 1990 || plan.getAnio() >= 2040){
            throw new ValidarPlanEx("El anio del plan esta fuera del rango permitido");
        }
        
        
        //si se cumple alguna condicion, el plan NO se valida   
        for(int i = 0; i<plan.getAnios().size(); i++){
                
            /*if(!plan.getAnios().get(i).getPlan().equals(plan)){
                throw new ValidarPlanEx("año del plan, no pertenece a ese plan");
            }*/
        }


            
        for(int i = 1; i<plan.getAnios().size()-1; i++){
               
            if(!(plan.getAnios().get(i-1).getNumero() < plan.getAnios().get(i).getNumero()) || !(plan.getAnios().get(i+1).getNumero() > plan.getAnios().get(i).getNumero())){     
                throw new ValidarPlanEx("no hay secuencialidad en los años");    
            }  
        }

        for(AnioPlan anio : plan.getAnios()){
            for(int j = 0; j<auxNumAnio.size(); j++){
                if(auxNumAnio.get(j).equals(anio.getNumero())){ 
                    throw new ValidarPlanEx("año con el mismo numero");
                }        
            }       
            
            auxNumAnio.add(anio.getNumero());
                  
            for (int j = 0; j<anio.getMaterias().size(); j++){           
                /*if(!anio.getMaterias().get(j).getAnio().equals(anio)){
                    throw new ValidarPlanEx("materia no pertenece a ese año");          
                }*/ //J -- recorre materias, la i los años.
            }   
        }
            

            auxNumAnio.clear();
            
        for(AnioPlan anio : plan.getAnios()){//año
                
            for(int i = 0; i<anio.getMaterias().size(); i++){//materia a comparar.

                for(int j = 0; j<auxNumAnio.size(); j++){//materia con la que comparo
                        
                    if(anio.getMaterias().get(i).getCodigo().equals(auxNumAnio.get(j)) && anio.getMaterias().get(i).getCodigo()>0){
                        throw new ValidarPlanEx("materia con mismo codigo " + anio.getMaterias().get(i). getCodigo() + " - " + auxNumAnio.get(j));
                    }
                }
                auxNumAnio.add(anio.getMaterias().get(i).getCodigo());
            }
        }

        auxNumAnio.clear();

        for(AnioPlan anio : plan.getAnios()){
            for(int i = 1; i<anio.getMaterias().size()-1; i++){
               if(!(anio.getMaterias().get(i-1).getCodigo()<anio.getMaterias().get(i).getCodigo()) || !(anio.getMaterias().get(i+1).getCodigo()>anio.getMaterias().get(i).getCodigo())){
                throw new ValidarPlanEx("materia sin secuencialidad");
                }
            }
        }

        if(!borrador){

            if(plan.getAnio() == null || plan.getAnio()<=0)
                throw new ValidarPlanEx("anio es nulo o menor que cero");
            
            if(plan.getAnios() == null)
                throw new ValidarPlanEx("el plan no posee anios asignados");
            
            
            for(AnioPlan anio : plan.getAnios()){
                if(anio.getNombre() == null){
                    throw new ValidarPlanEx("uno de los anios del plan no posee nombre");
                }

                if(anio.getMaterias() == null){
                    throw new ValidarPlanEx("uno de los anios del plan no posee materias asignadas");
                }

                for(Materia materia : anio.getMaterias()){
                    if(materia.getCargaHoraria()<0 || materia.getCargaHoraria()==null || materia.getNombre()==null || materia.getCodigo()<0){
                        throw new ValidarPlanEx("A materia se le cargo mal un dato");
                    }
                }
            }

        }

        return true;
    }
    
}