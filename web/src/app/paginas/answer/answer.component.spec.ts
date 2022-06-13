import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { AnswerComponent } from "./answer.component";
import { QuestionService  } from '../../Service/question.service';

describe("AnswerComponent", () => {
  let component: AnswerComponent;
  let fixture: ComponentFixture<AnswerComponent>;
  let myService: QuestionService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AnswerComponent],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [{ provide: QuestionService, useValue: {} }],
      imports: []
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    myService = TestBed.inject(QuestionService);
  });

  describe('method1', () => {
    it('should ...', () => {
      expect(component).toBeTruthy();
    });
  });
})
