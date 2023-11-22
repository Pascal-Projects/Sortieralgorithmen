import java.util.Random;
import java.util.Scanner;

public class Sortieren {

    /**
     * Gibt an, ob die sortierten Arrays ausgegeben werden sollen
     */
    private final boolean showArrays = false;

    /**
     * Gibt an, ob die unsortierten Arrays ausgegeben werden sollen
     */
    private final boolean showStartArray = false;

    /**
     * Gibt an, ob die Zeit ausgegeben werden soll
     */
    private final boolean showTime = true;

    /**
     * Gibt an, ob jede Änderung ausgegeben werden soll
     */
    private final boolean showEveryChange = false;

    /**
     * Liste mit zufälligen Zahlen
     */
    private int[] liste;

    private int iCounter = 0;
    private int iCounterAverage = 0;
    private int iCounterMax = 0;
    private int iCounterMin = 0;
    private int iChangeCounter = 0;
    private int iChangeCounterAverage = 0;
    private int iChangeCounterMax = 0;
    private int iChangeCounterMin = 0;

    private int iZeit = 0;
    private int iZeitAverage = 0;
    private int iZeitMax = 0;
    private int iZeitMin = 0;

    private int sCounter = 0;
    private int sCounterAverage = 0;
    private int sCounterMax = 0;
    private int sCounterMin = 0;

    private int sChangeCounter = 0;
    private int sChangeCounterAverage = 0;
    private int sChangeCounterMax = 0;
    private int sChangeCounterMin = 0;

    private int sZeit = 0;
    private int sZeitAverage = 0;
    private int sZeitMax = 0;
    private int sZeitMin = 0;

    private int bCounter = 0;
    private int bCounterAverage = 0;
    private int bCounterMax = 0;
    private int bCounterMin = 0;

    private int bChangeCounter = 0;
    private int bChangeCounterAverage = 0;
    private int bChangeCounterMax = 0;
    private int bChangeCounterMin = 0;

    private int bZeit = 0;
    private int bZeitAverage = 0;
    private int bZeitMax = 0;
    private int bZeitMin = 0;

    private boolean bs = false;
    private boolean is = false;
    private boolean ss = false;

    public Sortieren(int length, int durchlaeufe) {
        liste = new int[length];
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
            generateNumbers();
            if (is) {
                insertionSort(liste.clone());
            }
            if (ss) {
                selectionSort(liste.clone());
            }
            if (bs) {
                bubbleSort(liste.clone());
            }
            resetCounter();
        }

        System.out.println("Listen: " + durchlaeufe);
        System.out.println("Länge: " + length + "\n");
        if (is) {
            System.out.println("Insertion Sort:\n");
            System.out.println("Durchschnittswerte:");
            System.out.println("Durchläufe: " + iCounterAverage / durchlaeufe);
            System.out.println("Änderungen: " + iChangeCounterAverage / durchlaeufe);
            System.out.println("Zeit: " + iZeitAverage / 1000 / durchlaeufe + "s\n");
            System.out.println("Maximum/Minimum:");
            System.out.println("Durchläufe: " + iCounterMax + "/" + iCounterMin);
            System.out.println("Änderungen: " + iChangeCounterMax + "/" + iChangeCounterMin);
            System.out.println("Zeit: " + iZeitMax / 1000 + "/" + iZeitMin / 1000 + "s\n\n");
        }
        if (ss) {
            System.out.println("Selection Sort:\n");
            System.out.println("Durchschnittswerte:");
            System.out.println("Durchläufe: " + sCounterAverage / durchlaeufe);
            System.out.println("Änderungen: " + sChangeCounterAverage / durchlaeufe);
            System.out.println("Zeit: " + sZeitAverage / durchlaeufe / 1000000 + "ms\n");
            System.out.println("Maximum/Minimum:");
            System.out.println("Durchläufe: " + sCounterMax + "/" + sCounterMin);
            System.out.println("Änderungen: " + sChangeCounterMax + "/" + sChangeCounterMin);
            System.out.println("Zeit: " + sZeitMax / 1000000 + "/" + sZeitMin / 1000000 + "ms\n\n");
        }
        if (bs) {
            System.out.println("Bubble Sort:\n");
            System.out.println("Durchschnittswerte:");
            System.out.println("Durchläufe: " + bCounterAverage / durchlaeufe);
            System.out.println("Änderungen: " + bChangeCounterAverage / durchlaeufe);
            System.out.println("Zeit: " + bZeitAverage / durchlaeufe / 1000000 + "ms\n");
            System.out.println("Maximum/Minimum:");
            System.out.println("Durchläufe: " + bCounterMax + "/" + bCounterMin);
            System.out.println("Änderungen: " + bChangeCounterMax + "/" + bChangeCounterMin);
            System.out.println("Zeit: " + bZeitMax / 1000000 + "/" + bZeitMin / 1000000 + "ms\n\n\n");
        }
    }

    /**
     * Generiert zufällige Zahlen
     */
    public void generateNumbers() {
        Random random = new Random();
        for (int i = 0; i < liste.length; i++) {
            liste[i] = random.nextInt(liste.length * 10);
        }
        if (showStartArray) {
            printArray(liste);
        }
    }

    /**
     * Setzt alle, einmaligen, Counter zurück
     */
    public void resetCounter() {
        iCounter = 0;
        iChangeCounter = 0;
        iZeit = 0;

        sCounter = 0;
        sChangeCounter = 0;
        sZeit = 0;

        bCounter = 0;
        bChangeCounter = 0;
        bZeit = 0;
    }

    /**
     * Sortiert alle Zahlen per InsertionSort
     */
    public void insertionSort(int[] pArray) {
        long start = System.currentTimeMillis();
        int zahl1;
        for (int i = 1; i < pArray.length; i++) {
            zahl1 = pArray[i];
            for (int j = 0; j < i; j++) {
                if (zahl1 < pArray[j]) {
                    pArray[i] = pArray[j];
                    pArray[j] = zahl1;
                    i = 1;
                    iChangeCounter++;
                    if (showEveryChange) {
                        printArray(pArray);
                    }
                }
            }
            iCounter++;
        }
        long end = System.currentTimeMillis();
        if (showTime) {
            System.out.println("Ende: " + (double) ((end - start) / 1000000) + "ms");
        }
        iCounterAverage += iCounter;
        iChangeCounterAverage += iChangeCounter;
        iZeit = (int) (end - start);
        iZeitAverage += iZeit;
        if (iCounter > iCounterMax) {
            iCounterMax = iCounter;
        }
        if (iCounter < iCounterMin || iCounterMin == 0) {
            iCounterMin = iCounter;
        }
        if (iChangeCounter > iChangeCounterMax) {
            iChangeCounterMax = iChangeCounter;
        }
        if (iChangeCounter < iChangeCounterMin || iChangeCounterMin == 0) {
            iChangeCounterMin = iChangeCounter;
        }
        if (iZeit > iZeitMax) {
            iZeitMax = iZeit;
        }
        if (iZeit < iZeitMin || iZeitMin == 0) {
            iZeitMin = iZeit;
        }
        if (showArrays) {
            printArray(pArray);
        }
    }

    /**
     * Sortiert alle Zahlen per SelectionSort
     */
    public void selectionSort(int[] pArray) {
        long start = System.nanoTime();
        for (int j = pArray.length - 1; j >= 0; j--) {
            int zahl = -1;
            int indexMax = -1;
            for (int i = 0; i <= j; i++) {
                if (pArray[i] > zahl) {
                    zahl = pArray[i];
                    indexMax = i;
                    sChangeCounter++;
                    if (showEveryChange) {
                        printArray(pArray);
                    }
                    sCounter++;
                }
            }
            pArray[indexMax] = pArray[j];
            pArray[j] = zahl;
        }
        long end = System.nanoTime();
        if (showTime) {
            System.out.println("Ende: " + (double) ((end - start) / 1000000) + "ms");
        }
        sChangeCounterAverage += sChangeCounter;
        sCounterAverage += sCounter;
        sZeit = (int) (end - start);
        sZeitAverage += sZeit;
        if (sCounter > sCounterMax) {
            sCounterMax = sCounter;
        }
        if (sCounter < sCounterMin || sCounterMin == 0) {
            sCounterMin = sCounter;
        }
        if (sChangeCounter > sChangeCounterMax) {
            sChangeCounterMax = sChangeCounter;
        }
        if (sChangeCounter < sChangeCounterMin || sChangeCounterMin == 0) {
            sChangeCounterMin = sChangeCounter;
        }
        if (sZeit > sZeitMax) {
            sZeitMax = sZeit;
        }
        if (sZeit < sZeitMin || sZeitMin == 0) {
            sZeitMin = sZeit;
        }
        if (showArrays) {
            printArray(pArray);
        }
    }

    /**
     * Sortiert alle Zahlen per BubbleSort
     */
    public void bubbleSort(int[] pArray) {
        long start = System.nanoTime();
        for (int i = 0; i < pArray.length - 1; i++) {
            int zahl = pArray[0];
            for (int j = 1; j < pArray.length; j++) {
                bCounter++;
                if (pArray[j] < zahl) {
                    pArray[j - 1] = pArray[j];
                    pArray[j] = zahl;
                    bChangeCounter++;
                    if (showEveryChange) {
                        printArray(pArray);
                    }
                }
                zahl = pArray[j];
            }
        }
        long end = System.nanoTime();
        if (showTime) {
            System.out.println("Ende: " + (double) ((end - start) / 1000000) + "ms");
        }
        bChangeCounterAverage += bChangeCounter;
        bCounterAverage += bCounter;
        bZeit = (int) (end - start);
        bZeitAverage += bZeit;
        if (bCounter > bCounterMax) {
            bCounterMax = bCounter;
        }
        if (bCounter < bCounterMin || bCounterMin == 0) {
            bCounterMin = bCounter;
        }
        if (bChangeCounter > bChangeCounterMax) {
            bChangeCounterMax = bChangeCounter;
        }
        if (bChangeCounter < bChangeCounterMin || bChangeCounterMin == 0) {
            bChangeCounterMin = bChangeCounter;
        }
        if (bZeit > bZeitMax) {
            bZeitMax = bZeit;
        }
        if (bZeit < bZeitMin || bZeitMin == 0) {
            bZeitMin = bZeit;
        }
        if (showArrays) {
            printArray(pArray);
        }
    }

    /**
     * Gibt ein Array aus
     *
     * @param pArray Array das ausgegeben werden soll
     */
    public void printArray(int[] pArray) {
        System.out.println("Array:");
        for (int i : pArray) {
            System.out.println(i);
        }
        System.out.println("\n\n");
    }
}
