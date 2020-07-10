package leetCode.multthread;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author jiang
 * @date 2020/7/6
 */
public class ZeroEvenOdd {
    private int n;
    private Semaphore zero = new Semaphore(1);
    private Semaphore odd = new Semaphore(0);
    private Semaphore even = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int count = 0; count < n; count++) {
            zero.acquire();
            printNumber.accept(0);
            if ((count & 1) == 0) {
                odd.release();
            } else {
                even.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd o = new ZeroEvenOdd(2);
        new Thread(() -> {
            try {
                o.even(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                o.odd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                o.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
