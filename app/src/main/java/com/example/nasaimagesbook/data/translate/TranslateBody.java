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

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public void setTexts(String[] texts) {
        this.texts = texts;
    }

    public void setTargetLanguageCode(String targetLanguageCode) {
        this.targetLanguageCode = targetLanguageCode;
    }
}
