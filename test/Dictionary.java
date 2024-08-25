package test;


public class Dictionary {

    public CacheManager rCm;
    public CacheManager fCm;
    public BloomFilter bm;
    public String fileNames;

    public Dictionary(String fileNames)
    {
        rCm = new CacheManager(400, new LRU());
        fCm = new CacheManager(100, new LFU());
        bm = new BloomFilter();
        this.fileNames = fileNames;
    }


    public boolean query(Word w)
    {
        return false;
    }
}
