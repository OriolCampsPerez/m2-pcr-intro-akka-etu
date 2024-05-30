package m2dl.pcr.akka.helloworld4;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;


public class GoodbyeActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    //private ActorRef nameActorRef;

    public GoodbyeActor() {
        log.info("GoodbyeActor constructor");
        //nameActorRef = getContext().actorOf(Props.create(NameActor.class), "goodbye-actor");
    }

    @Override
    public void onReceive(Object msg) {
        if (msg instanceof String) {
            log.info("Goodbye " + msg + "!");
            //nameActorRef.tell(msg,getSelf());
        } else {
            unhandled(msg);
        }
    }


}
