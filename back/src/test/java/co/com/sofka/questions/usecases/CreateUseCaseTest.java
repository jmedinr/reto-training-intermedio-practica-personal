package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CreateUseCaseTest {

    @Autowired
    CreateUseCase createUseCase;

    @Autowired
    QuestionRepository repository;

    @Autowired
    MapperUtils mapper = new MapperUtils();

    @BeforeEach
    public void setup(){
        repository = mock(QuestionRepository.class);
        createUseCase = new CreateUseCase(mapper, repository);
    }

    @Test
    void createTest(){
        QuestionDTO questionDTO = new QuestionDTO("123", "a123", "cuánto es 2 + 2", "aritmetica", "suma");

        when(repository.save(any())).thenReturn(Mono.just(mapper.mapperToQuestion("id").apply(questionDTO)));

        StepVerifier.create(createUseCase.apply(questionDTO))
                .expectNextMatches(created ->  created.equals("id"))
                .verifyComplete();

        verify(repository).save(any());
    }
}