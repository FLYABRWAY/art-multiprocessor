package part1;

import java.util.concurrent.TimeUnit;

public class Action implements Runnable {

    private static final Long EAT_TIME = 1000L;
    private static final Long TIMEOUT = 100L;
    private static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    private Fork left;
    private Fork right;

    public Action(final Fork left, final Fork right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {

        while (true) {

            try {

                boolean leftLock = left.lock(TIMEOUT, TIME_UNIT);

                if (leftLock) {

                    boolean rightLock = right.lock(TIMEOUT, TIME_UNIT);

                    if (rightLock) {
                        System.out.println(Thread.currentThread() + " eating with " + left + " and " + right);
                        Thread.sleep(EAT_TIME);

                        // TODO Uncomment it and each philosopher will eat and finish his job.
                        //break;
                    } else {
                        left.unlock();
                    }
                }

            }
            catch (InterruptedException e) {
                System.err.println(Thread.currentThread() + " was interrupted");
            }
            finally {
                left.unlock();
                right.unlock();
            }

        }

    }

}
