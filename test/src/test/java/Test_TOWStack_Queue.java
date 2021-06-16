import com.cxl.algorithm.TOWStack_Queue;

public class Test_TOWStack_Queue {

    public static void main(String[] args) {
        TOWStack_Queue towStack_queue=new TOWStack_Queue();
        towStack_queue.appendTail(1);
        towStack_queue.appendTail(2);
        towStack_queue.appendTail(2);
        towStack_queue.appendTail(2);
        towStack_queue.appendTail(2);
        towStack_queue.appendTail(2);
        int i = towStack_queue.deleteHead();
        System.out.println(i);
    }
}
