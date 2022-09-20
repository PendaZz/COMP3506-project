import java.util.Objects;

public class LoginSystem extends LoginSystemBase {

    private int size;
    private int numUser;
    private MyArrayList<MyLinkedList> myHashMap;

    public LoginSystem() {
        size = 101;
        numUser = 0;
        myHashMap = new MyArrayList<MyLinkedList>();
        for (int i = 0; i < size; i++) {
            myHashMap.add(i, new MyLinkedList());
        }
    }

    @Override
    public int size() {
        /* Add your code here! */
        return myHashMap.getSize();
    }

    @Override
    public int getNumUsers() {
        /* Add your code here! */
        for (int i = 0; i < myHashMap.getSize(); i++) {
            if (!myHashMap.get(i).isEmpty()) {
                numUser = numUser + myHashMap.get(i).size();
            }
        }
        return numUser;
    }

    @Override
    public int hashCode(String key) {
        /* Add your code here! */
        int size = key.length();
        int[] tmp = new int[size];
        int c = 31;
        int sum = key.charAt(0);
        tmp[0] = key.charAt(0);
        for (int i = 1; i < size; i++) {
            sum = sum * c + key.charAt(i);
            tmp[i] = key.charAt(i);
        }
        return sum;
    }

    @Override
    public boolean addUser(String email, String password) {
        /* Add your code here! */
        int emailHashCode = hashCode(email);
        int emailIndex = compressionFunc(emailHashCode);
        MyLinkedList linkedList1 = myHashMap.get(emailIndex);
        int pasHashCode = hashCode(password);
        if (Objects.equals(linkedList1.find(emailHashCode), null)) {
            linkedList1.insert(emailHashCode, pasHashCode);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeUser(String email, String password) {
        /* Add your code here! */
        int emailHashCode = hashCode(email);
        int emailIndex = compressionFunc(emailHashCode);
        MyLinkedList linkedList1 = myHashMap.get(emailIndex);
        if (linkedList1.find(emailHashCode) != null) {
            linkedList1.delete(emailHashCode);
            return true;
        }
        return false;
    }

    @Override
    public int checkPassword(String email, String password) {
        /* Add your code here! */
        int emailHashCode = hashCode(email);
        int emailIndex = compressionFunc(emailHashCode);
        MyLinkedList linkedList1 = myHashMap.get(emailIndex);
        int pasHashCode = hashCode(password);
        int pasIndex = compressionFunc(pasHashCode);
        if (linkedList1.find(emailHashCode) == null) {
            return -1;
        }
        if (linkedList1.find(emailHashCode).value != pasHashCode) {
            return -2;
        }
        return compressionFunc(linkedList1.find(emailHashCode).value);
    }

    @Override
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        /* Add your code here! */
        removeUser(email, oldPassword);
        return addUser(email, newPassword);
    }

    /* Add any extra functions below */
    public int compressionFunc(int hashCode) {
        return hashCode%size();
    }

//    public static void main(String[] args) {
//        /*
//         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//         * REMOVE THE MAIN METHOD BEFORE SUBMITTING TO THE AUTOGRADER
//         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//         * The following main method is provided for simple debugging only
//         */
//        LoginSystem loginSystem = new LoginSystem();
//        assert loginSystem.hashCode("GQHTMP") == loginSystem.hashCode("H2HTN1");
//        System.out.println(loginSystem.hashCode("a@b.c"));
//        assert loginSystem.size() == 101;
//        System.out.println(loginSystem.size());
//        assert loginSystem.checkPassword("a@b.c", "L6ZS9") == -1;
//        System.out.println(loginSystem.checkPassword("a@b.c", "L6ZS9"));
//        System.out.println(loginSystem.addUser("a@b.c", "L6ZS9"));
//        assert loginSystem.checkPassword("a@b.c", "ZZZZZZ") == -2;
//        System.out.println(loginSystem.checkPassword("a@b.c", "ZZZZZZ"));
//        assert loginSystem.checkPassword("a@b.c", "L6ZS9") == 94;
//        System.out.println(loginSystem.checkPassword("a@b.c", "L6ZS9"));
//        System.out.println(loginSystem.changePassword("a@b.c", "L6ZS9", "123"));
//        System.out.println(loginSystem.getNumUsers());
//        System.out.println(loginSystem.checkPassword("a@b.c", "L6ZS9"));
//        System.out.println(loginSystem.checkPassword("a@b.c", "123")+ "_________");
//        System.out.println(loginSystem.removeUser("a@b.c", "L6ZS9"));
//        assert loginSystem.checkPassword("a@b.c", "L6ZS9") == -1;
//        System.out.println(loginSystem.checkPassword("a@b.c", "L6ZS9"));
//    }
}
