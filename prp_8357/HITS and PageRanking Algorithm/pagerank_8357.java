							  // Charanpreet Kaur cs610 8357 prp
import static java.lang.System.*;
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
class pagerank_8357{
  public static void main(String[] args) throws IOException {
	int vertices=0, edges = 0;
    int count_iter = 0; 
	boolean flag = true;
    DecimalFormat a = new DecimalFormat("0.0000000"); 
    int firstval = 0; 
    int iter= 0;   
    double rateoferr = 0.0;
    double dm = 0.85; 
    String graphtext = ""; 
    System.out.println(" \n \t \t \t PAGE RANKING for  8357 \n");  
    if (args.length != 3){
      System.out.println("Less number of arguments: Format is : pagerank_8357 interations firstval graphtext");
      return;
    }
    for (int i=0;i<args.length;i++) {
      iter = Integer.parseInt(args[0]); //parsing the iteration value 
      firstval = Integer.parseInt(args[1]); //parsing the initial value 
      graphtext = args[2]; // parsing the sample graph text file name
    }
    if (!(firstval >= -2 && firstval <= 1)){
      System.out.println("First values not between -2, -1, 0 or 1");
      return;
    }
    Scanner scanner = new Scanner(new File(graphtext));  // reading sample graph 
    vertices = scanner.nextInt();
    edges = scanner.nextInt();
    double graph[][] = new double[vertices][vertices]; // initializing and representing the graph as an adjacency matrix
    for(int i = 0; i < vertices; i++){
      for(int j = 0; j < vertices; j++){
            graph[i][j] = 0.0;
      }
    }
    while(scanner.hasNextInt()){
      graph[scanner.nextInt()][scanner.nextInt()] = 1.0;
    }
	
    if (iter < 0){                  //if number of iteration is negative it sets the rate of error
      rateoferr = Math.pow(10, iter);
    }
    double pagrnk[] = new double[vertices];
	double outdegree[] = new double[vertices];
    double u[] = new double[vertices];
    double initial_pagrnk [] = new double[vertices];
    for(int i=0; i<vertices; i++){
      outdegree[i] = 0;                 // calculating outdegree for the graph
      for(int j=0; j<vertices; j++){
        outdegree[i] = outdegree[i] + graph[i][j];
      }
    } 
      if (vertices < 10){           //using switch case to initialize of the vertices for the page rank is less than 10
        switch(firstval){
          case 0:
          for (int i=0; i<vertices;i++){
            initial_pagrnk[i] = 0.0;
          }
          break;
          case 1:
          for (int i=0; i<vertices;i++){
            initial_pagrnk[i] = 1.0;
          }
          break;
          case -1:
          for (int i=0; i<vertices;i++){
            initial_pagrnk[i] = 1.0/vertices;
          }
          break;
          case -2:
          for (int i=0; i<vertices;i++){
            initial_pagrnk[i] = 1.0/Math.sqrt(vertices);
          }
          break;
        }
      }
      else{  //else if vertices is greater than 10 we initialize the pagerank by loading the default values.
        iter = 0;
        firstval = -1;
        rateoferr = 0.00001;
        for (int i=0; i<vertices;i++){
          initial_pagrnk[i] = 1.0/vertices;
        }
      }
      System.out.print("Base: " +count_iter + " : ");
      if (vertices > 5){
        System.out.println();
      }
      for(int i=0; i<vertices; i++){
          System.out.print(" P [" + i + "] = " + a.format(initial_pagrnk[i]));
          if (vertices > 5){
            System.out.println();
        }
      }
      System.out.println("\n");
      if(iter > 0){
        do{
          for (int j=0; j<vertices; j++){
            pagrnk[j]=0.0;
          }
          for (int j=0; j<vertices; j++){
            for (int k=0; k<vertices; k++){
              if(graph[k][j]==1){
                pagrnk[j] = pagrnk[j]+initial_pagrnk[k]/outdegree[k];
              }
            }
          }
          System.out.print("Iter: " + (count_iter+1) + " : "); //it will Compute Page Rank for all vertices
          if (vertices > 5){
            System.out.println();
          }
          for (int j=0; j<vertices; j++){
            pagrnk[j] = dm*pagrnk[j] + (1-dm)/vertices;
            System.out.print(" P [" + j + "] = " + a.format(pagrnk[j]));
            if (vertices > 5){
              System.out.println();
          }
        }
        System.out.println();   
        for (int j=0; j<vertices; j++){
          initial_pagrnk[j]=pagrnk[j];
        }
        count_iter++;
        iter--;
      } while(iter !=0);
    }
    else{
      do{
          if(flag == true)
          {
             flag = false;
          }
          else
          {
            for(int i = 0; i < vertices; i++) {
              initial_pagrnk[i] = pagrnk[i];
            }
          }
          for (int j=0; j<vertices; j++){
            pagrnk[j]=0.0;
            u[j]=0.0;
          }
          for (int j=0; j<vertices; j++){
            for (int k=0; k<vertices; k++){
              if(graph[k][j]==1){
                pagrnk[j] = pagrnk[j]+initial_pagrnk[k]/outdegree[k];
              }
            }
          }
          System.out.print("Iter: " + (count_iter+1) + " : "); //it will Compute Page Rank for all vertices
          if (vertices > 5){
            System.out.println();
          }
          for (int j=0; j<vertices; j++){
            pagrnk[j] = dm*pagrnk[j] + (1-dm)/vertices;
            System.out.print(" P [" + j + "] = " + a.format(pagrnk[j]));
            if (vertices > 5){
              System.out.println();
          }
        }
        System.out.println();
        count_iter++;
      } while(CheckConverge8357(pagrnk, initial_pagrnk, vertices, rateoferr)!=true);
    }
  }
    public static boolean CheckConverge8357(double initial[], double previous[], int n, double rateoferr){ // convergence function
       for(int i = 0 ; i < n; i++){
        if (Math.abs(initial[i]-previous[i]) > rateoferr )
          return false;
        }
       return true;
    }
}