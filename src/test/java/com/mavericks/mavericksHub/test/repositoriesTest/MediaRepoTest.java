package com.mavericks.mavericksHub.test.repositoriesTest;


import com.mavericks.mavericksHub.models.Media;
import com.mavericks.mavericksHub.repositories.MediaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(scripts={"/db/data.sql"})
@Slf4j
public class MediaRepoTest {
    @Autowired
    private MediaRepository repo;
    @Test
    void testFindAllUserMedia(){
        List<Media> media = repo.findAllMediaFor(202L);
        assertThat(media).hasSize(2);
        log.info("items --> {}", media);
    }

}
