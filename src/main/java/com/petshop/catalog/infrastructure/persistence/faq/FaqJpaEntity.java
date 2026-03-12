package com.petshop.catalog.infrastructure.persistence.faq;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "faq")
public class FaqJpaEntity {

    @Id
    private UUID id;
    private String question;
    private String answer;

    public FaqJpaEntity() {

    }

    public FaqJpaEntity(UUID id, String question, String answer) {
        setQuestion(question);
        setAnswer(answer);
    }

    public UUID getId() {
        return id;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}