import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
//
public class Main {
    public static void main(String[] args) {
        URI uri1= URI.create("https://uacs.edu.mk/home/home/");

        try {
            URL url = uri1.toURL();
            InputStream inputstream=new BufferedInputStream(url.openStream());
            FileOutputStream FileOutputStream=new FileOutputStream("download2.html");
            byte[] bufferdata=new byte[1024];
            int readbytes;

            while((readbytes = inputstream.read(bufferdata,0,1024))!=-1){
                FileOutputStream.write(bufferdata,0,readbytes);
            }
            System.out.println("The content from url was successfully saved to file");
        } catch(IOException a){
            a.getMessage();

        }
    }
}