-- -----------------------------------------------------
-- Table "questions"
-- -----------------------------------------------------
CREATE TABLE "questions" (
  "id" VARCHAR(40) NOT NULL,
  "title" VARCHAR NOT NULL,
  "type" VARCHAR(20) NOT NULL,
  "is_required" BOOLEAN NOT NULL,
  PRIMARY KEY ("id"))
;

-- -----------------------------------------------------
-- Table "answers"
-- -----------------------------------------------------
CREATE TABLE "answers" (
  "id" VARCHAR(40) NOT NULL,
  "customer_name" VARCHAR(100) NOT NULL,
  "email" VARCHAR(100) NOT NULL,
  PRIMARY KEY ("id"))
;

-- -----------------------------------------------------
-- Table "question_options"
-- -----------------------------------------------------
CREATE TABLE "question_options" (
  "id" VARCHAR(40) NOT NULL,
  "questions_id" VARCHAR(40) NOT NULL,
  "name" VARCHAR(100) NOT NULL,
  PRIMARY KEY ("id"))
;

-- -----------------------------------------------------
-- Table "answer_questions"
-- -----------------------------------------------------
CREATE TABLE "answer_questions" (
  "id" VARCHAR(40) NOT NULL,
  "questions_id" VARCHAR(40) NOT NULL,
  "answers_id" VARCHAR(40) NOT NULL,
  "response" VARCHAR(500) NULL,
  PRIMARY KEY ("id"))
;

-- -----------------------------------------------------
-- Table "answer_question_options"
-- -----------------------------------------------------
CREATE TABLE "answer_question_options" (
  "id" VARCHAR(40) NOT NULL,
  "name" VARCHAR(100) NOT NULL,
  "answer_questions_id" VARCHAR(40) NOT NULL,
  "question_options_id" VARCHAR(40) NOT NULL,
  PRIMARY KEY ("id"))
;