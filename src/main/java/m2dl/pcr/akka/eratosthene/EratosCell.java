package m2dl.pcr.akka.eratosthene;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;


public class EratosCell extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private final int cellValue;
    private ActorRef nextCell;

    public EratosCell(int cellValue) {
        log.info("EratosCell constructor " + cellValue);
        this.cellValue = cellValue;
    }

    Procedure<Object> started = new Procedure<Object>() {
        public void apply(Object msg) {
            if ((msg instanceof Integer) && (Integer) msg % cellValue != 0) {
                log.info("Prime found: " + msg);

                nextCell = getContext().actorOf(Props.create(EratosCell.class, msg), msg.toString());
                getContext().become(chained,false);
            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> chained = new Procedure<Object>() {
        public void apply(Object msg) {
            if ((msg instanceof Integer) && (Integer) msg % cellValue != 0) {
                nextCell.tell(msg, getSelf());
            } else {
                unhandled(msg);
            }
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        started.apply(msg);
    }

    @Override
    public void unhandled(Object msg) {
        super.unhandled(msg);
        log.info("Discarded non-prime int: " + msg);
    }

}
