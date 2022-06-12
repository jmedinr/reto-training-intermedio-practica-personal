package co.com.sofka.questions.usecases;

import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DeleteUseCaseTest {

    @SpyBean
    DeleteUseCase deleteUseCase;

    @MockBean
    QuestionRepository repository;

    @MockBean
    AnswerRepository answerRepository;

    @Test
    void deleteTest() {
        String id = "123";
        when(repository.deleteById(id)).thenReturn(Mono.empty());
        deleteUseCase = new DeleteUseCase(answerRepository, repository);
        StepVerifier.create(deleteUseCase.apply(id))
                .expectNextMatches(deleted -> deleted.equals("123"));
        verify(repository).deleteById(id);
    }
}