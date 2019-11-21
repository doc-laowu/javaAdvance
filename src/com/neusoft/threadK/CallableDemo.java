package com.neusoft.threadK;

import java.util.ArrayList;
import java.util.concurrent.*;

public class CallableDemo {


    public static class TaskWithResult implements Callable<String>{

        private int id;

        public TaskWithResult(int id) {
            this.id = id;
        }

        @Override
        public String call() throws Exception {
            TimeUnit.MILLISECONDS.sleep(2000L);
            return "Result of TaskWithResult " + id;
        }
    }

    public static void main(String[] args) {

//        ExecutorService exec = Executors.newCachedThreadPool();
        ExecutorService exec = Executors.newSingleThreadExecutor();


        ArrayList<Future<String>> results = new ArrayList<>();
        for(int i=0; i<10; i++){
            results.add(exec.submit(new TaskWithResult(i)));
        }

        for(Future<String> fs : results){
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                exec.shutdown();
            }
        }

    }

}
