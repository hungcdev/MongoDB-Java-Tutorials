package com.hungcdev.mongodb.query;

import com.hungcdev.mongodb.data.Comment;
import com.hungcdev.mongodb.data.Post;
import com.hungcdev.mongodb.insertion.InsertDocument;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.UUID;

public class QueryArrayEmbeddedDocumentExample {
    public static void main(String[] args) {
        // Thong tin cau hinh de ket noi MongoDB
        String IP = "localhost";
        int port = 27017;
        String databaseName = "HungcDev";
        String collection = "Post";

        /* Khoi tao du lieu */
        initDataExample(IP, port, databaseName, collection);

        queryArrayEmbeddedDocument1(IP, port, databaseName,collection);
        queryArrayEmbeddedDocument2(IP, port, databaseName,collection);
        queryArrayEmbeddedDocument3(IP, port, databaseName,collection);
        queryArrayEmbeddedDocument4(IP, port, databaseName,collection);
        queryArrayEmbeddedDocument5(IP, port, databaseName,collection);

    }

    private static void queryArrayEmbeddedDocument1(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi comments co chua document comment thoa man cac gia tri */
        Bson query = Filters.eq("comments", new Comment("user1", "thankkkkk",15));

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void queryArrayEmbeddedDocument2(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi comments co chua document ma truong user co gia tri la user3 */
        Bson query = Filters.eq("comments.user", "user3");

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void queryArrayEmbeddedDocument3(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi comments co document vi tri dau tien ma truong user co gia tri la user3 */
        Bson query = Filters.eq("comments.0.user", "user3");

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void queryArrayEmbeddedDocument4(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi comments co it nhat mot document ma co truong user la user1 va truong likeNumber la 20  */
        Bson query = Filters.elemMatch("comments", Filters.and(Filters.eq("user", "user1"), Filters.eq("likeNumber", 20)));

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void queryArrayEmbeddedDocument5(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi comments co document ma truong user la user1 va co document ma truong likeNumber bang 20 */
        Bson query = Filters.and(Filters.eq("comments.user", "user1"), Filters.eq("comments.likeNumber", 20));

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void initDataExample(String ip, int port , String databaseName, String collection) {
        InsertDocument insertDocument = new InsertDocument(ip, port, databaseName);
        insertDocument.insertMany(collection, Post.class,
                Post.builder().id(UUID.randomUUID().toString()).title("Mongodb").user("Hungcdev").comments(List.of(new Comment("user1", "good post",10), new Comment("user2", "great! Tks",20))).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("Spring Java").user("Hungcdev").comments(List.of(new Comment("user2", "thank so much",15), new Comment("user1", "thankkkkk",20))).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("Java").user("AtomPtit").comments(List.of(new Comment("user3", "good post",10), new Comment("user1", "thankkkkk",15))).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("Spring Boot").user("AtomPtit").comments(List.of(new Comment("user2", "thank so much",20), new Comment("user3", "thankkkkk",30))).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("Spring").user("HungHoi").comments(List.of(new Comment("user1", "great! Tks",25))).build()
        );
    }
}
