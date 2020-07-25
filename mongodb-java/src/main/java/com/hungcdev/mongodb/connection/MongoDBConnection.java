package com.hungcdev.mongodb.connection;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBConnection {

    public static MongoClient connectMongoDB() {
        return MongoClients.create();
    }

    public static MongoClient connectMongoDB(String ip, int port) {
        return MongoClients.create("mongodb://" + ip + ":" + port);
    }

    public static MongoClient connectMongoDBWithPOJOs(String ip, int port) {
        ConnectionString connectionString = new ConnectionString("mongodb://" + ip + ":" + port);

        CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();

        return MongoClients.create(clientSettings);
    }

    public static void main(String[] args) {
        connectionExample();
    }

    private static void connectionExample() {
        MongoClient mongoClient = MongoDBConnection.connectMongoDBWithPOJOs("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("HungcDev");
        mongoDatabase.createCollection("User");
    }
}
