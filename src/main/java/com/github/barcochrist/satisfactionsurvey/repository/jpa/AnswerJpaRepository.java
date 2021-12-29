package com.github.barcochrist.satisfactionsurvey.repository.jpa;

import com.github.barcochrist.satisfactionsurvey.entity.AnswerJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerJpaRepository extends JpaRepository<AnswerJpa, String> {

}