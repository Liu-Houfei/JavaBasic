package com.test4.p7;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Test2 {

    private static AtomicReference<String> atomicReference =
            new AtomicReference<>("abc");
    private static AtomicMarkableReference<String> markableReference =
            new AtomicMarkableReference<>("abc", true);
    private static AtomicStampedReference<String> stampedReference =
            new AtomicStampedReference<>("abc", 0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                stampedReference.compareAndSet("abc", "def",
                        stampedReference.getStamp(), stampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + "---" + stampedReference.getReference());
                stampedReference.compareAndSet("def", "abc",
                        stampedReference.getStamp(), stampedReference.getStamp() + 1);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = stampedReference.getStamp();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(stamp+"----"+stampedReference.getStamp());
                System.out.println(stampedReference.compareAndSet("abc", "ggg",
                        stamp, stamp + 1));
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(stampedReference.getReference()+","+stampedReference.getStamp());
    }
}
