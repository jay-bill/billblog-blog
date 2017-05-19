package test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionServiceTest {
	public static void main(String [] args){
		ExecutorService es = Executors.newCachedThreadPool();
		CompletionService cs = new ExecutorCompletionService(es);
		for(int i=0;i<10;i++){
			cs.submit(new Callable<String>(){

				@Override
				public String call() throws Exception {
					long t = (int)(Math.random()*1000);
					Thread.sleep(t);
					return "你好，睡眠时间是"+t+Thread.currentThread().getName();
				}
				
			});
		}
		
		for(int i=0;i<10;i++){
			try {
				System.out.println(cs.take().get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
