import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AlternatePrinting {
    private static final int MAX_NUMBER = 100;
    private static int count = 1;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> oddPrinter = CompletableFuture.runAsync(() -> {
            while (count <= MAX_NUMBER) {
                synchronized (AlternatePrinting.class) {
                    if (count % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " " + count);
                        count++;
                    }
                }
            }
        });

        CompletableFuture<Void> evenPrinter = CompletableFuture.runAsync(() -> {
            while (count <= MAX_NUMBER) {
                synchronized (AlternatePrinting.class) {
                    if (count % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " " + count);
                        count++;
                    }
                }
            }
        });

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(oddPrinter, evenPrinter);
        combinedFuture.get();  // Wait for both threads to complete
    }
}

