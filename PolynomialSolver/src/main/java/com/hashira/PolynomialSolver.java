package com.hashira;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Map;

/**
 * Contains the core logic for solving the polynomial constant term.
 */
public class PolynomialSolver {

    /**
     * Calculates the constant term 'c' of a polynomial using Lagrange Interpolation.
     * The constant term is the value of the polynomial at x=0, i.e., F(0).
     *
     * @param filePath The path to the JSON file containing the points.
     * @return The constant term 'c' as a BigInteger.
     * @throws Exception if the file cannot be read or parsed.
     */
    public static BigInteger solveForConstantTerm(String filePath) throws Exception {
        // 1. Read and parse the JSON file
        Gson gson = new Gson();
        Reader reader = new FileReader(filePath);
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> jsonMap = gson.fromJson(reader, type);
        reader.close();

        // 2. Extract k (the number of points needed)
        Map<String, Double> keysMap = (Map<String, Double>) jsonMap.get("keys");
        int k = keysMap.get("k").intValue();
        System.out.println("Degree of polynomial (m) is " + (k - 1) + ". Need " + k + " points.");

        // 3. Extract the first k points (x, y)
        BigInteger[] x = new BigInteger[k];
        BigInteger[] y = new BigInteger[k];

        for (int i = 0; i < k; i++) {
            // The JSON keys "1", "2", "3", etc., represent the x-coordinates
            String key = String.valueOf(i + 1);
            x[i] = new BigInteger(key);

            // The value object contains the base and value for the y-coordinate
            Map<String, String> pointData = (Map<String, String>) jsonMap.get(key);
            String valueStr = pointData.get("value");
            int base = Integer.parseInt(pointData.get("base"));
            
            // Convert the value from the given base to decimal (BigInteger for large numbers)
            y[i] = new BigInteger(valueStr, base);
            System.out.println("Point " + (i + 1) + ": (x=" + x[i] + ", y=" + y[i] + ")");
        }

        // 4. Calculate F(0) using Lagrange Interpolation formula
        // F(0) = sum_{j=0}^{k-1} y_j * L_j(0)
        // L_j(0) = product_{m=0, m!=j}^{k-1} (x_m) / (x_m - x_j)
        
        // Use BigDecimal for high-precision floating-point arithmetic
        // The final result should be an integer, but intermediate steps are fractions.
        MathContext mc = new MathContext(200); // Set precision for division
        BigDecimal constantTerm = BigDecimal.ZERO;

        for (int j = 0; j < k; j++) {
            BigDecimal y_j = new BigDecimal(y[j]);
            
            // Calculate Lagrange basis polynomial L_j(0)
            BigDecimal lagrangeNumerator = BigDecimal.ONE;
            BigDecimal lagrangeDenominator = BigDecimal.ONE;

            for (int m = 0; m < k; m++) {
                if (j != m) {
                    // Numerator part: product of -x_m which is (0 - x_m)
                    lagrangeNumerator = lagrangeNumerator.multiply(new BigDecimal(x[m]).negate());
                    // Denominator part: product of (x_j - x_m)
                    BigDecimal diff = new BigDecimal(x[j]).subtract(new BigDecimal(x[m]));
                    lagrangeDenominator = lagrangeDenominator.multiply(diff);
                }
            }
            
            // L_j(0) = numerator / denominator
            BigDecimal lj0 = lagrangeNumerator.divide(lagrangeDenominator, mc);

            // Add the term y_j * L_j(0) to the total sum
            constantTerm = constantTerm.add(y_j.multiply(lj0));
        }

        // The result should be a whole number, so we round it and convert to BigInteger
        return constantTerm.setScale(0, BigDecimal.ROUND_HALF_UP).toBigInteger();
    }
}
