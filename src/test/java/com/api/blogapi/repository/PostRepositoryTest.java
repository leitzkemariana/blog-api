package com.api.blogapi.repository;

import com.api.blogapi.model.PostModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class PostRepositoryTest {

    @Autowired
    private PostRepository repository;

    @Test
    void deveSalvarERetornarPost(){
        PostModel post = new PostModel("Autor", "Aula", "Kachow");

        PostModel postSalvo = repository.save(post);

        Assertions.assertThat(postSalvo).isNotNull();
    }

    @Test
    void deveListarPorTitulo(){
        PostModel post1 = new PostModel("Autor", "Aula +devs", "Kachow");
        PostModel post2 = new PostModel("Autor1", "Tarefa +devs", "Kachow");
        PostModel post3 = new PostModel("Autor2", "Prova +devs", "Kachow");

        repository.save(post1);
        repository.save(post2);
        repository.save(post3);

        Page<PostModel> listaPosts = repository.findByTituloContainingIgnoreCase("+devs", PageRequest.of(0,10));

        Assertions.assertThat(listaPosts).hasSize(3);
    }
}
