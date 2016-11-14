



public class assemblyLineScheduler {
    
    public int NUM_STATION = 6;
    public int T1[] = new int[NUM_STATION];
    public int T2[] = new int[NUM_STATION];
    public int T3[] = new int[NUM_STATION];


    public int AssemblylineDP(int[][] a, int[][] t, int[] e, int[] x) {

        T1[0] = e[0] + a[0][0]; // s1 time
        T2[0] = e[1] + a[1][0]; // s2 time
        T3[0] = e[2] + a[2][0]; // s3 time
        
        for (int i = 1; i < NUM_STATION; ++i) {
            T1[i] = min3(T1[i - 1] + a[0][i], T2[i - 1] + t[1][i] + a[0][i],T3[i-1]+a[2][i]+t[4][i]);
            T2[i] = min3((T2[i - 1] + a[1][i]), (T1[i - 1] + t[0][i] + a[1][i]), (T3[i - 1] + a[1][i] + t[3][i]));
            T3[i] = min3(T3[i - 1] + a[2][i], T2[i - 1] + t[2][i] + a[2][i],t[5][i]+T1[i-1]+a[0][i]);
            
            System.out.println("Line 1 : " + T1[i]);
            System.out.println("Line 2 : " + T2[i]);
            System.out.println("Line 3 : " + T3[i]);
        }   
        return min3(T1[NUM_STATION - 1] + x[0], T2[NUM_STATION - 1] + x[1],T3[NUM_STATION - 1] + x[2]);
    }

    public static int min(int a, int b) {
        return a < b ? a : b;
    }

    public static int min3(int a, int b, int c) {
        int min = a;
        if (min > b) min = b;
        if (min > c) min = c;
        return min;
    }

    public int AssemblylineRec(int[][] a, int[][] t, int[] e, int[] x, int n, int j){
        if(n == 0){
            return e[j] + a[j][0];
        }

        int T1 = Integer.MAX_VALUE;
        int T2 = Integer.MAX_VALUE;
        int T3 = Integer.MAX_VALUE;
        if(j == 0){
            T1 =Math.min(AssemblylineRec(a, t, e, x, n-1, 0) + a[0][n],
                    AssemblylineRec(a, t, e, x, n-1, 1) + t[1][n] + a[0][n]);

        }else if(j == 1){
            T2 = min3(AssemblylineRec(a, t, e, x, n-1, 1) + a[1][n],
                    AssemblylineRec(a, t, e, x, n-1, 0) + t[0][n] + a[1][n],
                    AssemblylineRec(a, t, e, x, n-1, 2) + t[2][n] + a[1][n]);

        }else if(j == 2){
            T3 =Math.min(AssemblylineRec(a, t, e, x, n-1, 2) + a[2][n],
                    AssemblylineRec(a, t, e, x, n-1, 1) + t[1][n] + a[2][n]);


        }

        return min3(T1,T2,T3);
    }
    
    
    public int AssemblylineRec(int[][] a, int[][] t, int[] e, int[] x){
        int n = a[0].length-1;
        return min3(AssemblylineRec(a,t, e, x, n, 0) + x[0],
                AssemblylineRec(a,t, e, x, n, 1) + x[1],AssemblylineRec(a,t, e, x, n, 2) + x[2]);
    }
    
    public static void main(String[] args) {

        /* int A[][] = {{10,11,13,22},{5,6,4,12},{3,12,8,9}};
         int T[][] = {{12,4,1,15},{10,7,2,6},{1,2,11,8},{4,2,12,5}};
         int E[] = {2,2,2}; //entry times for the lines
         int X[] = {3,3,3}; //exit times for the lines
         */
     	
     	  int A[][] = {{3, 5, 3, 4,4,4}, {8, 2, 6, 4,5,7}, {5, 5, 3, 5,6,2}};
          int T[][] = {{0,2,3,1,3,4}, {0,2,1,2,2,1}, {0,2,3,4,4,1},{0,1,1,2,3,3},{0,2,2,1,1,3},{0,1,2,1,2,1}};
          int E[] = {2, 2, 2};
          int X[] = {1,1,1};
         
         assemblyLineScheduler  cass = new assemblyLineScheduler ();
         
         System.out.println("Dynamic Solution: ");
         System.out.println("Expected value is = 26 and calculated time = "+cass.AssemblylineDP(A, T, E, X));
         System.out.println("");
         System.out.println("Recursice Solution: ");
         System.out.println("Expected value is = 26 and calculated time = "+cass.AssemblylineRec(A, T, E, X));


     }

}

