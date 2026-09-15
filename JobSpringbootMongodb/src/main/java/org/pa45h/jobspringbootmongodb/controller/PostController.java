package org.pa45h.jobspringbootmongodb.controller;

import org.pa45h.jobspringbootmongodb.model.Post;
import org.pa45h.jobspringbootmongodb.repository.PostRepository;
import org.pa45h.jobspringbootmongodb.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    SearchRepository searchRepository;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PostMapping("add-post")
    public Post addPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @GetMapping("posts/{text}")
    public List<Post> getPosts(@PathVariable String text) {
        return searchRepository.findByText(text);
    }

}
