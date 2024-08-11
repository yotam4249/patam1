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

    public Word horizontalAfterWord(Word w,int i) // The word is after w - SENARIO 1
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

    public Word horizontalAbove(Word w,int i)
    {
        ArrayList<Tile> tempTileArr = new ArrayList<>();
        Tile[] t = null;
        Word tempWord = null;
        return tempWord;
    }

    public Word horizontalBelow(Word w,int i)
    {
        ArrayList<Tile> tempTileArr = new ArrayList<>();
        Tile[] t = null;
        Word tempWord = null;
        return tempWord;
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
        if(!w.vertical) // Horizontal word
        {
            for(int i = 0; i < 15;i++)
            {
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
        }
        else // Vertical word
        {
            
        }
        return words;
    }

}
