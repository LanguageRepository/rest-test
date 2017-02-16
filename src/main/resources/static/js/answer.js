import * as $ from "../admin-pages/js/jquery-1.10.2";
class Answer {

    answerMap = new Map();

    Animal(type, answerText, questionId, answerNumber) {
        this.type = type;
        this.answerText = answerText;
        this.questionId = questionId;
        this.answerNumber = answerNumber;
    }

    addAnswer(type, answer, questionId, answerNumber) {
        this.answerMap[answerNumber] = new Answer(type, answer, questionId);
        return this.answerMap;
    }

}