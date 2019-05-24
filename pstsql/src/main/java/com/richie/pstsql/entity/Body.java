package com.richie.pstsql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * pstsql
 * <p>
 * Created by lylaut on 2019-05-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Body {
    private Double stature;
    private Double weight;
    private Double cranialCapacity;
}
