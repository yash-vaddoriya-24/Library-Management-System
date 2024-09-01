package org.example;

import java.util.Random;

public class Library {
    private final String title;
    private final String author;
    private final String publisherYear;
    public Library(String title, String author, String publisherYear) {
        this.title = title;
        this.author = author;
        this.publisherYear = publisherYear;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisherYear() {
        return publisherYear;
    }

    public String generateISBN(){
        Random rand = new Random();

        return "ISBN-" + (rand.nextInt(900000000) + 100000000);
    }
}


