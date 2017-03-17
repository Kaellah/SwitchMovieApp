package com.kaellah.switchmovieapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 17.03.17
 */

public class DatesDTO {

    @SerializedName("minimum")
    @Expose
    private String minimum;
    @SerializedName("maximum")
    @Expose
    private String maximum;

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }
}
