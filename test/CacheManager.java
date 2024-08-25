package test;
import java.util.HashSet;

public class CacheManager implements CacheReplacementPolicy{
	public HashSet<String> map;
    public int size;
    public CacheReplacementPolicy crp;

    public CacheManager(int size, CacheReplacementPolicy crp)
    {
        this.size = size;
        map = new HashSet<>(size);
        this.crp = crp;
    }
    
    
    public boolean query(String w)
    {
        return map.contains(w);
    }

    @Override
    public void add(String word)
    {
        if(map.size() < size)
        {
            crp.add(word);
            map.add(word);
        }
        else
        {
            map.remove(crp.remove());
            crp.add(word);
            map.add(word);
        }
    }

    @Override
    public String remove()
    {
        return crp.remove();
    }
    
    

    public int getSize()
    {
        return size;
    }
}