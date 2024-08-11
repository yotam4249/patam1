package test;

import java.util.ArrayList;

public class Board {
    public Tile[][] b;
    public int numOfWords;
    private static Board board;
    

    public Board()
    {
        b = new Tile[15][15];
        for(int i =0;i < 15;i++)
        {
            for(int j = 0;j < 15;j++)
            {
                b[i][j] = null;
            }
        }
        numOfWords=0;
    }

    public static Board getBoard()
    {
        if(board == null)
        {
            board = new Board();
            return board;
        }
        return board;
    }

    public Tile[][] getTiles()
    {
        return b.clone();
    }

    public boolean boardLegal(Word w)
    {

        if(!w.legalWord)
        {
            return false;
        }
        if(w.numOfTile>15)
        {
            return false;
        }
        if(w.changer)
        {
            return false;
        }
        int counter = 0;
        int temp = numOfWords;
        if(w.isVertical())
        {
            for(int k = 0, j = w.row; j < 15 && k < w.numOfTile; j++,k++)
            {
                if(this.b[j][w.col] == null)
                {
                    counter++;
                }
                else if(b[j][w.col].equals(w.tiles[k]))
                {
                    counter++;
                }
                if(temp == 0)
                {
                    if(j == 7 && w.col == 7)
                    {
                        temp++;
                    }
                }
            }
        }
        else
        {
            for(int k = 0,j = w.col;j < 15 && k < w.numOfTile; j++,k++)
            {
                if(b[w.row][j] == null)
                {
                    counter++;
                }
                else if(b[w.row][j] == w.tiles[k])
                {
                    counter++;
                }
                if(temp == 0)
                {
                    if(j == 7 && w.row == 7)
                    {
                        temp++;
                    }
                }
            }
        }
        if(numOfWords >= 1)
        {
            temp++;
        }
        if(counter == w.numOfTile && temp > 0)
        {
            return true;
        }
        return false;
    }

    public boolean dictionaryLegal()
    {
        return true;
    }

    public Word horizontalAfterWord(Word w,int i) // The word is right to w - SENARIO 1
    {
        ArrayList<Tile> tempTileArr = new ArrayList<>();
        Tile[] t = null;
        Word tempWord = null;
        Word r = null;
        for (int j = w.col+w.numOfTile -1; j < 15 && b[i][j] != null; j++) 
        {
            tempTileArr.add(b[i][j]);
        }
        t = tempTileArr.toArray(new Tile[0]);
        tempWord = new Word(t, i, w.col+w.numOfTile-1, false);
        if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord))
        {
            return tempWord;
        }
        return r;
    }

    public Word horizontalBeforeWord(Word w,int i) //The word is before w - SENARIO 2
    {
        ArrayList<Tile> tempTileArr = new ArrayList<>();
        Tile[] t = null;
        Word tempWord = null;
        Word r = null;
        if (w.col > 0) 
        {
            int j = 0;
            for (j = w.col; b[i][j] != null && j >= 0; j--) {} // gets to the start of the word
            int temp = j;
            for(;j < 15 && b[i][j] != null &&j < w.col+1; j++)
            {
                tempTileArr.add(b[i][j]);
            }
            t = tempTileArr.toArray(new Tile[0]);
            tempWord = new Word(t, i, temp, false);
            if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord))
            {
                return tempWord;
            }
        }
        return r;
    }

    public Word verticalUp(Word w,int i, int j)// SENARIO 3 - the word is higher then w
    {
        ArrayList<Tile> tempTileArr = new ArrayList<>();
        Tile[] t = null;
        Word tempWord = null;
        Word r = null;
        int temp = i;
        for(;i >= 0 && b[i][j] != null; i--){}
        int temp2 = i;
        for(;i < 15 && i <= temp;i++)
        {
            tempTileArr.add(b[i][j]);
        }
        t = tempTileArr.toArray(new Tile[0]);
        tempWord = new Word(t,temp2, j, true);
        if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord))
        {
            return tempWord;
        }
        return r;
    }
    
    public Word verticalDown(Word w,int i,int j)// SENARIO 4
    {
        ArrayList<Tile> tempTileArr = new ArrayList<>();
        Tile[] t = null;
        Word tempWord = null;
        Word r = null;
        int temp = i;
        for(;i < 15 && i <= temp;i++)
        {
            tempTileArr.add(b[i][j]);
        }
        t = tempTileArr.toArray(new Tile[0]);
        tempWord = new Word(t,temp,w.col + j, true);
        if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord))
        {
            return tempWord;
        }
        return r;
    }

    public Word horizontalRight(Word w,int i,int j)// SENARIO 5
    {
        ArrayList<Tile> tempTileArr = new ArrayList<>();
        Tile[] t = null;
        Word tempWord = null;
        Word r = null;
        int tempCol = j;
        for(;j<15 && b[i][j] != null; j++)
        {
            tempTileArr.add(b[i][j]);
        }
        t = tempTileArr.toArray(new Tile[0]);
        tempWord = new Word(t,i,tempCol,false);
        if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord))
        {
            return tempWord;
        }
        return r;
    }

    public Word horizontalLeft(Word w,int i,int j)// SENARIO 6
    {
        ArrayList<Tile> tempTileArr = new ArrayList<>();
        Tile[] t = null;
        Word tempWord = null;
        Word r = null;
        for(;j<15 && b[i][j] != null; j--){}
        int tempCol = j;
        for(;j <= tempCol;j++)
        {
            tempTileArr.add(b[i][j]);
        }
        t = tempTileArr.toArray(new Tile[0]);
        tempWord = new Word(t,i,tempCol,false);
        if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord))
        {
            return tempWord;
        }
        return r;
    }

    public Word verticalAbove(Word w, int j)// SENARIO 7
    {
        ArrayList<Tile> tempTileArr = new ArrayList<>();
        Tile[] t = null;
        Word tempWord = null;
        Word r = null;
        int i = 0;
        for(i =w.row;i >= 0 && b[i][j] != null; i--){}
        int tempRow = i;
        for(;i <= w.row;i++)
        {
            tempTileArr.add(b[i][j]);
        }
        t = tempTileArr.toArray(new Tile[0]);
        tempWord = new Word(t,tempRow,j,true);
        if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord))
        {
            return tempWord;
        }
        return r;
    }

    public Word verticalBelow(Word w, int j)
    {
        ArrayList<Tile> tempTileArr = new ArrayList<>();
        Tile[] t = null;
        Word tempWord = null;
        Word r = null;
        int i = w.row + w.numOfTile - 1;
        for(;i<15 && b[i][j] != null;i++)
        {
            tempTileArr.add(b[i][j]);
        }
        t = tempTileArr.toArray(new Tile[0]);
        tempWord = new Word(t,w.row + w.numOfTile-1,j,true);
        if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord))
        {
            return tempWord;
        }
        return r;
    }

    public ArrayList<Word> getWords(Word w)
    {
        Word temp;
        ArrayList<Word> words = new ArrayList<>();
        if(!w.legalWord)
        {
            words = null;
            return words;
        }
        if(!boardLegal(w))
        {
            words = null;
            return words;
        }
        words.add(w);
        if(!w.vertical) // Horizontal word Senario 1-4
        {
            int i = w.row;
                if(i == 0)
                {
                    temp = horizontalAfterWord(w, i);
                    if(temp != null)
                    {
                        words.add(temp);
                    }
                    temp = horizontalBeforeWord(w, i);
                    if(temp != null)
                    {
                        words.add(temp);
                    }
                    for(int j = 0;j < 15 && b[i][j] != null;j++)
                    {
                        temp = verticalDown(w, i, j);
                        if(temp != null)
                        {
                            words.add(temp);
                        }
                    }
                }    
                else if(i == 14)
                {
                    temp = horizontalAfterWord(w, i);
                    if(temp != null)
                    {
                        words.add(temp);
                    }
                    temp = horizontalBeforeWord(w, i);
                    if(temp != null)
                    {
                        words.add(temp);
                    }
                    for(int j = 0;j < 15 && b[i][j] != null;j++)
                    {
                        temp = verticalUp(w, i, j);
                        if(temp != null)
                        {
                            words.add(temp);
                        }
                    }
                }
                else
                {
                    int t = 0;
                    int tempCol = 0;
                    int tempRow = 0;
                    Word tempWord = null;
                    Tile[] currT = null;
                    ArrayList<Tile> tempTileArr = new ArrayList<>();
                    temp = horizontalAfterWord(w, i);
                    if(temp != null)
                    {
                        words.add(temp);
                    }
                    temp = horizontalBeforeWord(w, i);
                    if(temp != null)
                    {
                        words.add(temp);
                    }
                    for(int j = 0;j < 15 && b[i][j] != null;j++)
                    {
                        temp = verticalUp(w, i, j);
                        if(temp != null)
                        {
                            tempRow = temp.row;
                            tempCol = temp.col;
                            for(Tile tile : tempTileArr)
                            {
                                tile = temp.tiles[t];
                                t++;
                            }
                            words.add(temp);
                        }
                        if(!tempTileArr.isEmpty())
                        {
                            tempTileArr.remove(tempTileArr.size() - 1);
                        }
                        temp = verticalDown(w, i, j);
                        if(temp != null)
                        {   
                            for(int p = 0;p < temp.tiles.length;p++)
                            {
                                tempTileArr.add(temp.tiles[p]);
                            }
                            currT = tempTileArr.toArray(new Tile[0]);
                            tempWord = new Word(currT,tempRow,tempCol, true);
                            words.add(temp);
                            if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord))
                            {
                                words.add(tempWord);
                            }
                        }
                    }
                }
        }
        else // Vertical word Senario 5-8
        {
            temp = null;
            int j = w.col;
            if(j == 0)
            {
                for(int i = w.row;i < 15 && b[i][j] != null;i++)
                {
                    temp = horizontalRight(w, i, j);
                    if(temp != null)
                    {
                        words.add(temp);
                    }
                }
                temp = verticalAbove(w, j);
                if(temp != null)
                {
                    words.add(temp);
                }
                temp = verticalBelow(w, j);
                if(temp != null)
                {
                    words.add(temp);
                }
            }
            else if(j == 14)
            {
                for(int i = w.row;i < 15 && b[i][j] != null;i++)
                {
                    temp = horizontalLeft(w, i, j);
                    if(temp != null)
                    {
                        words.add(temp);
                    }
                }
                temp = verticalAbove(w, j);
                if(temp != null)
                {
                    words.add(temp);
                }
                temp = verticalBelow(w, j);
                if(temp != null)
                {
                    words.add(temp);
                }
            }
            else
            {
                int t = 0;
                int tempCol = 0;
                int tempRow = 0;
                ArrayList<Tile> tempTileArr = new ArrayList<>();
                Word tempWord = null;
                Tile[] currT = null;

                temp = verticalAbove(w, j);
                if(temp != null)
                {
                    words.add(temp);
                }
                temp = verticalBelow(w, j);
                if(temp != null)
                {
                    words.add(temp);
                }

                for(int i = w.row;i < 15 && b[i][j] != null;i++)
                {
                    temp = horizontalLeft(w, i, j);
                    if(temp != null)
                    {
                        tempCol = temp.col;
                        tempRow = temp.row;
                        for(Tile tile : tempTileArr)
                        {
                            tile = temp.tiles[t];
                            t++;
                        }
                        tempTileArr.remove(tempTileArr.size()-1);
                    }
                    temp = horizontalRight(w, i, j);
                    if(temp != null && t > 0)
                    {
                        for(int k = 0;k < temp.numOfTile ;k++)
                        {
                            tempTileArr.add(temp.tiles[k]);
                        }
                    }
                    currT = tempTileArr.toArray(new Tile[0]);
                    tempWord = new Word(currT,tempRow,tempCol,false);
                    if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord))
                    {
                        words.add(tempWord);
                    }
                }
            }
        }
        return words;
    }



    public int getScore(Word w)
    {
        if(w.isVertical())
        {
            
        }
        return 0;
    }
}
