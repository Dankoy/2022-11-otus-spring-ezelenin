package ru.dankoy;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.dankoy.core.domain.Question;
import ru.dankoy.core.service.QuestionsServiceImpl;
import ru.dankoy.core.utils.PrinterImpl;


@PropertySource("/application.properties")
@Configuration
@ComponentScan
public class Main {

  public static void main(String[] args) {

    var ctx = new AnnotationConfigApplicationContext(Main.class);

    var questionsService = ctx.getBean(QuestionsServiceImpl.class);

    List<Question> questionList = questionsService.getQuestions();
    var printer = ctx.getBean(PrinterImpl.class);

    printer.printQuestions(questionList);

  }
}