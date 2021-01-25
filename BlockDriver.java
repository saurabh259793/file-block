package com.company.testm;

public class BlockDriver {

    MemoryNode head;

    MemoryNode freeNode;

    static int BLOCK_SIZE = 100;

    static double TOTAL_MEMORY = 1e+9;

    public BlockDriver() {

        head = new MemoryNode();
        MemoryNode next = head;
        freeNode = head;

        for(int i=0; i<TOTAL_MEMORY/BLOCK_SIZE; i++) {
            MemoryNode temp = new MemoryNode();
            next.next = temp;
            next = temp;
        }
    }

    private synchronized MemoryNode storeInMemory(MemoryNode memoryNode, String contents) {

        if(memoryNode == null)
            return null;

        memoryNode.setContents(contents);
        return getNextFreeMemoryNode();
    }

    public synchronized void storeFile(MemoryNode headNode, String contents) {

        if(contents.getBytes().length == 0 || freeNode == null)
            return;

        MemoryNode tempNode = headNode;
        String temp = "";
        int currentSize = 0;

        int eachCharMemory = contents.getBytes().length/contents.length();

        for(int i=0; i<contents.length(); i++) {
            if(currentSize + eachCharMemory >= BLOCK_SIZE) {
                tempNode = storeInMemory(tempNode, temp);
                temp = "";
                currentSize = 0;
            } else {
                temp+=contents.charAt(i);
                currentSize+=eachCharMemory;
            }
        }

        tempNode = storeInMemory(tempNode, temp);
        tempNode.next = null;

        return;
    }

    public synchronized void deleteFile(MemoryNode memoryNode) {

        MemoryNode currentFreeNode = freeNode;

        if(currentFreeNode == null) {
            currentFreeNode = memoryNode;
            freeNode = memoryNode;
        }

        else {
            while (currentFreeNode.next != null)
                currentFreeNode = currentFreeNode.next;
            currentFreeNode.next = memoryNode;
            currentFreeNode = memoryNode;
        }

        while (currentFreeNode.next != null) {
            currentFreeNode.setContents(null);
            currentFreeNode = currentFreeNode.next;
        }

    }

    public synchronized MemoryNode getNextFreeMemoryNode() {

        if(freeNode == null)
            return null;

        MemoryNode memoryNode = freeNode;
        freeNode = freeNode.next;

        return memoryNode;
    }

    public synchronized String getFile(MemoryNode memoryNode) {

        if(memoryNode == null)
            return null;

        MemoryNode temp = memoryNode;

        String result = "";

        while (temp.next != null) {
            result += temp.getContents();
            temp = temp.next;
        }

        return result;
    }
}
