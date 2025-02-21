import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        URI uri= URI.create("https://uacs.edu.mk/home/home/");
        try {
            URL url = uri.toURL();
            System.out.println(url.getAuthority());
            System.out.println(url.getDefaultPort());
            System.out.println(url.getPath());
            System.out.println(url.getFile());
            System.out.println(url.getQuery());
            System.out.println(url.getPort());
            System.out.println(url);
        } catch (MalformedURLException a){
            System.out.println(a.getMessage());
        }
    }
}