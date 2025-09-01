package com.hashira;

import java.math.BigInteger;

/**
 * Main class to execute the polynomial solver.
 * It reads the test case files, calls the solver, and prints the results.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Hashira Placements Assignment Solver ---");
        
        // Define paths to the test case files
        String testcase1Path = "testcases/testcase1.json";
        String testcase2Path = "testcases/testcase2.json";
        
        // --- Solve for Test Case 1 ---
        try {
            System.out.println("\nSolving for Test Case 1...");
            BigInteger constant1 = PolynomialSolver.solveForConstantTerm(testcase1Path);
            System.out.println("Result (c) for Test Case 1: " + constant1);
        } catch (Exception e) {
            System.err.println("Error processing Test Case 1: " + e.getMessage());
            e.printStackTrace();
        }
        
        // --- Solve for Test Case 2 ---
        try {
            System.out.println("\nSolving for Test Case 2...");
            BigInteger constant2 = PolynomialSolver.solveForConstantTerm(testcase2Path);
            System.out.println("Result (c) for Test Case 2: " + constant2);
        } catch (Exception e) {
            System.err.println("Error processing Test Case 2: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
