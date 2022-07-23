package api;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;

import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import com.sun.net.httpserver.HttpServer;
import main.App;

// https://docs.oracle.com/en/java/javase/17/docs/api/jdk.httpserver/com/sun/net/httpserver/package-summary.html

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println();

        System.out.println("Empezando... cargo bd");
        boolean first = true;
        if(first){
            App.main(args);
            first = false;
        }


        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            server.createContext("/api", new APIHandler()); // http://localhost:8080/api
            server.createContext("/web", new HTMLHandler()); // http://localhost:8080/web

            server.setExecutor(null); // creates a default executor
            server.start();

            System.out.print("Servidor escuchando en ");
            System.out.print(server.getAddress().getHostName().equals("0:0:0:0:0:0:0:0") ? "localhost" : server.getAddress().getHostString());
            System.out.print(":");
            System.out.print(server.getAddress().getPort());
            System.out.println();


            //ejemploCliente2();

        } catch (IOException e) {
            System.err.println(e);
            System.err.println(e.getMessage());
            e.printStackTrace();
        } /*catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

    }

    private static void ejemploCliente() throws URISyntaxException, IOException, InterruptedException {

        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************************** EJEMPLO X ****************************");
        System.out.println("*******************************************************************");

        String url = "http://localhost:8080/api/planes/1?nombre=diego&apellido=Mansilla";

        String jsonString = """
                {
                    \"id\": 100,
                    \"title\": \"123 MM\",
                    \"body\": \"456 ejemplocliente SERVER MMM\",
                    \"userId\": 1
                }
                    """;

        byte[] jsonByteArray = jsonString.getBytes();

        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(new URI(url))
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "application/json")
                .header("charset", "UTF-8")
                .timeout(Duration.of(30, ChronoUnit.SECONDS))
                .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteArray))
                .build();

        // ---

        HttpResponse<String> respuesta = HttpClient.newBuilder().proxy(ProxySelector.getDefault())
                .build()
                .send(peticion, BodyHandlers.ofString());

        // ---

        int codigoHttpRespuesta = respuesta.statusCode();

        System.out.println("Status Code: " + codigoHttpRespuesta);

        String cuerpo = respuesta.body();

        if (codigoHttpRespuesta == 200) {
            System.out.println(cuerpo);
        } else {
            System.err.println("ERROR:");
            System.err.println(cuerpo);
        }

    } // end ejemplo cliente

    private static void ejemploCliente2() throws URISyntaxException, IOException, InterruptedException {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************************** EJEMPLO 2 ****************************");
        System.out.println("*******************************************************************");

        String url = "http://localhost:8080/api/planes/2001";

        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(new URI(url))
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "application/json")
                .header("charset", "UTF-8")
                .timeout(Duration.of(30, ChronoUnit.SECONDS))
                .GET()
                .build();

        // ---

        System.out.println("dsp peticion");

        HttpResponse<String> respuesta = HttpClient.newBuilder().proxy(ProxySelector.getDefault())
                .build()
                .send(peticion, BodyHandlers.ofString());

        // ---

        System.out.println("dsp respuesta");

        int codigoHttpRespuesta = respuesta.statusCode();

        System.out.println("Status Code: " + codigoHttpRespuesta);

        String cuerpo = respuesta.body();

        if (codigoHttpRespuesta == 200) {
            System.out.println(cuerpo);
        } else {
            System.err.println("ERROR:");
            System.err.println(cuerpo);
        }
    } // end class
}
