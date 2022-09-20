import java.util.Iterator;
import java.util.Objects;

public class Hospital1 extends HospitalBase {

    private int index = 0;
    private static Patient[] patientArray;

    public Hospital1() {
        /* Add your code here! */
        patientArray = new Patient[1];
    }

    @Override
    public boolean addPatient(PatientBase patient) {
        /* Add your code here! */
        String[] time = patient.getTime().split(":");
        int hour = Integer.parseInt(time[0]);
        int mins = Integer.parseInt(time[1]);
        if (patientArray.length != 1) {
            for (int i = 0; i < patientArray.length-1; i++) {
                if (Objects.equals(patientArray[i].getTime(), patient.getTime())) {
                    return false;
                }
            }
        }
        if (hour >= 8 && hour <= 17 && hour != 12) {
            if (Objects.equals(mins, 0) || Objects.equals(mins, 20)  || Objects.equals(mins, 40) ) {
                patientArray[patientArray.length - 1] = (Patient) patient;
                sortArray();
                patientArray = addLengthArray(patientArray);
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<PatientBase> iterator() {
        /* Add your code here! */
        return new Iterator<PatientBase>() {
            @Override
            public boolean hasNext() {
                return index != patientArray.length-1;
            }

            @Override
            public PatientBase next() {
                return patientArray[index++];
            }
        };
    }

    /* Add any extra functions below */

    // Expand the array
    public static Patient[] addLengthArray(Patient[] array) {
        Patient[] newArray = new Patient[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    // Sort patient array use Insertion sort
    public static void sortArray() {
        int i, j;
        Patient p;
        for (i = 1; i != patientArray.length; i++) {
            p = patientArray[i];
            j = i - 1;
            while (j >= 0 && patientArray[j].compareTo(p) > 0) {
                patientArray[j+1] = patientArray[j];
                j--;
            }
            patientArray[j+1] = p;
        }
    }

//    public static void main(String[] args) {
//        /*
//         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//         * REMOVE THE MAIN METHOD BEFORE SUBMITTING TO THE AUTOGRADER
//         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//         * The following main method is provided for simple debugging only
//         */
//        var hospital = new Hospital1();
//        var p1 = new Patient("Max", "11:00");
//        var p2 = new Patient("Alex", "13:00");
//        var p3 = new Patient("George", "14:00");
//        hospital.addPatient(p1);
//        hospital.addPatient(p2);
//        hospital.addPatient(p3);
//        var patients = new Patient[] { p1, p2, p3 };
//        int i = 0;
//        for (var patient : hospital) {
//            System.out.println(patient);
//            if (!Objects.equals(patient, patients[i++])) {
//                System.err.println("Wrong patient encountered, check your implementation!");
//            }
//        }
//    }
}
