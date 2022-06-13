import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AnswerI } from 'src/app/models/answer-i';
import { QuestionI } from 'src/app/models/question-i';
import { QuestionService } from 'src/app/Service/question.service';
import {formatDate} from '@angular/common';

@Component({
  selector: 'app-requestion',
  templateUrl: './requestion.component.html',
  styleUrls: ['./requestion.component.css']
})
export class RequestionComponent implements OnInit {

  question: QuestionI | undefined;
  answers: AnswerI[] | undefined;
  dateAnswer: Date = new Date();
  answersNew: AnswerI[] = [];
  currentAnswer: number = 0;

  questions: QuestionI[] | undefined;
  estrellas: number = 0;
  promedio: number = 0;
  date = formatDate(this.dateAnswer, 'dd/MM/yyyy', 'en-US');

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
    //this.get2();

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
        return (b.position - a.position);
      });
      this.dateAnswer = new Date();
      this.answers.map((respuesta) => {
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
    this.dateAnswer = new Date();
    this.date = formatDate(this.dateAnswer, 'dd/MM/yyyy', 'en-US');

  }

  onScroll() {

  }

}
