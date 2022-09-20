public class MyLink {
    public int key, value;//节点的内容
    public MyLink next;//下一个节点

    //初始化
    public MyLink(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    //打印节点内容
    public void displayLink(){
        System.out.println("{" + key + ":" + value + "}");
    }
}

