package com.hungcdev.insert;

import com.hungcdev.connection.MongoDBConnection;
import com.hungcdev.data.Post;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.List;
import java.util.UUID;

public class InsertDocument {
    private MongoDBConnection mongoDBConnection;

    private MongoDatabase mongoDatabase;

    public InsertDocument(String ip, int port, String databaseName) {
        this.mongoDBConnection = new MongoDBConnection();
        this.mongoDBConnection.connectMongoDBWithPOJOs(ip, port);
        this.mongoDatabase = mongoDBConnection.getMongoClient().getDatabase(databaseName);
    }

    public <T> void insert(String collectionName, T document , Class<T> classType) {
        MongoCollection<T> collection = mongoDatabase.getCollection(collectionName, classType);
        collection.insertOne(document);
    }

    public static void main(String[] args) {
        InsertDocument insertDocument = new InsertDocument("localhost", 27017, "HungcDev");
        Post post = Post.builder()
                .id(UUID.randomUUID().toString())
                .title("Mongodb Java tutorial")
                .user("Hungcdev")
                .content("Huong dan intert Document trong MongoDB")
                .tags(List.of("Mongodb", "Mongodb Java"))
                .enable(true)
                .build();
        insertDocument.insert("Post", post, Post.class);
        System.out.println("Insert Post Successful!");
    }



}
