package net.jcip.examples.part4_advancedtopics.chapter14_customsynctool;

import net.jcip.annotations.ThreadSafe;

/**
 * GrumpyBoundedBuffer

 将前提条件的失败传递给调用者


 */
@ThreadSafe
public class GrumpyBoundedBuffer <V> extends BaseBoundedBuffer<V> {
    public GrumpyBoundedBuffer() {
        this(100);
    }

    public GrumpyBoundedBuffer(int size) {
        super(size);
    }

    public synchronized void put(V v) throws BufferFullException {
        if (isFull())
            throw new BufferFullException();    //当不满足前提条件时，有界队列不会执行相应的操作
        doPut(v);
    }

    public synchronized V take() throws BufferEmptyException {
        if (isEmpty())
            throw new BufferEmptyException();
        return doTake();
    }
}


//调用GrumpyBoundedBuffer的代码
class ExampleUsage {
    private GrumpyBoundedBuffer<String> buffer;
    int SLEEP_GRANULARITY = 50;

    void useBuffer() throws InterruptedException {
        while (true) {
            try {
                String item = buffer.take();
                // use item
                break;
            } catch (BufferEmptyException e) {   //调用者必须自行处理前提条件失败的情况BufferEmptyException
                Thread.sleep(SLEEP_GRANULARITY);
            }
        }
    }
}

class BufferFullException extends RuntimeException {
}

class BufferEmptyException extends RuntimeException {
}
