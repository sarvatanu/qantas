# Instruction to run the program on command line.


Sample output of the DateService on command line is as below :

##### Success scenario
###### *****************************************
###### ** Enter Start Date in yyyy-MM-dd format : 2010-03-02
###### ** Enter End date in yyyy-MM-dd format   : 2010-03-22
###### ** Total number of days between 2010-03-02 and 2010-03-22 is 19
###### *****************************************

##### Failure scenario with message
###### *****************************************
###### ** Enter Start Date in yyyy-MM-dd format : 2011s
###### ** Enter End date in yyyy-MM-dd format   : d
###### ** Start date is invalid. Date should be in yyyy-MM-dd
###### *****************************************

### command to execute
Execute the following commands in the order to execute the program. When second step is executed, system will expect the user innput.

1. mvn clean compile -q
2. mvn exec:java -Dexec.mainClass="au.com.qantas.Application" -q
