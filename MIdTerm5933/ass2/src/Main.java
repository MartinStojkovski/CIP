import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

    // Use CopyOnWriteArrayList to ensure thread-safe concurrent modifications and accesses
    private static final List<String> resultFiles = new CopyOnWriteArrayList<>();

    public static class KeywordFinderTask implements Runnable {

        private final String keyword;
        private final File file;

        public KeywordFinderTask(String keyword, File file) {
            this.keyword = keyword;
            this.file = file;
        }

        @Override
        public void run() {
            try {
                if (searchKeywordInFile()) {
                    resultFiles.add(file.getAbsolutePath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private boolean searchKeywordInFile() throws IOException {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(keyword)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {

        String keyword = "lol";
        List<File> files = new ArrayList<>();
        Path path1=Paths.get("text1.txt");
        Path path2=Paths.get("text2.txt");
        Path path3= Paths.get("text3.txt");

        files.add(new File(String.valueOf(path1)));
        files.add(new File(String.valueOf(path2)));
        files.add(new File(String.valueOf(path3)));

        List<Thread> threads = new ArrayList<>();
        System.out.println("java ConcurrentKeywordFinder "+keyword+" text1.txt text2.txt text3.txt");

        for (File file : files) {
            KeywordFinderTask task = new KeywordFinderTask(keyword, file);
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }


        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (resultFiles.isEmpty()) {
            System.out.println("No files found containing the keyword.");
        } else {
            System.out.println("Files containing the keyword '" + keyword + "':");
            for (String filePath : resultFiles) {
                System.out.println(filePath);
            }
        }
    }
}
