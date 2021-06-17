The CountBits.java program counts how many similar bits there are for two input files. This is the instructions for how to run it on the course Virtual Machine (LINUX): 

Step 1: Download the CountBits.java file from canvas
Step 2: Open the terminal in Linux
Step 3: Go to the directory of the downloaded file 
Step 3: Create two new txt. files. The first one should contain the original MD5/SHA256 bits, for example: "originalBits.txt". The second one should contain the bits with the first bit flipped, and the file name could be: "flippedBit.txt". 
Step 4: In the terminal, compile the CountBits.java program by typing: 

javac CountBits.java 

Then you should send the two txt files created to the terminal by typing: 

java Countbits <name of original file> <name of flipped bit file>   

Step 5: You should see a response in the terminal telling you how many bits that are similar. 

