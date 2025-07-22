package com.tcc.taskmanager.application.dtos.response;

public class DashboardStatsDto {
    private int totalTasks;
    private int completed;
    private int inProgress;
    private int toDo;
    private int highPriority;
    private int mediumPriority;
    private int lowPriority;
    private int overdue;
    private int completionPercentage;

    // Getters y setters
    public int getTotalTasks() { return totalTasks; }
    public void setTotalTasks(int totalTasks) { this.totalTasks = totalTasks; }
    public int getCompleted() { return completed; }
    public void setCompleted(int completed) { this.completed = completed; }
    public int getInProgress() { return inProgress; }
    public void setInProgress(int inProgress) { this.inProgress = inProgress; }
    public int getToDo() { return toDo; }
    public void setToDo(int toDo) { this.toDo = toDo; }
    public int getHighPriority() { return highPriority; }
    public void setHighPriority(int highPriority) { this.highPriority = highPriority; }
    public int getMediumPriority() { return mediumPriority; }
    public void setMediumPriority(int mediumPriority) { this.mediumPriority = mediumPriority; }
    public int getLowPriority() { return lowPriority; }
    public void setLowPriority(int lowPriority) { this.lowPriority = lowPriority; }
    public int getOverdue() { return overdue; }
    public void setOverdue(int overdue) { this.overdue = overdue; }
    public int getCompletionPercentage() { return completionPercentage; }
    public void setCompletionPercentage(int completionPercentage) { this.completionPercentage = completionPercentage; }
} 