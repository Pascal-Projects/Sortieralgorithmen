import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int length;
        int durchlaeufe;
        boolean is = false;
        boolean ss = false;
        boolean bs = false;
        Sortieren sortieren = new Sortieren();
        System.out.println("Wie lange soll jedes Array sein?");
        length = new java.util.Scanner(System.in).nextInt();
        System.out.println("Wie viele Arrays sollen sortiert werden?");
        durchlaeufe = new java.util.Scanner(System.in).nextInt();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Soll Insertion Sort ausgeführt werden? (y/n)");
        if (scanner.nextLine().equals("y")) {
            is = true;
        }
        System.out.println("Soll Selection Sort ausgeführt werden? (y/n)");
        if (scanner.nextLine().equals("y")) {
            ss = true;
        }
        System.out.println("Soll Bubble Sort ausgeführt werden? (y/n)");
        if (scanner.nextLine().equals("y")) {
            bs = true;
        }
        if (!is && !ss && !bs) {
            System.out.println("Es muss mindestens ein Sortierverfahren ausgewählt werden!");
            System.exit(0);
        }

        for (int i = 0; i < durchlaeufe; i++) {
            sortieren.sortieren(length, is, ss, bs);
        }
        sortieren.ergebnis(length, durchlaeufe, is, ss, bs);
    }
}