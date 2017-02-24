package com.berlin.lambdawerk.Updater.service;

import com.berlin.lambdawerk.Updater.model.Person;
import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Chaklader on 2/23/17.
 */
public class ConcurrentPersonUpdater
    extends AbstractPersonUpdater {

    private ThreadPoolExecutor executor;

    private int poolSize = 1;

    private int tasksQueueSize = 1000;

    private long tasksQueueWaitTime = 50;

    @PostConstruct
    public void init() {
        executor = new ThreadPoolExecutor(poolSize, poolSize,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    @Override
    public void handle(final Person person) {
        try {
            while (executor.getQueue().size() > tasksQueueSize) {
                Thread.sleep(tasksQueueWaitTime);
            }
        } catch (InterruptedException e) {
        }

        executor.execute(() -> doUpdatePerson(person));
    }

    @Override
    protected void beginMeasure() {
        reporter.report("Number of threads: " + poolSize);
        super.beginMeasure();
    }

    protected void endMeasure() {
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        super.endMeasure();
    }

    public void setThreadPoolSize(final int poolSize) {
        this.poolSize = poolSize;
    }

    public void setTasksQueueSize(int tasksQueueSize) {
        this.tasksQueueSize = tasksQueueSize;
    }

    public void setTasksQueueWaitTime(long tasksQueueWaitTime) {
        this.tasksQueueWaitTime = tasksQueueWaitTime;
    }
}
