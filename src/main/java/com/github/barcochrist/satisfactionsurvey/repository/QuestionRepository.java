package com.github.barcochrist.satisfactionsurvey.repository;

import com.github.barcochrist.satisfactionsurvey.model.Question;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionRepository {

  @NotNull
  Optional<Question> findById(String id);

  @NotNull
  Page<Question> findAll(Pageable pageable);

  @NotNull
  List<Question> findRequired();
}