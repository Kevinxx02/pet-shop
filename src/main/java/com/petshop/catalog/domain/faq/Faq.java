package com.petshop.catalog.domain.faq;

import java.util.UUID;

public class Faq {
    private UUID id;
    private String question;
    private String answer;
    private boolean isVisible;

    private Faq(UUID id, String question,
                String answer, boolean isVisible) {
        this.setId(id);
        this.setQuestion(question);
        this.setAnswer(answer);
        this.setVisible(isVisible);
    }

    public static Faq create(String question, String answer) {
        final UUID id = UUID.randomUUID();
        final boolean isVisible = true;

        return new Faq(id, question, answer, isVisible);
    }

    public static Faq rehydrate(UUID id, String question, String answer, boolean isVisible) {
        return new Faq(id, question, answer, isVisible);
    }

    public void update(String question, String answer, boolean isVisible) {
        this.setQuestion(question);
        this.setAnswer(answer);
        this.setVisible(isVisible);
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

    private void setId(UUID id) {
        this.id = id;
    }

    private void setQuestion(String question) {
        this.question = question;
    }

    private void setAnswer(String answer) {
        this.answer = answer;
    }

    private void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public boolean getVisible() {
        return this.isVisible;
    }
}