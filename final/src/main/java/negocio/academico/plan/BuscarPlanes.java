package negocio.academico.plan;

import java.util.List;

import modelo.academico.plan.Plan;
import excepciones.BuscarPlanEx;

public interface BuscarPlanes {

    // este metodo debe devolver todos los planes que contengan en sus datos el termino
    // si el att del plan es != String, se debe convertir el att a String y luego comparar
    // si el termino esta compuesto por varias palabras, se debe buscar por cada palabra, si coincide alguna se debe retornar ese plan
    // la busqueda debe ser ignore case, por contenido (que contenga la plabra) y con traslate (es decir no importan los acentos, comillas simples, ñ, etc)
    // se debe buscar tanto en los atts directos del plan, como en todos sus años y materias  
    List<Plan> buscar(String terminos) throws BuscarPlanEx;
    
}
