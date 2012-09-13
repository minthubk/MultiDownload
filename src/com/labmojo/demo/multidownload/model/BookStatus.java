package com.labmojo.demo.multidownload.model;

public class BookStatus {

    private final long bookId;
    private final int percent;

    public long getBookId() {
        return bookId;
    }

    public int getPercent() {
        return percent;
    }

    public BookStatus(long bookId, int percent) {
        this.bookId = bookId;
        this.percent = percent;
    }
}
