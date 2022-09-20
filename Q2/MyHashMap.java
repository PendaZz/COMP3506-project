public class MyHashMap {
    static class HashNode<String, Integer> {
        String key;
        Integer value;
        HashNode<String, Integer> next;
        public HashNode(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private MyArrayList<HashNode<String, Integer>> bucketArray;
    private int numBuckets;
    private int size;

    public MyHashMap() {
        bucketArray = new MyArrayList<>();
        numBuckets = 10;
        size = 0;
        for(int i = 0; i < numBuckets; i++) {
            bucketArray.add(0, null);
        }
    }
}
