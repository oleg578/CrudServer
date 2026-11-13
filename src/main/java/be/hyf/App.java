package be.hyf;

import java.io.IOException;

import be.hyf.handlers.*;
import be.hyf.srv.ApiServer;
import be.hyf.srv.AutumnApplication;

public class App {

    public static void main(String[] args) throws IOException {
        // start spring application on port 8080
        try {
            AutumnApplication.create(ApiServer.class)
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
