package net.blep.modtech.explosion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Kelan on 28/01/2016.
 */
public class ExplotionThreadListener extends Thread
{
    private ExplosionThread thread;
    private Method invoke;
    private Object invoker;
    private Object[] args;

    public ExplotionThreadListener(ExplosionThread thread, Method invoke, Object invoker, Object... args)
    {
        this.thread = thread;
        this.invoke = invoke;
        this.invoker = invoker;
        this.args = args;
    }


    @Override
    public void run()
    {
        System.out.println("Joining thread");
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("Thread finished");
        if (thread.doneExplode)
        {
            System.out.println("Thread exploded successfully");
            try
            {
                invoke.invoke(invoker, args);
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {
                e.printStackTrace();
            }
        } else
        {
            System.out.println("An error occurred while exploding in a thread.");
        }

        interrupt();
    }
}
