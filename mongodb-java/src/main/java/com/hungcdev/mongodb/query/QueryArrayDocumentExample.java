package com.hungcdev.mongodb.query;

import com.hungcdev.mongodb.data.Post;
import com.hungcdev.mongodb.data.PostQuality;
import com.hungcdev.mongodb.insertion.InsertDocument;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.UUID;

public class QueryArrayDocumentExample {

    public static void main(String[] args) {
        // Thong tin cau hinh de ket noi MongoDB
        String IP = "localhost";
        int port = 27017;
        String databaseName = "HungcDev";
        String collection = "Post";

        /* Khoi tao du lieu */
//        initDataExample(IP, port, databaseName, collection);

        findArrayOfDocumentExample1(IP, port, databaseName,collection);

        findArrayOfDocumentExample2(IP, port, databaseName,collection);
        findArrayOfDocumentExample3(IP, port, databaseName,collection);
        findArrayOfDocumentExample4(IP, port, databaseName,collection);
        findArrayOfDocumentExample5(IP, port, databaseName,collection);
        findArrayOfDocumentExample6(IP, port, databaseName,collection);
        findArrayOfDocumentExample7(IP, port, databaseName,collection);

    }

    private static void findArrayOfDocumentExample1(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi tags co chua cac phan tu: mongodb, java */
        Bson query = Filters.all("tags", List.of("mongodb", "java"));

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findArrayOfDocumentExample2(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi tags co chinh xac 2 phan tu theo thu tu la: mongodb, java */
        Bson query = Filters.eq("tags", List.of("mongodb", "java"));

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findArrayOfDocumentExample3(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi tags co chua phan tu: mongodb */
        Bson query = Filters.eq("tags", "mongodb");

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findArrayOfDocumentExample4(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi numbers co it nhat mot phan tu > 15, co it nhat mot phan tu < 25, hoac la mot phan tu thoa ma ca 2 dieu kien */
        Bson query = Filters.and(Filters.gt("numbers", 15), Filters.lt("numbers", 25));

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findArrayOfDocumentExample5(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi numbers chua it nhat 1 phan tu x voi dieu kien: 15 < x < 25 */
        Bson query = Filters.elemMatch("numbers", Document.parse("{ $gt: 15, $lt: 25 }"));

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findArrayOfDocumentExample6(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi numbers co phan tu thu 2 bang 40 */
        Bson query = Filters.eq("numbers.1", 40);

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findArrayOfDocumentExample7(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi numbers co 3 phan tu */
        Bson query = Filters.size("numbers", 3);

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void initDataExample(String ip, int port , String databaseName, String collection) {
        InsertDocument insertDocument = new InsertDocument(ip, port, databaseName);
        insertDocument.insertMany(collection, Post.class,
                Post.builder().id(UUID.randomUUID().toString()).title("Mongodb").user("Hungcdev").tags(List.of("mongodb", "java")).numbers(List.of(10,30)).view(100).enable(true).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("Spring Java").user("Hungcdev").tags(List.of("spring", "java")).numbers(List.of(20, 30)).enable(false).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("Java").user("AtomPtit").tags(List.of("java", "mongodb")).numbers(List.of(30, 40)).enable(true).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("Spring Boot").user("AtomPtit").tags(List.of("spring", "java", "mongodb")).numbers(List.of(20,30,40)).enable(true).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("Spring").user("HungHoi").tags(List.of("spring")).numbers(List.of(40,50)).enable(true).build()
        );
    }
}
