package mongoapp;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.util.Scanner;
import static com.mongodb.client.model.Sorts.ascending;

public class Joc_de_cartes {

    static MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    static MongoDatabase database = mongoClient.getDatabase("Mustapha");
    static MongoCollection<Document> collection = database.getCollection("Magic");

    public static void llistarCartasPerRareza(String raresa) {
        //collection.find(new Document("raresa", raresa)).sort(ascending("nom"))
                //.forEach(document -> System.out.println(document.toJson()));
    	FindIterable<Document> doc = collection.find(Filters.eq("raresa",raresa)).sort(ascending("nom"));

        for (Document dc:doc) {
            if (dc != null) {
                for (String key:dc.keySet()){
                    System.out.println(key + ": "  + dc.get(key));
                }
            } else System.out.println("No matching documents found.");
        }
    }

    public static void insertarNovaCarta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introdueix el nom de la nova carta:");
        String nom = scanner.nextLine();
        System.out.println("Introdueix la raresa de la nova carta:");
        String raresa = scanner.nextLine();
        System.out.println("Introdueix el color de la nova carta:");
        String color = scanner.nextLine();
        System.out.println("Introdueix el mana de la nova carta:");
        String mana = scanner.nextLine();

        Document nuevaCarta = new Document("nom", nom)
                .append("raresa", raresa)
                .append("color", color)
        		.append("mana", mana);
        collection.insertOne(nuevaCarta);

        System.out.println("Nova carta insertada correctament.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Selecciona una opción:");
            System.out.println("1. Llistar cartas per Raresa");
            System.out.println("2. Insertar nova carta");
            System.out.println("3. Sortir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea después de leer un entero

            switch (opcion) {
                case 1:
                    System.out.println("Introdueix la raresa de la carta:");
                    String raresa = scanner.nextLine();
                    llistarCartasPerRareza(raresa);
                    break;
                case 2:
                    insertarNovaCarta();
                    break;
                case 3:
                    System.out.println("Has sortit amb éxit.");
                    mongoClient.close();
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("No esta bé.");
            }
        }
    }
}
