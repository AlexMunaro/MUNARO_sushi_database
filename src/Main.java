import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database db;
        try {
            db = new Database();
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("\n===== MENU SUSHI DATABASE =====");
            System.out.println("1. Visualizza tutti i piatti");
            System.out.println("2. Inserisci nuovo piatto");
            System.out.println("3. Modifica piatto");
            System.out.println("4. Elimina piatto");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");
            scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1 -> System.out.println(db.selectAll());
                case 2 -> {
                    System.out.print("Nome piatto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Prezzo: ");
                    float prezzo = scanner.nextFloat();
                    System.out.print("Quantità: ");
                    int quantita = scanner.nextInt();
                    scanner.nextLine();
                    if (db.insert(nome, prezzo, quantita))
                        System.out.println("Piatto inserito con successo!");
                }
                case 3 -> {
                    System.out.print("ID piatto da modificare: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nuovo nome: ");
                    String nuovoNome = scanner.nextLine();
                    System.out.print("Nuovo prezzo: ");
                    float nuovoPrezzo = scanner.nextFloat();
                    System.out.print("Nuova quantità: ");
                    int nuovaQuantita = scanner.nextInt();
                    scanner.nextLine();
                    if (db.update(id, nuovoNome, nuovoPrezzo, nuovaQuantita))
                        System.out.println("Piatto aggiornato con successo!");
                    else
                        System.out.println("Nessun piatto aggiornato!");
                }
                case 4 -> {
                    System.out.print("ID piatto da eliminare: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (db.delete(id))
                        System.out.println("Piatto eliminato con successo!");
                    else
                        System.out.println("Nessun piatto eliminato!");
                }
                case 0 -> System.out.println("Uscita dal programma...");
                default -> System.out.println("Scelta non valida!");
            }
        } while (scelta != 0);

        scanner.close();
    }
}
