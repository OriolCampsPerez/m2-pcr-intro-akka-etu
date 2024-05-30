package m2dl.pcr.akka.eratosthene;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Main runtime class.
 */
public class System {

    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        final ActorRef actorRef = actorSystem.actorOf(Props.create(Launcher.class), "launcher-actor");

        actorRef.tell(100,null);

        Thread.sleep(3000);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }
}
