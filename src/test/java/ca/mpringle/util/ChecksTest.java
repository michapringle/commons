package ca.mpringle.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

final class ChecksTest {

    private static final Standard STANDARD_A = new Standard("a");
    private static final Standard STANDARD_B = new Standard("b");
    private static final Standard STANDARD_C = new Standard("c");
    private static final Standard STANDARD_D = new Standard("d");
    private static final Standard STANDARD_NULL = null;

    private static final Optional<String> OPTIONAL_A = Optional.of("a");
    private static final Optional<String> OPTIONAL_B = Optional.of("b");
    private static final Optional<String> OPTIONAL_C = Optional.of("c");
    private static final Optional<String> OPTIONAL_D = Optional.of("d");
    public static final Optional<Object> OPTIONAL_EMPTY = Optional.empty();
    private static final Optional<String> OPTIONAL_NULL = null;

    private static final Collection<String> COLLECTION_A = List.of("a");
    private static final Collection<String> COLLECTION_B = List.of("b");
    public static final List<Object> COLLECTION_EMPTY = List.of();
    private static final Collection<String> COLLECTION_NULL = null;

    private static final Integer NUMBER_1 = 1;
    private static final Integer NUMBER_2 = 2;
    private static final Integer NUMBER_3 = 3;
    private static final Integer NUMBER_4 = 4;
    private static final Integer NUMBER_NULL = null;

    @Test
    void standardStaticFactoryMethodsShouldValidateInvariant() {

        assertThatThrownBy(() -> Checks.notNullAnd(STANDARD_NULL))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("reference must not be null.");
        assertDoesNotThrow(() -> Checks.notNullAnd(STANDARD_A));
        assertDoesNotThrow(() -> Checks.nullableAnd(STANDARD_A));
        assertDoesNotThrow(() -> Checks.nullableAnd(STANDARD_NULL));
    }

    @Test
    void optionalStaticFactoryMethodsShouldValidateInvariant() {

        assertThatThrownBy(() -> Checks.notNullAnd(OPTIONAL_NULL))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("reference must not be null.");
        assertDoesNotThrow(() -> Checks.notNullAnd(OPTIONAL_A));
        assertDoesNotThrow(() -> Checks.nullableAnd(OPTIONAL_A));
        assertDoesNotThrow(() -> Checks.nullableAnd(OPTIONAL_NULL));
    }

    @Test
    void collectionsStaticFactoryMethodsShouldValidateInvariant() {

        assertThatThrownBy(() -> Checks.notNullAnd(COLLECTION_NULL))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("reference must not be null.");
        assertDoesNotThrow(() -> Checks.notNullAnd(COLLECTION_A));
        assertDoesNotThrow(() -> Checks.nullableAnd(COLLECTION_A));
        assertDoesNotThrow(() -> Checks.nullableAnd(COLLECTION_NULL));
    }

    @Test
    void comparableStaticFactoryMethodsShouldValidateInvariant() {

        assertThatThrownBy(() -> Checks.notNullAnd(NUMBER_NULL))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("reference must not be null.");
        assertDoesNotThrow(() -> Checks.notNullAnd(NUMBER_1));
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_1));
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_NULL));
    }

    @Test
    void mapShouldMapToTypeWhenCalled() {

        // standard
        assertEquals("a", Checks.notNullAnd(STANDARD_A).map(Standard::toString));
        assertNull(Checks.nullableAnd(STANDARD_NULL).map(Standard::toString));

        // optional
        assertEquals("a", Checks.notNullAnd(OPTIONAL_A).map(Optional::get));

        // collections
        assertEquals("[a]", Checks.notNullAnd(COLLECTION_A).map(Collection::toString));
        assertNull(Checks.nullableAnd(COLLECTION_NULL).map(Collection::toString));

        // number
        assertEquals("1", Checks.notNullAnd(NUMBER_1).map(Object::toString));
        assertNull(Checks.nullableAnd(NUMBER_NULL).map(Object::toString));
    }

    @Test
    void standardIsValidShouldThrowExceptionWhenPredicateFails() {

        assertThatThrownBy(() -> Checks.notNullAnd(STANDARD_A).isValid(v -> v.equals(STANDARD_B)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Predicate check failed for 'a'");

        assertThatThrownBy(() -> Checks.notNullAnd(STANDARD_A).isValidAnd(v -> v.equals(STANDARD_B)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Predicate check failed for 'a'");

        assertThatThrownBy(() -> Checks.notNullAnd(STANDARD_A).isValid(v -> v.equals(STANDARD_B), "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");

        assertThatThrownBy(() -> Checks.notNullAnd(STANDARD_A).isValidAnd(v -> v.equals(STANDARD_B), "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");
    }

    @Test
    void optionalIsValidShouldThrowExceptionWhenPredicateFails() {

        assertThatThrownBy(() -> Checks.notNullAnd(OPTIONAL_A).isValid(v -> v.equals(OPTIONAL_B)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Predicate check failed for 'Optional[a]'");

        assertThatThrownBy(() -> Checks.notNullAnd(OPTIONAL_A).isValidAnd(v -> v.equals(OPTIONAL_B)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Predicate check failed for 'Optional[a]'");

        assertThatThrownBy(() -> Checks.notNullAnd(OPTIONAL_A).isValid(v -> v.equals(OPTIONAL_B), "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");

        assertThatThrownBy(() -> Checks.notNullAnd(OPTIONAL_A).isValidAnd(v -> v.equals(OPTIONAL_B), "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");

    }

    @Test
    void collectionsIsValidShouldThrowExceptionWhenPredicateFails() {

        assertThatThrownBy(() -> Checks.notNullAnd(COLLECTION_A).isValid(v -> v.equals(COLLECTION_B)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Predicate check failed for '[a]'");

        assertThatThrownBy(() -> Checks.notNullAnd(COLLECTION_A).isValidAnd(v -> v.equals(COLLECTION_B)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Predicate check failed for '[a]'");

        assertThatThrownBy(() -> Checks.notNullAnd(COLLECTION_A).isValid(v -> v.equals(COLLECTION_B), "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");

        assertThatThrownBy(() -> Checks.notNullAnd(COLLECTION_A).isValidAnd(v -> v.equals(COLLECTION_B), "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");
    }

    @Test
    void numberIsValidShouldThrowExceptionWhenPredicateFails() {

        assertThatThrownBy(() -> Checks.notNullAnd(NUMBER_1).isValid(v -> v.equals(NUMBER_2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Predicate check failed for '1'");

        assertThatThrownBy(() -> Checks.notNullAnd(NUMBER_1).isValidAnd(v -> v.equals(NUMBER_2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Predicate check failed for '1'");

        assertThatThrownBy(() -> Checks.notNullAnd(NUMBER_1).isValid(v -> v.equals(NUMBER_2), "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");

        assertThatThrownBy(() -> Checks.notNullAnd(NUMBER_1).isValidAnd(v -> v.equals(NUMBER_2), "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");
    }

    @Test
    void standardIsValidShouldNotThrowExceptionWhenPredicateIsValid() {

        assertDoesNotThrow(() -> Checks.notNullAnd(STANDARD_A).isValid(v -> v.equals(STANDARD_A)));
        assertDoesNotThrow(() -> Checks.notNullAnd(STANDARD_A).isValid(v -> v.equals(STANDARD_A), "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.notNullAnd(STANDARD_A).isValidAnd(v -> v.equals(STANDARD_A)));
        assertDoesNotThrow(() -> Checks.notNullAnd(STANDARD_A).isValidAnd(v -> v.equals(STANDARD_A), "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(STANDARD_NULL).isValid(v -> v.equals(STANDARD_A)));
        assertDoesNotThrow(() -> Checks.nullableAnd(STANDARD_NULL).isValid(v -> v.equals(STANDARD_A), "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(STANDARD_NULL).isValidAnd(v -> v.equals(STANDARD_A)));
        assertDoesNotThrow(() -> Checks.nullableAnd(STANDARD_NULL).isValidAnd(v -> v.equals(STANDARD_A), "some message %s", "bob"));
    }

    @Test
    void optionalIsValidShouldNotThrowExceptionWhenPredicateIsValid() {

        assertDoesNotThrow(() -> Checks.notNullAnd(OPTIONAL_A).isValid(v -> v.equals(OPTIONAL_A)));
        assertDoesNotThrow(() -> Checks.notNullAnd(OPTIONAL_A).isValid(v -> v.equals(OPTIONAL_A), "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.notNullAnd(OPTIONAL_A).isValidAnd(v -> v.equals(OPTIONAL_A)));
        assertDoesNotThrow(() -> Checks.notNullAnd(OPTIONAL_A).isValidAnd(v -> v.equals(OPTIONAL_A), "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(OPTIONAL_NULL).isValid(v -> v.equals(OPTIONAL_A)));
        assertDoesNotThrow(() -> Checks.nullableAnd(OPTIONAL_NULL).isValid(v -> v.equals(OPTIONAL_A), "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(OPTIONAL_NULL).isValidAnd(v -> v.equals(OPTIONAL_A)));
        assertDoesNotThrow(() -> Checks.nullableAnd(OPTIONAL_NULL).isValidAnd(v -> v.equals(OPTIONAL_A), "some message %s", "bob"));
    }

    @Test
    void collectionsIsValidShouldNotThrowExceptionWhenPredicateIsValid() {

        assertDoesNotThrow(() -> Checks.notNullAnd(COLLECTION_A).isValid(v -> v.equals(COLLECTION_A)));
        assertDoesNotThrow(() -> Checks.notNullAnd(COLLECTION_A).isValid(v -> v.equals(COLLECTION_A), "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.notNullAnd(COLLECTION_A).isValidAnd(v -> v.equals(COLLECTION_A)));
        assertDoesNotThrow(() -> Checks.notNullAnd(COLLECTION_A).isValidAnd(v -> v.equals(COLLECTION_A), "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(COLLECTION_A).isValid(v -> v.equals(COLLECTION_A)));
        assertDoesNotThrow(() -> Checks.nullableAnd(COLLECTION_NULL).isValid(v -> v.equals(COLLECTION_A)));
        assertDoesNotThrow(() -> Checks.nullableAnd(COLLECTION_NULL).isValid(v -> v.equals(COLLECTION_A), "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(COLLECTION_NULL).isValidAnd(v -> v.equals(COLLECTION_A)));
        assertDoesNotThrow(() -> Checks.nullableAnd(COLLECTION_NULL).isValidAnd(v -> v.equals(COLLECTION_A), "some message %s", "bob"));
    }

    @Test
    void numberIsValidShouldNotThrowExceptionWhenPredicateIsValid() {

        assertDoesNotThrow(() -> Checks.notNullAnd(NUMBER_1).isValid(v -> v.equals(NUMBER_1)));
        assertDoesNotThrow(() -> Checks.notNullAnd(NUMBER_1).isValid(v -> v.equals(NUMBER_1), "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.notNullAnd(NUMBER_1).isValidAnd(v -> v.equals(NUMBER_1)));
        assertDoesNotThrow(() -> Checks.notNullAnd(NUMBER_1).isValidAnd(v -> v.equals(NUMBER_1), "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_1).isValid(v -> v.equals(NUMBER_1)));
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_NULL).isValid(v -> v.equals(NUMBER_1)));
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_NULL).isValid(v -> v.equals(NUMBER_1), "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_NULL).isValidAnd(v -> v.equals(NUMBER_1)));
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_NULL).isValidAnd(v -> v.equals(NUMBER_1), "some message %s", "bob"));
    }

    @Test
    void standardIsAnyOfShouldThrowExceptionWhenThereIsNoMatch() {

        final List<Standard> l = List.of(STANDARD_A, STANDARD_B, STANDARD_C);
        assertThatThrownBy(() -> Checks.notNullAnd(STANDARD_D).isAnyOf(l))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid value: d, accepted values: [a, b, c]");

        assertThatThrownBy(() -> Checks.notNullAnd(STANDARD_D).isAnyOf(l, "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");

        assertThatThrownBy(() -> Checks.notNullAnd(STANDARD_D).isAnyOfAnd(l))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid value: d, accepted values: [a, b, c]");

        assertThatThrownBy(() -> Checks.notNullAnd(STANDARD_D).isAnyOfAnd(l, "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");
    }

    @Test
    void optionalIsAnyOfShouldThrowExceptionWhenThereIsNoMatch() {

        final List<Optional<String>> l = List.of(OPTIONAL_A, OPTIONAL_B, OPTIONAL_C);
        assertThatThrownBy(() -> Checks.notNullAnd(OPTIONAL_D).isAnyOf(l))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid value: Optional[d], accepted values: [Optional[a], Optional[b], Optional[c]]");

        assertThatThrownBy(() -> Checks.notNullAnd(OPTIONAL_D).isAnyOf(l, "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");

        assertThatThrownBy(() -> Checks.notNullAnd(OPTIONAL_D).isAnyOfAnd(l))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid value: Optional[d], accepted values: [Optional[a], Optional[b], Optional[c]]");

        assertThatThrownBy(() -> Checks.notNullAnd(OPTIONAL_D).isAnyOfAnd(l, "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");
    }

    @Test
    void collectionIsAnyOfShouldThrowExceptionWhenThereIsNoMatch() {

        final List<Integer> l = List.of(NUMBER_1, NUMBER_2, NUMBER_3);
        assertThatThrownBy(() -> Checks.notNullAnd(NUMBER_4).isAnyOf(l))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid value: 4, accepted values: [1, 2, 3]");

        assertThatThrownBy(() -> Checks.notNullAnd(NUMBER_4).isAnyOf(l, "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");

        assertThatThrownBy(() -> Checks.notNullAnd(NUMBER_4).isAnyOfAnd(l))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid value: 4, accepted values: [1, 2, 3]");

        assertThatThrownBy(() -> Checks.notNullAnd(NUMBER_4).isAnyOfAnd(l, "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");
    }

    @Test
    void standardIsAnyOfShouldNotThrowExceptionWhenThereIsMatch() {

        final List<Standard> l = List.of(STANDARD_A, STANDARD_B, STANDARD_C);
        assertDoesNotThrow(() -> Checks.nullableAnd(STANDARD_A).isAnyOf(l));
        assertDoesNotThrow(() -> Checks.nullableAnd(STANDARD_A).isAnyOf(l, "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(STANDARD_A)).isAnyOfAnd(l);
        assertDoesNotThrow(() -> Checks.nullableAnd(STANDARD_A).isAnyOfAnd(l, "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(STANDARD_NULL).isAnyOf(l));
        assertDoesNotThrow(() -> Checks.nullableAnd(STANDARD_NULL).isAnyOf(l, "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(STANDARD_NULL).isAnyOfAnd(l));
        assertDoesNotThrow(() -> Checks.nullableAnd(STANDARD_NULL).isAnyOfAnd(l, "some message %s", "bob"));
    }

    @Test
    void optionalIsAnyOfShouldNotThrowExceptionWhenThereIsMatch() {

        final List<Optional<String>> l = List.of(OPTIONAL_A, OPTIONAL_B, OPTIONAL_C);
        assertDoesNotThrow(() -> Checks.notNullAnd(OPTIONAL_A).isAnyOf(l));
        assertDoesNotThrow(() -> Checks.notNullAnd(OPTIONAL_A).isAnyOf(l, "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.notNullAnd(OPTIONAL_A).isAnyOfAnd(l));
        assertDoesNotThrow(() -> Checks.notNullAnd(OPTIONAL_A).isAnyOfAnd(l, "some message %s", "bob"));
    }

    @Test
    void collectionIsAnyOfShouldNotThrowExceptionWhenThereIsMatch() {

        final List<Integer> l = List.of(NUMBER_1, NUMBER_2, NUMBER_3);
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_1).isAnyOf(l));
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_1).isAnyOf(l, "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_1)).isAnyOfAnd(l);
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_1).isAnyOfAnd(l, "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_NULL).isAnyOf(l));
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_NULL).isAnyOf(l, "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_NULL).isAnyOfAnd(l));
        assertDoesNotThrow(() -> Checks.nullableAnd(NUMBER_NULL).isAnyOfAnd(l, "some message %s", "bob"));
    }

    @Test
    void isPresentShouldThrowExceptionWhenEmpty() {

        assertThatThrownBy(() -> Checks.notNullAnd(OPTIONAL_EMPTY).isPresent())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("reference Optional must not be empty");

        assertThatThrownBy(() -> Checks.notNullAnd(OPTIONAL_EMPTY).isPresent("some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");

        assertThatThrownBy(() -> Checks.notNullAnd(OPTIONAL_EMPTY).isPresentAnd())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("reference Optional must not be empty");

        assertThatThrownBy(() -> Checks.notNullAnd(OPTIONAL_EMPTY).isPresentAnd("some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");
    }

    @Test
    void isPresentShouldNotThrowExceptionWhenPresent() {

        assertDoesNotThrow(() -> Checks.notNullAnd(OPTIONAL_A).isPresent());
        assertDoesNotThrow(() -> Checks.notNullAnd(OPTIONAL_A).isPresent("some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.notNullAnd(OPTIONAL_A).isPresentAnd());
        assertDoesNotThrow(() -> Checks.notNullAnd(OPTIONAL_A).isPresentAnd("some message %s", "bob"));
    }

    @Test
    void isNotEmptyShouldThrowExceptionWhenEmpty() {

        assertThatThrownBy(() -> Checks.notNullAnd(COLLECTION_EMPTY).isNotEmpty())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("reference collection must not be empty");

        assertThatThrownBy(() -> Checks.notNullAnd(COLLECTION_EMPTY).isNotEmpty("some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");

        assertThatThrownBy(() -> Checks.notNullAnd(COLLECTION_EMPTY).isNotEmptyAnd())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("reference collection must not be empty");

        assertThatThrownBy(() -> Checks.notNullAnd(COLLECTION_EMPTY).isNotEmptyAnd("some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");
    }

    @Test
    void isNotEmptyShouldNotThrowExceptionWhenNotEmpty() {

        assertDoesNotThrow(() -> Checks.notNullAnd(COLLECTION_A).isNotEmpty());
        assertDoesNotThrow(() -> Checks.notNullAnd(COLLECTION_A).isNotEmpty("some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.notNullAnd(COLLECTION_A).isNotEmptyAnd());
        assertDoesNotThrow(() -> Checks.notNullAnd(COLLECTION_A).isNotEmptyAnd("some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(COLLECTION_NULL).isNotEmpty());
        assertDoesNotThrow(() -> Checks.nullableAnd(COLLECTION_NULL).isNotEmpty("some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd(COLLECTION_NULL).isNotEmptyAnd());
        assertDoesNotThrow(() -> Checks.nullableAnd(COLLECTION_NULL).isNotEmptyAnd("some message %s", "bob"));
    }

    @ParameterizedTest
    @MethodSource("doesNotEqualData")
    void doesEqualShouldThrowExceptionWhenConditionIsFalse(final Number input,
                                                           final Number toCheck,
                                                           final String errorMessage) {

        assertThatThrownBy(() -> Checks.notNullAnd(input).doesEqual(toCheck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

        assertThatThrownBy(() -> Checks.notNullAnd(input).doesEqual(toCheck, "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");

        assertThatThrownBy(() -> Checks.notNullAnd(input).doesEqualAnd(toCheck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

        assertThatThrownBy(() -> Checks.notNullAnd(input).doesEqualAnd(toCheck, "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");
    }

    @ParameterizedTest
    @MethodSource("doesEqualData")
    void doesEqualShouldNotThrowExceptionWhenConditionIsTrue(final Number input,
                                                             final Number toCheck) {

        assertDoesNotThrow(() -> Checks.notNullAnd(input).doesEqual(toCheck));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).doesEqual(toCheck, "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).doesEqualAnd(toCheck));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).doesEqualAnd(toCheck, "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).doesEqual(toCheck));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).doesEqual(toCheck, "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).doesEqualAnd(toCheck));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).doesEqualAnd(toCheck, "some message %s", "bob"));
    }

    @ParameterizedTest
    @MethodSource("doesEqualData")
    void doesNotEqualShouldThrowExceptionWhenConditionIsFalse(final Number input,
                                                              final Number toCheck,
                                                              final String errorMessage) {

        assertThatThrownBy(() -> Checks.notNullAnd(input).doesNotEqual(toCheck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

        assertThatThrownBy(() -> Checks.notNullAnd(input).doesNotEqual(toCheck, "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");

        assertThatThrownBy(() -> Checks.notNullAnd(input).doesNotEqualAnd(toCheck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

        assertThatThrownBy(() -> Checks.notNullAnd(input).doesNotEqualAnd(toCheck, "some message %s", "bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some message bob");
    }

    @ParameterizedTest
    @MethodSource("doesNotEqualData")
    void doesNotEqualShouldNotThrowExceptionWhenConditionIsTrue(final Number input,
                                                                final Number toCheck) {

        assertDoesNotThrow(() -> Checks.notNullAnd(input).doesNotEqual(toCheck));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).doesNotEqual(toCheck, "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).doesNotEqualAnd(toCheck));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).doesNotEqualAnd(toCheck, "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).doesNotEqual(toCheck));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).doesNotEqual(toCheck, "some message %s", "bob"));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).doesNotEqualAnd(toCheck));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).doesNotEqualAnd(toCheck, "some message %s", "bob"));
    }

    static Stream<Arguments> doesNotEqualData() {

        return Stream.of(
                Arguments.of(5, 0, "The reference 5 must == 0"),
                Arguments.of(5L, 0L, "The reference 5 must == 0"),
                Arguments.of(5F, 0F, "The reference 5.0 must == 0.0"),
                Arguments.of(5D, 0D, "The reference 5.0 must == 0.0")
        );
    }

    static Stream<Arguments> doesEqualData() {

        return Stream.of(
                Arguments.of(5, 5, "The reference 5 must != 5"),
                Arguments.of(5L, 5L, "The reference 5 must != 5"),
                Arguments.of(5F, 5F, "The reference 5.0 must != 5.0"),
                Arguments.of(5D, 5D, "The reference 5.0 must != 5.0")
        );
    }

    @ParameterizedTest
    @MethodSource("isLessThanData")
    void isLessThanShouldThrowExceptionWhenConditionIsFalse(final Number input,
                                                            final Number toCheck,
                                                            final String errorMessage) {

        assertThatThrownBy(() -> Checks.notNullAnd(input).isLessThan(toCheck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

        assertThatThrownBy(() -> Checks.notNullAnd(input).isLessThanAnd(toCheck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("isLessThanOrEqualToData")
    void isLessThanShouldNotThrowExceptionWhenConditionIsTrue(final Number toCheck,
                                                              final Number input) {

        assertDoesNotThrow(() -> Checks.notNullAnd(input).isLessThan(toCheck));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).isLessThanAnd(toCheck));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isLessThan(toCheck));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isLessThanAnd(toCheck));
    }

    static Stream<Arguments> isLessThanData() {

        return Stream.of(
                Arguments.of(5, 0, "The reference is 5, but must be < 0"),
                Arguments.of(5, 5, "The reference is 5, but must be < 5"),
                Arguments.of(5L, 0L, "The reference is 5, but must be < 0"),
                Arguments.of(5L, 5L, "The reference is 5, but must be < 5"),
                Arguments.of(5F, 0F, "The reference is 5.0, but must be < 0.0"),
                Arguments.of(5F, 5F, "The reference is 5.0, but must be < 5.0"),
                Arguments.of(5D, 0D, "The reference is 5.0, but must be < 0.0"),
                Arguments.of(5D, 5D, "The reference is 5.0, but must be < 5.0")
        );
    }

    @ParameterizedTest
    @MethodSource("isLessThanWithMessageData")
    void isLessThanWithMessageShouldThrowExceptionWhenConditionIsFalse(final Number input,
                                                                       final Number toCheck,
                                                                       final String message1,
                                                                       final String message2,
                                                                       final String errorMessage) {

        assertThatThrownBy(() -> Checks.notNullAnd(input).isLessThan(toCheck, message1, message2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

        assertThatThrownBy(() -> Checks.notNullAnd(input).isLessThanAnd(toCheck, message1, message2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("isLessThanOrEqualToWithMessageData")
    void isLessThanWithMessageShouldNotThrowExceptionWhenConditionIsTrue(final Number toCheck,
                                                                         final Number input,
                                                                         final String message1,
                                                                         final String message2) {

        assertDoesNotThrow(() -> Checks.notNullAnd(input).isLessThan(toCheck, message1, message2));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).isLessThanAnd(toCheck, message1, message2));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isLessThan(toCheck, message1, message2));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isLessThanAnd(toCheck, message1, message2));
    }

    static Stream<Arguments> isLessThanWithMessageData() {

        return Stream.of(
                Arguments.of(5, 0, "The reference is %s, which is too high", "5", "The reference is 5, which is too high"),
                Arguments.of(5, 5, "The reference is %s, which is too high", "5", "The reference is 5, which is too high"),
                Arguments.of(5L, 0L, "The reference is %s, which is too high", "5", "The reference is 5, which is too high"),
                Arguments.of(5L, 5L, "The reference is %s, which is too high", "5", "The reference is 5, which is too high"),
                Arguments.of(5F, 0F, "The reference is %s, which is too high", "5.0", "The reference is 5.0, which is too high"),
                Arguments.of(5F, 5F, "The reference is %s, which is too high", "5.0", "The reference is 5.0, which is too high"),
                Arguments.of(5D, 0D, "The reference is %s, which is too high", "5.0", "The reference is 5.0, which is too high"),
                Arguments.of(5D, 5D, "The reference is %s, which is too high", "5.0", "The reference is 5.0, which is too high")
        );
    }

    @ParameterizedTest
    @MethodSource("isLessThanOrEqualToData")
    void isLessThanOrEqualToShouldThrowExceptionWhenConditionIsFalse(final Number input,
                                                                     final Number toCheck,
                                                                     final String errorMessage) {

        assertThatThrownBy(() -> Checks.notNullAnd(input).isLessThanOrEqualTo(toCheck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

        assertThatThrownBy(() -> Checks.notNullAnd(input).isLessThanOrEqualToAnd(toCheck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("isLessThanData")
    void isLessThanOrEqualToShouldNotThrowExceptionWhenConditionIsTrue(final Number toCheck,
                                                                       final Number input) {

        assertDoesNotThrow(() -> Checks.notNullAnd(input).isLessThanOrEqualTo(toCheck));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).isLessThanOrEqualToAnd(toCheck));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isLessThanOrEqualTo(toCheck));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isLessThanOrEqualToAnd(toCheck));
    }

    static Stream<Arguments> isLessThanOrEqualToData() {

        return Stream.of(
                Arguments.of(5, 0, "The reference is 5, but must be <= 0"),
                Arguments.of(5, 4, "The reference is 5, but must be <= 4"),
                Arguments.of(5L, 0L, "The reference is 5, but must be <= 0"),
                Arguments.of(5L, 4L, "The reference is 5, but must be <= 4"),
                Arguments.of(5F, 0F, "The reference is 5.0, but must be <= 0.0"),
                Arguments.of(5F, 4.99F, "The reference is 5.0, but must be <= 4.99"),
                Arguments.of(5D, 0D, "The reference is 5.0, but must be <= 0.0"),
                Arguments.of(5D, 4.99D, "The reference is 5.0, but must be <= 4.99")
        );
    }

    @ParameterizedTest
    @MethodSource("isLessThanOrEqualToWithMessageData")
    void isLessThanOrEqualToWithMessageShouldThrowExceptionWhenConditionIsFalse(final Number input,
                                                                                final Number toCheck,
                                                                                final String message1,
                                                                                final String message2,
                                                                                final String errorMessage) {

        assertThatThrownBy(() -> Checks.notNullAnd(input).isLessThanOrEqualTo(toCheck, message1, message2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

        assertThatThrownBy(() -> Checks.notNullAnd(input).isLessThanOrEqualToAnd(toCheck, message1, message2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("isLessThanWithMessageData")
    void isLessThanOrEqualToWithMessageShouldNotThrowExceptionWhenConditionIsTrue(final Number toCheck,
                                                                                  final Number input,
                                                                                  final String message1,
                                                                                  final String message2) {

        assertDoesNotThrow(() -> Checks.notNullAnd(input).isLessThanOrEqualTo(toCheck, message1, message2));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).isLessThanOrEqualToAnd(toCheck, message1, message2));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isLessThanOrEqualTo(toCheck, message1, message2));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isLessThanOrEqualToAnd(toCheck, message1, message2));
    }

    static Stream<Arguments> isLessThanOrEqualToWithMessageData() {

        return Stream.of(
                Arguments.of(5, 0, "The reference is %s, which is too high", "5", "The reference is 5, which is too high"),
                Arguments.of(6, 5, "The reference is %s, which is too high", "6", "The reference is 6, which is too high"),
                Arguments.of(5L, 0L, "The reference is %s, which is too high", "5", "The reference is 5, which is too high"),
                Arguments.of(6L, 5L, "The reference is %s, which is too high", "6", "The reference is 6, which is too high"),
                Arguments.of(5F, 0F, "The reference is %s, which is too high", "5.0", "The reference is 5.0, which is too high"),
                Arguments.of(5.01F, 5F, "The reference is %s, which is too high", "5.01", "The reference is 5.01, which is too high"),
                Arguments.of(5D, 0D, "The reference is %s, which is too high", "5.0", "The reference is 5.0, which is too high"),
                Arguments.of(5.01D, 5D, "The reference is %s, which is too high", "5.01", "The reference is 5.01, which is too high")
        );
    }

    @ParameterizedTest
    @MethodSource("isGreaterThanData")
    void isGreaterThanShouldThrowExceptionWhenConditionIsFalse(final Number input,
                                                               final Number toCheck,
                                                               final String errorMessage) {

        assertThatThrownBy(() -> Checks.notNullAnd(input).isGreaterThan(toCheck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

        assertThatThrownBy(() -> Checks.notNullAnd(input).isGreaterThanAnd(toCheck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("isGreaterThanOrEqualToData")
    void isGreaterThanShouldNotThrowExceptionWhenConditionIsTrue(final Number toCheck,
                                                                 final Number input) {

        assertDoesNotThrow(() -> Checks.notNullAnd(input).isGreaterThan(toCheck));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).isGreaterThanAnd(toCheck));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isGreaterThan(toCheck));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isGreaterThanAnd(toCheck));
    }

    static Stream<Arguments> isGreaterThanData() {

        return Stream.of(
                Arguments.of(0, 5, "The reference is 0, but must be > 5"),
                Arguments.of(5, 5, "The reference is 5, but must be > 5"),
                Arguments.of(0L, 5L, "The reference is 0, but must be > 5"),
                Arguments.of(5L, 5L, "The reference is 5, but must be > 5"),
                Arguments.of(0F, 5F, "The reference is 0.0, but must be > 5.0"),
                Arguments.of(5F, 5F, "The reference is 5.0, but must be > 5.0"),
                Arguments.of(0D, 5D, "The reference is 0.0, but must be > 5.0"),
                Arguments.of(5D, 5D, "The reference is 5.0, but must be > 5.0")
        );
    }

    @ParameterizedTest
    @MethodSource("isGreaterThanWithMessageData")
    void isGreaterThanWithMessageShouldThrowExceptionWhenConditionIsFalse(final Number input,
                                                                          final Number toCheck,
                                                                          final String message1,
                                                                          final String message2,
                                                                          final String errorMessage) {

        assertThatThrownBy(() -> Checks.notNullAnd(input).isGreaterThan(toCheck, message1, message2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

        assertThatThrownBy(() -> Checks.notNullAnd(input).isGreaterThanAnd(toCheck, message1, message2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("isGreaterThanOrEqualToWithMessageData")
    void isGreaterThanWithMessageShouldNotThrowExceptionWhenConditionIsTrue(final Number toCheck,
                                                                            final Number input,
                                                                            final String message1,
                                                                            final String message2) {

        assertDoesNotThrow(() -> Checks.notNullAnd(input).isGreaterThan(toCheck, message1, message2));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).isGreaterThanAnd(toCheck, message1, message2));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isGreaterThan(toCheck, message1, message2));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isGreaterThanAnd(toCheck, message1, message2));
    }

    static Stream<Arguments> isGreaterThanWithMessageData() {

        return Stream.of(
                Arguments.of(0, 5, "The reference is %s, which is too low", "0", "The reference is 0, which is too low"),
                Arguments.of(5, 5, "The reference is %s, which is too low", "5", "The reference is 5, which is too low"),
                Arguments.of(0L, 5L, "The reference is %s, which is too low", "0", "The reference is 0, which is too low"),
                Arguments.of(5L, 5L, "The reference is %s, which is too low", "5", "The reference is 5, which is too low"),
                Arguments.of(0F, 5F, "The reference is %s, which is too low", "0.0", "The reference is 0.0, which is too low"),
                Arguments.of(5F, 5F, "The reference is %s, which is too low", "5.0", "The reference is 5.0, which is too low"),
                Arguments.of(0D, 5D, "The reference is %s, which is too low", "0.0", "The reference is 0.0, which is too low"),
                Arguments.of(5D, 5D, "The reference is %s, which is too low", "5.0", "The reference is 5.0, which is too low")
        );
    }

    @ParameterizedTest
    @MethodSource("isGreaterThanOrEqualToData")
    void isGreaterThanOrEqualToShouldThrowExceptionWhenConditionIsFalse(final Number input,
                                                                        final Number toCheck,
                                                                        final String errorMessage) {

        assertThatThrownBy(() -> Checks.notNullAnd(input).isGreaterThanOrEqualTo(toCheck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

        assertThatThrownBy(() -> Checks.notNullAnd(input).isGreaterThanOrEqualToAnd(toCheck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("isGreaterThanData")
    void isGreaterThanOrEqualToShouldNotThrowExceptionWhenConditionIsTrue(final Number toCheck,
                                                                          final Number input) {

        assertDoesNotThrow(() -> Checks.notNullAnd(input).isGreaterThanOrEqualTo(toCheck));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).isGreaterThanOrEqualToAnd(toCheck));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isGreaterThanOrEqualTo(toCheck));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isGreaterThanOrEqualToAnd(toCheck));
    }

    static Stream<Arguments> isGreaterThanOrEqualToData() {

        return Stream.of(
                Arguments.of(0, 5, "The reference is 0, but must be >= 5"),
                Arguments.of(4, 5, "The reference is 4, but must be >= 5"),
                Arguments.of(0L, 5L, "The reference is 0, but must be >= 5"),
                Arguments.of(4L, 5L, "The reference is 4, but must be >= 5"),
                Arguments.of(0F, 5F, "The reference is 0.0, but must be >= 5.0"),
                Arguments.of(4.99F, 5F, "The reference is 4.99, but must be >= 5.0"),
                Arguments.of(0D, 5D, "The reference is 0.0, but must be >= 5.0"),
                Arguments.of(4.99D, 5D, "The reference is 4.99, but must be >= 5.0")
        );
    }

    @ParameterizedTest
    @MethodSource("isGreaterThanOrEqualToWithMessageData")
    void isGreaterThanOrEqualToWithMessageShouldThrowExceptionWhenConditionIsFalse(final Number input,
                                                                                   final Number toCheck,
                                                                                   final String message1,
                                                                                   final String message2,
                                                                                   final String errorMessage) {

        assertThatThrownBy(() -> Checks.notNullAnd(input).isGreaterThanOrEqualTo(toCheck, message1, message2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

        assertThatThrownBy(() -> Checks.notNullAnd(input).isGreaterThanOrEqualToAnd(toCheck, message1, message2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("isGreaterThanWithMessageData")
    void isGreaterThanOrEqualToWithMessageShouldNotThrowExceptionWhenConditionIsTrue(final Number toCheck,
                                                                                     final Number input,
                                                                                     final String message1,
                                                                                     final String message2) {

        assertDoesNotThrow(() -> Checks.notNullAnd(input).isGreaterThanOrEqualTo(toCheck, message1, message2));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).isGreaterThanOrEqualToAnd(toCheck, message1, message2));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isGreaterThanOrEqualTo(toCheck, message1, message2));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isGreaterThanOrEqualToAnd(toCheck, message1, message2));
    }

    static Stream<Arguments> isGreaterThanOrEqualToWithMessageData() {

        return Stream.of(
                Arguments.of(0, 5, "The reference is %s, which is too low", "0", "The reference is 0, which is too low"),
                Arguments.of(4, 5, "The reference is %s, which is too low", "4", "The reference is 4, which is too low"),
                Arguments.of(0L, 5L, "The reference is %s, which is too low", "0", "The reference is 0, which is too low"),
                Arguments.of(4L, 5L, "The reference is %s, which is too low", "4", "The reference is 4, which is too low"),
                Arguments.of(0F, 5F, "The reference is %s, which is too low", "0.0", "The reference is 0.0, which is too low"),
                Arguments.of(4.99F, 5F, "The reference is %s, which is too low", "4.99", "The reference is 4.99, which is too low"),
                Arguments.of(0D, 5D, "The reference is %s, which is too low", "0.0", "The reference is 0.0, which is too low"),
                Arguments.of(4.99D, 5D, "The reference is %s, which is too low", "4.99", "The reference is 4.99, which is too low")
        );
    }

    @ParameterizedTest
    @MethodSource("isBetweenData")
    void isBetweenShouldThrowExceptionWhenConditionIsFalse(final Number input,
                                                           final Number toCheckLowInclusive,
                                                           final Number toCheckHighInclusive,
                                                           final String errorMessage) {

        assertThatThrownBy(() -> Checks.notNullAnd(input).isBetween(toCheckLowInclusive, toCheckHighInclusive))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

        assertThatThrownBy(() -> Checks.notNullAnd(input).isBetweenAnd(toCheckLowInclusive, toCheckHighInclusive))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("isBetweenData")
    void isBetweenShouldNotThrowExceptionWhenConditionIsTrue(final Number toCheckLowInclusive,
                                                             final Number input,
                                                             final Number toCheckHighInclusive) {

        assertDoesNotThrow(() -> Checks.notNullAnd(input).isBetween(toCheckLowInclusive, toCheckHighInclusive));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).isBetweenAnd(toCheckLowInclusive, toCheckHighInclusive));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isBetween(toCheckLowInclusive, toCheckHighInclusive));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isBetweenAnd(toCheckLowInclusive, toCheckHighInclusive));
    }

    static Stream<Arguments> isBetweenData() {

        return Stream.of(
                Arguments.of(0, 1, 5, "The reference is 0, but must be in the range of 1-5 inclusive"),
                Arguments.of(4, 5, 5, "The reference is 4, but must be in the range of 5-5 inclusive"),
                Arguments.of(0L, 1L, 5L, "The reference is 0, but must be in the range of 1-5 inclusive"),
                Arguments.of(4L, 5L, 5L, "The reference is 4, but must be in the range of 5-5 inclusive"),
                Arguments.of(0F, 1F, 5F, "The reference is 0.0, but must be in the range of 1.0-5.0 inclusive"),
                Arguments.of(4.99F, 5F, 5F, "The reference is 4.99, but must be in the range of 5.0-5.0 inclusive"),
                Arguments.of(0D, 1D, 5D, "The reference is 0.0, but must be in the range of 1.0-5.0 inclusive"),
                Arguments.of(4.99D, 5D, 5D, "The reference is 4.99, but must be in the range of 5.0-5.0 inclusive")
        );
    }

    @ParameterizedTest
    @MethodSource("isBetweenWithMessageData")
    void isBetweenWithMessageShouldThrowExceptionWhenConditionIsFalse(final Number input,
                                                                      final Number toCheckLowInclusive,
                                                                      final Number toCheckHighInclusive,
                                                                      final String message1,
                                                                      final String message2,
                                                                      final String errorMessage) {

        assertThatThrownBy(() -> Checks.notNullAnd(input).isBetween(toCheckLowInclusive, toCheckHighInclusive, message1, message2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

        assertThatThrownBy(() -> Checks.notNullAnd(input).isBetween(toCheckLowInclusive, toCheckHighInclusive, message1, message2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("isBetweenWithMessageData")
    void isBetweenShouldNotThrowExceptionWhenConditionIsTrue(final Number toCheckLowInclusive,
                                                             final Number input,
                                                             final Number toCheckHighInclusive,
                                                             final String message1,
                                                             final String message2) {

        assertDoesNotThrow(() -> Checks.notNullAnd(input).isBetween(toCheckLowInclusive, toCheckHighInclusive, message1, message2));
        assertDoesNotThrow(() -> Checks.notNullAnd(input).isBetweenAnd(toCheckLowInclusive, toCheckHighInclusive, message1, message2));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isBetween(toCheckLowInclusive, toCheckHighInclusive, message1, message2));
        assertDoesNotThrow(() -> Checks.nullableAnd((Number) null).isBetweenAnd(toCheckLowInclusive, toCheckHighInclusive, message1, message2));
    }

    static Stream<Arguments> isBetweenWithMessageData() {

        return Stream.of(
                Arguments.of(0, 1, 5, "The reference is %s, which is wrong", "0", "The reference is 0, which is wrong"),
                Arguments.of(4, 5, 5, "The reference is %s, which is wrong", "4", "The reference is 4, which is wrong"),
                Arguments.of(0L, 1L, 5L, "The reference is %s, which is wrong", "0", "The reference is 0, which is wrong"),
                Arguments.of(4L, 5L, 5L, "The reference is %s, which is wrong", "4", "The reference is 4, which is wrong"),
                Arguments.of(0F, 1F, 5F, "The reference is %s, which is wrong", "0.0", "The reference is 0.0, which is wrong"),
                Arguments.of(4.99F, 5F, 5F, "The reference is %s, which is wrong", "4.99", "The reference is 4.99, which is wrong"),
                Arguments.of(0D, 1D, 5D, "The reference is %s, which is wrong", "0.0", "The reference is 0.0, which is wrong"),
                Arguments.of(4.99D, 5D, 5D, "The reference is %s, which is wrong", "4.99", "The reference is 4.99, which is wrong")
        );
    }

    private static class Standard {

        final String s;

        private Standard(final String s) {

            this.s = s;
        }

        @Override
        public String toString() {

            return s;
        }
    }
}
