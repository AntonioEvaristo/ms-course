package com.devsuperior.hrpayroll.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer days;
    private Double dailyIncome;


    public Double getTotal(){
        return this.days * this.dailyIncome;
    }
}
