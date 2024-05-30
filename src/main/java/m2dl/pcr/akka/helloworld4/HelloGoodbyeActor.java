package m2dl.pcr.akka.helloworld4;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;


public class HelloGoodbyeActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private final ActorRef helloActorRef;
    private final ActorRef goodbyeActorRef;

    public HelloGoodbyeActor() {
        log.info("HelloGoodbyeActor constructor");

        helloActorRef = getContext().actorOf(Props.create(HelloActor.class), "hello-actor");
        goodbyeActorRef = getContext().actorOf(Props.create(GoodbyeActor.class), "name-actor");
    }

    Procedure<Object> hello = new Procedure<Object>() {
        public void apply(Object msg) {
            if (msg instanceof String) {
                //log.info("Hello " + msg + "!");
                helloActorRef.tell(msg,getSelf());
                getContext().become(goodbye,false);
            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> goodbye = new Procedure<Object>() {
        public void apply(Object msg) {
            if (msg instanceof String) {
                //log.info("Good bye " + msg + "!");
                goodbyeActorRef.tell(msg,getSelf());
                getContext().unbecome();
            } else {
                unhandled(msg);
            }
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        hello.apply(msg);
    }


}
