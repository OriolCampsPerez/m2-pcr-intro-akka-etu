package m2dl.pcr.akka.helloworld4;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;


public class HelloActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    //private ActorRef nameActorRef;

    public HelloActor() {
        log.info("HelloActor constructor");
        //nameActorRef = getContext().actorOf(Props.create(NameActor.class), "name-actor");
    }

    @Override
    public void onReceive(Object msg) {
        if (msg instanceof String) {
            log.info("Hello " + msg + "!");
            //nameActorRef.tell(msg,getSelf());
        } else {
            unhandled(msg);
        }
    }


}
