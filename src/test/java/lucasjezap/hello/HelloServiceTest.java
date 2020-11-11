package lucasjezap.hello;

import lucasjezap.lang.Lang;
import lucasjezap.lang.LangRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Hola";

    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallbackName() {
        // given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, -1);

        // then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        // given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        var name = "test";

        // when
        var result = SUT.prepareGreeting(name, -1);

        // then
        assertEquals("Hello " + name + "!", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackLangId() {
        // given
        var fallbackIdWelcome = "Hola";
        LangRepository mockRepository = new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null,fallbackIdWelcome  , null));
                }
                return Optional.empty();
            }
        };
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, null);

        // then
        assertEquals(fallbackIdWelcome + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }
}
