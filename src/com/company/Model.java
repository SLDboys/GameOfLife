package com.company;

public class Model {
    private final int height = 250,
                        width = 200;
    /* height & width are +2 of what's shown, because of border cells
     * EXAMPLE: 0 -- not shown on screen, 1 -- shown on screen
     * 00000
     * 01110
     * 01110
     * 01110
     * 00000
     * While calculating all border cells we can just use basic rule, because
     * all not-visible are just basic 'dead' cells
     * */

    private int[][] table,
    oldTable;

    //Just nullifying
    Model() {
        table = new int[width][height];
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                table[i][j] = 0;
            }
        }
    }

    Model(int[][] table) {
        this.table = table.clone();
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    void setCell(int val, int i, int j) throws IndexOutOfBoundsException {
        if(i>=width-1||i<1) throw new IndexOutOfBoundsException("Wrong width. Max is " + (width-1) + ",Min is 1; got " + i);
        if(j>=height-1||j<1) throw new IndexOutOfBoundsException("Wrong height. Max is " + (height-1) + ",Min is 1; got " + j);
        table[i][j] = val;
    }

    void updateModel() {
        oldTable = new int[width][];
        for(int i = 0; i < width; i++)
            oldTable[i] = table[i].clone();
        for(int i = 1; i < width-1; i++) {
            for(int j = 1; j < height-1; j++) {
                int sum = neighborSum(i, j);
                if(oldTable[i][j] == 0 && sum == 3)  table[i][j] = 1;
                else if(oldTable[i][j] == 1 && (sum < 2 || sum > 3)) table[i][j] = 0;
            }
        }
    }

    int getCell(int i, int j) {
        return table[i][j];
    }

    int neighborSum(int i, int j) {//TODO make this faster
        int ans = 0;
        ans = (oldTable[i-1][j-1] == 1) ? ans+1 : ans;
        ans = (oldTable[i-1][j] == 1) ? ans+1 : ans;
        ans = (oldTable[i-1][j+1] == 1) ? ans+1 : ans;
        ans = (oldTable[i][j-1] == 1) ? ans+1 : ans;
        ans = (oldTable[i][j+1] == 1) ? ans+1 : ans;
        ans = (oldTable[i+1][j-1] == 1) ? ans+1 : ans;
        ans = (oldTable[i+1][j] == 1) ? ans+1 : ans;
        ans = (oldTable[i+1][j+1] == 1) ? ans+1 : ans;
        return ans;
    }
}
