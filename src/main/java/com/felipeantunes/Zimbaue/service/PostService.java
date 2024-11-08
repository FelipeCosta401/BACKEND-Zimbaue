package com.felipeantunes.Zimbaue.service;

import com.felipeantunes.Zimbaue.interfaces.IPostDTO;
import com.felipeantunes.Zimbaue.model.dto.PostDTO;
import com.felipeantunes.Zimbaue.model.entity.Post;
import com.felipeantunes.Zimbaue.repository.PostRepository;
import com.felipeantunes.Zimbaue.repository.UserRepository;
import com.felipeantunes.Zimbaue.service.exceptions.BadRequestException;
import com.felipeantunes.Zimbaue.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public List<PostDTO> getPostList(){
        List<IPostDTO> iPostDTOList = this.postRepository.getPostList();
        ArrayList<PostDTO> postDTOArrayList = new ArrayList<>();
        for(IPostDTO iPostDTO : iPostDTOList){
            postDTOArrayList.add(iPostDTO.toPostDTO());
        }

        return postDTOArrayList;
    }

    public PostDTO getPostById(Integer postId){
        //verifies if there are any post with the id
        boolean postExists = this.postRepository.existsById(postId);
        if(!postExists){
            throw new NotFoundException("Publicação não encontrada com esse id!");
        }
        //if so, returns the postDTO
        return this.postRepository.getPostById(postId).toPostDTO();
    }


    public PostDTO save(PostDTO postDTO) {

        //Verifies if there is any empty field
        if(postDTO.getTitle() == null){
            throw new BadRequestException("O título não pode ser vazio");
        } else if(postDTO.getUserDTO().getId() == null){
            throw new BadRequestException("Usuário é obrigatório");
        } else if (!this.userRepository.existsById(postDTO.getUserDTO().getId())){
           throw new BadRequestException("Usuário não encontrado com esse id");
        } else {
            Post post = postDTO.toPostEntity();
            Post createdPost = this.postRepository.save(post);
            return this.getPostById(createdPost.getId());
        }

    }
}
