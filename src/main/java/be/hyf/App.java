package be.hyf;
import java.io.IOException;

import be.hyf.handlers.*;
import be.hyf.srv.SpringApplication;
import be.hyf.srv.CrudServer;

public class App {

    public static void main(String[] args) throws IOException {
        // start spring application on port 8080
        try {
            CrudServer srv = SpringApplication.create(CrudServer.class);
            srv
                    .init(8080)
                    .addHandler("/", new RootHandler())
                    .addHandler("/favicon.ico", new FaviconHandler())
                    .addHandler("/language", new LanguageHandler())
                    .addHandler("/film", new FilmHandler())
                    .addHandler("/headerslist", new HeadersListHandler())
                    .start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
