package negocio.academico.plan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modelo.academico.plan.AnioPlan;
import modelo.academico.plan.Materia;
import modelo.academico.plan.Plan;
import persistencia.BaseDeDatos;
import excepciones.BuscarPlanEx;

public class BuscarPlanesImpl implements BuscarPlanes {

    @Override
    public List<Plan> buscar(String terminos) throws BuscarPlanEx {
        
        
        List<Plan> planes = new ArrayList<>();
        terminos = terminos.toLowerCase();
        terminos = Normalizador.cleanString(terminos);
        List<String>palabras = Arrays.asList(terminos.split("\\s+"));

        if(terminos.contains("all")){
            for(Plan plan : BaseDeDatos.planes){
                encontrado(planes,plan);
            }
            return planes;
        }

        for(String palabra : palabras){
            
            if(palabra == null){
                continue;
            } 
            
            for(Plan plan : BaseDeDatos.planes){
                //Busqueda por año.
                if(plan.getAnio().toString().contains(palabra)){
                    encontrado(planes, plan);
                    continue;
                }
                //Busqueda por estado.
                if(plan.estadoString().toLowerCase().contains(palabra)){
                    encontrado(planes, plan);
                    continue;
                }

                //busqueda por materia.
                for(AnioPlan anio : plan.getAnios()){
                    for(Materia materia : anio.getMaterias()){
                        String aux1 = Normalizador.cleanString(materia.getNombre()).toLowerCase();
                        List<String> aux = Arrays.asList(aux1.split("\\s+")); 
                        for(String limpiar: aux){
                            Normalizador.cleanString(limpiar);
                        }

                        for(String aux2 : aux){
                            if(aux2.contains(palabra)){
                                encontrado(planes, plan);
                                continue;
                            }
                        }
                    }
                } 
            }
        }  
        return planes;
    }


    private void encontrado(List<Plan> planes, Plan plan){
        if(planes.contains(plan)){
            return;
        } else {
            planes.add(plan);
            return;
        }
        
        
 
    }
}
