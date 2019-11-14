/**
 * 
 */
package controller;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author migue
 *
 */
public class MongoConnection {
	private static MongoClient mongoClient;
	private static String db = "Seguridad";

	public MongoConnection() {
		MongoClientURI uri = new MongoClientURI(
				"mongodb+srv://admindb:admin123@cluster0-eayyw.mongodb.net/test?retryWrites=true&w=majority");
		mongoClient = new MongoClient(uri);
	}

	public MongoCollection<Document> findCollection(String nameColection) {
		MongoDatabase mongoBD = mongoClient.getDatabase(db);
		return mongoBD.getCollection(nameColection);
	}

	public void insertObject(String nameColection, Document nDoc) {
		MongoDatabase mongoBD = mongoClient.getDatabase(db);
		MongoCollection<Document> colection = mongoBD.getCollection(nameColection);

		colection.insertOne(nDoc);
	}

	public void updateObject(String nameCollection, String _id, Document nDoc) {
		MongoDatabase mongoBD = mongoClient.getDatabase("Ecotech");
		MongoCollection<Document> collection = mongoBD.getCollection(nameCollection);
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(_id));

		collection.replaceOne(query, nDoc);
	}

	public Document searchByID(String nameColection, String _id) {
		MongoDatabase mongoBD = mongoClient.getDatabase(db);
		MongoCollection<Document> coleccion = mongoBD.getCollection(nameColection);

		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(_id));

		Document doc = coleccion.find(query).first();
		return doc;
	}

	public void deleteByID(String nameColection, String _id) {
		MongoDatabase mongoBD = mongoClient.getDatabase(db);
		MongoCollection<Document> coleccion = mongoBD.getCollection(nameColection);

		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(_id));

		coleccion.deleteOne(query);
	}

	public void closeMongoDB() {
		mongoClient.close();
	}
}
