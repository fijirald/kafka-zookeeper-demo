package com.fijirald.kafkazookeeperdemo.zookeeper;

import lombok.SneakyThrows;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZKManagerImpl implements ZKManager {
    private static ZooKeeper zkeeper;
    private static ZKConnection zkConnection;

    public ZKManagerImpl() {
        initialize();
    }

    @SneakyThrows
    private void initialize() {
        zkConnection = new ZKConnection();
        zkeeper = zkConnection.connect("localhost");
    }

    @SneakyThrows
    public void closeConnection() {
        zkConnection.close();
    }

    public void create(String path, byte[] data)
            throws KeeperException,
            InterruptedException {

        zkeeper.create(
                path,
                data,
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
    }

    @SneakyThrows
    public Object getZNodeData(String path, boolean watchFlag)
            throws KeeperException,
            InterruptedException {

        byte[] b = null;
        b = zkeeper.getData(path, null, null);
        return new String(b, "UTF-8");
    }

    public void update(String path, byte[] data) throws KeeperException,
            InterruptedException {
        int version = zkeeper.exists(path, true).getVersion();
        zkeeper.setData(path, data, version);
    }
}
