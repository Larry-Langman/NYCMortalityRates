# Directory Structure:
    
## ana_code 
* contains the hql file with all the analytics outputs to any analytic listed in the hql file can be obtained by adding

       INSERT OVERWRITE DIRECTORY '/path/to/output/dir' 
* prior to the select portion of the hive query with the desired output director in the path

## data_ingest
* contains the hql file with the process to load data both for profiling, done in profiling_code (hive was used to understand irregularities/errors in the data) and for data analytics, done in ana_code

## etl_code
* contains the map and reduce java files and classes along with output (part-r-00000)
* Note: the output part-r-00000 was renamed and sent to "cleaned.csv", also in this directory
* the Readme.md contained in this directory describes the process of transfering the hive output file to a csv

## profiling_code
* contains the map and reduce java files and classes along with output for counting the number of lines in the data file
* also contains hive-profiling.hql where some exploration of the data was done with hive in order to better understand how best to clean the data

## screenshots
* ### cleaning
  * contains screenshots of etl_code compilation and run
* ### data_exploration
  *  contains screenshots of the hql file outputs from the hql file in profiling_code
* ### hive_analytics
  * contains screenshots of ana_code compilation and run
* ### jira_board
  * contains screenshots of jira board (of each of the 3 sprints)
* ### profiling
  * contains screenshots of the profiling_code's map reduce file to calculate the line count

## sharing_access.jpg 
* does not fit into any aforementioned folder so it was instead included separatly
* this file provides proof that the commands to share my directory with the graders was run

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
* Note: to re-run the analytic the output directory must be deleted with:
  * rm -f -r /path/to/output/dir

## Analytic/Exploration
Note: for the analytic to run one must already be connected to a hive cluster with the data file available to be loaded from hdfs, the step-by-step for how this is done is located in the data_ingest file (beeline --silent and the !connect jdbc:hive2://hm-1.hpc.nyu.edu:10000/ statements)
1. to run the analytic or profiling exploration hql statments, first copy the correct code from data_ingest (each is labeled with a comment)
2. after creating the table and uploading the data, use the hql statements in the hql file you are attempting to run, this would be in ana_code if you are attempting the run the analytics and profiling_code if you are attempting to run the data exploration hql statements
3. copy the hql statements (each statement is terminated by a semicolon) into the terminal
4. after a few moments hive will return a table with the query output
5. to save the output, add INSERT OVERWRITE DIRECTORY '/path/to/output/dir' with the path to the output directory, prior to any select statement
6. the results will then be saved to the directory from step 5 if you chose to save the results, the output will also be displayed in the terminal in which you put the hql statements