#!/bin/sh

if [ -d "Peel" ]; then 
	echo "The folder is already extracted, so cleaning the old directory"
	rm -rf Peel
fi


echo "Unzipping the package.. "
unzip Peel.zip
echo "Extraction Successful"
cd Peel
mv json-simple-1.1.jar junit-4.10.jar bin/
mv input.json bin/
cd bin

echo "Executing the assignment...."
echo "Executing the JUnit test cases ... "
java -cp .:junit-4.10.jar:json-simple-1.1.jar com.peel.test.UnitTestCaseRunner

echo "Please enter the content name and country "
java -cp .:json-simple-1.1.jar com.peel.testclient.TestClient

