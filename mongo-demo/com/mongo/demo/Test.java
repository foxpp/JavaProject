package com.mongo.demo;

import com.mongodb.*;
import org.junit.Assert;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaochao on 15/8/31.
 */
public class Test {
    public static void main(String[] args) {

        MongoClient mongo = null;
        try {
            MongoClientOptions options = MongoClientOptions.builder()
                    .connectionsPerHost(3000)
                    .threadsAllowedToBlockForConnectionMultiplier(10)
                    .readPreference(ReadPreference.nearest())
                    .build();
            ServerAddress serverAddress = new ServerAddress("172.20.5.57", 30000);


            MongoCredential credentials = MongoCredential.createMongoCRCredential("ofc", "ofc",
                    "ofc321".toCharArray());

            List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
            credentialsList.add(credentials);

            mongo =  new MongoClient(serverAddress, credentialsList ,options);

        } catch (MongoException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(mongo);
        System.out.println(mongo.getAddress().toString());

    }
}
