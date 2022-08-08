package scheduler;

import hospital.Doctor;

public class Scheduler {
    private SchedulerNode head;
    private SchedulerNode curr;
    private SchedulerNode tail;
    private int numDoctors;

    public Scheduler() {
        head = null;
        curr = null;
        tail = null;
        numDoctors = 0;
    }

    public void addDoctor(Doctor doctor) {
        SchedulerNode nextDoctor = new SchedulerNode(doctor);

        if (head == null) {
            head = nextDoctor;
            curr = head;
        } else {
            tail.next = nextDoctor;
        }

        tail = nextDoctor;
        tail.next = head;

        numDoctors++;
    }

    public Doctor getDoctor() {
        Doctor doctor = curr.doctor;
        curr = curr.next;
        return doctor;
    }

    public int getNumDoctors() {
        return numDoctors;
    }

    public void reset() {
        curr = head;
    }
}