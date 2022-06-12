package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ListUseCaseTest {


    @SpyBean
    ListUseCase listUseCase;

    @MockBean
    QuestionRepository repository;

    @BeforeEach
    void setUp() {
        MapperUtils mapperUtils = new MapperUtils();
        repository = mock(QuestionRepository.class);
        listUseCase = new ListUseCase(mapperUtils, repository);
    }

    @Test
    void listTest() {
        var question = new Question();
        question.setId("123");
        question.setUserId("456");
        question.setQuestion("What is your name?");
        question.setType("type");
        question.setCategory("category");

        when(repository.findAll()).thenReturn(Flux.just(question));

        StepVerifier.create(listUseCase.get())
                .expectNextMatches(pregunta -> {
                    assert pregunta.getId().equals("123");
                    assert pregunta.getUserId().equals("456");
                    assert pregunta.getQuestion().equals("What is your name?");
                    assert pregunta.getType().equals("type");
                    assert pregunta.getCategory().equals("category");
                    return true;
                }).verifyComplete();
        verify(repository).findAll();
    }
}