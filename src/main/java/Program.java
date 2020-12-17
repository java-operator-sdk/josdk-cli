import picocli.CommandLine;

public class Program implements Runnable {
    public void run() {
            System.out.println("hello world");
    }

    public static void main(String[] args) {
        System.exit(new CommandLine(new Program()).execute(args));
    }
}
