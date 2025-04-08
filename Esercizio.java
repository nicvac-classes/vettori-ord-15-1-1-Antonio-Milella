//LEGGERE LE ISTRUZIONI NEL FILE README.md

//Import di Classi Java necessarie al funzionamento del programma
import java.util.Scanner;
import java.util.Random;

// Classe principale, con metodo main
class Esercizio {

    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int n, i;

        System.out.println("Inserire il numero di automobili");
        n = Integer.parseInt( input.nextLine() );

        String[] nomi = new String[n];
        int[] posizioni = new int[n];

        // Caricamento dei due vettori
        for (i = 0; i <= n - 1; i++) {
            System.out.println("Nome " + (i + 1) + "° automobile");
            nomi[i] = input.nextLine();
        }
        impostaVettoreRandom(posizioni);
        visualizzaVettori(nomi, posizioni);

        // Ordinamento dei vettori paralleli. Quando ordino Posizioni, ordino gli elementi corrispondenti di Nomi.
        bubbleSort(nomi, posizioni);

        // Visualizzo i valori ordinati.
        System.out.println("🏁🏁🏁🏁🏁 Posizione di arrivo 🏁🏁🏁🏁🏁");
        visualizzaVettori(nomi, posizioni);
        
        //Chiedo la posizione, visualizzo l'auto corrispondente.
        int posizione;

        System.out.println("Inserire la posizione desiderata: ");
        posizione = Integer.parseInt( input.nextLine() );

        System.out.println("Ricerca sequenziale 🐌 🐌 🐌");
        i = ricercaSequenziale(posizioni, posizione);
        System.out.println("Auto in posizione " + posizione + ": " + nomi[i]);

        System.out.println("Ricerca binaria 🏎️ 🏎️ 🏎️");
        i = ricercaBinaria(posizioni, posizione);
        System.out.println("Auto in posizione " + posizione + ": " + nomi[i]);
    }
    
    public static void bubbleSort(String[] nomi, int[] posizioni) {
        String ts;
        int t;
        boolean scambio;
        int i, j;
        int n = nomi.length;

        scambio = true;
        i = 0;
        while (i <= n - 1 && scambio) {
            scambio = false;
            j = 0;
            while (j <= n - 2 - i) {
                if (posizioni[j] > posizioni[j + 1]) {
                    scambio = true;

                    // Prima ordino Posizioni
                    t = posizioni[j];
                    posizioni[j] = posizioni[j + 1];
                    posizioni[j + 1] = t;

                    // Per mantenere la corrispondenza fra nomi e posizioni, devo scambiare anche le celle dei nomi. In questo modo i vettori paralleli rimangono coerenti.
                    ts = nomi[j];
                    nomi[j] = nomi[j + 1];
                    nomi[j + 1] = ts;
                }
                j = j + 1;
            }
            i = i + 1;
        }
    }
    
    public static void impostaVettoreRandom(int[] posizioni) {
        int i;
        int n = posizioni.length;

        for (i = 0; i <= n - 1; i++) {
            posizioni[i] = 1 + random.nextInt(n);
        }
    }
    
    public static int ricercaBinaria(int[] v, int valore) {
        int inizio, fine, medio, indice, numControlli;
        int n = v.length;

        numControlli = 0;
        indice = -1;
        inizio = 0;
        fine = n - 1;
        while (inizio <= fine && indice == -1) {
            medio = inizio + (fine - inizio) / 2;
            numControlli = numControlli + 1;
            if (valore == v[medio]) {
                indice = medio;
            } else {
                numControlli = numControlli + 1;
                if (valore > v[medio]) {
                    inizio = medio + 1;
                } else {
                    fine = medio - 1;
                }
            }
        }
        System.out.println("Controlli eseguiti: " + numControlli);
        
        return indice;
    }
    
    public static int ricercaSequenziale(int[] v, int valore) {
        int i, indice, numControlli;
        int n = v.length;

        numControlli = 0;
        indice = -1;
        i = 0;
        while (i <= n - 1 && indice == -1) {
            numControlli = numControlli + 1;
            if (valore == v[i]) {
                indice = i;
            }
            i = i + 1;
        }
        System.out.println("Controlli eseguiti: " + numControlli);
        
        return indice;
    }
    
    public static void visualizzaVettori(String[] nomi, int[] posizioni) {
        int i;
        int n = nomi.length;

        for (i = 0; i <= n - 1; i++) {
            System.out.println("Auto " + nomi[i] + "; Posizione: " + posizioni[i]);
        }
    }
}