# Esports Tournament Management System

An Object-Oriented Java application that simulates an esports tournament lifecycle. It handles data ingestion, match generation, scoring algorithms, and performance querying.

## Key Features
* **Data Encapsulation & File I/O:** Securely loads and parses `Game` and `Gamer` data from external CSV files using strict exception handling.
* **Deep Copying & Immutability:** Utilizes copy constructors and cloned arrays to maintain data integrity across match generation and points board calculations.
* **Algorithmic Matchmaking:** Dynamically generates randomized 15-match sets for each gamer, factoring in skill points, experience multipliers, and bonus thresholds.
* **Complex Data Querying:** Implements a querying engine to extract specific metrics (e.g., highest-scoring match, most contributing game, medal distribution) from deeply nested data structures.

## Technologies
* Java (OOP, File I/O, Array Manipulations, Exception Handling)
