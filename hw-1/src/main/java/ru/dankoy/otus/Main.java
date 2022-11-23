package ru.dankoy.otus;

import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dankoy.otus.core.domain.Question;
import ru.dankoy.otus.core.service.QuestionsServiceImpl;
import ru.dankoy.otus.core.utils.Printer;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello world!");

    var ctx = new ClassPathXmlApplicationContext("/spring-context.xml");

    var questionsService = ctx.getBean(QuestionsServiceImpl.class);

    List<Question> questionList = questionsService.getQuestions();
    var printer = ctx.getBean(Printer.class);

    printer.printQuestions(questionList);

  }
}