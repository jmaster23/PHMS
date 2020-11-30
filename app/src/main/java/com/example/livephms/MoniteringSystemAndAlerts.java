package com.example.livephms;

public class MoniteringSystemAndAlerts {
    public void glucoseAlert() {
        String defaultMsg = "Warning! You have Diabetic glucose levels!";
        Communication com = new Communication();
        com.textSMS(defaultMsg);
    }

    public void bloodPressureAlert() {
        String defaultMsg = "Hypertensive Crisis! Seek Emergency Care!";
        Communication com = new Communication();
        com.textSMS(defaultMsg);
    }

    public void cholesterolAlert() {
        String defaultMsg = "Warning! You have High Total Cholesterol!";
        Communication com = new Communication();
        com.textSMS(defaultMsg);
    }

    public void medConsumptionAlert() {
        String defaultMsg = "Time to take your " + R.id.dosage + " of " + R.id.medicationName + ".\nLook for a " + R.id.medicationColor + " " + R.id.dosageType + ".";
        Communication com = new Communication();
        com.textSMS(defaultMsg);
        com.medicationConsumptionNotification();
    }

    public void medConflictAlert() {
        String defaultMsg = "Stop all use of " + R.id.medicationName + " immediately!";
        Communication com = new Communication();
        com.textSMS(defaultMsg);
    }

    public void medRenewalAlert() {
        String defaultMsg = "Time to renew your " + R.id.medicationName + ".\n";
        Communication com = new Communication();
        com.textSMS(defaultMsg);
    }

    public void annualCheckupAlert() {
        String defaultMsg = "Time for your annual checkup with Dr. "; //+ R.id.doctorLastName;
        Communication com = new Communication();
        com.textSMS(defaultMsg);
    }
}
