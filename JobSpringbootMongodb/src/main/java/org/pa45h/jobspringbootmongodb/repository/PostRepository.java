package org.pa45h.jobspringbootmongodb.repository;

import org.pa45h.jobspringbootmongodb.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
