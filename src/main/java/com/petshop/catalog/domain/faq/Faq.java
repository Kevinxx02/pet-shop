package com.petshop.catalog.domain.faq;

import java.util.UUID;

public class Faq {
    private UUID id;
    private String question;
    private String answer;

    private Faq(UUID id, String question, String answer) {
        this.setId(id);
        this.setQuestion(question);
        this.setAnswer(answer);
    }

    public static Faq create(String question, String answer) {
        final UUID id = UUID.randomUUID();
        return new Faq(id, question, answer);
    }

    public static Faq rehydrate(UUID id, String question, String answer) {
        return new Faq(id, question, answer);
    }

    public UUID getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private void setQuestion(String question) {
        this.question = question;
    }

    private void setAnswer(String answer) {
        this.answer = answer;
    }
}