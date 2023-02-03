# Sharif University Of Technology
* **Department of Computer Engineering**
* **Advanced Programming project**
* **Fall 2022**

# Educational consulting system
This is an **educational consulting system**.
This system consists of Managers, Consultants and Students.
Managers supervise consultants and each consultant, write study plans and checks them correctly executed
by their group of students.



# installation
You can run jar file of the project in out/artifacts/AP_Project_jar directory.
please notice that you must have redis installed and running on port 6379 of your machine
in order for project to get connected to DataBase.
alternatively you can pull the docker image of project from [here](https://hub.docker.com/r/mr87mohammadi/ap-project-image) and run the following instructions
in the terminal.<STRONG>please notice that you have to have docker installed on your machine. you also must have the official image of redis on your machine</STRONG>
* docker network create mynet
* docker run -p 6000:6379 -d --name myred --network mynet redis
* docker run -p 80:80 -i -t --network mynet mr87mohammadi/ap-project-image
# Usage
At the beginning you have to choose your role and then enter your credentials to sing in, then you'll see a menu, based on your role,
and you can choose the items on the menu.
anyone can sign up as a student, but consultants must be 
signed up by the manager. managers themselves must be signed up by the owner, and they cannot be added by themselves or any other role but the owner.
for interacting with the program you just have to click on the numbers based on the menu that you're currently seeing on the terminal.
if you had any question about object-oriented design of the project, you can check
diagrams and other related assets which are in the root of project in specified folders.

# Author
**Mahdi Mohammadi**

# License
Feel free to use the project resources.

# Support
If you had any question about the project, send a message to my [Telegram](https://t.me/Mahdi_Mohammadi_z)

# RoadMap
In the future, we are going to make a graphical user interface for the project.