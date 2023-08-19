package com.sophossolutions.programacion.reactiva.seguimiento2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrdenLocal{
    private Integer id;
    private LocalDateTime fecha;
    private Double total;

    @Override
    public String toString() {
        return "{" +
                "'id':" + id +
                ", 'fecha':'" + fecha + '\'' +
                ", 'total':" + total +
                '}';
    }
}
