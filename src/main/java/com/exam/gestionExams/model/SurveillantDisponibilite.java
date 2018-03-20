package com.exam.gestionExams.model;

import java.util.List;

public class SurveillantDisponibilite {
    private Surveillant surveillant;
    private List<Long> ids;

    public Surveillant getSurveillant() {
        return surveillant;
    }

    public void setSurveillant(Surveillant surveillant) {
        this.surveillant = surveillant;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
