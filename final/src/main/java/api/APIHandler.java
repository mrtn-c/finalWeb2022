package api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import excepciones.BorrarPlanEx;
import excepciones.BuscarPlanEx;
import excepciones.CrearPlanEx;
import excepciones.ModificarPlanEx;
import interfazusuario.BuscarEImprimirPlanesImpl;
import modelo.academico.plan.Plan;
import negocio.academico.plan.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import persistencia.BaseDeDatos;

public class APIHandler implements HttpHandler {

    static int requestCount = 0;

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        requestCount++;

        // ---

        String protocol = exchange.getProtocol();
        String verb = exchange.getRequestMethod();
        String contextPath = exchange.getHttpContext().getPath();
        URI uri = exchange.getRequestURI();
        String path = uri.toString().replaceFirst(contextPath, "");
        Map<String, String> params = readParamsQuery(path);
        String body = readBody(exchange);
        System.out.println("------------------------------------------------------");
        System.out.println("REQUEST #" + requestCount + ":");
        // System.out.println("\tCX PATH: " + contextPath);
        System.out.println("\tPROTOCOL: " + protocol);
        System.out.println("\tHEADERS COUNT: " + exchange.getRequestHeaders().size());
        //System.out.println("\tLENGHT: " + exchange.get
        System.out.println("\tVERB: " + verb);
        System.out.println("\tPATH: " + path);
        System.out.println("\tPARAMETERS COUNT: " + params.size());
        //System.out.println("\tURI: " + uri);
        if(exchange.getRequestHeaders().containsKey("Content-Type")){
            System.out.println(exchange.getRequestHeaders().get("Content-Type").toString());
        }
        System.out.println("------------------------------------------------------");

        ObjectMapper mapper = new ObjectMapper();
        //ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();



        if (verb.equals("GET")){
            body = " ";
            String parametros = null;
            try {
                List<Plan> planes = new ArrayList<>();
                for(String aux : params.values()){
                    parametros = parametros + " " + aux;
                }
                planes = buscarPlanes(parametros); //HACER MAS COMPLETO
                body = "{ \"planes\": " + mapper.writeValueAsString(planes) + "}";
                BuscarEImprimirPlanesImpl.imprimirPlanes(BaseDeDatos.planes);
            } catch (BuscarPlanEx e ) {
                throw new RuntimeException(e.toString());
            } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
                throw new RuntimeException(e.toString());
            }
        }

        if(verb.equals("DELETE")){
                try {
                    String parametros = null;
                    List<Plan> planes = new ArrayList<>();
                    for(String aux : params.values()){
                        parametros = parametros + " " + aux;
                    }
                    planes = buscarPlanes(parametros);
                    BorrarPlanes borrarPlanes = new BorrarPlanesImpl();
                    borrarPlanes.borrar(planes);
                    BuscarEImprimirPlanesImpl.imprimirPlanes(BaseDeDatos.planes);
                } catch (BuscarPlanEx | BorrarPlanEx e) {
                    throw new RuntimeException(e);
                }
        }

        if(verb.equals("PUT")) {
            System.out.println("PUT");
            ModificarPlan modificarPlan = new ModificarPlanImpl();
            try {
                if (body.startsWith("[")) {
                    List<Plan> plan = new ArrayList<Plan>();
                    plan = Arrays.asList(mapper.readValue(body, Plan[].class));
                    for (Plan planAux : plan) {
                        if (modificarPlan.modificar(planAux)) {
                            System.out.println("Plan " + planAux.getAnio() + " modificado");
                            BuscarEImprimirPlanesImpl.imprimirPlanes(BaseDeDatos.planes);
                        }
                    }
                } else {
                    Plan plan = mapper.readValue(body, Plan.class);
                    modificarPlan.modificar(plan);
                    BuscarEImprimirPlanesImpl.imprimirPlanes(BaseDeDatos.planes);
                }
                //Podria usar el modificar planes, mando todo, pero para ir viendo...
            } catch (ModificarPlanEx e) {
                throw new RuntimeException(e);
            }
        }

            if (verb.equals("POST")) {
                System.out.println("POST");

                CrearPlan crearPlan = new CrearPlanImpl();
                try {

                    if (body.startsWith("[")) {
                        List<Plan> plan = new ArrayList<Plan>();
                        plan = Arrays.asList(mapper.readValue(body, Plan[].class));
                        for (Plan planAux : plan) {
                            crearPlan.crear(planAux);
                            body = "{ \"plan\": " + mapper.writeValueAsString(plan) + "}";
                            BuscarEImprimirPlanesImpl.imprimirPlanes(BaseDeDatos.planes);
                        } //Podria usar el crear planes, mando todo, pero para ir viendo...
                    } else {
                        Plan plan = mapper.readValue(body, Plan.class);
                        crearPlan.crear(plan);
                        body = "{ \"plan\": " + mapper.writeValueAsString(plan) + "}";
                        BuscarEImprimirPlanesImpl.imprimirPlanes(BaseDeDatos.planes);
                    }

                } catch (CrearPlanEx e) {
                    throw new RuntimeException(e.toString());
                } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
                    System.out.println(e.toString());
                    throw new RuntimeException(e.toString());
                }

            }

        executeResponse(exchange, params, body);

    }// end handle()

    private void executeResponse(HttpExchange exchange, Map<String, String> params, String body) throws IOException {

        if (!exchange.getRequestHeaders().containsKey("Content-Type") || exchange.getRequestHeaders().get("Content-Type").equals("application/json")) {
            String msg = "415 Unsupported Media Type: Usted debe enviar un header Content-Type = application/json";
            exchange.sendResponseHeaders(415, msg.length());
            OutputStream os = exchange.getResponseBody();
            os.write(msg.getBytes());
            os.close();
        }

        String msg = """
                %s 
                """;

        msg = String.format(msg, body);
        byte[] jsonByteArray = msg.getBytes("UTF-8");
        exchange.sendResponseHeaders(200, jsonByteArray.length);
        OutputStream os = exchange.getResponseBody();
        os.write(msg.getBytes());
        os.close();

    }

    private String readBody(HttpExchange exchange) throws IOException {
        InputStream inputStream = exchange.getRequestBody();
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    // https://static.semrush.com/blog/uploads/media/00/6e/006eebc38b54220916caecfc80fed202/Guide-to-URL-Parameters-2.png
    private Map<String, String> readParamsQuery(String path) throws IOException {

        Map<String, String> params = new HashMap<>();

        String[] tmp = path.split("\\?");
        if (tmp.length > 1) {
            String[] paramsStrings = tmp[1].split("&");
            for (String paramString : paramsStrings) {
                String[] keyValue = paramString.split("=");
                params.put(keyValue[0], keyValue[1]);
            }

        }

        return params;
    }

    private List<Plan> buscarPlanes(String key) throws BuscarPlanEx {
        BuscarPlanes buscador = (BuscarPlanes) new BuscarPlanesImpl();
        return buscador.buscar(key);
    }

} // end class
