package api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HTMLHandler implements HttpHandler {

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
        System.out.println("\tVERB: " + verb);
        System.out.println("\tPATH: " + path);
        System.out.println("\tPARAMETERS COUNT: " + params.size());
        // System.out.println("\tURI: " + uri);
        System.out.println("\tBODY:\n" + body);
        System.out.println("------------------------------------------------------");

        // ---

        executeResponse(exchange, params, body);

    } // end handle()

    private void executeResponse(HttpExchange exchange, Map<String, String> params,  String body) throws IOException {
        
        String msg = """


                <!DOCTYPE html>
                <html>
                    <head>
                        <style>
                            h1 {
                                color: blue;
                                font-family: verdana;
                                font-size: 300%;
                            }
                            p  {
                                color: red;
                                font-family: courier;
                                font-size: 160%;
                            }
                        </style>
                    </head>
                    <body>
                        <h1>This is a heading</h1>
                        <p>This is a paragraph.</p>
                    </body>
                </html>


                    """;
        ;
        exchange.sendResponseHeaders(200, msg.length());
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

} // end class 
