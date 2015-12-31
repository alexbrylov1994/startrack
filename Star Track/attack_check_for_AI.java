public boolean attackLocationPlayer()    {
   boolean placeTaken = false;

   int aiRow=computerPlayer.getRow();
   int aiColumn=computerPlayer.getColumn();

   int humanRow=currentLocation[0];
   int humanColumn=currentLocation[1];
   // Change for when it's on borders, add two checks(Diagonals)
   // check for Problem, when a ship is in corners
   if (humanRow==0 && humanColumn==0)
   		if(grid[humanRow][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow+1][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow+1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true;

   // This works well! 
  	if (humanRow==0 && humanColumn==3)

   		if(grid[humanRow][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow+1][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow+1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true;

   	//works fine!
	if (humanRow==3 && humanColumn==0)

   		if(grid[humanRow-1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow-1][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true;
   	// works
  	if (humanRow==3 && humanColumn==3)

   		if(grid[humanRow][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow-1][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow-1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true;




    // checks if ai is located on borders, but no it conrners
   	if (humanRow==0 && humanColumn!=0 && humanColumn!=3)

   		if(grid[humanRow][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow+1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true;

   		else if(grid[humanRow+1][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true;

   		else if(grid[humanRow+1][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true;


	if (humanRow==3 && humanColumn!=0 && humanColumn!=3)

   		if(grid[humanRow][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow-1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true;

   		else if(grid[humanRow-1][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true;

   		else if(grid[humanRow-1][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true;


	if (humanColumn==0 && humanRow!=0 && humanRow!=3)

   		if(grid[humanRow+1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow-1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true;

   		else if(grid[humanRow+1][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true;

   		else if(grid[humanRow-1][humanColumn+1]==grid[aiRow][aiColumn])
   			placeTaken = true;


	if (humanColumn==3 && humanRow!=0 && humanRow!=3)

   		if(grid[humanRow+1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow-1][humanColumn]==grid[aiRow][aiColumn])
   			placeTaken = true; 

   		else if(grid[humanRow][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true;
   

   		else if(grid[humanRow+1][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true;

   		else if(grid[humanRow-1][humanColumn-1]==grid[aiRow][aiColumn])
   			placeTaken = true;

   	// checks if ai player is located in the middle of grid (outside bounders and not in corners of the grid)

   	if (humanRow!=0 && humanColumn!=0 && humanRow!=3 && humanColumn!=3)

		if(grid[humanRow-1][humanColumn]==grid[aiRow][aiColumn]) // 8
			placeTaken = true; 

		else if (grid[humanRow-1][humanColumn+1]==grid[aiRow][aiColumn]) //9
			placeTaken = true; 

		else if (grid[humanRow][humanColumn+1]==grid[aiRow][aiColumn]) // 6
			placeTaken = true; 

		else if (grid[humanRow+1][humanColumn+1]==grid[aiRow][aiColumn])// 3
			placeTaken = true; 

		else if (grid[humanRow+1][humanColumn]==grid[aiRow][aiColumn])//2
			placeTaken = true; 

		else if (grid[humanRow+1][humanColumn-1]==grid[aiRow][aiColumn])//1
			placeTaken = true;

		else if (grid[humanRow][humanColumn-1]==grid[aiRow][aiColumn])//4
			placeTaken = true; 

		else if (grid[humanRow-1][humanColumn-1]==grid[aiRow][aiColumn])//7
			placeTaken = true; 

   return placeTaken;

  	// test is checking if u have 2 things on one location. maybe use it to check it space it take or not. 
  	// just saying  
   // write the same thing for AI, I know Alex, embrace your self....
   }