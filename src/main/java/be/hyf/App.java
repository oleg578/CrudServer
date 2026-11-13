package be.hyf;
import java.io.IOException;
import be.hyf.handlers.FaviconHandler;
import be.hyf.handlers.FilmHandler;
import be.hyf.handlers.LanguageHandler;
import be.hyf.handlers.RootHandler;
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
                    .start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
