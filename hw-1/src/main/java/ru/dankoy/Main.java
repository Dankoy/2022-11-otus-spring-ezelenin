package ru.dankoy;

import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dankoy.core.domain.Question;
import ru.dankoy.core.service.QuestionsServiceImpl;
import ru.dankoy.core.utils.PrinterImpl;

public class Main {

  public static void main(String[] args) {

    var ctx = new ClassPathXmlApplicationContext("/spring-context.xml");

    var questionsService = ctx.getBean(QuestionsServiceImpl.class);

    List<Question> questionList = questionsService.getQuestions();
    var printer = ctx.getBean(PrinterImpl.class);

    printer.printQuestions(questionList);

  }
}