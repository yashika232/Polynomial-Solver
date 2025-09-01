Polynomial Constant Solver
Overview
This Java application solves for the constant term (c) of a polynomial given a set of points that lie on its curve. The input points are read from JSON files, and the program uses Lagrange Interpolation to determine the polynomial's value at x=0, which corresponds to the constant term.

This project was created as a solution for the Hashira Placements programming assignment.

Core Concepts
Problem: Given n points (x, y) that satisfy a polynomial of degree m, where k = m + 1 points are sufficient to define the polynomial, find the constant term c.

Solution: The constant term c is the y-intercept of the polynomial, which is the value of the function when x=0, i.e., F(0).

Methodology: Lagrange Interpolation is used to find F(0) without needing to explicitly calculate the polynomial's coefficients (a, b, etc.). The formula directly computes the value of the polynomial at a specific point (x=0 in our case) using a given set of points.

Data Handling: The solution uses Java's BigInteger and BigDecimal classes to handle the arbitrarily large numbers found in the test cases and to maintain precision during fractional calculations. The JSON input is parsed using the popular Gson library.

Getting Started
Follow these instructions to set up and run the project in Visual Studio Code.

Prerequisites
Java Development Kit (JDK): Version 11 or higher.

Visual Studio Code: The latest version.

Extension Pack for Java: The official Java extension pack from Microsoft, available on the VS Code Marketplace.

Project Structure
Ensure your project folder is set up with the following structure:

PolynomialSolver/
├── pom.xml                 # Maven project configuration
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── hashira/
│                   ├── Main.java           # Main executable class
│                   └── PolynomialSolver.java # Core logic
└── testcases/
    ├── testcase1.json      # First test case
    └── testcase2.json      # Second test case

⚙Setup and Execution
Open the Project: Open the root PolynomialSolver folder in Visual Studio Code.

Load Project: VS Code's Java extension will automatically detect the pom.xml file and configure the project. It will download the necessary dependencies (Gson) in the background. You can monitor the progress in the status bar.

Clean the Workspace (If Needed): If you see any package-related errors (e.g., "The declared package does not match..."), open the Command Palette with Ctrl+Shift+P, search for Java: Clean Java Language Server Workspace, and select it. Click "Reload and Delete" when prompted.

Run the Application:

Open the src/main/java/com/hashira/Main.java file.

Click the ▶ Run button that appears above the main method.

Do not try to compile and run using javac and java commands from the terminal, as this will not link the required Gson library.

Expected Output
After running the application, the output will be displayed in the TERMINAL panel within VS Code. It should look exactly like this:


<img width="1402" height="763" alt="image" src="https://github.com/user-attachments/assets/9c7d4cda-ee68-4244-a5cc-b9b36f8cd3c0" />

