import { Component, OnInit } from '@angular/core';
import { CommonModule } from "@angular/common";
import quizz_questions from "../../../assets/data/quizz_questions.json"
import { Routes, RouterModule, Router } from '@angular/router';
@Component({
  selector: 'app-quizz',
  imports: [CommonModule],
  templateUrl: './quizz.component.html',
  styleUrl: './quizz.component.css'
})
export class QuizzComponent implements OnInit {
  title: string = ""
  questions: any
  questionSelect: any

  answrs: string[] = []
  answrSelected: string = ""

  questionIndex: number = 0
  questionMaxIndex: number = 0

  finished: boolean = false

  constructor(private router: Router) { }
  ngOnInit(): void {
    if (quizz_questions) {
      this.finished = false
      this.questionIndex = 0

      this.title = quizz_questions.title
      this.questions = quizz_questions.questions
      this.questionSelect = this.questions[this.questionIndex]


      this.questionMaxIndex = this.questions.length
    }
  }



  playerChoose(optionSelect: string) {
    this.answrs.push(optionSelect)
    this.advancedQuestion()
  }

  private advancedQuestion() {
    this.questionIndex++
    if (this.questionMaxIndex > this.questionIndex) {
      this.questionSelect = this.questions[this.questionIndex]
    } else {
      const responseFinal: string = this.checkResult(this.answrs)
      this.finished = true
      // AQUI O ANNY NÂO INDENTIFICA O COMPORTAMENTO da "responseFinal" PRCISANOD ESPLICITAR OCM O KEYOF
      this.questionSelect = quizz_questions.results[responseFinal as keyof typeof quizz_questions.results]
    }

  }

  private checkResult(answrsNow: string[]) {
    const result = answrsNow.reduce((previousValue, currentValue, currentIndex, array) => {
      if (array.filter(item => item === previousValue).length >
        array.filter(item => item === currentValue).length) {
        return previousValue
      }
      else {
        return currentValue
      }
    })

    return result
  }

  protected chageRefresh() {
    console.log("CLIQUEI");

    this.finished = false
      this.questionIndex = 0

      this.title = quizz_questions.title
      this.questions = quizz_questions.questions
      this.questionSelect = this.questions[this.questionIndex]
  }

}
