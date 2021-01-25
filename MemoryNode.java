package com.company.testm;

public class MemoryNode {

    String contents;

    MemoryNode next;

    public MemoryNode(String contents) {
        this.contents = contents;
        next = null;
    }

    public MemoryNode() {
        contents = null;
        next = null;
    }

    public String getContents() {
        return contents;
    }

    public MemoryNode getNext() {
        return next;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setNext(MemoryNode next) {
        this.next = next;
    }
}
