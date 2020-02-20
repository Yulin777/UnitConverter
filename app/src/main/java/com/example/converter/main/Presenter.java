package com.example.converter.main;

class Presenter {
    private MyView mView;

    Presenter(MyView view) {
        mView = view;
    }

    void onConversionBtnClicked() {
        //input
        //type from
        //type to
        //conversion
        //return converted value

        double input = mView.getInput();
        Type from = mView.getFromType();
        Type to = mView.getToType();

        double convertedValue = calculateConvertedValue(input, from, to);

        mView.updateConvertedValue(convertedValue);
    }

    private double calculateConvertedValue(double input, Type from, Type to) {
        switch (from) {
            case MM:
                switch (to) {
                    case MM:
                        return input;
                    case CM:
                        return input / 10;
                    case DM:
                        return input / 100;
                    case METER:
                        return input / 1000;
                    case KM:
                        return input / 1000000;
                }
                break;
            case CM:
                switch (to) {
                    case MM:
                        return input * 10;
                    case CM:
                        return input;
                    case DM:
                        return input / 10;
                    case METER:
                        return input / 100;
                    case KM:
                        return input / 100000;
                }
                break;

            case DM:
                switch (to) {
                    case MM:
                        return input * 100;
                    case CM:
                        return input * 10;
                    case DM:
                        return input;
                    case METER:
                        return input / 10;
                    case KM:
                        return input / 10000;
                }
                break;

            case METER:
                switch (to) {
                    case MM:
                        return input * 1000;
                    case CM:
                        return input * 100;
                    case DM:
                        return input * 10;
                    case METER:
                        return input;
                    case KM:
                        return input / 1000;
                }
                break;

            case KM:
                switch (to) {
                    case MM:
                        return input / 1000000;
                    case CM:
                        return input / 100000;
                    case DM:
                        return input / 10000;
                    case METER:
                        return input / 1000;
                    case KM:
                        return input;
                }

        }
        return -1;
    }
}