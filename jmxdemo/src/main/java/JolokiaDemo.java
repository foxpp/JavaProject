/**
 * Created by zhaochao on 15/9/8.
 */

import org.jolokia.client.*;
import org.jolokia.client.request.*;

import java.util.Map;

public class JolokiaDemo {

    public static void main(String[] args) throws Exception {
        J4pClient j4pClient = new J4pClient("http://localhost:8080/jolokia");
        J4pReadRequest req = new J4pReadRequest("java.lang:type=Memory",
                "HeapMemoryUsage");
        J4pReadResponse resp = j4pClient.execute(req);
        Map<String, String> vals = resp.getValue();
        int used = Integer.parseInt(vals.get("used"));
        int max = Integer.parseInt(vals.get("max"));
        int usage = (int) (used * 100 / max);
        System.out.println("Memory usage: used: " + used +
                " / max: " + max + " = " + usage + "%");
    }
}

