package com.arturfrimu.pattern.proxy.gumball;

import lombok.Getter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@Getter
public final class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote {

    private static final long serialVersionUID = 2L;

    private State soldOutState;
    private State state = soldOutState;
    private int count;

    private final State noQuarterState;
    private final State hasQuarterState;
    private final State soldState;
    private final State winnerState;
    private final String location;

    public GumballMachine(String location, int numberGumballs) throws RemoteException {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        winnerState = new WinnerState(this);

        this.count = numberGumballs;
        if (numberGumballs > 0) {
            state = noQuarterState;
        }
        this.location = location;
    }

    void setState(State state) {
        this.state = state;
    }

    void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count = count - 1;
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\nMighty Gumball, Inc.");
        result.append("\nJava-enabled Standing Gumball Model #2014");
        result.append("\nInventory: ").append(count).append(" gumball");
        if (count != 1) {
            result.append("s");
        }
        result.append("\n").append("Machine is ").append(state).append("\n");
        return result.toString();
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public String getLocation() {
        return location;
    }
}
