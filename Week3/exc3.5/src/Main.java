import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        if(!Desktop.isDesktopSupported()){
            System.out.println("operations are not supported");
            return;
        }
        Desktop desktop=Desktop.getDesktop();
        File file=new File("download.html");
        try {
            desktop.open(file);
            URI uri = URI.create("mailto:mstojkovski5@gmail.com?subject=TestSubject&body=Body%20Example");
            desktop.mail(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}