package com.fdmgroup.tdd.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

	Calculator calculator;

	@BeforeEach
	void setup() {
		calculator = new Calculator();
	}

	@Test
	void test_OpPrecedence_with_negative_number_at_front() {
		assertEquals(0, calculator.OpPrecedence("-1+2"));
	}

	@Test
	void test_OpPrecedence_with_two_addition() {
		assertEquals(1, calculator.OpPrecedence("1+2+3"));
	}

	@Test
	void test_OpPrecedence_with_addition_at_front_and_multiplication_at_the_back() {
		assertEquals(3.0, calculator.OpPrecedence("1+2*3"));
	}

	@Test
	void test_single_number() {
		assertEquals(1.0, calculator.evaluate("1"));
	}

	@Test
	void test_single_negative_number() {
		assertEquals(-1.0, calculator.evaluate("-1"));
	}

	@Test
	void test_single_number_with_bracket() {
		assertEquals(1.0, calculator.evaluate("(1)"));
	}

	@Test
	void test_single_negative_number_with_bracket() {
		assertEquals(-1.0, calculator.evaluate("(-1)"));
	}

	@Test
	void test_single_negative_number_with_negativr_bracket() {
		assertEquals(1.0, calculator.evaluate("-(-1)"));
	}

	@Test
	void test_1_add_2_equal_to_3() {
		assertEquals(3.0, calculator.evaluate("1+2"));
	}

	@Test
	void test_1_add_2_equal_to_3_with_bracket() {
		assertEquals(3.0, calculator.evaluate("(1+2)"));
	}

	@Test
	void test_negative_1_add_2_equal_to_1() {
		assertEquals(1.0, calculator.evaluate("-1+2"));
	}

	@Test
	void test_1_add_negative_2_equal_to_negative_1() {
		assertEquals(-1.0, calculator.evaluate("1+-2"));
	}

	@Test
	void test_1_minus_negative_2_equal_to_3() {
		assertEquals(3.0, calculator.evaluate("1--2"));
	}

	@Test
	void test_negative_1_add_2_equal_to_1_with_bracket() {
		assertEquals(1.0, calculator.evaluate("(-1)+2"));
	}

	@Test
	void test_1_add_2_equal_to_3_with_negative_bracket() {
		assertEquals(-3.0, calculator.evaluate("-(1+2)"));
	}

	@Test
	void test_1_add_negative_2_equal_to_3_with_negative_bracket() {
		assertEquals(1.0, calculator.evaluate("-(1+-2)"));
	}

	@Test
	void test_2_multiply_3_equal_to_6() {
		assertEquals(6.0, calculator.evaluate("2*3"));
	}

	@Test
	void test_negative_2_multiply_3_equal_to_negative_6() {
		assertEquals(-6.0, calculator.evaluate("-2*3"));
	}

	@Test
	void test_negative_2_multiply_negative_3_equal_to_6() {
		assertEquals(6.0, calculator.evaluate("-2*-3"));
	}

	@Test
	void test_negative_2_multiply_negative_3_equal_to_6_with_bracket() {
		assertEquals(6.0, calculator.evaluate("-2*(-3)"));
	}

	@Test
	void test_1_multiply_negative_2_add__3_equal_to_1() {
		assertEquals(1.0, calculator.evaluate("1*-2+3"));
	}

	@Test
	void test_1_add_negative_2_multiply_3_equal_to_negative_5() {
		assertEquals(-5.0, calculator.evaluate("1+-2*3"));
	}

	@Test
	void test_2_multiply_negative_bracket_2_add_3_equal_to_negative_10() {
		assertEquals(-10.0, calculator.evaluate("2*-(2+3)"));
	}

	@Test
	void test_1_add_minus_2_times_3_divide_by_4_equal_to_negative_1_over_2() {
		assertEquals(-0.5, calculator.evaluate("1+-2*3/4"));
	}

	@Test
	void test_a_bracket_chuck_with_big_bracket_equal_to_minus_2_point_2_5() {
		assertEquals(-2.25, calculator.evaluate("((1+2)*-3/4)"));
	}

	@Test
	void test_two_bracket_chucks_with_big_bracket_equal_to_3_point_2_5() {
		assertEquals(2.25, calculator.evaluate("(-(1+2)*-(3/4))"));
	}

	@Test
	void test_1_mius_two_bracket_chucks_with_big_bracket_equal_to_3_point_2_5() {
		assertEquals(3.25, calculator.evaluate("(1-(1+2)*-(3/4))"));
	}

	@Test
	void test_2_power_2_equal_to_4() {
		assertEquals(4, calculator.evaluate("2^2"));
	}

	@Test
	void test_2_power_negative_2_equal_to_1_over_4() {
		assertEquals(0.25, calculator.evaluate("2^-2"));
	}

	@Test
	void test_negative_2_power_negative_2_equal_to_negative_1_over_4() {
		assertEquals(-0.25, calculator.evaluate("-2^-2"));
	}

	@Test
	void test_negative_3_power_negative_3_equal_to_negative_1_over_27() {
		assertEquals(-0.037037037037037035, calculator.evaluate("-3^-3"));
	}

	@Test
	void test_1_power_0_equal_to_1() {
		assertEquals(1, calculator.evaluate("1^0"));
	}

	@Test
	void test_1_plus_2_power_2_equal_to_5() {
		assertEquals(5, calculator.evaluate("1+2^2"));
	}

	@Test
	void test_1_plus_2_power_2_with_bracket_chunk_equal_to_9() {
		assertEquals(9, calculator.evaluate("1+2^2+(1+3)"));
	}

	@Test
	void test_0_point_5_plus_1000_equal_to_1000_point_5() {
		assertEquals(1000.5, calculator.evaluate("0.5+1000"));
	}

	@Test
	void test_0_point_5_plus_1_point_55_equal_to_2_point_0_5() {
		assertEquals(2.05, calculator.evaluate("0.5+1.55"));
	}

	@Test
	void test_10000000_divide_by_0_point_55_equal_to_2_point_0_5() {
		assertEquals(1.818181818181818E7, calculator.evaluate("10000000/0.55"));
	}

}
