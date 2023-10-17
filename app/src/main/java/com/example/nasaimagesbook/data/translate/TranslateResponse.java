package com.example.nasaimagesbook.data.translate;

public class TranslateResponse {
    public class Translations{
        public String text;
        public String detectedLanguageCode;
    }

    public Translations [] translations;
}
