package test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LFU implements CacheReplacementPolicy{
    public HashMap<String,Integer> dic;
    public String minStr;

    public LFU()
    {
        dic = new HashMap<>();
    }

    public ArrayList<String> getMinFreq()
    {
        ArrayList<String> temp = new ArrayList<>();
        int minVal = Integer.MAX_VALUE;
        for(Integer value : dic.values())
        {
            if(value < minVal)
            {
                minVal = value;
            }
        }
        for(Map.Entry<String,Integer> entry : dic.entrySet())
        {
            if(entry.getValue() == minVal)
            {
                temp.add(entry.getKey());
            }
        }
        return temp;
    }
    @Override
    public void add(String word)
    {
        ArrayList<String> arr = new ArrayList<>();
        if(dic.containsKey(word))
        {
            dic.put(word,dic.get(word)+1);
            if(getMinFreq() != null)
            {
                arr.addAll(getMinFreq());
                minStr = arr.get(0);
            }
        }
        else
        {
            if(dic.isEmpty())
            {
                minStr = word;
                dic.put(word,1);
            }
            else
            {
                dic.put(word,1);
            }
        }
    }
    @Override
    public String remove()
    {
        dic.remove(minStr);
        return minStr;
    }
}
