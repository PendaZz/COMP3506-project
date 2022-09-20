public class Patient extends PatientBase {

    private String[] times;

    public Patient(String name, String time) {
        super(name, time);
        times = this.getTime().split(":");
    }

    @Override
    public int compareTo(PatientBase o) {
        /* Add your code here! */
        if (o == null) {
            return 0;
        }
        int hour1 = this.getHour();
        int mins1 = this.getMinute();
        int hour2 = ((Patient) o).getHour();
        int mins2 = ((Patient) o).getMinute();
        if (hour1 <= hour2) {
            return 0;
        } else if (hour1 > hour2) {
            return 1;
        } else {
            if (mins1 <= mins2) {
                return 0;
            } else {return 1;}
        }
    }

    /* Add any extra functions below */

    public int getHour() {
        return Integer.parseInt(times[0]);
    }

    public int getMinute() {
        return Integer.parseInt(times[1]);
    }
}
