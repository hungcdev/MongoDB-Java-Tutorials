package com.hungcdev.mongodb.query;

import com.hungcdev.mongodb.data.Post;
import com.hungcdev.mongodb.data.PostQuality;
import com.hungcdev.mongodb.insertion.InsertDocument;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.UUID;

public class QueryEmbeddedDocumentExample {

    public static void main(String[] args) {
        // Thong tin cau hinh de ket noi MongoDB
        String IP = "localhost";
        int port = 27017;
        String databaseName = "HungcDev";
        String collection = "Post";

        /* Khoi tao du lieu */
        initDataExample(IP, port, databaseName, collection);

        findEmbeddedDocumentExample1(IP, port, databaseName,collection);

        findEmbeddedDocumentExample2(IP, port, databaseName,collection);
    }

    private static void findEmbeddedDocumentExample2(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi user la AtomPtit va so luong share bang 50 */
        Bson query = Filters.and(Filters.eq("user", "AtomPtit"), Filters.eq("postQuality.shareNumber", 50));

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findEmbeddedDocumentExample1(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi so luong like lon hon 100 */
        Bson query = Filters.gt("postQuality.likeNumber", 100);

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void initDataExample(String ip, int port , String databaseName, String collection) {
        InsertDocument insertDocument = new InsertDocument(ip, port, databaseName);
        insertDocument.insertMany(collection, Post.class,
                Post.builder().id(UUID.randomUUID().toString()).title("Mongodb").user("Hungcdev").postQuality(new PostQuality(120, 50)).view(100).enable(true).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("Spring").user("Hungcdev").postQuality(new PostQuality(120, 100)).view(200).enable(false).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("Java").user("AtomPtit").postQuality(new PostQuality(50, 10)).view(300).enable(true).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("Spring Boot").user("AtomPtit").postQuality(new PostQuality(70, 50)).view(400).enable(true).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("PHP").user("HungHoi").postQuality(new PostQuality(150, 120)).view(500).enable(true).build()
        );
    }
}
