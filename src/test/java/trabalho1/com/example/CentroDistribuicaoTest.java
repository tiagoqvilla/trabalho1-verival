package trabalho1.com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Unit test for simple App.
 */
public class CentroDistribuicaoTest {
    CentroDistribuicao c = null;

    @BeforeEach
    void setup() {
        c = new CentroDistribuicao(0, 0, 0, 0);
        
    }
}
