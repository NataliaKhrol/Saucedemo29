import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTest {
    @AfterMethod
    public void bla() {
        System.out.println("x");
    }

    @Test
    public void test1() {
        System.out.println("c");
    }

    @Test
    public void test2() {
        System.out.println("v");
    }

    @BeforeMethod
    public void bla2() {
        System.out.println("z");
    }
}
