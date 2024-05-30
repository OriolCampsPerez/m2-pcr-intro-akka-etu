package m2dl.pcr.akka.eratosthene;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;


public class Launcher extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public Launcher() {
        log.info("Launcher constructor");
    }

    @Override
    public void onReceive(Object max) {
        if (max instanceof Integer) {
            log.info("Starting EratosCell");
            ActorRef firstCell = getContext().actorOf(Props.create(EratosCell.class, 2), "2-cell-actor");
            for (int i = 3; i <= (Integer) max; i++) {
                firstCell.tell(i, getSelf());
            }
        } else {
            unhandled(max);
        }
    }


}
