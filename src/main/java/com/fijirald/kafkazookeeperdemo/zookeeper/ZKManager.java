package com.fijirald.kafkazookeeperdemo.zookeeper;

import org.apache.zookeeper.KeeperException;

public interface ZKManager {
    public void create(String path, byte[] data)
            throws KeeperException, InterruptedException;
    public Object getZNodeData(String path, boolean watchFlag) throws KeeperException, InterruptedException;
    public void update(String path, byte[] data)
            throws KeeperException, InterruptedException;
}
