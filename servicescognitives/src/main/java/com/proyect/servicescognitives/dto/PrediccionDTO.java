package com.proyect.servicescognitives.dto;

import java.util.List;

public class PrediccionDTO {
    private String id;
    private String project;
    private String iteration;
    private String created;
    private List<Object> predictions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getIteration() {
        return iteration;
    }

    public void setIteration(String iteration) {
        this.iteration = iteration;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<Object> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Object> predictions) {
        this.predictions = predictions;
    }
}
