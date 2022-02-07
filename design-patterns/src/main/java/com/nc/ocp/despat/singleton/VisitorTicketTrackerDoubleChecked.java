package com.nc.ocp.despat.singleton;

public class VisitorTicketTrackerDoubleChecked {

    private static volatile VisitorTicketTrackerDoubleChecked instance;

    private VisitorTicketTrackerDoubleChecked() {}

    public static VisitorTicketTrackerDoubleChecked getInstance() {
        if(instance == null) {
            synchronized (VisitorTicketTrackerDoubleChecked.class) {
                if(instance == null) {
                    instance = new VisitorTicketTrackerDoubleChecked();
                }
            }
        }
        return instance;
    }

    // Data access methods...
}
