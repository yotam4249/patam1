package test;

public class Word {
    public Tile[] tiles;
    public int row,col;
    public boolean vertical;
    public boolean changer;
    public boolean legalWord;
    public final boolean dicLegalWord = true;
    public final int numOfTile;
    public final int wordScore;
    
	
    public Word(Tile[] tiles, int row, int col,boolean vertical)
    {
        int counter = 0;
        this.numOfTile = tiles.length;
        this.tiles = new Tile[numOfTile];
        for(int i=0;i<numOfTile;i++)
        {
            this.tiles[i] = tiles[i];
            counter += tiles[i].score;
        }
        wordScore = counter;
        if(row < 0 || row>15)
        {
            legalWord = false;
        }
        else if(col < 0 || col > 15)
        {
            legalWord = false;
        }
        else
        {
            legalWord = true;
        }
        this.row = row;
        this.col = col;
        this.vertical = vertical;
        this.changer = false;
        if(legalWord == true)
        {
            if(col+tiles.length > 15 && !this.vertical)
            {
                legalWord = false;
            }
            if(tiles.length+row > 15 && this.vertical)
            {
                legalWord = false;
            }
        }
    }
    public Tile[] getTiles()
    {
        return tiles;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public boolean isVertical()
    {
        return vertical;
    }

    public boolean isHorizon()
    {
        if(vertical)
        {
            return false;
        }
        return true;
    }

    public int getWordSize()
    {
        return numOfTile;
    }

    public int getScore()
    {
        return wordScore;
    }


    @Override
    public boolean equals(Object ob)            
    {
        if (this == ob)
        {
            return true;
        } 
        if(this.getClass() == ob.getClass())
        {
            Word w = (Word)ob;
            if(w.isVertical() == this.isVertical() && w.getCol() == this.getCol() && w.getRow() == this.getRow())
            {
                for(int i = 0;i < numOfTile; i++)
                {
                    if(!(this.tiles[i].equals(w.tiles[i])))
                    {
                        return false;
                    }
                }
                return true;
            }
            
        }
        return false;
    }
}
