12/21/23
Author: Charan Williams
Version 1.0

# SCHEDULING APPLICATION

This GUI application connects to a provided MySQL database and was built to meet specific business requirements for a hypothetical global consulting organization.
This application enables the user to:

Log-in with a user ID Password stored in the database
View tracked login attempts stored in a text file called login_activity.txt
View all customers and appointments
View a filtered list of appointments by current month or week
Add, update, and delete customers
Add, update, and delete appointments
Be alerted if the user has upcoming appointments within 15 minutes
Run 3 different reports to view important business information

The three reports available in this application are Total Appointments, Contact Schedules, Total Customers by Location.

The Total Appointments report allows the user to select a month and appointment type, and view a total count of the matching appointments.
The Contact Schedules report allows the user to select a contact and view a schedule of appointments including appointment ID, title, description, location, contact, type, start date and time,
end date and time, customer, user, as well as a count of the number of appointments for the selected contact.
The third report, Total Customers by Location, allows the user to select a country, and then select a first-level division from a filtered list of divisions and view a count of the number of customers in that first-level division.

All appointment times are displayed in the application in the system default time zone. Times are saved into the database in UTC time, the database server's default timezone.
When adding or updating an appointment, it is checked against all appointments in the database to ensure it does not overlap with an existing appointment.
Appointment times are also checked against the business's hours of 8:00 am to 10:00 pm EST to ensure no appointments are scheduled outside business hours.

In order to support an international organization, the login screen is translated into French if the user's default system language is French.

This application was made using IntelliJ IDEA 2022.2 (Community Edition), Java SDK version 17.0.4, JavaFX version 17.0.2-ea, and MySQL Connector version 8.0.30

## HOW TO RUN THIS APPLICATION:
In IntelliJ, go to File, New, Project from Existing Sources and select the project directory.
Then, select Import project from external module and select Maven.
Once the project directory opens, right-click the pom.xml file and select Add as Maven Project.
Then, select Edit Configurations and ensure that Java 17 SDK is selected, and enter main.main as the main class.
Finally, go to File, Project Structure, Libraries, and add the MySQL Connector.

You should now be able to build and run the application.
Ensure that the correct details are set in the JDBC class to connect to the database.
