package scripts;

import org.tribot.script.Script;
import scripts.utl.ZybezRSItem;

public class Clazz extends Script {

    @Override
    public void run() {
        ZybezRSItem item = new ZybezRSItem("Bronze bar");
        println(item.getAveragePrice());
        println(item.getFullJsonReturn());
    }

}
