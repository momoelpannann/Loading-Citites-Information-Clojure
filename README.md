City and Province Information System

This project provides a Clojure-based menu-driven interface for interacting with city and province data. It allows users to list cities, display detailed information about specific cities, list provinces, and display population statistics for provinces. 
The program is designed to be user-friendly, guiding users through different options and submenus for retrieving relevant information.

Features

	•	List Cities:
	•	List all cities.
	•	List all cities for a given province, ordered by size and name.
	•	List all cities for a given province, ordered by population density.
	•	Display City Information:
	•	Enter a city name to retrieve detailed information.
	•	List Provinces:
	•	List all provinces with the total number of cities.
	•	Display Province Information:
	•	List all provinces with their total population.
	•	Exit:
	•	Exit the program.

How It Works

The program reads city and province data from a database (simulated with the db namespace) and presents a simple text-based menu to the user.
Depending on the user’s selection, the program will perform different operations, such as listing cities, retrieving specific information, and displaying statistics.

Usage

	1.	Run the program in a Clojure environment.
	2.	Follow the on-screen menu to interact with the data.
	3.	Enter your choice to navigate through the options and submenus.

Dependencies

	•	Clojure
	•	The db namespace, which handles data retrieval and storage.
