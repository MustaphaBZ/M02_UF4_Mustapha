package mongoapp;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;

public class Connection {
	static ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
	static MongoClient mgCli = MongoClients.create(connectionString);
	

	public static void getAnyPeli( MongoClient mglCli, int any) {
		MongoDatabase mgDB = mgCli.getDatabase("moviedb");
		MongoCollection<Document> colMovies = mgDB.getCollection("movies");
		FindIterable<Document> docMovies = colMovies.find(Filters.eq("year", any));
		for(Document doc : docMovies) {
		System.out.println("Pelicuals de l'any: " + any + " " + doc.toString() );
		}
		
	}
	
	public static void main(String[] args) {
		getAnyPeli(mgCli, 2003);
	}
	
}
