package com.api.blogapi.utils;

import com.api.blogapi.model.PostModel;
import com.api.blogapi.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataUtil {

    @Autowired
    PostRepository postRepository;

    //@PostConstruct
    public void savePosts(){

        List<PostModel> postList = new ArrayList<>();
        PostModel post1 = new PostModel();
        post1.setAutor("Homem Aranha");
        post1.setData(LocalDate.now());
        post1.setTexto("Lorem Ipsum is simply dummy text of the printing and typesetting industry."
                + " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "
                + "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
                + "It has survived not only five centuries, but also the leap into electronic typesetting, "
                + "remaining essentially unchanged. It was popularised in the 1960s with the release of"
                + "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
                + "It has survived not only five centuries, but also the leap into electronic typesetting, "
                + "remaining essentially unchanged. It was popularised in the 1960s with the release of"
                + "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
                + "It has survived not only five centuries, but also the leap into electronic typesetting, "
                + "remaining essentially unchanged. It was popularised in the 1960s with the release of"
                + " Letraset sheets containing Lorem Ipsum passages, and more recently with desktop "
                + "publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        post1.setTitulo("Docker");

        PostModel post2 = new PostModel();
        post2.setAutor("Homem de Ferro");
        post2.setData(LocalDate.now());
        post2.setTexto("Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
                + "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "
                + "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
                + "It has survived not only five centuries, but also the leap into electronic typesetting,"
                + " remaining essentially unchanged. It was popularised in the 1960s with the release of "
                + "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing "
                + "software like Aldus PageMaker including versions of Lorem Ipsum.");
        post2.setTitulo("API REST");

        postList.add(post1);
        postList.add(post2);

        for(PostModel post: postList){
            PostModel postSaved = postRepository.save(post);
            System.out.println(postSaved.getId());
        }
    }
}
