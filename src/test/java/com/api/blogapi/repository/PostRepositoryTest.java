package com.api.blogapi.repository;

import com.api.blogapi.model.PostModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
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
}
