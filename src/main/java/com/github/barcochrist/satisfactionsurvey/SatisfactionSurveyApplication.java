package com.github.barcochrist.satisfactionsurvey;

import com.github.barcochrist.satisfactionsurvey.controller.QuestionRestController;
import com.github.barcochrist.satisfactionsurvey.entity.QuestionJpa;
import com.github.barcochrist.satisfactionsurvey.repository.QuestionRepository;
import com.github.barcochrist.satisfactionsurvey.repository.impl.QuestionRepositoryImpl;
import com.github.barcochrist.satisfactionsurvey.service.impl.QuestionServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackageClasses = {
    QuestionRepositoryImpl.class,
    QuestionServiceImpl.class,
    QuestionRestController.class
})
@EntityScan(basePackageClasses = {
    QuestionJpa.class
})
@EnableJpaRepositories(basePackageClasses = {
    QuestionRepository.class
})
public class SatisfactionSurveyApplication {

  public static void main(String[] args) {
    SpringApplication.run(SatisfactionSurveyApplication.class, args);
  }
}