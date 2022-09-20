public class MyArrayList<E> {

    private E[] data; // 定义一个基础数组，用来存放数据。
    private int size;  // 用记录数组中的数据个数。

    // 数组扩容系数，当数组的元素个数大于等于数组的容量*系数时，进行扩容
    private double resizeRatio = 0.75;

    public MyArrayList() {
        this(10);
        // TODO Auto-generated constructor stub
    }

    public MyArrayList(int capacity) {
        data = (E[])new Object[capacity];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    // 向索引index位置插入一个元素e
    public void add(int index, E e) {
        // 索引不合法就报错
        if (index > size || index < 0)
            throw new IllegalArgumentException("Wrong index");
        // 如果数组满足：已存元素个数等于元素容量*系数，就扩容，新数组容量为之前的3倍
        if (size == (int)(data.length*resizeRatio))
            resize(3 * data.length);
        // 继续进行插入操作
        for (int i = size - 1; i >= index; i--)
        {
            data[i+1] = data[i];
        }
        data[index] = e;
        size ++;
    }

    // 扩容方法，对数组进行扩容，该方法对用户屏蔽，所以私有
    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++)
            newData[i] = data[i]; // 拷贝数据

        // 将对象的引用更新，此时指针指向了新数组的内存地址
        data = newData;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Wrong index " + index + "__" + size);
        return data[index];
    }

    public void set(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Wrong index");
        data[index] = e;
    }

    // 查询数组中是否存在元素e
    public boolean  isExist(E e) {
        for (int i = 0; i < size; i++)
        {
            if (data[i].equals(e))
            {
                return true;
            }
        }
        return false;
    }

    // 查询元素e在数组中的位置
    public int findIndex(E e) {
        for (int i = 0; i < size; i++)
        {
            if (data[i].equals(e) )
            {
                return i;
            }
        }
        return -1;
    }

    // 从数组中移除索引为index位置的元素，并将该元素返回
    // 缩容后，如果数组元素个数满足条件，就进行缩容
    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Remove failed");
        E ret = data[index];
        for (int i = index + 1; i < size; i++)
        {
            data[i-1] = data[i];
        }
        size --;
        data[size] = null;

        // 如果数组的空间有一半没用就缩容为原来的1/2
        if (size == data.length / 2)
            resize(data.length/2);

        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = findIndex(e);
        if (index != -1)
            remove(index);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size -1)
            {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }

}
