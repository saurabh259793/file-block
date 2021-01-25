package com.company.testm;

import java.util.Hashtable;

public class FileSystemImpl {

    static Hashtable<String, MemoryNode> fileMap = new Hashtable<>();

    static BlockDriverImpl blockDriver = BlockDriverImpl.getBlockDriver();

    public static MemoryNode fopen(String fileName) {

        MemoryNode memoryNode = null;
        if(fileMap.containsKey(fileName))
            memoryNode = fileMap.get(fileName);
        else {
            memoryNode = blockDriver.getNextFreeMemoryNode();
            fileMap.put(fileName, memoryNode);
        }

        return memoryNode;
    }

    public static MemoryNode fwrite(String filename, String contents) {

        MemoryNode memoryNode = fopen(filename);
        blockDriver.storeFile(memoryNode, contents);
        return memoryNode;
    }

    public static void remove(String fileName) {
        if(!fileMap.containsKey(fileName))
            return;
        MemoryNode memoryNode = fileMap.get(fileName);
        blockDriver.deleteFile(memoryNode);
    }

    public static void main(String[] args) {
        MemoryNode memoryNode = fwrite("test", "asdadadasdasdasdadadsajasdaksdbkbdowqdbas" +
                "\n" +
                "sdadkajdbkajdbkajsbdkasbdkasbdkasbdkasbdkajsbdkasbdkajsdbsaasda" +
                "\n" +  "adasdasdbidbioubiaubsaiucbaiubiuvbaovbnivobnaboudahgudhoaidjpowjdpoajdpakm xsx ljncinvba;gbrjvbnaofuh98hoinflndlkandslkan");



        System.out.println(memoryNode);
        remove("test");

        System.out.println(memoryNode);

    }
}
