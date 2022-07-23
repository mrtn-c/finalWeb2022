package api;

import java.io.IOException;
import java.io.InputStream;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

// https://www.baeldung.com/java-9-http-client
// https://developer.mozilla.org/es/docs/Web/HTTP/Overview
// https://docs.google.com/document/d/1D94WeD3bfdzD4zM6Aqbs_lBdSKBV0UMuFUiLxWCh8w4/edit#


// https://jsonplaceholder.typicode.com/
// https://openweathermap.org/
// https://www.abstractapi.com/user-avatar-api
// https://ui-avatars.com/
// https://joeschmoe.io/

public class Client {

    public static void main(String[] args) {

        System.out.println("Inicio");

        try {

            

          //  System.exit(0);

            ejemplo();
            //ejemplo1();
            //ejemplo2();
            //ejemplo3();
            //ejemplo4();
            //ejemplo5();
            //ejemplo6();
            //ejemplo7();
            //ejemplo8();
            //ejemplo9();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Fin");

    }

    

    private static void ejemplo() throws URISyntaxException, IOException, InterruptedException {

        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************************** EJEMPLO ******************************");
        System.out.println("*******************************************************************");

        String url = "https://es.wikipedia.org/wiki/Argentina";

        // ---

        URI uri = new URI(url);

        HttpRequest.Builder builder = HttpRequest.newBuilder(uri);
        builder.version(HttpClient.Version.HTTP_2);
        builder.header("Content-Type", "application/html");
        builder.header("charset", "UTF-8");
        builder.timeout(Duration.of(30, ChronoUnit.SECONDS));
        builder.GET();

        HttpRequest peticion = builder.build();

        /*
         * HttpRequest peticion = HttpRequest.newBuilder()
         * .uri(new URI(url))
         * .version(HttpClient.Version.HTTP_2)
         * .header("Content-Type", "application/json")
         * .header("charset", "UTF-8")
         * .timeout(Duration.of(10, ChronoUnit.SECONDS))
         * .GET()
         * .build();
         */

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
            Files.write(Paths.get(new URI("file:/Users/dmansilla/Desktop/arg.html")), cuerpo.getBytes());
        } else {
            System.err.println("ERROR:");
            System.err.println(cuerpo);
        }

    } // end ejemplo

    private static void ejemplo1() throws URISyntaxException, IOException, InterruptedException {

        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************************** EJEMPLO 1 ****************************");
        System.out.println("*******************************************************************");

        String url = "https://jsonplaceholder.typicode.com/todos/1";

        // ---

        URI uri = new URI(url);

        HttpRequest.Builder builder = HttpRequest.newBuilder(uri);
        builder.version(HttpClient.Version.HTTP_2);
        builder.header("Content-Type", "application/json");
        builder.header("charset", "UTF-8");
        builder.timeout(Duration.of(30, ChronoUnit.SECONDS));
        builder.GET();

        HttpRequest peticion = builder.build();

        /*
         * HttpRequest peticion = HttpRequest.newBuilder()
         * .uri(new URI(url))
         * .version(HttpClient.Version.HTTP_2)
         * .header("Content-Type", "application/json")
         * .header("charset", "UTF-8")
         * .timeout(Duration.of(10, ChronoUnit.SECONDS))
         * .GET()
         * .build();
         */

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

    } // end ejemplo1

    private static void ejemplo2() throws URISyntaxException, IOException, InterruptedException {

        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************************** EJEMPLO 2 ****************************");
        System.out.println("*******************************************************************");

        String url = "https://jsonplaceholder.typicode.com/posts";

        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(new URI(url))
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "application/json")
                .header("charset", "UTF-8")
                .timeout(Duration.of(30, ChronoUnit.SECONDS))
                .GET()
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

    } // end ejemplo2

    private static void ejemplo3() throws URISyntaxException, IOException, InterruptedException {

        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************************** EJEMPLO 3 ****************************");
        System.out.println("*******************************************************************");

        String url = "https://jsonplaceholder.typicode.com/posts";

        String jsonString = """
                {
                    \"title\": \"titulo de la publicacion\",
                    \"body\": \"cuerpo de la publicacion\",
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

        if (codigoHttpRespuesta == 201) {
            System.out.println(cuerpo);
        } else {
            System.err.println("ERROR:");
            System.err.println(cuerpo);
        }

    } // end ejemplo3

    private static void ejemplo4() throws URISyntaxException, IOException, InterruptedException {

        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************************** EJEMPLO 4 ****************************");
        System.out.println("*******************************************************************");

        String url = "https://jsonplaceholder.typicode.com/posts/100";

        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(new URI(url))
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "application/json")
                .header("charset", "UTF-8")
                .timeout(Duration.of(30, ChronoUnit.SECONDS))
                .GET()
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

    } // end ejemplo4

    private static void ejemplo5() throws URISyntaxException, IOException, InterruptedException {

        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************************** EJEMPLO 5 ****************************");
        System.out.println("*******************************************************************");

        String url = "https://jsonplaceholder.typicode.com/posts/101";

        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(new URI(url))
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "application/json")
                .header("charset", "UTF-8")
                .timeout(Duration.of(30, ChronoUnit.SECONDS))
                .GET()
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

    } // end ejemplo5

    private static void ejemplo6() throws URISyntaxException, IOException, InterruptedException {

        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************************** EJEMPLO 6 ****************************");
        System.out.println("*******************************************************************");

        String url = "https://jsonplaceholder.typicode.com/posts";

        String jsonString = """
                {
                    \"id\": 100,
                    \"title\": \"titulo de la publicacion MM\",
                    \"body\": \"cuerpo de la publicacion MMM\",
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

        if (codigoHttpRespuesta == 201) {
            System.out.println(cuerpo);
        } else {
            System.err.println("ERROR:");
            System.err.println(cuerpo);
        }

    } // end ejemplo6

    private static void ejemplo7() throws URISyntaxException, IOException, InterruptedException {

        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************************** EJEMPLO 7 ****************************");
        System.out.println("*******************************************************************");

        String url = "https://jsonplaceholder.typicode.com/posts/101";

        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(new URI(url))
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "application/json")
                .header("charset", "UTF-8")
                .timeout(Duration.of(30, ChronoUnit.SECONDS))
                .GET()
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

    } // end ejemplo7

    private static void ejemplo8() throws URISyntaxException, IOException, InterruptedException {

        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************************** EJEMPLO 8 ****************************");
        System.out.println("*******************************************************************");

        String url = "https://jsonplaceholder.typicode.com/posts/101";

        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(new URI(url))
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "application/json")
                .header("charset", "UTF-8")
                .timeout(Duration.of(30, ChronoUnit.SECONDS))
                .DELETE()
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

    } // end ejemplo8

    private static void ejemplo9() throws URISyntaxException, IOException, InterruptedException {

        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************************** EJEMPLO 9 ****************************");
        System.out.println("*******************************************************************");

        String url = "https://ui-avatars.com/api/?name=Elon+Musk";

        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(new URI(url))
                .version(HttpClient.Version.HTTP_2)
                // .header("Content-Type", "application/json")
                // .header("charset", "UTF-8")
                // .timeout(Duration.of(30, ChronoUnit.SECONDS))
                .GET()
                .build();

        // ---

        HttpResponse<InputStream> respuesta = HttpClient.newBuilder().proxy(ProxySelector.getDefault())
                .build()
                .send(peticion, BodyHandlers.ofInputStream());

        // ---

        int codigoHttpRespuesta = respuesta.statusCode();

        System.out.println("Status Code: " + codigoHttpRespuesta);

        InputStream cuerpo = respuesta.body();

        if (codigoHttpRespuesta == 200) {

            System.out.println(cuerpo);

            checkPNG(cuerpo);

            Files.write(Paths.get(new URI("file:/Users/dmansilla/Desktop/avatar.png")), cuerpo.readAllBytes());

        } else {
            System.err.println("ERROR:");
            System.err.println(cuerpo);
        }

    } // end ejemplo9

    private static void checkPNG(InputStream inputStream) throws IOException {
        int[] pngSignature = { 137, 80, 78, 71, 13, 10, 26, 10 }; // ?PNG

        for (int n : pngSignature) {
            System.out.print((char) n);
        }
        System.out.println();

        int[] headerBytes = new int[8];
        boolean isPNG = true;

        for (int i = 0; i < 8; i++) {

            headerBytes[i] = inputStream.read();

            if (headerBytes[i] != pngSignature[i]) {
                isPNG = false;
                break;
            }
        }

        System.out.println("Is PNG file? " + isPNG);

    }

} // end class
