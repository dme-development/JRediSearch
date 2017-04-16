package io.redisearch.client;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by dvirvolk on 12/04/2017.
 */
public class ClusterClient extends Client {

    /**
     * Create a new client to a RediSearch index
     *
     * @param indexName the name of the index we are connecting to or creating
     * @param host      the redis host
     * @param port      the redis pot
     */
    public ClusterClient(String indexName, String host, int port) {
        super(indexName, host, port);
        this.commands = new Commands.ClusterCommands();
    }

    public List<Object> broadcast(String... args) {

        Jedis conn = _conn();
        List ret = conn.getClient().sendCommand(Commands.ClusterCommand.BROADCAST, args).getObjectMultiBulkReply();
        conn.close();
        return ret;
    }
}
