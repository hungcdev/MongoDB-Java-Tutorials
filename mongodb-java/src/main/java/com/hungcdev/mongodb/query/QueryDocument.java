package com.hungcdev.mongodb.query;

import com.hungcdev.mongodb.connection.MongoDBConnection;
import com.hungcdev.mongodb.data.Post;
import com.hungcdev.mongodb.insertion.InsertDocument;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QueryDocument {

    private MongoDatabase mongoDatabase;

    public QueryDocument(String ip, int port, String databaseName) {
        MongoClient mongoClient = MongoDBConnection.connectMongoDBWithPOJOs(ip, port);
        this.mongoDatabase = mongoClient.getDatabase(databaseName);
    }

    /**
     * The method to find all document in Collection
     * @param collectionName
     * @param classType
     * @param <T>
     * @return
     */
    public <T> List<T> findAll(String collectionName, Class<T> classType) {
        MongoCollection<T> collection = mongoDatabase.getCollection(collectionName, classType);
        FindIterable<T> findResult = collection.find();
        return convert(findResult);
    }

    /**
     * The method to find document in Collection with condition
     * @param collectionName
     * @param classType
     * @param query
     * @param <T>
     * @return
     */
    public <T> List<T> find(String collectionName, Class<T> classType, Bson query) {
        MongoCollection<T> collection = mongoDatabase.getCollection(collectionName, classType);
        FindIterable<T> findResult = collection.find(query);
        return convert(findResult);
    }

    /**
     * The method to convert from FindIterable to List
     * @param findIterable
     * @param <T>
     * @return
     */
    private <T> List<T> convert(FindIterable<T> findIterable) {
        List<T> documents = new ArrayList<>();
        MongoCursor<T> cursor = findIterable.cursor();
        while (cursor.hasNext())
            documents.add(cursor.next());

        return documents;
    }

}
