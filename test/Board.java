package test;

import java.util.ArrayList;

public class Board {
    public Tile[][] b;
    public int numOfWords;
    private static Board board;
    ArrayList<Word> existingWords = new ArrayList<>();

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
        if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord) && tempWord.tiles.length > 1)
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
            int temp = j+1;
            j=j+1;
            for(;j < 15 && b[i][j] != null &&j <= w.col; j++)
            {
                tempTileArr.add(b[i][j]);
            }
            t = tempTileArr.toArray(new Tile[0]);
            tempWord = new Word(t, i, temp, false);
            if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord)&& tempWord.tiles.length > 1)
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
        int temp2 = i+1;
        i = i+1;
        for(;i < 15 && b[i][j] != null;i++)
        {
            tempTileArr.add(b[i][j]);
        }
        t = tempTileArr.toArray(new Tile[0]);
        tempWord = new Word(t,temp2, j, true);
        if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord)&& tempWord.tiles.length > 1)
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
        for(;i < 15 && b[i][j]!=null;i++)
        {
            tempTileArr.add(b[i][j]);
        }
        t = tempTileArr.toArray(new Tile[0]);
        tempWord = new Word(t,temp,j, true);
        if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord) && tempWord.tiles.length > 1)
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
        if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord) && tempWord.tiles.length > 1)
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
        int tempCol = j;
        for(;j>=0 && b[i][j] != null; j--){}
        j++;
        int tempCol2 = j;
        for(;j < 15 && b[i][j] != null;j++)
        {
            tempTileArr.add(b[i][j]);
        }
        t = tempTileArr.toArray(new Tile[0]);
        tempWord = new Word(t,i,tempCol2,false);
        if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord) && tempWord.tiles.length > 1)
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
        int tempRow = i+1;
        for(i=i+1;i <= w.row;i++)
        {
            tempTileArr.add(b[i][j]);
        }
        t = tempTileArr.toArray(new Tile[0]);
        tempWord = new Word(t,tempRow,j,true);
        if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord) && tempWord.tiles.length > 1)
        {
            return tempWord;
        }
        return r;
    }

    public Word verticalBelow(Word w, int j)//SENARIO 8
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
        if(tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord) && tempWord.tiles.length > 1)
        {
            return tempWord;
        }
        return r;
    }

    public ArrayList<Word> getWords(Word w)
    {
        Word temp = null;
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
                    for(int j = w.col;j < 15 && b[i][j] != null && j <= w.col + w.numOfTile - 1;j++)
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
                    for(int j = w.col;j < 15 && b[i][j] != null && j <= w.col + w.numOfTile - 1;j++)
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
                    for(int j = w.col;j < 15 && b[i][j] != null && j <= w.col + w.numOfTile - 1;j++)
                    {
                        tempTileArr.clear();
                        temp = verticalUp(w, i, j);
                        if(temp != null)
                        {
                            tempRow = temp.row;
                            tempCol = temp.col;
                            for(int p = 0;p < temp.tiles.length;p++)
                            {
                                tempTileArr.add(temp.tiles[p]);
                            }
                            words.add(temp);
                        }
                        else {
                            if (!(tempTileArr.isEmpty())) {
                                tempTileArr.remove(tempTileArr.size() - 1);
                            }
                            temp = verticalDown(w, i, j);
                            if (temp != null) {
                                for (int p = 0; p < temp.tiles.length; p++) {
                                    tempTileArr.add(temp.tiles[p]);
                                }
                                currT = tempTileArr.toArray(new Tile[0]);
                                tempWord = new Word(currT, tempRow, tempCol, true);
                                if (temp != null) {
                                    words.add(temp);
                                }
                                if (tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord)
                                        && tempWord.tiles.length > 1) {
                                    words.add(tempWord);
                                }
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
                for(int i = w.row;i < 15 && b[i][j] != null && i<= w.numOfTile + w.row -1;i++)
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
                for(int i = w.row;i < 15 && b[i][j] != null&& i<= w.numOfTile + w.row -1;i++)
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

                for(int i = w.row;i < 15 && b[i][j] != null && i<= w.numOfTile + w.row -1;i++)
                {
                    tempTileArr.clear();
                    temp = horizontalLeft(w, i, j);
                    if(temp != null)
                    {
                        tempCol = temp.col;
                        tempRow = temp.row;
                        for(int l = 0;l<temp.tiles.length;l++)
                        {
                            tempTileArr.add(temp.tiles[l]);
                        }
                        tempTileArr.remove(tempTileArr.size()-1);
                        words.add(temp);
                    }
                    else {
                        temp = horizontalRight(w, i, j);
                        if (temp != null) {
                            if (horizontalLeft(w, i, j) != null) {
                                for (int k = 0; k < temp.numOfTile; k++) {
                                    tempTileArr.add(temp.tiles[k]);
                                }
                            }
                            words.add(temp);
                        }
                        currT = tempTileArr.toArray(new Tile[0]);
                        tempWord = new Word(currT, tempRow, tempCol, false);
                        if (tempWord.dicLegalWord && tempWord.legalWord && boardLegal(tempWord)
                                && tempWord.tiles.length > 1) {
                            words.add(tempWord);
                        }
                    }
                }
            }
        }
        return words;
    }

    public boolean emptyBoard()
    {
        for(int i =0;i<15;i++)
        {
            for(int j = 0;j<15;j++)
            {
                if(b[i][j] != null)
                {
                    return false;
                }
            }
        }
        return true;
    }


    public int calcScore(Word w, boolean empty)
    {
        int totalScore = w.getScore();
        int tempDub = 1;
        int tempScore = 0;
            if (!boardLegal(w) || !w.legalWord || !w.dicLegalWord) 
            {
                return 0;
            }
            if (this.emptyBoard()) 
            {
                empty = true;
            }
            if (w.isVertical()) 
            {
                int j = w.col;
                for (int i = w.row; i < w.numOfTile + w.row; i++) 
                { // TRIPLE WORD SCORE
                    if ((i == 0 && j == 0) || (i == 1 && j == 7) || (i == 0 && j == 14) || (i == 7 && j == 0)
                            || (i == 7 && j == 14) || (i == 14 && j == 0) || (i == 14 && j == 7)
                            || (i == 14 && j == 14)) 
                    {
                        tempScore = tempScore + b[i][j].getScore();
                        tempDub = tempDub * 3;
                    }
                    // DOUBLE WORD SCORE
                    else if ((i == 1 && j == 1) || (i == 2 && j == 2) || (i == 3 && j == 3) || (i == 4 && j == 4)
                            || (i == 13 && j == 1) || (i == 12 && j == 2) || (i == 11 && j == 3) ||(i == 10 && j ==4)
                            || (i == 10 && j == 10) || (i == 11 && j == 11) || (i == 12 && j == 12)
                            || (i == 13 && j == 13) || (i == 4 && j == 10) || (i == 3 && j == 11) || (i == 2 && j == 12)
                            || (i == 1 && j == 13)) 
                    {
                        tempScore = tempScore + b[i][j].getScore();
                        tempDub = tempDub * 2;
                    }
                    // TRIPLE LETTER SCORE
                    else if ((i == 1 && j == 5) || (i == 1 && j == 9) || (i == 5 && j == 1) || (i == 5 && j == 5)
                            || (i == 5 && j == 9) || (i == 5 && j == 13) || (i == 9 && j == 1) || (i == 9 && j == 5)
                            || (i == 9 && j == 9) || (i == 9 && j == 13) || (i == 13 && j == 5)
                            || (i == 13 && j == 9)) 
                    {
                        tempScore = tempScore + (b[i][j].getScore() * 3);
                    }
                    // DOUBLE LETTER SCORE
                    else if ((i == 0 && j == 3) || (i == 0 && j == 11) || (i == 2 && j == 6) || (i == 2 && j == 8)
                            || (i == 3 && j == 0) || (i == 3 && j == 7) || (i == 3 && j == 14) || (i == 6 && j == 2)
                            || (i == 6 && j == 6) || (i == 6 && j == 8) || (i == 6 && j == 12) || (i == 7 && j == 3)
                            || (i == 7 && j == 11) ||(i==8 && j ==2) || (i == 8 && j == 6) ||(i==8 && j ==8) ||(i == 8 && j == 12)
                            || (i == 11 && j == 0) || (i == 11 && j == 7) || (i == 11 && j == 14) || (i == 12 && j == 6)
                            || (i == 12 && j == 8) || (i == 14 && j == 3) || (i == 14 && j == 11)) 
                    {
                        tempScore = tempScore + (b[i][j].getScore() * 2);
                    }
                    // FIRST PLAYER BONUS - STAR BONUS
                    else if (empty) 
                    {
                        empty = false;
                        tempScore = tempScore + b[i][j].getScore();
                        tempDub = tempDub *2;
                    }
                    //REGULAR SCORE
                    else
                    {
                        tempScore = tempScore + b[i][j].getScore();
                    }
                }
            } 
            else 
            {
                int i = w.row;
                for (int j = w.col; j < w.numOfTile + w.col; j++) 
                { // TRIPLE WORD SCORE
                    if ((i == 0 && j == 0) || (i == 1 && j == 7) || (i == 0 && j == 14) || (i == 7 && j == 0)
                            || (i == 7 && j == 14) || (i == 14 && j == 0) || (i == 14 && j == 7)
                            || (i == 14 && j == 14)) 
                    {
                        tempScore = tempScore + b[i][j].getScore();
                        tempDub = tempDub * 3;
                    }
                    // DOUBLE WORD SCORE
                    else if ((i == 1 && j == 1) || (i == 2 && j == 2) || (i == 3 && j == 3) || (i == 4 && j == 4)
                            || (i == 13 && j == 1) || (i == 12 && j == 2) || (i == 11 && j == 3) ||(i == 10 && j ==4)
                            || (i == 10 && j == 10) || (i == 11 && j == 11) || (i == 12 && j == 12)
                            || (i == 13 && j == 13) || (i == 4 && j == 10) || (i == 3 && j == 11) || (i == 2 && j == 12)
                            || (i == 1 && j == 13)) 
                    {
                        tempScore = tempScore + b[i][j].getScore();
                        tempDub = tempDub * 2;
                    }
                    // TRIPLE LETTER SCORE
                    else if ((i == 1 && j == 5) || (i == 1 && j == 9) || (i == 5 && j == 1) || (i == 5 && j == 5)
                            || (i == 5 && j == 9) || (i == 5 && j == 13) || (i == 9 && j == 1) || (i == 9 && j == 5)
                            || (i == 9 && j == 9) || (i == 9 && j == 13) || (i == 13 && j == 5)
                            || (i == 13 && j == 9)) 
                    {
                        tempScore = tempScore + (b[i][j].getScore() * 3);
                    }
                    // DOUBLE LETTER SCORE
                    else if ((i == 0 && j == 3) || (i == 0 && j == 11) || (i == 2 && j == 6) || (i == 2 && j == 8)
                            || (i == 3 && j == 0) || (i == 3 && j == 7) || (i == 3 && j == 14) || (i == 6 && j == 2)
                            || (i == 6 && j == 6) || (i == 6 && j == 8) || (i == 6 && j == 12) || (i == 7 && j == 3)
                            || (i == 7 && j == 11) ||(i==8 && j ==2) || (i == 8 && j == 6) ||(i==8 && j ==8) ||(i == 8 && j == 12)
                            || (i == 11 && j == 0) || (i == 11 && j == 7) || (i == 11 && j == 14) || (i == 12 && j == 6)
                            || (i == 12 && j == 8) || (i == 14 && j == 3) || (i == 14 && j == 11)) 
                    {
                        tempScore = tempScore + (b[i][j].getScore() * 2);
                    }
                    // FIRST PLAYER BONUS - STAR BONUS
                    else if (empty && i == 7 && j == 7) 
                    {
                        empty = false;
                        tempScore = tempScore + b[i][j].getScore();
                        tempDub = tempDub *2;
                    }
                    //REGULAR SCORE
                    else
                    {
                        tempScore = tempScore + b[i][j].getScore();
                    }
                }
            }
        totalScore = tempDub * tempScore;    
        return totalScore;
    }


    public int getScore(Word w,boolean empty)
    {
        int totalScore = 0;
        ArrayList<Word> words = new ArrayList<>();
        if(getWords(w) != null)
        {
            words.addAll(getWords(w));
            for(int i = 0;i < words.size();i++)
            {
                if(isLeaning(words.get(i)) > 1)
                {
                    words.remove(i);
                    i--;
                }
                
            }
        }
        else
        {
            return 0;
        }
        for(int i= 0;i < words.size()-1;i++)
        {
            if(words.get(i).equals(words.get(i+1)))
            {
                words.remove(i);
                i--;
            }
        }
        for(int i=0;i < words.size();i++)
        {
            for(int j=0;j < numOfWords;j++)
            {
                if(words.get(i).equals(existingWords.get(j)))
                {
                    words.remove(i);
                    i--;
                }
            }
        }
        if(!existingWords.isEmpty())
        {
            numOfWords++;
            existingWords.remove(numOfWords-1);
            numOfWords--;
        }
        existingWords.addAll(words);
        numOfWords += words.size();
        int size = words.size();
        for(int i = 0;i<size;i++)
        {
            totalScore += calcScore(words.get(i), empty);
        }
        return totalScore;
    }

    public Word wordFixer(Word w)
    {
        if(w.isVertical())
        {
            for(int i = w.row,k = 0;i < 15 && i < w.row + w.numOfTile;i++,k++)
            {
                if(b[i][w.col] != null && w.tiles[k] == null)
                {
                    w.tiles[k] = b[i][w.col];
                }
            }
        }
        else
        {
            for(int j = w.col,k = 0;j < 15 && j < w.col + w.numOfTile;j++,k++)
            {
                if(b[w.row][j] != null && w.tiles[k] == null)
                {
                    w.tiles[k] = b[w.row][j];
                }
            }
        }
        Word newW = new Word(w.tiles,w.row,w.col,w.vertical);
        return newW;
    }

    public int isLeaning(Word w)
    {
        Word temp = null;
        int wCol = w.col;
        int wRow = w.row;
        int tempCol = 0;
        int tempRow = 0;
        int counter = 0;
        if(this.numOfWords == 0)
        {
            return 0;
        }
        else
        {
            for(int k = 0;k < existingWords.size(); k++)
            {
                temp = existingWords.get(k);
                if(!temp.equals(w))
                {
                    tempCol = temp.col;
                    tempRow = temp.row;
                    if (w.isVertical() && temp.isHorizon() || !w.isVertical() && temp.isVertical()) {
                        for (int i = 0; i < w.numOfTile; i++) {
                            for (int j = 0; j < temp.numOfTile; j++) {
                                if (wCol == tempCol + j && wRow + i == tempRow) {
                                    counter++;
                                }
                            }
                        }
                        if(counter > temp.numOfTile)
                        {
                            return 1;
                        }
                    } else if (w.isVertical() && temp.isVertical()) {
                        if (w.col == temp.col) {
                            for (int i = 0; i < w.numOfTile; i++) {
                                for (int j = 0; j < temp.numOfTile; j++) {
                                    if (wRow + i == tempRow + j) {
                                        counter++;
                                    }
                                }
                            }
                        } 
                        else {
                            return 0;
                        }
                        if(counter > temp.numOfTile)
                        {
                            return 1;
                        }
                    }
                    else if(w.isHorizon() && temp.isHorizon())
                    {
                        if(w.row == temp.row)
                        {
                            for(int i = 0;i < w.numOfTile;i++)
                            {
                                for(int j =0;j < temp.numOfTile;j++)
                                {
                                    if(wCol + i == tempCol + j)
                                    {
                                        counter++;
                                    }
                                }
                            }
                        }
                        else
                        {
                            return 0;
                        }
                        if(counter > temp.numOfTile)
                        {
                            return 1;
                        }
                    }
                }
            }
        }
        
        return counter;
    }

    public int tryPlaceWord(Word w)
    {
        w = wordFixer(w);
        if(isLeaning(w) > 1)
        {
            return 0;
        }
        for(int i =0;i<numOfWords;i++)
        {
            if(w.equals(existingWords.get(i)))
            {
                return 0;
            }
        }
        if(!w.dicLegalWord || !w.legalWord || !boardLegal(w))
        {
            return 0;
        }
        
        int temp = 0;
        int totalScore = 0;
        int i = 0;
        int j = 0;
        boolean empty = false;
        if(numOfWords == 0)
        {
            empty = true;
        }
        if(w.isVertical())
        {
            j = w.col;
            i = w.row;
            for(int k =0 ; i < 15 && k < w.numOfTile;i++,k++)
            {
                if(b[i][j] != null)
                {
                    if(b[i][j] != w.tiles[k])
                    {
                        return 0;
                    }
                }
                if(i == 7 && j == 7)
                {
                    temp = 1;
                }
                b[i][j] = w.tiles[k];
            }
            if(empty)
            {
                if(temp != 1)
                {
                    return 0;
                }
            }
        }
        else
        {
            i = w.row;
            j = w.col;
            for(int k =0 ; j < 15 && k<w.numOfTile;j++,k++)
            {
                if(b[i][j] != null)
                {
                    if(b[i][j] != w.tiles[k])
                    {
                        return 0;
                    }
                }
                if(i == 7 && j == 7)
                {
                    temp = 1;
                }
                b[i][j] = w.tiles[k];
            }
            if(empty)
            {
                if(temp != 1)
                {
                    return 0;
                }
            }
        }
        existingWords.add(w);
         /* for(i = 0;i < 15 ;i++)
        {
            for(j=0;j<15;j++)
            {
                if(b[i][j] != null)
                {
                    System.out.print(b[i][j].letter + " ");
                }
                else
                {
                    System.out.print("- ");
                }
            }
            System.err.println();
        }*/
        totalScore = getScore(w,empty);
        numOfWords = existingWords.size();
        return totalScore;
    }
}
