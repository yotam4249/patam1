package test;

import java.util.Random;

public class Tile {
    public final int score;
    public final char letter;

    private Tile(int score,char letter)
    {
        this.score = score;
        this.letter = letter;
    }


    
    int getScore()
    {
        return this.score;
    }

     char getLetter()
    {
        return this.letter;
    }
    @Override
    public int hashCode()
    {
        return ((Integer.hashCode(score)*15) + Character.hashCode(letter));
    }
	@Override
    public boolean equals(Object o)        
    {
        
        if (this==o)
        {
            return true;
        } 
        if(this.getClass() == o.getClass())
        {
            Tile t = (Tile)o;
            if(t.getScore() == this.getScore() && t.getLetter() == this.getLetter())
            {
                return true;
            }
        }
        return false;
    }
    public static class Bag{
        private int[] amounts = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        public final int[] starters = amounts.clone();
        private Tile[] tiles;
        private static Bag b = null;
        private int bagSize=98; 

        private Bag()
        {
            tiles = new Tile[]{
                new Tile(1, 'A'), new Tile(3, 'B'), new Tile(3, 'C'), new Tile(2, 'D'),
                new Tile(1, 'E'), new Tile(4, 'F'), new Tile(2, 'G'), new Tile(4, 'H'),
                new Tile(1, 'I'), new Tile(8, 'J'), new Tile(5, 'K'), new Tile(1, 'L'),
                new Tile(3, 'M'), new Tile(1, 'N'), new Tile(1, 'O'), new Tile(3, 'P'),
                new Tile(10, 'Q'), new Tile(1, 'R'), new Tile(1, 'S'), new Tile(1, 'T'),
                new Tile(1, 'U'), new Tile(4, 'V'), new Tile(4, 'W'), new Tile(8, 'X'),
                new Tile(4, 'Y'), new Tile(10, 'Z')
            };
        }


        public Tile getRand()
        {
            if(bagSize == 0)
            {
                return null;
            }
            Random random = new Random();
            int rand = random.nextInt(26);
            if(this.amounts[rand] == 0)
            {
                return this.getRand();
            }
            this.amounts[rand] = this.amounts[rand] - 1;
            this.bagSize--;
            return this.tiles[rand];
        }

        public Tile getTile(char c)
        {
            for(int i = 0;i < 26;i++)
            {
                if(this.tiles[i].letter == c)
                {
                    if(this.amounts[i] != 0)
                    {
                        this.amounts[i] = this.amounts[i] - 1;
                        this.bagSize--;
                        return this.tiles[i];
                    }
                    return null;
                }
            }
            return null;
        }

        public void put(Tile t)
        {
            for(int i = 0;i < 26; i++)
            {
                if(t == this.tiles[i] && this.amounts[i] < this.starters[i])
                {
                    this.amounts[i] = this.amounts[i] + 1;
                    bagSize++;
                    break;
                }
            }
        }

        public int size()
        {
            return bagSize;
        }

        public int[] getQuantities()
        {
            return amounts.clone();
        }
       
        public static Bag getBag()
        {
            if(b == null)
            {
                b = new Bag();
            }
            return b;
        }
    }
}


