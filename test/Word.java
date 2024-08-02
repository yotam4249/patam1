package test;


public class Word {
    public Tile[] tiles;
    public int row,col;
    public boolean vertical;
	
    public Word(Tile[] tiles,int size, int row, int col,boolean vertical)
    {
        this.tiles = new Tile[size];
        for(int i=0;i<size;i++)
        {
            this.tiles[i] = tiles[i];
        }
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }
    Tile[] getTiles()
    {
        return tiles;
    }

    int getRow()
    {
        return row;
    }

    int getCol()
    {
        return col;
    }

    boolean isVertical()
    {
        return vertical;
    }

    @Override
    public boolean equals(Object ob)
    {
        int flag = 0;
        if (this == ob && this.getClass() == ob.getClass())
        {
            Word w = (Word)ob;
            if(w.isVertical() == this.isVertical() && w.getCol() == this.getCol() && w.getRow() == this.getRow())
            {
                for(int i = 0;i < 26; i++)
                {
                    if(!(this.tiles[i].equals(w.tiles[i])))
                    {
                        flag = 1;
                    }
                }
                if(flag == 0)
                {
                    return true;
                }
                return false;
            }
            
        }
        return false;
    }
}
