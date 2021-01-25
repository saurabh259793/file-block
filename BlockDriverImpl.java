package com.company.testm;

public class BlockDriverImpl extends BlockDriver {

    static BlockDriverImpl blockDriver = new BlockDriverImpl();

    public static BlockDriverImpl getBlockDriver() {
        return blockDriver;
    }

}
