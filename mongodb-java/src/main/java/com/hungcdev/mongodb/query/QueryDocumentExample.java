package com.hungcdev.mongodb.query;

import com.hungcdev.mongodb.data.Post;
import com.hungcdev.mongodb.insertion.InsertDocument;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.UUID;

public class QueryDocumentExample {

    public static void main(String[] args) {
        // Thong tin cau hinh de ket noi MongoDB
        String IP = "localhost";
        int port = 27017;
        String databaseName = "HungcDev";
        String collection = "Post";

        /* Khoi tao du lieu */
        initDataExample(IP, port, databaseName, collection);

        /* Tim kiem tat ca document Post trong Collection */
        findAllExample(IP, port, databaseName, collection);

        /* Tim kiem document Post trong Collection voi dieu kien so sanh Equals */
        findEqualsExample(IP, port, databaseName, collection);

        /* Tim kiem document Post trong Collection voi dieu kien so sanh Not Equals */
        findNotEqualsExample(IP, port, databaseName, collection);

        /* Tim kiem document Post trong Collection voi dieu kien so sanh In */
        findInExample(IP, port, databaseName, collection);

        /* Tim kiem document Post trong Collection voi dieu kien so sanh Not In */
        findNotInExample(IP, port, databaseName, collection);

        /* Tim kiem document Post trong Collection voi dieu kien so sanh Greater Than */
        findGreaterThanExample(IP, port, databaseName, collection);

        /* Tim kiem document Post trong Collection voi dieu kien so sanh Greater Than Or Equals */
        findGreaterThanOrEqualsExample(IP, port, databaseName, collection);

        /* Tim kiem document Post trong Collection voi dieu kien so sanh Less than */
        findLessThanExample(IP, port, databaseName, collection);

        /* Tim kiem document Post trong Collection voi dieu kien so sanh Less than or Equals */
        findLessThanOrEqualsExample(IP, port, databaseName, collection);

        /* Tim kiem document Post trong Collection thoa man tat ca cac dieu kien  */
        findANDLogicExample(IP, port, databaseName, collection);

        /* Tim kiem document Post trong Collection bat ky mot trong cac dieu kien  */
        findORLogicExample(IP, port, databaseName, collection);
    }

    private static void findORLogicExample(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi  view >= 100 && view < 300 */
        Bson query = Filters.or(Filters.eq("user", "Hungcdev"), Filters.in("title", List.of("Spring", "Java")));

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findANDLogicExample(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi  view >= 100 && view < 300 */
        Bson query = Filters.and(Filters.gte("view", 100), Filters.lt("view", 300));

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findLessThanOrEqualsExample(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi view <= 300 */
        Bson query = Filters.lte("view", 300);

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findLessThanExample(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi view < 300 */
        Bson query = Filters.lt("view", 300);

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findGreaterThanOrEqualsExample(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi view >= 300 */
        Bson query = Filters.gte("view", 300);

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findGreaterThanExample(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi view > 300 */
        Bson query = Filters.gt("view", 300);

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findNotInExample(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi title khong nam trong danh sach {Mongodb, Spring, Java}*/
        Bson query = Filters.nin("title",List.of("Mongodb", "Spring", "Java"));

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findInExample(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi title nam trong danh sach {Mongodb, Spring, Java}*/
        Bson query = Filters.in("title",List.of("Mongodb", "Spring", "Java"));

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findNotEqualsExample(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi user khac gia tri Hungcdev */
        Bson query = Filters.ne("user","Hungcdev");

        /* tim kiem document voi enable khac gia tri true */
//        Bson query = Filters.ne("enable",true);

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void findEqualsExample(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);

        /* tim kiem document voi id = 8cf90a70-d7cd-4b7b-995b-167daab3c059 */
//        Bson query = Filters.eq("_id","8cf90a70-d7cd-4b7b-995b-167daab3c059");

        /* tim kiem document voi user la Hungcdev */
//        Bson query = Filters.eq("user","Hungcdev");

        /* tim kiem document voi enable la true */
        Bson query = Filters.eq("enable",true);

        List<Post> postList = queryDocument.find(collection, Post.class, query);
        System.out.println("--- Document trong Collection Post voi "+ query.toString() + "---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

    private static void initDataExample(String ip, int port , String databaseName, String collection) {
        InsertDocument insertDocument = new InsertDocument(ip, port, databaseName);

        insertDocument.insertMany(collection, Post.class,
                Post.builder().id(UUID.randomUUID().toString()).title("Mongodb").user("Hungcdev").content("MongoDB Tutorial").view(100).enable(true).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("Spring").user("Hungcdev").content("Spring Tutorial").view(200).enable(false).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("Java").user("AtomPtit").content("Java Tutorial").view(300).enable(true).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("Spring Boot").user("AtomPtit").content("Spring Boot Tutorial").view(400).enable(true).build(),
                Post.builder().id(UUID.randomUUID().toString()).title("PHP").user("HungHoi").content("PHP Tutorial").view(500).enable(true).build()
        );
    }

    private static void findAllExample(String ip, int port, String databaseName, String collection) {
        QueryDocument queryDocument = new QueryDocument(ip, port,databaseName);
        List<Post> postList = queryDocument.findAll(collection, Post.class);
        System.out.println("--- Document trong Collection Post ---");
        postList.forEach(post -> System.out.println(post.toString()));
        System.out.println("--- End ---");
    }

}
