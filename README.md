### Practical task
***
The implementation of the REST service.
---
## REQUAREMENTS
---
You need installed:

1. JDK 8. Type in console(windows) or terminal(linux) "javac -version" to check.  If not, see [this](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)

2. PostgreSQL. [install](https://www.postgresql.org/download/) 

3. Maven. [install](https://maven.apache.org/install)

## INSTALLATION
---
First you need to clone or download repository.

1. Using PostgreSQL, create DB ‘employeedb’. 
Create table ‘employee’ and add at least 2 employees. Or execute sql queries 'create_table_employee.sql' and 'insert_into_employee.sql' in ./src/main/resources/sql.
Table ‘employee’ required to create in accordance with the scheme:
	~~~employee
		*column name* 	|	*type*
		*employee_id	|	bigserial
		*first_name	|	text
		*last_name	|	text
		*department_id	|	int
		*job_title	|	text
		*gender		|	text
		*date_of_birth 	|	date

2. In command line go to project directory *java-mastery-task-simplewebapp*. 
3. In *../src/main/resources/application.properties* change username and password to yours if necessary. By default username **postgres** password **root**.
4. In command line type the following commands:
	~~~
	*'mvn clean package' - to generate jar file 'simplewebapp-0.0.1-SNAPSHOT.jar'
	*'java -jar target/simplewebapp-0.0.1-SNAPSHOT.jar' - to run app

For testing app type in command line: ~~~*mvn test. 
	
5. Now the REST api running and you need to go [there](localhost:8080/simplewebapp) to work with it.


## WORK WITH REST API
---

1) Get list of Employees
       
   To get list of Employees press on button *show all Employees*. You will see list of Employees in the left-bottom side of page.
   If list is empty, you'll see "No Employees found".
	
2) Get an Employee by id

    To get an Employee by id: enter the id in text box 'Id' and then press button *show an Employee by id*.
    If Employee with such id is not exist, you'll see the message.
    
3) Create an Employee

	To create an Employee: enter the employee data in text boxes and the press button *create an Employee*. For example:
	
	 ~~~*First Name: Pavel
        *Last Name: Vasin
        *Department Id: 12
        *Job Title: Job
        *Gender : MALE
        *Date Of Birth: 22/11/1991
         
    Date of birth must be entered in the following format: 'dd/MM/yyyy'.
    If all entered correct, you will see "Emplyee created".
    Else you can see text messages with explanation of problem.

4) Update an Employee

	To update an Employee: enter the employee data and employee id in text boxes. Then press button *update an Employee*. For example:
	
    ~~~*First Name: Pavel
       *Last Name: Vander
       *Department Id: 12
       *Job Title: Job
       *Gender : MALE
       *Date Of Birth: 22/11/1991
       *Id: 12
             
        Date of birth must be entered in the following format: 'dd/MM/yyyy'.
        If all entered correct, you will see "Accepted".
        Else you can see text messages with explanation of problem.
	
5) Delete Employee by id

	To delete an Employee by id: enter the id in text box 'Id' and then press button *delete an Employee by id*.
    If Employee with such id is exist, you'll see "Accepted", else -  messages with explanation of problem.
	
6) Delete all Employees

    To delete all Employees press on button *delete all Employees*. You will see "No Employees found".

## FAQ

"Employee with such id not found" - check Id text box
"Employee with such id is already exist" - empty Id text box(id is auto generated)
"No Employees found" - list of Employees is empty
"Inter correct data" - check text boxes, some of them have incorrect value
"Inter Id and Employee data" - for updating an Employee all text boxes must be filled