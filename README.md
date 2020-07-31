# Todo-List App

This is a command line todo-list application. The app will allow a user to add and track their todos by due date, category, priority, and status (complete/incomplete). 
The application stores all todos in a CSV file. The CSV file is a plain text file, containing data organized into columns separated by a comma. 
The data in each column is enclosed in double quotes. The first line of the file contains the headers for each column.

## Design patterns:

### MVC Design pattern

whole project uses **MVC** design pattern.

- Model: model package 
- View: view package 
- Controller:  task package, command package

### The "Option" class uses builder pattern

* For the **Option** class, We used builder pattern as it contains a lot of fields, and most of them are optional. Therefore, I applied builder pattern to create an Option.

* The **Options** contain all the Option objects and go through the Optionmaker to build commandline object that match the user intense, and pass it to the next step.

### The "filter" uses "Factory pattern & Observer pattern".

* In order to build filters, we use factory design pattern. Filter() does not need to know which concrete filters would be used, but only make filters according to the command line arguments. So we hides the constructor behind abstraction; the method decides which subtype to return.

* Using polymorphism to implement different concrete filters and store them in a filter stash.

* Then we use Observer design pattern on a list of filters. For each filter, we invoke filter()
and modify the same to-do List regardless of the order. 
    
