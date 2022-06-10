package co.com.sofka.questions.routers;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QuestionRouter {

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(path = "/getAll", produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = ListUseCase.class,
                            beanMethod = "get",
                            operation = @Operation(operationId = "getAllQuestions",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = QuestionDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")},
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "userId", description = "User Id")}
                            )),
                    @RouterOperation(path = "/getOwnerAll/{userId}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = OwnerListUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "getOwnerQuestions",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = QuestionDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")},
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "userId", description = "User Id")})
                    ),
                    @RouterOperation(path = "/create",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.POST,
                            beanClass = CreateUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "createQuestion",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = QuestionDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")})
                    ),
                    @RouterOperation(path = "/update",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.PUT,
                            beanClass = UpdateUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "updateQuestion",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = QuestionDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")})
                    ),
                    @RouterOperation(path = "/delete/{questionId}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.DELETE,
                            beanClass = DeleteUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "deleteQuestion",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = QuestionDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")})
                    ),
                    @RouterOperation(path = "/get/{questionId}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = GetUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "getQuestion",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = QuestionDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")},
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "questionId", description = "Question Id")})
                    ),
                    @RouterOperation(path = "add",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.POST,
                            beanClass = AddAnswerUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(operationId = "addAnswer",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = AnswerDTO.class))),
                                            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                                            @ApiResponse(responseCode = "400", description = "Bad Request"),
                                            @ApiResponse(responseCode = "404", description = "Not Found")},
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "questionId", description = "Question Id"),
                                            @Parameter(in = ParameterIn.PATH, name = "answerId", description = "Answer Id")})
                    )
            }

    )

    RouterFunction<ServerResponse> routerFunctionQuestionsAndAnswers(ListUseCase listUseCase, AddAnswerUseCase addAnswerUseCase, GetUseCase getUseCase, CreateUseCase createUseCase, DeleteUseCase deleteUseCase,
                                                                     OwnerListUseCase ownerListUseCase, UpdateUseCase updateUseCase) {
        return RouterFunctions
                .route(RequestPredicates.GET("/getAll"), request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.get(), QuestionDTO.class))
                )
                .andRoute(RequestPredicates.GET("/getOwnerAll/{userId}"), request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(ownerListUseCase.apply(request.pathVariable("id")), QuestionDTO.class))
                )
                .andRoute(RequestPredicates.POST("/create").and(accept(MediaType.APPLICATION_JSON)),
                        request -> request.bodyToMono(QuestionDTO.class).flatMap(
                                questionDTO -> createUseCase.apply(questionDTO)
                                        .flatMap(result -> ServerResponse.ok()
                                                .contentType(MediaType.TEXT_PLAIN)
                                                .bodyValue(result))
                        )
                )
                .andRoute(RequestPredicates.GET("/get/{id}").and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getUseCase.apply(
                                        request.pathVariable("id")),
                                QuestionDTO.class
                        ))
                )
                .andRoute(RequestPredicates.POST("/add").and(accept(MediaType.APPLICATION_JSON)), request -> request.bodyToMono(AnswerDTO.class)
                        .flatMap(addAnswerDTO -> addAnswerUseCase.apply(addAnswerDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
                )
                .andRoute(RequestPredicates.DELETE("/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),  request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteUseCase.apply(request.pathVariable("id")), Void.class))
                )
                .andRoute(RequestPredicates.PUT("/update").and(accept(MediaType.APPLICATION_JSON)), request -> request.bodyToMono(QuestionDTO.class)
                        .flatMap(updateQuestionDTO -> updateUseCase.apply(updateQuestionDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
                );

    }
}
