# The code in this repo:
* has the goal of conducting ETL and profiling to put the New_York_City_Leading_Causes_of_Death.csv file into a cleaned state where analytics can be run on it. In my case, the goal was to run analytics that would determine the leading causes of death in NYC and thus shed light onto if the largest causes are mitigable. This analysis was done in Hive.
* contains the map and reduce java files and classes for filtering and cleaning the data (output saved as cleanedOutput which was then saved as cleaned.csv)
* these classes should be run on an HDFS cluster as a MapReduce program (instructions below)
* contains the map and reduce java files and classes for counting the number of lines in the data file (output saved as lineCount.txt)
* also contains hive-profiling.hql where some exploration of the data was done with Hive in order to best understand how to clean the data
* 

# How to Build And Run Code

## Cleaning/Line Count
Note: to apply the following to line count, replace the file names of "Clean" with "CountRecs" (case sensitive)
1. to run the cleaning code, first compile each of the java files with:
* javac -classpath `yarn classpath` -d . CleanMapper.java
* javac -classpath `yarn classpath` -d . CleanReducer.java
* javac -classpath `yarn classpath`:. -d . Clean.java
2. create a jar file with the cleaned java files:
* jar -cvf clean.jar *.class
3. create a directory to store the input file:
* hdfs dfs -mkdir input
4. add the inputfile to the directory
* hdfs dfs -put /path/to/New_York_City_Leading_Causes_of_Death.csv input
5. run the map reduce program:
* hadoop jar clean.jar Clean /path/to/New_York_City_Leading_Causes_of_Death.csv /user/lsl359/Not-Yet-Created-Output-Directory
* Note: to re-run the cleaning the output directory must be deleted with:
  * rm -f -r /path/to/output/dir