import java.util.Iterator;
import java.util.Objects;

public class Hospital2 extends HospitalBase {

    private int index = 0;
    private static Patient[] patientArray;

    private static Patient[][] buckets;

    public Hospital2() {
        /* Add your code here! */
        patientArray = new Patient[0];
        buckets = new Patient[9][0];
    }

    @Override
    public boolean addPatient(PatientBase patient) {
        /* Add your code here! */
        int hour = ((Patient) patient).getHour();
        if (hour >= 8 && hour <= 17 && hour != 12) {
            patientArray = arrayAppend(patientArray, (Patient) patient);
            patientArray = bucketSort(patientArray);
            return true;
        }
        return false;
    }

    @Override
    public Iterator<PatientBase> iterator() {
        /* Add your code here! */
        return new Iterator<PatientBase>() {
            @Override
            public boolean hasNext() {
                return index != patientArray.length;
            }

            @Override
            public PatientBase next() {
                return patientArray[index++];
            }
        };
    }

    /* Add any extra functions below */

    // Expand the array
    public static Patient[] arrayAppend(Patient[] array, Patient patient) {
        Patient[] newArray = new Patient[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = patient;
        return newArray;
    }

    // Bucket sort
    public static Patient[] bucketSort(Patient[] array) {
        if (Objects.equals(array.length, 0)) {
            return array;
        }

        for (int i = array.length - 1; i < array.length; i++) {
            Patient patient = array[i];
            int hour = patient.getHour();
            if (Objects.equals(hour, 8)) {
                buckets[0] = arrayAppend(buckets[0], patient);
            } else if (Objects.equals(hour, 9)) {
                buckets[1] = arrayAppend(buckets[1], patient);
            } else if (Objects.equals(hour, 10)) {
                buckets[2] = arrayAppend(buckets[2], patient);
            } else if (Objects.equals(hour, 11)) {
                buckets[3] = arrayAppend(buckets[3], patient);
            } else if (Objects.equals(hour, 13)) {
                buckets[4] = arrayAppend(buckets[4], patient);
            } else if (Objects.equals(hour, 14)) {
                buckets[5] = arrayAppend(buckets[5], patient);
            } else if (Objects.equals(hour, 15)) {
                buckets[6] = arrayAppend(buckets[6], patient);
            } else if (Objects.equals(hour, 16)) {
                buckets[7] = arrayAppend(buckets[7], patient);
            } else if (Objects.equals(hour, 17)) {
                buckets[8] = arrayAppend(buckets[8], patient);
            }
        }
        int bucketIndex = 0;
        for (int q = 0; q < buckets.length; q++) {
            if (Objects.equals(buckets[q].length, 0)) {
                continue;
            }
            Patient[] a = insertSort(buckets[q]);
            for (int w = 0; w < a.length; w++) {
                array[bucketIndex++] = a[w];
            }
        }
        return array;
    }

    // Insertion sort
    public static Patient[] insertSort(Patient[] array) {
        for (int i = 1; i < array.length; i++) {
            Patient tmp = array[i];
            int j = i;
            while (j >= 0 && tmp.compareTo(array[j-1]) > 0) {
                array[j] = array[j-1];
                j--;
            }
            if (j != i) {
                array[j] = tmp;
            }
        }
        return array;
    }


//    public static void main(String[] args) {
//        /*
//         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//         * REMOVE THE MAIN METHOD BEFORE SUBMITTING TO THE AUTOGRADER
//         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//         * The following main method is provided for simple debugging only
//         */
//        var hospital = new Hospital2();
//        var p1 = new Patient("Max", "11:00");
//        var p2 = new Patient("Alex", "13:20");
//        var p3 = new Patient("George", "14:00");
//        var p4 = new Patient("Penda", "13:20");
//        var p5 = new Patient("abc", "11:30");
//        hospital.addPatient(p1);
//        hospital.addPatient(p4);
//        hospital.addPatient(p3);
//        hospital.addPatient(p5);
//        hospital.addPatient(p2);
//        var patients = new Patient[] {p1, p2, p3};
//        int i = 0;
//        for (var patient : hospital) {
//            System.out.println(patient);
//            assert Objects.equals(patient, patients[i++]);
//        }
//    }
}
