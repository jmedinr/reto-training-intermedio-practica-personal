import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AnswerI } from 'src/app/models/answer-i';
import { QuestionI } from 'src/app/models/question-i';
import { QuestionService } from 'src/app/Service/question.service';

@Component({
  selector: 'app-requestion',
  templateUrl: './requestion.component.html',
  styleUrls: ['./requestion.component.css']
})
export class RequestionComponent implements OnInit {

  question: QuestionI | undefined;
  answers: AnswerI[] | undefined;
  answersNew: AnswerI[] = [];
  currentAnswer: number = 0;

  questions: QuestionI[] | undefined;
  estrellas: number = 0;
  promedio: number = 0;

  page: number = 0;

  constructor(
    private route: ActivatedRoute,
    private questionService: QuestionService,
    private service: QuestionService,

  ) {

  }

  id: string | undefined;

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.getQuestions(`${id}`);
    this.get2();

  }

  get2() {
    let id = this.route.snapshot.paramMap.get('id');


    this.service.getAnswer(id).subscribe((data) => {
      this.answers = data.answers;
    });
  }

  getQuestions(id: string): void {
    this.questionService.getQuestion(id).subscribe((data) => {
      this.question = data;
      this.answers = data.answers.sort((a, b) => {
        return (a.position - b.position);
      });
      this.answers.map((respuesta) => {
        console.log(respuesta.position);
        this.estrellas += respuesta.position;
        this.promedio = this.estrellas / data.answers.length;
      });
    });
  }

  AddAnwsers(index: number) {
    let last = this.currentAnswer + index;
    for (let i = this.currentAnswer; i < last; i++) {
    }
    this.currentAnswer += 10;
  }

  onScroll() {

  }

}
