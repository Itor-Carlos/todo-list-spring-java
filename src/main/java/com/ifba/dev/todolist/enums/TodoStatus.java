package com.ifba.dev.todolist.enums;

public enum TodoStatus {

    PENDENTE("pendente"),
    CONCLUIDO("concluido");

    private String status;

    TodoStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
