package util;

import java.io.IOException;
import java.util.List;

import interfazusuario.BuscarEImprimirPlanes;
import interfazusuario.BuscarEImprimirPlanesImpl;
import modelo.academico.plan.Plan;
import negocio.academico.plan.CrearPlanes;
import negocio.academico.plan.CrearPlanesImpl;
import negocio.academico.plan.ModificarPlan;
import negocio.academico.plan.ModificarPlanImpl;
import persistencia.BaseDeDatos;
import util.GenerarEjemplosDePlanes;

public class Pruebas {

    public void probar() throws IOException {

        try {

            // genera los 2 planes hardcodeados y x aleatorios
            List<Plan> planes = GenerarEjemplosDePlanes.generar(1, true);

            System.out.println("\n\nSe crearon " + planes.size() + " planes.");

            CrearPlanes crearPlanes = new CrearPlanesImpl();

            // envia la lista a la bd
            boolean ok = crearPlanes.crear(planes);

            if (ok == false) {
                System.out.println("\n\nNo se pudieron crear los planes.");
                return;
            }

            if (BaseDeDatos.planes == null) {
                System.out.println("\n\nNo se pudieron crear los planes.");
                return;
            }


            BuscarEImprimirPlanes buscador = new BuscarEImprimirPlanesImpl();

            planes.get(0).setEstadoActivo();
            planes.get(1).setEstadoNoActivo();

            BuscarEImprimirPlanesImpl.imprimirPlanes(BaseDeDatos.planes);

            buscador.buscarEImprimirPlanes("lengua");
            System.out.println("\n\n");

        } catch (Exception e) {

        }
    }

}
