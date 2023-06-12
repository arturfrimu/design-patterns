package com.arturfrimu.pattern.state;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/state")
public class StateController {

    @GetMapping
    public void handle() {
        Context context = new Context(new StateA());
        context.request();
        context.request();
        context.request();
        context.request();
    }

    interface State {
        void handle(Context context);
    }

    class StateA implements State {
        public void handle(Context context) {
            context.setState(new StateB());
            log.info("State A handled, switched to State B");
        }
    }

    class StateB implements State {
        public void handle(Context context) {
            context.setState(new StateA());
            log.info("State B handled, switched to State A");
        }
    }

    static class Context {
        private State state;

        public Context(State state) {
            this.state = state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public void request() {
            state.handle(this);
        }
    }
}
