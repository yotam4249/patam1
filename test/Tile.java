package test;
public class Tile {
    public final int score;
    public final char letter;

    private Tile(int score,char letter)
    {
        this.score = score;
        this.letter = letter;
    }

    private static Tile creator(int score,char letter)
    {
        return new Tile(score,letter);
    }
    
    public int getValue()
    {
        return this.score;
    }

    public char getLetter()
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
        if (this==o && this.getClass() == o.getClass())
        {
            return true;
        }
        return false;
    }
    public static class Bag{
        public final int[] starters = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        private int[] amounts = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        private Tile[] tiles;
        private static Bag b = null;

        private Bag()
        {
            tiles = new Tile[26];
            tiles[0] = Tile.creator(1,'A');
            tiles[1] = Tile.creator(3,'B');
            tiles[2] = Tile.creator(3,'C');
            tiles[3] = Tile.creator(2, 'D');
            tiles[4] = Tile.creator(1, 'E');
            tiles[5] = Tile.creator(4, 'F');
            tiles[6] = Tile.creator(2, 'G');
            tiles[7] = Tile.creator(4, 'H');
            tiles[8] = Tile.creator(1, 'I');
            tiles[9] = Tile.creator(8, 'J');
            tiles[10] = Tile.creator(5, 'K');
            tiles[11] = Tile.creator(1, 'L');
            tiles[12] = Tile.creator(3, 'M');
            tiles[13] = Tile.creator(1, 'N');
            tiles[14] = Tile.creator(1, 'O');
            tiles[15] = Tile.creator(3, 'P');
            tiles[16] = Tile.creator(10, 'Q');
            tiles[17] = Tile.creator(1, 'R');
            tiles[18] = Tile.creator(1, 'S');
            tiles[19] = Tile.creator(1, 'T');
            tiles[20] = Tile.creator(1, 'U');
            tiles[21] = Tile.creator(4, 'V');
            tiles[22] = Tile.creator(4, 'W');
            tiles[23] = Tile.creator(8, 'X');
            tiles[24] = Tile.creator(4, 'Y');
            tiles[25] = Tile.creator(10, 'Z');
        }

        Tile getRand()
        {
            if(this.getClass() != Bag.class)
            {
                return null;
            }
            int i = 0;
            for(;i < 26;i++)
            {
                if(this.amounts[i] != 0)
                {
                    break;
                }
            }
            if(i == 25)
            {
                return null;
            }
            long temp = System.currentTimeMillis();
            int rand = (int)temp % 26;
            if(this.amounts[rand] == 0)
            {
                this.getRand();
            }
            this.amounts[rand] = this.amounts[rand] - 1;
            return this.tiles[rand];
        }

        Tile getTile(char c)
        {
            for(int i = 0;i < 26;i++)
            {
                if(this.tiles[i].letter == c)
                {
                    if(this.amounts[i] != 0)
                    {
                        this.amounts[i] = this.amounts[i] - 1;
                        return this.tiles[i];
                    }
                    return null;
                }
            }
            return null;
        }

        void put(Tile t)
        {
            for(int i = 0;i < 26; i++)
            {
                if(t == this.tiles[i] && this.amounts[i] < this.starters[i])
                {
                    this.amounts[i] = this.amounts[i] + 1;
                    break;
                }
            }
        }

        int size()
        {
            int size = 0;
            for(int i = 0;i < 26;i++)
            {
                size += this.amounts[i];
            }
            return size;
        }

        int[] getQuantities()
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


