package com.neusoft.ThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

interface FutureExecutor extends Executor {

    <T> Future<T> submit(Callable<T> command);

}
