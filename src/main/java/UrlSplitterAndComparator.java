import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class UrlSplitterAndComparator {

    public static void main(String... args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter URL: ");
        final String exampleUrl = reader.readLine();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = new URL(exampleUrl);
                    long startTime = System.nanoTime();
                    for (int retriesCount = 0; retriesCount <= 10000; retriesCount++) {
                        if (retriesCount == 10000) {
                            long endTime = System.nanoTime();
                            System.out.println(url.getProtocol());
                            System.out.println(url.getHost());
                            System.out.println(url.getPort());
                            System.out.println(url.getPath());
                            System.out.println("Splitted by URL: " + (endTime - startTime) / 1000 + "ms");
                        }
                    }
                } catch (MalformedURLException murle) {
                    murle.getCause().printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    /**
                     * Regex pattern to match any non-word character
                     */
                    Pattern pattern = Pattern.compile("\\W+");
                    long startTime = System.nanoTime();
                    for (int retriesCount = 0; retriesCount <= 10000; retriesCount++) {
                        String[] splittedUrl = pattern.split(exampleUrl);
                        if (retriesCount == 10000) {
                            for (String str : splittedUrl) {
                                System.out.println(str);
                            }
                            long endTime = System.nanoTime();
                            System.out.println("Splitted by Regex: " + (endTime - startTime) / 1000 + "ms");
                        }
                    }
                } catch (Exception e) {
                    e.getCause().printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            public void run() {

            }
        });

        t1.start();

        t2.start();
    }
}
