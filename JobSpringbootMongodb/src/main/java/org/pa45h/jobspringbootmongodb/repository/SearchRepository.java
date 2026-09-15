package org.pa45h.jobspringbootmongodb.repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.pa45h.jobspringbootmongodb.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SearchRepository {

    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoConverter mongoConverter;

    public List<Post> findByText(String text) {
        List<Post> posts = new ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase("JobSpringbootMongodb");
        MongoCollection<Document> collection = database.getCollection("posts");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                new Document("$search",
                        new Document("index", "default").append("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("techs", "desc", "profile", "exp")).append("matchCriteria", "any"))),
                new Document("$sort",
                        new Document("exp", 1L)),
                new Document("$limit", 5L)));

        result.forEach(document -> posts.add(mongoConverter.read(Post.class, document)));

        return posts;
    }
}
