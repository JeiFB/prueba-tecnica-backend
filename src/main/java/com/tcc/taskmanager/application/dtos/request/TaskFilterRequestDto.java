package com.tcc.taskmanager.application.dtos.request;

public class TaskFilterRequestDto {
    private String status;      // Estado de la tarea
    private String priority;    // Prioridad
    private String fromDate;    // Fecha inicio (opcional, formato yyyy-MM-dd)
    private String toDate;      // Fecha fin (opcional, formato yyyy-MM-dd)

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getFromDate() { return fromDate; }
    public void setFromDate(String fromDate) { this.fromDate = fromDate; }

    public String getToDate() { return toDate; }
    public void setToDate(String toDate) { this.toDate = toDate; }
} 