package sudoku;
import java.util.*;
//import javafx.scene.paint.Color;

public class Sudoku {
    int n=0;
    Square[][] squares = new Square[9][9];

    public static void main(String[] args) {
        Square[][] t = new Square[9][9];
	
	Scanner R = new Scanner(System.in);
        Sudoku s = new Sudoku();
        s.setsquares(t);
	System.out.println("press 1 to get a random game, press 2 to solve a puzzle");

	int a = R.nextInt();

	if (a == 1){
	    System.out.println("Please wait while your puzzle gets generated");
	    t = s.generate();
	    for (int I = 0; I < 9; I++) {
		for (int J = 0; J < 9; J++) {
		    if (t[I][J].truenum != 0) {
			System.out.print(t[I][J].truenum + "|");
		    } else {
			System.out.print(" |");
		    }
		}
		System.out.println();
	    }
	    System.out.println("press any number to get the solution");
	    a = R.nextInt();
	    t = s.backtracker(t);
	    for (int I = 0; I < 9; I++) {
		for (int J = 0; J < 9; J++) {
		    if (t[I][J].truenum != 0) {
			System.out.print( t[I][J].truenum + "|");
		    } else {
			System.out.print( t[I][J].falsenum + "|");
		    }
		}
		System.out.println();
	    }
	}
	else {
	    System.out.println("enter number of spaces filled");
	    int num = R.nextInt();
	    System.out.println("enter description of each number as row.no colm.co number");
	    for (int i = 0; i < num; i ++){
		int r = R.nextInt();
		int c = R.nextInt();
		int x = R.nextInt();
		r--;
		c--;
		t[r][c].truenum = (byte)x;
	    }
	    System.out.println("The solution is:");
	    t = s.backtracker(t);
	    for (int I = 0; I < 9; I++) {
		for (int J = 0; J < 9; J++) {
		    if (t[I][J].truenum != 0) {
			System.out.print( t[I][J].truenum + "|");
		    } else {
			System.out.print(t[I][J].falsenum + "|");
		    }
		}
		System.out.println();
	    }
	}
        /*t[0][2].truenum = 3;
        t[0][3].truenum = 4;
        t[0][6].truenum = 6;
        t[1][0].truenum = 5;
        t[1][4].truenum = 2;
        t[1][7].truenum = 3;
        t[2][4].truenum = 8;
        t[3][1].truenum = 1;
        t[3][2].truenum = 7;
        t[4][2].truenum = 4;
        t[4][4].truenum = 6;
        t[4][5].truenum = 2;
        t[5][5].truenum = 9;
        t[6][1].truenum = 7;
        t[6][6].truenum = 5;
        t[6][8].truenum = 8;
        t[7][0].truenum = 9;
        t[7][3].truenum = 6;
        t[8][4].truenum = 4;
        t[8][6].truenum = 1;
        t[8][8].truenum = 7;
        /*long h = System.currentTimeMillis();
        for(int o=0;o<100000;o++){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    t[0][0].falsenum = 0;
                }
            }
            t = s.backtracker(t);
        }
        System.out.println(System.currentTimeMillis()-h);*/
        /*t = s.backtracker(t);
        System.out.println(s.n);
        for (int I = 0; I < 9; I++) {
            for (int J = 0; J < 9; J++) {
                if (t[I][J].truenum != 0) {
                    System.out.print("\033[31m" + t[I][J].truenum + "|");
                } else {
                    System.out.print("\033[30m" + t[I][J].falsenum + "|");
                }
            }
            System.out.println();
        }
        for (int I = 0; I < 9; I++) {
            for (int J = 0; J < 9; J++) {
                if (t[I][J].truenum != 0) {
                    System.out.print(t[I][J].truenum + "");
                } else {
                    System.out.print(".");
                }
            }
        }*/
        /*t = s.generate();
        t = s.backtracker(t);
        System.out.println();
        for (int I = 0; I < 9; I++) {
            for (int J = 0; J < 9; J++) {
                if (t[I][J].truenum != 0) {
                    System.out.print(t[I][J].truenum + "");
                } else {
                    System.out.print(".");
                }
            }
        }
        System.out.println();
        for (int I = 0; I < 9; I++) {
            for (int J = 0; J < 9; J++) {
                if (t[I][J].truenum != 0) {
                    System.out.print(t[I][J].truenum + "|");
                } else {
                    System.out.print(" |");
                }
            }
            System.out.println();
        }
        System.out.println();
        for (int I = 0; I < 9; I++) {
            for (int J = 0; J < 9; J++) {
                if (t[I][J].truenum != 0) {
                    System.out.print("\033[31m" + t[I][J].truenum + "|");
                } else {
                    System.out.print("\033[30m" + t[I][J].falsenum + "|");
                }
            }
            System.out.println();
        }
        System.out.println();*/
    }

    void setsquares(Square[][] e) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                e[i][j] = new Square();
            }
        }
    }

    void setavaliblenums(Square[][] f) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                setavaliblenums(i, j, f);
            }
        }
    }

    void setavaliblenums(int i, int j, Square[][] f) {
        if (f[i][j].truenum != 0) {
            for (int k = 0; k < 9; k++) {
                f[k][j].avaliblenums[f[i][j].truenum - 1] = false;
                f[i][k].avaliblenums[f[i][j].truenum - 1] = false;
            }
            for (int k = 0; k < 9; k++) {
                f[i][j].avaliblenums[k] = false;
            }
            int p, h;
            if (i < 3) {
                p = 0;
            } else if (i < 6) {
                p = 3;
            } else {
                p = 6;
            }
            if (j < 3) {
                h = 0;
            } else if (j < 6) {
                h = 3;
            } else {
                h = 6;
            }
            for (int x = p; x < p + 3; x++) {
                for (int y = h; y < h + 3; y++) {
                    f[x][y].avaliblenums[f[i][j].truenum - 1] = false;
                }
            }
        }
    }

    Square[][] backtracker(Square[][] d) {
        Square[][] a = new Square[9][9];
        setsquares(a);
        boolean issolveable = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                a[i][j].falsenum = d[i][j].falsenum;
                a[i][j].truenum = d[i][j].truenum;
                for (int k = 0; k < 9; k++) {
                    a[i][j].avaliblenums[k] = d[i][j].avaliblenums[k];
                    a[i][j].bavaliblenums[k] = d[i][j].bavaliblenums[k];
                }
            }
        }
        setavaliblenums(a);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (a[i][j].truenum == 0) {
                    int u = 0;
                    for (int k = 0; k < 9; k++) {
                        if (a[i][j].avaliblenums[k]) {
                            u++;
                        }
                    }
                    if (u == 0) {
                        issolveable = false;
                    }
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    a[i][j].bavaliblenums[k] = a[i][j].avaliblenums[k];
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int o = 0;
                n++;
                if (a[i][j].truenum == 0) {
                    for (int k = 0; k < 9; k++) {
                        if (a[i][j].bavaliblenums[k]) {
                            o++;
                        }
                    }
                    int[] s = new int[o];
                    if (o == 0) {
                        if (i == 0 && j == 0) {
                            issolveable = false;
                            break;
                        } else {
                            a[i][j].falsenum = 0;
                            for (int w = 0; w < 9; w++) {
                                a[i][j].bavaliblenums[w] = a[i][j].avaliblenums[w];
                            }
                            while (true) {
                                j--;
                                if (j == -1) {
                                    j = 8;
                                    i--;
                                }
                                if (i == -1) {
                                    issolveable = false;
                                    break;
                                }
                                if (a[i][j].truenum == 0) {
                                    break;
                                }
                            }
                            if (!issolveable) {
                                break;
                            }
                            a[i][j].bavaliblenums[a[i][j].falsenum - 1] = false;
                            a[i][j].falsenum = 0;
                            j--;
                            if (j == -1) {
                                j = 8;
                                i--;
                            }
                            continue;
                        }
                    }
                    for (int k = 0; k < o; k++) {
                        s[k] = -1;
                    }
                    for (int k = 0; k < 9; k++) {
                        if (a[i][j].bavaliblenums[k]) {
                            int e;
                            for (e = 0; e < o; e++) {
                                if (s[e] == -1) {
                                    break;
                                }
                            }
                            s[e] = k;
                        }
                    }
                    int r = (int) (Math.random() * o);
                    int g = s[r];
                    g++;
                    a[i][j].falsenum = (byte) g;
                    boolean l = check(i, j, a);
                    if (!l) {
                        a[i][j].bavaliblenums[a[i][j].falsenum - 1] = false;
                        a[i][j].falsenum = 0;
                        j--;
                    }
                }
            }
            if (!issolveable) {
                break;
            }
        }
        if (!issolveable) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    a[i][j].truenum = 0;
                    a[i][j].falsenum = 0;
                }
            }
        }
        return a;
    }

    boolean check(int i, int j, Square[][] a) {
        boolean h = true;
        byte s = a[i][j].falsenum;
        if (s != 0) {
            for (int c = 0; c < 9; c++) {
                if (c == i) {
                    continue;
                }
                if (a[c][j].falsenum == s) {
                    h = false;
                    break;
                }
            }
            if (h) {
                for (int c = 0; c < 9; c++) {
                    if (c == j) {
                        continue;
                    }
                    if (a[i][c].falsenum == s) {
                        h = false;
                        break;
                    }
                }
            }
            if (h) {
                int p, l;
                if (i < 3) {
                    p = 0;
                } else if (i < 6) {
                    p = 3;
                } else {
                    p = 6;
                }
                if (j < 3) {
                    l = 0;
                } else if (j < 6) {
                    l = 3;
                } else {
                    l = 6;
                }
                for (int x = p; x < p + 3; x++) {
                    for (int y = l; y < l + 3; y++) {
                        if (x != i && y != i) {
                            if (a[x][y].falsenum == s) {
                                h = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return h;
    }

    Square[][] generate() {
        Square[][] a = new Square[9][9];
        setsquares(a);
        Square[][] b = new Square[9][9];
        setsquares(b);
        Square[][] c = new Square[9][9];
        setsquares(c);
        Square[][] d = new Square[9][9];
        setsquares(d);
        boolean[][] p = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                p[i][j] = true;
            }
        }
        int q = (int) (Math.random() * 9);
        int r = (int) (Math.random() * 9);
        int s = (int) (Math.random() * 9);
        s++;
        a[q][r].truenum = (byte) s;
        b = backtracker(a);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (b[i][j].truenum == 0 && b[i][j].falsenum != 0) {
                    b[i][j].truenum = b[i][j].falsenum;
                    c[i][j].truenum = b[i][j].falsenum;
                    b[i][j].falsenum = 0;
                } else if (b[i][j].truenum != 0) {
                    c[i][j].truenum = b[i][j].truenum;
                } else {
		    
                }
                c[i][j].falsenum = 0;
                for (int k = 0; k < 9; k++) {
                    c[i][j].avaliblenums[k] = true;
                    c[i][j].bavaliblenums[k] = true;
                }
            }
        }
        boolean flag = false;
        int h = 0, l = 0;
        while (true) {
            int y = rand(p);
            if (y == 1000) {
                break;
            }
            l++;
            //System.out.print(l+" ");
            int f = (y) / 10;
            int g = (y) % 10;
            //System.out.print((y)+" ");
            c[f][g].truenum = 0;
            p[f][g] = false;
            for (int k = 0; k < 12; k++) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        d[i][j].falsenum = 0;
                        d[i][j].truenum = 0;
                        for (int o = 0; o < 9; o++) {
                            d[i][j].avaliblenums[o] = true;
                            d[i][j].bavaliblenums[o] = true;
                        }
                    }
                }
                d = backtracker(c);
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (!(d[i][j].truenum == b[i][j].truenum || d[i][j].falsenum == b[i][j].truenum)) {
                            flag = true;
                            h++;
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            if (flag) {
                //System.out.println("no");
                c[f][g].truenum = b[f][g].truenum;
                flag = false;
            } else {
                //System.out.println("yes");
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                c[i][j].falsenum = 0;
                for (int k = 0; k < 9; k++) {
                    c[i][j].avaliblenums[k] = true;
                    c[i][j].bavaliblenums[k] = true;
                }
            }
        }
        setavaliblenums(c);
        return c;
    }

    public int rand(boolean[][] a) {
        int y = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (a[i][j]) {
                    y++;
                }
            }
        }
        if (y == 0) {
            return 1000;
        }
        int t = (int) (Math.random() * y);
        int[] b = new int[y];
        int h = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (a[i][j]) {
                    b[h] = (i * 10) + j;
                    h++;
                }
            }
        }
        return b[t];
    }

    public void nakedsingle(Square[][] a) {
        boolean wrong = false, done = false;
        int i, j, k;
        R:
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                int u = 0;
                for (k = 0; k < 9; k++) {
                    if (a[i][j].avaliblenums[k]) {
                        u++;
                    }
                }
                switch (u) {
                    case 0:
                        if (a[i][j].truenum == 0) {
                            wrong = true;
                            break R;
                        }
                    case 1:
                        done = true;
                        for (k = 0; k < 9; k++) {
                            if (a[i][j].avaliblenums[k]) {
                                break;
                            }
                        }
                        break R;
                    default:
                        break;
                }
            }
        }
    }

    public void hiddensingle(Square[][] a) {
        boolean inrow = false, incolumn = false, insquare = false, done = false;
        int i, j = 0, k = 0, u, I, J, num, row, col;
        R:
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                for (k = 0; k < 9; k++) {
                    if (a[i][j].avaliblenums[k]) {
                        u = 0;
                        for (I = 0; I < 9; I++) {
                            if (a[I][j].avaliblenums[k]) {
                                u++;
                            }
                        }
                        if (u == 1) {
                            done = true;
                            inrow = true;
                            break R;
                        }
                        u = 0;
                        for (J = 0; J < 9; J++) {
                            if (a[i][J].avaliblenums[k]) {
                                u++;
                            }
                        }
                        if (u == 1) {
                            done = true;
                            incolumn = true;
                            break R;
                        }
                        u = 0;
                        int p, h;
                        if (i < 3) {
                            p = 0;
                        } else if (i < 6) {
                            p = 3;
                        } else {
                            p = 6;
                        }
                        if (j < 3) {
                            h = 0;
                        } else if (j < 6) {
                            h = 3;
                        } else {
                            h = 6;
                        }
                        for (I = p; I < p + 3; I++) {
                            for (J = h; J < h + 3; J++) {
                                if (a[I][J].avaliblenums[k]) {
                                    u++;
                                }
                                if (u == 1) {
                                    insquare = true;
                                    done = true;
                                    break R;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (done) {
            num = k + 1;
            row = i;
            col = j;
        }
    }

    public void candidatelines(Square[][] a) {
        int i, j = 0, k = 0, u = 0, I, J, m = 1, y = 0, type = 0, row, col, num, p, h, box = 0;
        boolean flag = false, t = false, done = false, inrow = false, incolumn = false;
        R:
        for (i = 0; i < 9; i++) {
            for (k = 0; k < 9; k++) {
                for (j = 0; j < 9; j++) {
                    if (a[i][j].avaliblenums[k]) {
                        if (u == 0) {
                            u = getbox(i, j);
                            t = true;
                        } else if (getbox(i, j) != u) {
                            flag = true;
                            break;
                        } else {
                            m++;
                        }
                    }
                }
                if ((!flag) && t) {
                    switch (u % 3) {
                        case 1:
                            p = 0;
                            break;
                        case 2:
                            p = 3;
                            break;
                        default:
                            p = 6;
                            break;
                    }
                    int o = u - 1;
                    h = ((int) (o / 3)) * 3;
                    for (I = p; I < p + 3; I++) {
                        for (J = h; J < h + 3; J++) {
                            if (a[I][J].avaliblenums[k]) {
                                y++;
                            }
                        }
                    }
                    if (y > m) {
                        done = true;
                        break R;
                    }
                }
            }
        }
        if (done) {
            num = k + 1;
            box = u;
            col = j;
            type = 1;
            incolumn = true;
        }
        flag = false;
        t = false;
        R:
        for (j = 0; j < 9; j++) {
            for (k = 0; k < 9; k++) {
                for (i = 0; i < 9; i++) {
                    if (a[i][j].avaliblenums[k]) {
                        if (u == 0) {
                            u = getbox(i, j);
                            t = true;
                        } else if (getbox(i, j) != u) {
                            flag = true;
                            break;
                        } else {
                            m++;
                        }
                    }
                }
                if ((!flag) && t) {
                    switch (u % 3) {
                        case 1:
                            p = 0;
                            break;
                        case 2:
                            p = 3;
                            break;
                        default:
                            p = 6;
                            break;
                    }
                    int o = u - 1;
                    h = ((int) (o / 3)) * 3;
                    for (I = p; I < p + 3; I++) {
                        for (J = h; J < h + 3; J++) {
                            if (a[I][J].avaliblenums[k]) {
                                y++;
                            }
                        }
                    }
                    if (y > m) {
                        done = true;
                        break R;
                    }
                }
            }
        }
        if (done) {
            num = k + 1;
            box = u;
            row = i;
            inrow = true;
            type = 1;
        }
        p = 0;
        h = 0;
        R:
        for (k = 0; k < 9; k++) {
            for (I = 0; I < 7; I += 3) {
                for (J = 0; J < 7; J += 3) {
                    int e = -1, l = -1;
                    boolean cl = false, rw = false;
                    for (p = I; p < I + 3; p++) {
                        for (h = J; h < J + 3; h++) {
                            if (a[p][h].avaliblenums[k]) {
                                if (e == -1) {
                                    e = p;
                                    l = h;
                                }
                                if (e != p && e != -1) {
                                    rw = true;
                                }
                                if (l != h && l != -1) {
                                    cl = true;
                                }
                            }
                        }
                    }
                    if (!rw) {
                        done = true;
                        inrow = true;
                        break R;
                    } else if (!cl) {
                        done = true;
                        incolumn = true;
                        break R;
                    }
                }
            }
        }
        if (done) {
            box = getbox(p, h);
            type = 2;
            num = k + 1;
            if (inrow) {
                row = p;
            } else {
                col = h;
            }
        }
    }

    /*public void nakedpair(Square[][]a){
        int n1,n2,p1,p2,I,J=0;
        boolean inrow=false,incolumn=false,done=false;
        int[][]g=new int[9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                int u=0;
                for(int k=0;k<9;k++){
                    if(a[i][j].avaliblenums[k]){
                        u++;
                    }
                }
                g[i][j]=u;
            }
        }
        int i,j,l=0,k=0;
        int [] o = new int[9];
        boolean flag=false;
        for(i=0;i<9;i++){
            for(k=0;k<9;k++){
                o[k]=0;
            }
            for(j=0;j<9;j++){
                if(g[i][j]==2){
                    for(k=0;k<9;k++){
                        if(a[i][j].avaliblenums[k]){
                            if(!flag){
                                o[j]=k+1;
                                flag=true;
                            }
                            else{
                                o[j]+=((k+1)*10);
                                flag=false;
                                break;
                            }
                        }
                    }
                }
            }
            for(k=0;k<9;k++){
                if(o[k]!=0){
                    for(l=k+1;l<9;l++){
                        if(o[l]==o[k]){
                            n1=o[k]%10;
                            n2=o[k]/10;
                            for(j=0;j<9;j++){
                                if(j!=k&&j!=l){
                                    if(a[i][j].avaliblenums[n1-1]||a[i][j].avaliblenums[n2-1]){
                                        done=true;
                                        break;
                                    }
                                }
                            }
                            if(done){
                                inrow=true;
                                p1=(i*10)+k;
                                p2=(i*10)+l;
                            }
                        }
                    }
                }
            }
           
        }
        
        done=false;
        flag=false;
        R:
        for(j=0;j<9;j++){
            for(k=0;k<9;k++){
                o[k]=0;
            }
            for(i=0;i<9;i++){
                if(g[i][j]==2){
                    for(k=0;k<9;k++){
                        if(a[i][j].avaliblenums[k]){
                            if(!flag){
                                o[i]=k+1;
                                flag=true;
                            }
                            else{
                                o[i]+=((k+1)*10);
                                flag=false;
                                break;
                            }
                        }
                    }
                }
            }
            for(k=0;k<9;k++){
                if(o[k]!=0){
                    for(l=k+1;l<9;l++){
                        if(o[l]==o[k]){
                            done1 = true;
                            break R;
                        }
                    }
                }
            }
        }
        if(done1){
            n1=o[k]%10;
            n2=o[k]/10;
            for(i=0;i<9;i++){
                if(i!=k&&i!=l){
                    if(a[i][j].avaliblenums[n1-1]||a[i][j].avaliblenums[n2-1]){
                        done2=true;
                        break;
                    }
                }
            }
            if(done2){
                incolumn=true;
                p1=(k*10)+j;
                p2=(l*10)+j;
            }
        }
        done1=false;
        flag=false;
        R:
        for(I=0;I<7;I+=3){
            for(J=0;J<7;J+=3){
                for(k=0;k<9;k++){
                    o[k]=0;
                }
                for(i=I;i<I+3;i++){
                    for(j=J;j<J+3;j++){
                        if(g[i][j]==2){
                            for(k=0;k<9;k++){
                                if(a[i][j].avaliblenums[k]){
                                    if(!flag){
                                        o[(j-J)+(3*(i-I))]=k+1;
                                        flag=true;
                                    }
                                    else{
                                        o[(j-J)+(3*(i-I))]+=((k+1)*10);
                                        flag=false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                for(k=0;k<9;k++){
                    if(o[k]!=0){
                        for(l=k+1;l<9;l++){
                            if(o[l]==o[k]){
                                done1 = true;
                                break R;
                            }
                        }
                    }
                }
            }
        }
        if(done1){
            n1=o[k]%10;
            n2=o[k]/10;
            for(i=I;i<I+3;i++){
                for(j=J;j<J+3;j++){
                    if(()&&()){}
                }
            }
            if(done2){
                incolumn=true;
                p1=(k*10)+j;
                p2=(l*10)+j;
            }
        }
    }*/
    public int getbox(int a, int b) {
        if (a < 3) {
            if (b < 3) {
                return 1;
            } else if (b < 6) {
                return 2;
            } else {
                return 3;
            }
        } else if (a < 6) {
            if (b < 3) {
                return 4;
            } else if (b < 6) {
                return 5;
            } else {
                return 6;
            }
        } else if (b < 3) {
            return 7;
        } else if (b < 6) {
            return 8;
        } else {
            return 9;
        }
    }
}
