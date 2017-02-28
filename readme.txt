Installation guide for COMP5134 PersonNet Java project.
For example, if you extract the project .zip file to E:\COMP5134,

Open windows command prompt and type the following command:

To compile the program, change directory to E:\COMP5134src to type:
javac -cp .;E:\COMP5134\src;E:\COMP5134\lib;E:\COMP5134\lib\twitter4j-core-2.1.11.jar PersonNetUI.java

Then to run the program, change directory E:\COMP5134bin to type:
java -cp .;E:\COMP5134\lib;E:\COMP5134\lib\twitter4j-core-2.1.11.jar PersonNetUI
