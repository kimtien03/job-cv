package org.example.Models;

import java.util.List;

public class PaginatedResponse {
    private List<Template_cvs> content;
    private int totalPages;
    private int number;
    private boolean first;
    private boolean last;
    public PaginatedResponse()
    {

    }
    public PaginatedResponse(List<Template_cvs> content, int totalPages, int number, boolean first, boolean last)
    {
        this.setContent(content);
        this.setTotalPages(totalPages);
        this.setNumber(number);
        this.setFirst(first);
        this.setLast(last);
    }

    public List<Template_cvs> getContent() {
        return content;
    }

    public void setContent(List<Template_cvs> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}