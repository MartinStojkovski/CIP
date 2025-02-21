import java.io.*;
import java.net.URI;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        URI uri1= URI.create("https://uacs.edu.mk/home/home/");

        try {
            URL url = uri1.toURL();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("download.html"));

            String readLine;
            while((readLine=bufferedReader.readLine())!=null){
                bufferedWriter.write(readLine);
                bufferedWriter.newLine();
            }
            System.out.println("The whole file is downloaded.");
        } catch(IOException a){
            a.getMessage();

        }
    }
}