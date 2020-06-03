# Fraud_Detection_Exercise
Coding exercise for Afterpay Touch

## Build
 
```
mvn package
```

## Run 
```
java -jar target/frad-detection-0.1.0.jar [threshold] [filename]
```
 #### For example 
```
java -jar target/frad-detection-0.1.0.jar 200.00 test_files/test2.csv
```

## Test 
#### Generate Test Files 
```
cd test_files
npm install 
node testFileGenerator.js [filename]
```
#### Current Test Cases
##### Test case 1
###### Command
```
java -jar target/frad-detection-0.1.0.jar 200.00 test_files/test1.csv
```
###### Output
```
2d7426b72a604755a211cfe86bc8ab16
35eab6e9225147b5bbb8ebc1dc45bcc9
```
##### Test case 2
###### Command
```
java -jar target/frad-detection-0.1.0.jar 200.00 test_files/test2.csv
```
###### Output
```
c319beef8db74fc5a24fccffcca67391
```
##### Test case 3
###### Command
```
java -jar target/frad-detection-0.1.0.jar 200.00 test_files/test3.csv
```
###### Output
```
30bcf27d2cb94e0a8539f2c26f026182
059afe29102845429b6aa249961f7b98
```

