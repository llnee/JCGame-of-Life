import java.util.*; // to use Scanner
import java.io.*;  // to work with files
public class GameOfLife
{
   public static char[][] init(String filename) throws IOException {
       // create/open file
       File inputFile = new File(filename);
       Scanner input = new Scanner(inputFile);
       
       int rows = input.nextInt();
       int columns = input.nextInt();
       
       char[][] currGen = new char[rows][columns];
       int rowVal;
       int columnVal;
       while(input.hasNext()){
               String line = input.nextLine();
               rowVal = input.nextInt();
               columnVal = input.nextInt();
               currGen[rowVal][columnVal] = '*'; // fill/occupy cell
           }
       return currGen;
   }                             
  
   public static void display(int generation, char[][] grid){
       System.out.println("Generation " + generation);
       for(int i = 0; i < grid.length; i++){
          for(int j = 0; j < grid.length; j++){
              if (grid[i][j] == '*'){
                  System.out.print("| * |");
              }
              else
                  System.out.print("|   |");
          }
          System.out.println();
       }
   }
   
   
   /*
    * Returns an integer representing the number of neighbors a cell has.
   */
   public static int getNeighbors(char[][] currGen, int i, int j){
       int end = currGen.length-1;
       int numNeighbors = 0;
       if (i < end){ //checks that cell is not on the bottom
           //every cell has one cell below it if not on the bottom row
           if (currGen[i+1][j] == '*'){ //B
               numNeighbors+=1;
           }
           //every cell besides the last one has a R & BR
           if (j < end){
               if(currGen[i][j+1] == '*') //R
                   numNeighbors+=1;
               if(currGen[i+1][j+1] == '*') //BR
                   numNeighbors+=1;
           }
           //every cell besides the first one has a L & BL
           if (j > 0){
               if(currGen[i][j-1] == '*') //L
                   numNeighbors+=1;
               if(currGen[i+1][j-1] == '*') //BL
                   numNeighbors+=1;
           }
       }
       if (i > 0){ //checks that cell is not on the top
           //every cell has one cell above it if not on the top row
           if (currGen[i-1][j] == '*'){ //T
               numNeighbors+=1;
           }
           //every cell besides the last one has a TR
           if (j < end){
               if(currGen[i-1][j+1] == '*') //TR
                   numNeighbors+=1;
           }
           //every cell besides the first one has a TL
           if (j > 0){
               if(currGen[i-1][j-1] == '*') //TL
                   numNeighbors+=1;
           }
       }
       return numNeighbors;
   }
   
   public static char[][] nextGeneration(char[][] currGen){
       char[][] nextGen = new char[currGen.length][currGen.length];
       int numNeighbors;
       for (int i = 0; i < currGen.length; ++i){
           for (int j = 0; j < currGen.length; ++j){
               numNeighbors = getNeighbors(currGen, i, j);
               if (numNeighbors < 2 || numNeighbors > 3){//death
                   nextGen[i][j] = ' ';
               }
               else if (numNeighbors == 3 && currGen[i][j] != '*'){//birth
                   nextGen[i][j] = '*';
               }
               else //survival, no change between generations
                   nextGen[i][j] = currGen[i][j];
            }
       }
       return nextGen;
    }
   
   /*
    * Returns true if the next gen is not equal to this gen, if the nextNext
    * gen is not equal to this one, and if the next gen has at least one 
    * organism.
   */
   public static boolean validNextGen(char[][] currGen){
       char[][] nextGen = nextGeneration(currGen);
       char[][] nextNextGen = nextGeneration(nextGen);
       
       boolean filled = false;
       boolean gensEqual1 = true;
       boolean gensEqual2 = true;
       for (int i = 0; i < currGen.length; ++i){
           for (int j = 0; j < currGen.length; ++j){
               if (currGen[i][j] == '*') //else, will stay false
                   filled = true;
               if (currGen[i][j] != nextGen[i][j])
                   gensEqual1 = false;
               if (currGen[i][j] != nextNextGen[i][j])
                   gensEqual2 = false;
           }
       }
       if (!filled)
           System.out.println("nextGen is empty");
       if (gensEqual1)
           System.out.println("two consecutive gens were equal");
       if (gensEqual2)
           System.out.println("alternating pattern of gens equal");
       
       if (filled && !gensEqual1 && !gensEqual2)
           return true;
       return false;
   }

   public static void main(String [] args) throws IOException
   {
       if (args.length == 0)
          System.out.println("You must enter a filename. Exiting.");
       else
       {
           File inputFile = new File(args[0]);
           //Scanner input = new Scanner(inputFile);
           Scanner keyboard = new Scanner(System.in);
           
           //makes a gameboard with correctly filled cells based on the
           // input file
           char[][] currGen = init(args[0]);
           
           int genCount = 0;
           System.out.println("How many generations of Life would you like to play? ");
           int genLimit = keyboard.nextInt();
           
           String continuousDisplay = "";
           boolean validInput = false;
           while (!validInput){
               System.out.println("Do you want generations to be displayed continuously? Enter Y or N.");
               continuousDisplay = keyboard.next(); //'N' or 'Y'
               if (continuousDisplay.equalsIgnoreCase("Y")){//display gens consecutively
                   validInput = true;
               }
               else if (continuousDisplay.equalsIgnoreCase("N")){//user hits enter key to see nextGen
                   validInput = true;
               }
               else 
                   System.out.println("Invalid response.");
           }
           String enter;
           display(genCount, currGen);//display first generation
           while (validNextGen(currGen) && genCount <= genLimit-1){
               //play game
               enter = " ";
               if (continuousDisplay.equalsIgnoreCase("Y")){
                   currGen = nextGeneration(currGen);
                   genCount+=1;
                   display(genCount, currGen);
               }
               else {//prompt for enter key/input
                   System.out.println("Press enter to see next generation");
                   enter = keyboard.nextLine();
                   currGen = nextGeneration(currGen);
                   genCount+=1;
                   display(genCount, currGen);
               }
           }
       }
    }
}
