package com.example.nasaimagesbook.data.translate;

public class TranslateBody {
    private String folderId;
    private String [] texts;
    private String targetLanguageCode;

    public String getFolderId() {
        return folderId;
    }

    public String[] getTexts() {
        return texts;
    }

    public String getTargetLanguageCode() {
        return targetLanguageCode;
    }
}
