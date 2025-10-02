package fr.diginamic.testunitaire.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorTest {
    private Calculator calculator;

    // ====================== Before / After ======================

    @BeforeAll
    static void beforeAll() {
        System.out.println("Debut de la campagne de tests Calculator");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Fin de la campagne de tests Calculator");
    }

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        System.out.println("Nouveau calculator initialisé");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Fin du test sur le calcul");
    }

    // ====================== Test NotNull ======================

    @Test
    void testCalculatorInstancie() {
        assertNotNull(calculator, "Calculator doit etre instancié");
    }

    // ====================== Test Add ======================

    @Test
    void testAdd2And5() {
        assertEquals(7, calculator.add(2, 5));
    }

    @Test
    void testAddMinus2And5() {
        assertEquals(3, calculator.add(-2, 5));
    }

    @Test
    void testNotEqualsAdd() {
        assertNotEquals(10, calculator.add(2, 5));
    }

    // ====================== Test Div ======================

    @Test
    void testDivide10by2() {
        assertEquals(5, calculator.div(10, 2));
    }

    @Test
    void testDivideBy0() {
        assertThrows(ArithmeticException.class, () -> calculator.div(10, 0));
    }

    @Test
    void testFalseDiv() {
        assertFalse(calculator.div(10, -3) > 0);
    }

    // ====================== Test Sub ======================

    @Test
    void testSub2And5() {
        assertEquals(-3, calculator.sub(2, 5));
    }

    @Test
    void testSubMinus2And5() {
        assertEquals(-7, calculator.sub(-2, 5));
    }

    @Test
    void testSub10And3() {
        assertEquals(7, calculator.sub(10, 3));
    }

    // ====================== Test Mul ======================

    @Test
    void testMul2And5() {
        assertEquals(10, calculator.mul(2, 5));
    }

    @Test
    void testMulMinus2And5() {
        assertEquals(-10, calculator.mul(-2, 5));
    }

    @Test
    void testMulMinus2AndMinus5() {
        assertEquals(10, calculator.mul(-2, -5));
    }

    @Test
    void testMul4And0() {
        assertEquals(0, calculator.mul(4, 0));
    }

    @Test
    void testNegMul() {
        assertTrue(calculator.mul(-2, 3) < 0);
    }

    // ====================== Test Pow ======================

    @Test
    void testPow2And5() {
        assertEquals(32, calculator.pow(2, 5));
    }

    @Test
    void testPowMinus5And2() {
        assertEquals(25, calculator.pow(-5, 2));
    }

    @Test
    void testPow5And0() {
        assertEquals(1, calculator.pow(5, 0));
    }

    // ====================== Force fail ======================

    @Test
    @Disabled
    void testFail() {
        fail("Echec du test forcé");
    }
}
