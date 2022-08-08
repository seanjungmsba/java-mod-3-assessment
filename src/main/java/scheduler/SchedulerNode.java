package scheduler;

import hospital.Doctor;

public class SchedulerNode {
    Doctor doctor;
    SchedulerNode next;

    SchedulerNode(Doctor doctor) {
        this.doctor = doctor;
    }

}