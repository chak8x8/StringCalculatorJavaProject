package com.fdmgroup.tdd.Calculator;

import java.util.Scanner;

/*The logic of this project is that:
 * Firstly, find the first operator to go first according to the order of operations, and put an index. 
 * Secondly, find the number on the leftside of the index, and the length should be from the index to the first leftside operator. 
 * Take the leftside number as V1.
 * Thirdly, find the number on the rightside of the index, and the length should be from the index to the first rightside operator. 
 * Take the rightside as V2.
 * Calculate the V1 and V2 and get the result.
 * Do the math recursively and calculate the rest of the numbers and operators until there is operator left.
 * */

public class Calculator implements ICalculator {

	// Addition method
	public double add(double num1, double num2) {
		return num1 + num2;
	}

	// Substraction method
	public double substract(double num1, double num2) {
		return num1 - num2;
	}

	// Multiplication method
	public double multiply(double num1, double num2) {
		return num1 * num2;
	}

	// Division method
	public double divide(double num1, double num2) {
		if (num1 > 0 && num2 == 0) {
			return Double.POSITIVE_INFINITY;
		} else if (num1 < 1 && num2 == 0) {
			return Double.NEGATIVE_INFINITY;
		} else {
			return num1 / num2;
		}
	}

	// Power method
	public double exponent(double num1, double num2) {
		if (num2 == 0) {
			return 1;
		} else if (num2 > 0) {
			return num1 * exponent(num1, num2 - 1);
		} else if (num2 < 0) {
			if (num1 < 0) {
				return (1 / num1) * exponent(-num1, num2 + 1);
			} else {
				return (1 / num1) * exponent(num1, num2 + 1);
			}
		} else {
			return -1;
		}
	}

	// Find the operator precedence method
	public int OpPrecedence(String expression) {
		int indexOfPriority;
		int indexOfE = expression.indexOf("^");
		int indexOfM = expression.indexOf('*');
		int indexOfD = expression.indexOf('/');
		int indexOfA = expression.indexOf('+');
		int indexOfS = expression.indexOf('-');

		if (indexOfE > -1) {
			indexOfPriority = 4;
		} else if (indexOfM > -1 || indexOfD > -1) {
			indexOfPriority = 3;
		} else if (indexOfA > -1 || indexOfS > -1) {
			indexOfPriority = 2;
		} else {
			indexOfPriority = 1;
		}

		if (indexOfPriority == 4) {
			return indexOfE;
		} else if (indexOfPriority == 3) {
			if (indexOfM > -1 && indexOfD > -1) {
				return (indexOfM < indexOfD) ? indexOfM : indexOfD;
			} else if (indexOfM > -1) {
				return indexOfM;
			} else {
				return indexOfD;
			}
		} else if (indexOfPriority == 2) {
			if (indexOfA > -1 && indexOfS > -1) {
				return (indexOfA < indexOfS) ? indexOfA : indexOfS;
			} else if (indexOfA > -1) {
				return indexOfA;
			} else {
				return indexOfS;
			}
		} else {
			return -1;
		}
	}

	// Find the first operator from the right
	public int firstOp(String expression) {
		int indexOfE = expression.indexOf('^');
		if (indexOfE == -1) {
			indexOfE = Integer.MAX_VALUE;
		}
		int indexOfM = expression.indexOf('*');
		if (indexOfM == -1) {
			indexOfM = Integer.MAX_VALUE;
		}
		int indexOfD = expression.indexOf('/');
		if (indexOfD == -1) {
			indexOfD = Integer.MAX_VALUE;
		}
		int indexOfA = expression.indexOf('+');
		if (indexOfA == -1) {
			indexOfA = Integer.MAX_VALUE;
		} else if (indexOfA == 0) {
			int tempFirstOp = firstOp(expression.substring(1, expression.length()));
			indexOfA = (tempFirstOp == Integer.MAX_VALUE) ? Integer.MAX_VALUE : 1 + 1 + tempFirstOp;
		}

		int indexOfS = expression.indexOf('-');
		if (indexOfS == -1) {
			indexOfS = Integer.MAX_VALUE;
		} else if (indexOfS == 0) {
			int tempFirstOp = firstOp(expression.substring(1, expression.length()));
			indexOfS = (tempFirstOp == Integer.MAX_VALUE) ? Integer.MAX_VALUE : 1 + 1 + tempFirstOp;
		}

		int returnIndex = indexOfE;

		if (indexOfM < returnIndex) {
			returnIndex = indexOfM;
		}
		if (indexOfD < returnIndex) {
			returnIndex = indexOfD;
		}
		if (indexOfA < returnIndex) {
			returnIndex = indexOfA;
		}
		if (indexOfS < returnIndex) {
			returnIndex = indexOfS;
		}

		return returnIndex;
	}

	// Find the first operator from the left
	public int lastOp(String expression) {
		int indexOfE = expression.indexOf('^');
		if (indexOfE == -1) {
			indexOfE = Integer.MIN_VALUE;
		}
		int indexOfM = expression.lastIndexOf('*');
		if (indexOfM == -1) {
			indexOfM = Integer.MIN_VALUE;
		}
		int indexOfD = expression.lastIndexOf('/');
		if (indexOfD == -1) {
			indexOfD = Integer.MIN_VALUE;
		}
		int indexOfA = expression.lastIndexOf('+');
		if (indexOfA == -1) {
			indexOfA = Integer.MIN_VALUE;
		}
		int indexOfS = expression.lastIndexOf('-');
		if (indexOfS == -1) {
			indexOfS = Integer.MIN_VALUE;
		}

		int returnIndex = indexOfE;

		if (indexOfM > returnIndex) {
			returnIndex = indexOfM;
		}
		if (indexOfD > returnIndex) {
			returnIndex = indexOfD;
		}
		if (indexOfA > returnIndex) {
			returnIndex = indexOfA;
		}
		if (indexOfS > returnIndex) {
			returnIndex = indexOfS;
		}

		return returnIndex;
	}

	// Find the length of V1
	public int[] getV1Indices(String expression, char[] ch, int opIndex) {
		int v1Indices[] = { 0, opIndex };
		String ChunkOnTheLeft = expression.substring(0, v1Indices[1]);
		int firstOpCountFromRightToLeft = lastOp(ChunkOnTheLeft);

		if (firstOpCountFromRightToLeft != Integer.MIN_VALUE) {
			if (ch[firstOpCountFromRightToLeft] == '+' || ch[firstOpCountFromRightToLeft] == '-') {
				if (firstOpCountFromRightToLeft == 0) {
					v1Indices[0] = 0;
				} else if (ch[firstOpCountFromRightToLeft - 1] == '+' || ch[firstOpCountFromRightToLeft - 1] == '-'
						|| ch[firstOpCountFromRightToLeft - 1] == '*' || ch[firstOpCountFromRightToLeft - 1] == '/'
						|| ch[firstOpCountFromRightToLeft - 1] == '^') {
					v1Indices[0] = firstOpCountFromRightToLeft;
				} else {
					v1Indices[0] = firstOpCountFromRightToLeft + 1;
				}
			} else {
				v1Indices[0] = firstOpCountFromRightToLeft + 1;
			}
		}

		return v1Indices;
	}

	// Find the length of V2
	public int[] getV2Indices(String expression, int opIndex) {
		int v2Indices[] = { (opIndex + 1), expression.length() };
		String ChunkOnTheRight = expression.substring(v2Indices[0], v2Indices[1]);

		int firstOpCountFromLeftToRight = firstOp(ChunkOnTheRight);
		if (firstOpCountFromLeftToRight != Integer.MAX_VALUE) {
			v2Indices[1] = v2Indices[0] + firstOpCountFromLeftToRight;
		}
		return v2Indices;
	}

	// Get the value based on the length
	public double getValueFromIndices(String expression, int indices[]) {
		String value = expression.substring(indices[0], indices[1]);
		return Double.parseDouble(value);
	}

	// Find the staring point and the ending point of the outmost bracket.
	public int[] getOutmostBracketIndices(String expression) {
		int bracketIndices[] = { expression.indexOf('('), expression.lastIndexOf(')') };
		return bracketIndices;
	}

	// To convert String into Double value
	public double evaluate(String expression) {

		expression = expression.replaceAll(" ", "");
		expression = expression.replaceAll("\t", "");

		char[] ch = expression.toCharArray();

		int[] outmostBracketIndices = getOutmostBracketIndices(expression);

		// To deal with the case that there is more than one brackets in the expression
		if (outmostBracketIndices[0] > -1 && outmostBracketIndices[1] > -1) {
			int[] bracketChunkIndices = { outmostBracketIndices[0], outmostBracketIndices[1] };

			int bracketChunkStartIndex = expression.indexOf("(");
			int bracketChunkEndIndex = expression.indexOf(")");

			if (bracketChunkStartIndex != expression.lastIndexOf("(", bracketChunkEndIndex)) {
				bracketChunkStartIndex = expression.lastIndexOf("(", bracketChunkEndIndex);
			}
			bracketChunkIndices[0] = bracketChunkStartIndex;
			bracketChunkIndices[1] = bracketChunkEndIndex;

			String bracketExpression = expression.substring(bracketChunkIndices[0] + 1, bracketChunkIndices[1]);
			double bracketResult = evaluate(bracketExpression);
			String bracketResultString = Double.toString(bracketResult);
			String chunkOnTheLeftOfTheBracket = expression.substring(0, bracketChunkIndices[0]);
			String chunkOnTheRightOfTheBracket = expression.substring(bracketChunkIndices[1] + 1, expression.length());

			if (((chunkOnTheLeftOfTheBracket.indexOf("+") == 0 && chunkOnTheLeftOfTheBracket.length() == 1)
					|| (chunkOnTheLeftOfTheBracket.indexOf("-") == 0 && chunkOnTheLeftOfTheBracket.length() == 1))
					&& (bracketResultString.indexOf("+") == 0 || bracketResultString.indexOf("-") == 0)) {
				if (chunkOnTheLeftOfTheBracket.indexOf("+") == 0) {
					if (bracketResultString.indexOf("+") == 0) {
						chunkOnTheLeftOfTheBracket = "";
					} else {
						chunkOnTheLeftOfTheBracket = "-";
						bracketResultString = bracketResultString.substring(1, bracketResultString.length());
					}
				} else {
					if (bracketResultString.indexOf("+") == 0) {
						bracketResultString = bracketResultString.substring(1, bracketResultString.length());
					} else {
						chunkOnTheLeftOfTheBracket = "";
						bracketResultString = bracketResultString.substring(1, bracketResultString.length());
					}
				}
			}

			return evaluate(chunkOnTheLeftOfTheBracket + bracketResultString + chunkOnTheRightOfTheBracket);
		}

		int opIndex = OpPrecedence(expression);

		if (opIndex == -1) {
			return Double.parseDouble(expression);
		} else if (opIndex == 0) {
			int tempIndex = OpPrecedence(expression.substring(1, expression.length()));
			if (tempIndex == -1) {
				return Double.parseDouble(expression);
			} else {
				opIndex = tempIndex + 1;
			}
		}

		int[] v1Indices = getV1Indices(expression, ch, opIndex);
		int[] v2Indices = getV2Indices(expression, opIndex);

		double v1 = getValueFromIndices(expression, v1Indices);
		double v2 = getValueFromIndices(expression, v2Indices);
		double result;

		System.out.println("v1: " + v1);
		System.out.println("v2: " + v2);

		if (ch[opIndex] == '^') {
			result = exponent(v1, v2);
		} else if (ch[opIndex] == '*') {
			result = multiply(v1, v2);
		} else if (ch[opIndex] == '/') {
			result = divide(v1, v2);
		} else if (ch[opIndex] == '+') {
			result = add(v1, v2);
		} else {
			result = substract(v1, v2);
		}
		System.out.println("result: " + result);
		String resultString = Double.toString(result);
		String ChunkOnTheLeftOfResultString = expression.substring(0, v1Indices[0]);
		String ChunkOnTheRightOfResultString = expression.substring(v2Indices[1], expression.length());

		return evaluate(ChunkOnTheLeftOfResultString + resultString + ChunkOnTheRightOfResultString);
	}

	// To let user to input an expression and calculate the number
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a math expression");
		String expression = input.nextLine();
		Calculator calculator = new Calculator();
		System.out.println("The answer is: " + calculator.evaluate(expression));

	}
}
