package interfazusuario;

import excepciones.BuscarPlanEx;

public interface BuscarEImprimirPlanes {

    // este metodo debe imprimir un listado de planes segun los terminos de busqueda

        //          PLAN        Años        Materias        ESTADO
        //          ----        ----        --------       -------- 
        //          2018          5            42          ACTIVO  
        //          2013          7            2           BORRADOR  

    void buscarEImprimirPlanes(String terminos) throws BuscarPlanEx;
    
}