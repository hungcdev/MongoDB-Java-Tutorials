package com.hungcdev.connection;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDbConnection {
    MongoClient mongoClient;

    public void connectMongoDB() {
        mongoClient = MongoClients.create();
        System.out.println("Connect MongoDB successful!");
    }

    public void connectMongoDB(String ip, int port) {
        mongoClient = MongoClients.create("mongodb://" + ip + ":" + port);
        System.out.println("Connect MongoDB successful!");
    }

    public void connectMongoDBWithPOJOs(String ip, int port) {
        ConnectionString connectionString = new ConnectionString("mongodb://" + ip + ":" + port);
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        mongoClient = MongoClients.create(clientSettings);
        System.out.println("Connect MongoDB successful!");
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public static void main(String[] args) {
        MongoDbConnection mongoDbConnection = new MongoDbConnection();
        mongoDbConnection.connectMongoDBWithPOJOs("localhost", 27017);
        MongoDatabase mongoDatabase = mongoDbConnection.getMongoClient().getDatabase("HungcDev");
        mongoDatabase.createCollection("User");
    }
}
