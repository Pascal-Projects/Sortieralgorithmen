import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;


public class Sortieren {
    /**
     * Gibt an, ob die sortierten Arrays ausgegeben werden sollen
     */
    private boolean showArrays = false;

    /**
     * Gibt an, ob die unsortierten Arrays ausgegeben werden sollen
     */
    private boolean showStartArray = false;

    /**
     * Gibt an, ob jede Änderung ausgegeben werden soll
     */
    private boolean showEveryChange = false;

    /**
     * Gibt an, ob die Zeit ausgegeben werden soll
     */
    private boolean showTime = false;

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

    public Sortieren() {
        load();
    }

    public void load() {
        Properties properties = new Properties();
        java.net.URL url = ClassLoader.getSystemResource("config.properties");

        try  {
            properties.load(url.openStream());
            showArrays = Boolean.parseBoolean(properties.getProperty("showArrays"));
            showStartArray = Boolean.parseBoolean(properties.getProperty("showStartArray"));
            showEveryChange = Boolean.parseBoolean(properties.getProperty("showEveryChange"));
            showTime = Boolean.parseBoolean(properties.getProperty("showTime"));
        } catch (FileNotFoundException fie) {
            System.out.println("Konfigurationsdatei nicht gefunden!");
        }
        catch (IOException e) {
            System.out.println("Fehler beim Laden der Konfigurationsdatei!");
        }
    }

    public void sortieren(int pLength, boolean pis, boolean pss, boolean pbs) {
        liste = new int[pLength];
        generateNumbers();

        if (pis) {
            insertionSort();
        }
        if (pss) {
            selectionSort();
        }
        if (pbs) {
            bubbleSort();
        }
        resetCounter();
    }

    public void ergebnis(int pLength, int pDurchlaeufe, boolean pis, boolean pss, boolean pbs) {
        System.out.println("Listen: " + pDurchlaeufe);
        System.out.println("Länge: " + pLength + "\n");
        if (pis) {
            System.out.println("Insertion Sort:\n");
            System.out.println("Durchschnittswerte:");
            System.out.println("Durchläufe: " + iCounterAverage / pDurchlaeufe);
            System.out.println("Änderungen: " + iChangeCounterAverage / pDurchlaeufe);
            System.out.println("Zeit: " + iZeitAverage / 1000 / pDurchlaeufe + "s\n");
            System.out.println("Maximum/Minimum:");
            System.out.println("Durchläufe: " + iCounterMax + "/" + iCounterMin);
            System.out.println("Änderungen: " + iChangeCounterMax + "/" + iChangeCounterMin);
            System.out.println("Zeit: " + iZeitMax / 1000 + "/" + iZeitMin / 1000 + "s\n\n");
        }
        if (pss) {
            System.out.println("Selection Sort:\n");
            System.out.println("Durchschnittswerte:");
            System.out.println("Durchläufe: " + sCounterAverage / pDurchlaeufe);
            System.out.println("Änderungen: " + sChangeCounterAverage / pDurchlaeufe);
            System.out.println("Zeit: " + sZeitAverage / pDurchlaeufe / 1000000 + "ms\n");
            System.out.println("Maximum/Minimum:");
            System.out.println("Durchläufe: " + sCounterMax + "/" + sCounterMin);
            System.out.println("Änderungen: " + sChangeCounterMax + "/" + sChangeCounterMin);
            System.out.println("Zeit: " + sZeitMax / 1000000 + "/" + sZeitMin / 1000000 + "ms\n\n");
        }
        if (pbs) {
            System.out.println("Bubble Sort:\n");
            System.out.println("Durchschnittswerte:");
            System.out.println("Durchläufe: " + bCounterAverage / pDurchlaeufe);
            System.out.println("Änderungen: " + bChangeCounterAverage / pDurchlaeufe);
            System.out.println("Zeit: " + bZeitAverage / pDurchlaeufe / 1000000 + "ms\n");
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

    public void printTime(long pStart, long pEnd, long pFaktor) {
        if (showTime) {
            System.out.println("Ende: " + (double) ((pEnd - pStart) / pFaktor) + "ms");
        }
    }

    /**
     * Sortiert alle Zahlen per InsertionSort
     */
    public void insertionSort() {
        int[] array = liste.clone();
        long start = System.currentTimeMillis();
        int zahl1;
        for (int i = 1; i < array.length; i++) {
            zahl1 = array[i];
            for (int j = 0; j < i; j++) {
                if (zahl1 < array[j]) {
                    array[i] = array[j];
                    array[j] = zahl1;
                    i = 1;
                    iChangeCounter++;
                    if (showEveryChange) {
                        printArray(array);
                    }
                }
            }
            iCounter++;
        }
        long end = System.currentTimeMillis();
        printTime(start, end, 1);
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
            printArray(array);
        }
    }

    /**
     * Sortiert alle Zahlen per SelectionSort
     */
    public void selectionSort() {
        int[] array = liste.clone();
        long start = System.nanoTime();
        for (int j = array.length - 1; j >= 0; j--) {
            int zahl = -1;
            int indexMax = -1;
            for (int i = 0; i <= j; i++) {
                if (array[i] > zahl) {
                    zahl = array[i];
                    indexMax = i;
                    sChangeCounter++;
                    if (showEveryChange) {
                        printArray(array);
                    }
                    sCounter++;
                }
            }
            array[indexMax] = array[j];
            array[j] = zahl;
        }
        long end = System.nanoTime();
        printTime(start, end, 1000000);
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
            printArray(array);
        }
    }

    /**
     * Sortiert alle Zahlen per BubbleSort
     */
    public void bubbleSort() {
        int[] array = liste.clone();
        long start = System.nanoTime();
        for (int i = 0; i < array.length - 1; i++) {
            int zahl = array[0];
            for (int j = 1; j < array.length; j++) {
                bCounter++;
                if (array[j] < zahl) {
                    array[j - 1] = array[j];
                    array[j] = zahl;
                    bChangeCounter++;
                    if (showEveryChange) {
                        printArray(array);
                    }
                }
                zahl = array[j];
            }
        }
        long end = System.nanoTime();
        printTime(start, end, 1000000);
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
            printArray(array);
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
