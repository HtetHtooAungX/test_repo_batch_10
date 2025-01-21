package org.example.foodie.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
public class Remark {
    private String remark;
    private String status;
    private LocalDate remarkDate;

    public Remark() {
    }

    public Remark(String remark, String status, LocalDate remarkDate) {
        this.remark = remark;
        this.status = status;
        this.remarkDate = remarkDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getRemarkDate() {
        return remarkDate;
    }

    public void setRemarkDate(LocalDate remarkDate) {
        this.remarkDate = remarkDate;
    }
}
