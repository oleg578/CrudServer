package be.hyf;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import be.hyf.handlers.FaviconHandler;
import be.hyf.handlers.FilmHandler;
import be.hyf.handlers.LanguageHandler;
import be.hyf.handlers.RootHandler;
import com.sun.net.httpserver.HttpServer;

public class App {

    public static void main(String[] args) throws IOException {
        // start server on 8080
        //https://docs.oracle.com/en/java/javase/21/docs/api/jdk.httpserver/com/sun/net/httpserver/HttpServer.html
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Context Routing
        //https://docs.oracle.com/en/java/javase/21/docs/api/jdk.httpserver/com/sun/net/httpserver/HttpServer.html#create(java.net.InetSocketAddress,int,java.lang.String,com.sun.net.httpserver.HttpHandler,com.sun.net.httpserver.Filter...)
        server.createContext("/favicon.ico", new FaviconHandler());
        server.createContext("/", new RootHandler());
        server.createContext("/language", new LanguageHandler());
        server.createContext("/film", new FilmHandler());
        // use virtual threads
        //https://docs.oracle.com/en/java/javase/21/docs/api/jdk.httpserver/com/sun/net/httpserver/HttpServer.html#setExecutor(java.util.concurrent.Executor)
        server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        server.start();
        System.out.println("Server started - http://localhost:8080");
    }
}
