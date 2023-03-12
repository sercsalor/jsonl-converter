# Jsonl Converter
A jsonl converter made in Java

## Project Setup
### Requirements
- Java 11, Maven 3

### Setup
To generate the executable jar file we need to go to the project directory and execute `mvn clean package`
This will generate a new file under `target` folder called `jsonl-converter-1.0-shaded.jar`

### Execution
The executable jar accepts two arguments. The first one is the file path of the file, and the second one is the delimiter character.

#### Example:

`java -jar target/jsonl-converter-1.0-shaded.jar src/main/resource/input-1.txt ,`
This will run the converter with the file on `src/main/resource/input-1.txt` and `,` as the delimiter.

## Notes

### Design decisions
- I am not sure if I am allowed to use 3rd party libraries aside from JUnit so I didn't use libraries for converting Java Objects to JSON.
- For the string tokenization, I went with the per character checking since I am not really good with regex. A much easier approach would be to using `String#split`. 

### Limitations
- The solution is limited to the input file having:
	- In the case of value with ", it is assumed that it is always closed.
