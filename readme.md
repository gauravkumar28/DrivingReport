### Prerequisit
Check `JAVA_HOME` is set with path to java.
Otherwise update JAVA_HOME with path to java.
You can find file path to java by running `which java `

####Example:
```
which java
/usr/bin/java
export JAVA_HOME=/usr/bin/java
```
### How to run the Program?

`./run.sh <path_to_input_txt_file>

####Example:
`./run.sh ./data/test_input.txt`

### Description
#### DrivingReportApplication
This is the main program which will take the input from the user and parse the input 
with the help of `IDrivingDataParsar` and then will call `IDrivingService` to
generate the report and later call `IDisplayService` to show the desired result.

#### IDrivingDataParsar
Interface to parse input data to generate Collection of Drivers and Trips

#### TxtFileDrivingDataParsar
Implementation of interface `IDrivingDataParsar` to take file object as input and parse the content 
of the file and generate collection of Drivers and Trips. Internally it call
`IDrivingLogEntryValidator` for data validation and `IDrivinglogTokenizer` to tokenize
driving data entry
#### IDrivingLogEntryValidator
Validator to validate each entry of the input file
#### TripLogEntryValidator
Implementation of `IDrivingLogEntryValidator` to validate driving data for Trip
#### DriverLogEntryValidator
Implementation of `IDrivingLogEntryValidator` to validate driving data for Driver
#### DriverLogEntryValidatorFactory
Factory to generate objects for `TripLogEntryValidator` and `DriverLogEntryValidator`
#### Driver
Class to represent driver
#### Trip
class to represent trip
#### DrivinglogEntry
class to represent each entry of input file. It can have information for either driver or trip
#### DrivingLogEntryCollections
collections having lists of drivers and trips from input file returned by 
`IDrivingDataParsar` parser
#### DrivingLogTokenizer
Class to tokenize driving log entry to create `Trip` or `Driver` objects
#### LogEntryType
Enum to represent log entry will be either of Trip type or Driver type.
#### IDrivingService
Interface to generate driving report for each driver
#### DrivingService
Implementation of IDrivingService which will filter outlier trips and calculate avg speed and total distance for each driver
#### DrivingSummary
Class to represent summary of driving for each driver having total distance covered and avg speed
#### IDisplayService
Interfcae to display List of `DrivingSummary` object
#### DrivingReportDisplayService
Implementation of IDisplayService to display driving summary of each driver in descending order of their distance.
#### InvalidDataException
Exception thrown incase of invalid input by implemntations of IDrivingDataParsar


