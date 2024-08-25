package test;

import java.util.Queue;
import java.util.Iterator;
import java.util.LinkedList;


public class LRU implements CacheReplacementPolicy{

    Queue<String> queue;
    public String str;

    public LRU()
    {
        queue = new LinkedList<>();
        str = new String();
    }

    @Override
    public void add(String word)
    {
        if(queue.contains(word))
        {
            queue.remove(word);
            queue.add(word);
        }
        else
        {
            queue.add(word);
        }
    }
    @Override
    public String remove()
    {
        String lastString = queue.poll();
        return lastString;
    }
}
