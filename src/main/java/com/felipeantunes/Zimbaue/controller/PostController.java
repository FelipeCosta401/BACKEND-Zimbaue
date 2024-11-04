package com.felipeantunes.Zimbaue.controller;

import com.felipeantunes.Zimbaue.model.dto.PostDTO;
import com.felipeantunes.Zimbaue.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getPost(){
        List<PostDTO> postDTOList = this.postService.getPostList();
        return ResponseEntity.status(HttpStatus.OK).body(postDTOList);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
        PostDTO postDTO = this.postService.getPostById(postId);
        return ResponseEntity.status(HttpStatus.OK).body(postDTO);
    }


    @PostMapping
    public PostDTO savePost(@RequestBody PostDTO postDTO){
        return this.postService.save(postDTO);
    }


}
