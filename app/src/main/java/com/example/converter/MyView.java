package com.example.converter;

public interface MyView {
    double getInput();

    Type getFromType();

    Type getToType();

    void updateConvertedValue(double convertedValue);
}
