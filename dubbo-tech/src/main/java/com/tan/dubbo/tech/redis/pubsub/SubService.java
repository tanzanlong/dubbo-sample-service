package com.tan.dubbo.tech.redis.pubsub;

import redis.clients.jedis.JedisPubSub;

import com.tan.dubbo.tech.proxy.common.JedisClient;

public class SubService {
 
 
 private static class NotifySub extends JedisPubSub {

     public NotifySub() {
     }

     @Override
     public void onMessage(String key, String msg) {
         System.out.println("redis event: " + key + " = " + msg);
     }

     @Override
     public void onPMessage(String pattern, String key, String msg) {
         onMessage(key, msg);
     }

     @Override
     public void onSubscribe(String key, int num) {
     }

     @Override
     public void onPSubscribe(String pattern, int num) {
     }

     @Override
     public void onUnsubscribe(String key, int num) {
     }

     @Override
     public void onPUnsubscribe(String pattern, int num) {
     }

 }
 
 
    public static class Notifier extends Thread {
        private volatile JedisClient jedisClient;
        private volatile boolean running = true;
        String[] channelArray;

        public Notifier(JedisClient jedisClient, String[] channelArray) {
            super.setDaemon(true);
            super.setName("DubboRedisSubscribe");
            this.jedisClient = jedisClient;
            this.channelArray = channelArray;
        }

        @Override
        public void run() {
            while (running) {
                try {
                    jedisClient.subscribeMsg(new NotifySub(), channelArray);// blocking
                } catch (Throwable t) {
                    this.shutdown();
                }
            }
        }

        public void shutdown() {
            try {
                running = false;
                jedisClient.getJedis().close();
            } catch (Throwable t) {
            }
        }

    }

}
