/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import java.util.Map;

/**
 * String-based commands provided by the expression system.
 * 
 * <p>PS3 instructions: this is a required class.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You MUST NOT add fields, constructors, or instance methods.
 * You may, however, add additional static methods, or strengthen the specs of existing methods.
 */
public class Commands {
    
    /**
     * Differentiate an expression with respect to a variable.
     * @param expression the expression to differentiate
     * @param variable the variable to differentiate by, a case-sensitive nonempty string of letters.
     * @return expression's derivative with respect to variable.  Must be a valid expression equal
     *         to the derivative, but doesn't need to be in simplest or canonical form.
     * @throws IllegalArgumentException if the expression or variable is invalid
     */
    public static String differentiate(String expression, String variable) {
        return Expression.parse(expression).differentiate(variable).toString();
    }

	/**public static String simplify(String string, Map<String, Double> of) {
		// TODO Auto-generated method stub
		return null;
	}**/
	
    public static String simplify(String expression, Map<String, Double> variableMap) {
        // Validate the input expression
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }

        // Split the expression into tokens
        String[] tokens = expression.split("\\*");
        if (tokens.length == 0 || expression.startsWith("*") || expression.endsWith("*")) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }

        StringBuilder simplifiedExpression = new StringBuilder();
        double constantMultiplier = 1.0;
        boolean hasVariables = false;

        for (String token : tokens) {
            token = token.trim();
            if (token.isEmpty()) {
                throw new IllegalArgumentException("Invalid expression: " + expression);
            }
            if (variableMap.containsKey(token)) {
                // Replace variable with its value
                constantMultiplier *= variableMap.get(token);
            } else if (token.matches("[a-zA-Z]+")) {
                // Keep the variable as is
                if (simplifiedExpression.length() > 0) {
                    simplifiedExpression.append("*");
                }
                simplifiedExpression.append(token);
                hasVariables = true;
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }

        // Combine the constant multiplier with remaining variables
        if (!hasVariables) {
            return String.valueOf(constantMultiplier);
        } else {
            if (constantMultiplier != 1.0) {
                simplifiedExpression.insert(0, constantMultiplier + "*");
            }
            return simplifiedExpression.toString();
        }
    }
    
    /**
     * Simplify an expression.
     * @param expression the expression to simplify
     * @param environment maps variables to values.  Variables are required to be case-sensitive nonempty 
     *         strings of letters.  The set of variables in environment is allowed to be different than the 
     *         set of variables actually found in expression.  Values must be nonnegative numbers.
     * @return an expression equal to the input, but after substituting every variable v that appears in both
     *         the expression and the environment with its value, environment.get(v).  If there are no
     *         variables left in this expression after substitution, it must be evaluated to a single number.
     *         Additional simplifications to the expression may be done at the implementor's discretion.
     * @throws IllegalArgumentException if the expression is invalid
     */ 
}
