package com.example.converter.main;

public interface MyView {
    double getInput();

    Type getFromType();

    Type getToType();

    void updateConvertedValue(double convertedValue);
}
