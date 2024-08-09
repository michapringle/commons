package ca.mpringle.util;

import jakarta.annotation.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Checks {

    private Checks() {
    }

    @NotNull
    public static <T> T notNull(@NotNull final T reference) {

        if (reference != null) {
            return reference;
        }

        throw new NullPointerException("reference must not be null.");
    }

    @NotNull
    public static <T extends Number> NumberChecks<T> notNullAnd(@NotNull final T reference) {

        final StandardChecks<T> standardChecks = new StandardChecks<>(notNull(reference));
        return new NumberChecks<>(standardChecks);
    }

    @NotNull
    public static <T extends Number> NumberChecks<T> nullableAnd(@Nullable final T reference) {

        final StandardChecks<T> standardChecks = new StandardChecks<>(reference);
        return new NumberChecks<>(standardChecks);
    }

    @NotNull
    public static <T> CollectionChecks<T> notNullAnd(@NotNull final Collection<T> reference) {

        final StandardChecks<Collection<T>> standardChecks = new StandardChecks<>(notNull(reference));
        return new CollectionChecks<>(standardChecks);
    }

    @NotNull
    public static <T> CollectionChecks<T> nullableAnd(@Nullable final Collection<T> reference) {

        final StandardChecks<Collection<T>> standardChecks = new StandardChecks<>(reference);
        return new CollectionChecks<>(standardChecks);
    }

    @NotNull
    public static <T> OptionalChecks<T> notNullAnd(@NotNull final Optional<T> reference) {

        final StandardChecks<Optional<T>> standardChecks = new StandardChecks<>(notNull(reference));
        return new OptionalChecks<>(standardChecks);
    }

    @NotNull
    public static <T> Checks.StandardChecks<T> notNullAnd(@NotNull final T reference) {

        return new StandardChecks<>(notNull(reference));
    }

    @NotNull
    public static <T> Checks.StandardChecks<T> nullableAnd(@Nullable final T reference) {

        return new StandardChecks<>(reference);
    }

    public static class NumberChecks<T extends Number> {

        private final Checks.StandardChecks<T> standardChecks;

        private NumberChecks(final Checks.StandardChecks<T> reference) {

            this.standardChecks = reference;
        }

        public T get() {

            return standardChecks.get();
        }

        public <R> R map(final Function<Number, R> mapperFunction) {

            return get() == null ? null : mapperFunction.apply(get());
        }

        public T isValid(final Predicate<T> predicate) {

            return standardChecks.isValidAnd(predicate).get();
        }

        public T isValid(final Predicate<T> predicate,
                         final String errorMessageTemplate,
                         final Object... errorMessageArgs) {

            return standardChecks.isValidAnd(predicate, errorMessageTemplate, errorMessageArgs).get();
        }

        public NumberChecks<T> isValidAnd(final Predicate<T> predicate) {

            standardChecks.isValidAnd(predicate);
            return this;
        }

        public NumberChecks<T> isValidAnd(final Predicate<T> predicate,
                                          final String errorMessageTemplate,
                                          final Object... errorMessageArgs) {

            standardChecks.isValidAnd(predicate, errorMessageTemplate, errorMessageArgs);
            return this;
        }

        public T isAnyOf(final List<T> validValues) {

            return isAnyOfAnd(validValues).get();
        }

        public T isAnyOf(final List<T> validValues,
                         final String errorMessageTemplate,
                         final Object... errorMessageArgs) {

            return isAnyOfAnd(validValues, errorMessageTemplate, errorMessageArgs).get();
        }

        public NumberChecks<T> isAnyOfAnd(final List<T> validValues) {

            return isAnyOfAnd(validValues, "Invalid value: %s, accepted values: %s", get(), Arrays.toString(validValues.toArray()));
        }

        public NumberChecks<T> isAnyOfAnd(final List<T> validValues,
                                          final String errorMessageTemplate,
                                          final Object... errorMessageArgs) {

            if (get() == null || validValues.contains(get())) {
                return this;
            }

            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }

        public T doesEqual(final T number) {

            return doesEqualAnd(number, "The reference %s must == %s", get(), number).get();
        }

        public T doesEqual(final T number,
                           final String errorMessageTemplate,
                           final Object... errorMessageArgs) {

            return doesEqualAnd(number, errorMessageTemplate, errorMessageArgs).get();
        }

        public NumberChecks<T> doesEqualAnd(final T number) {

            return doesEqualAnd(number, "The reference %s must == %s", get(), number);
        }

        public NumberChecks<T> doesEqualAnd(final T number,
                                            final String errorMessageTemplate,
                                            final Object... errorMessageArgs) {

            if (get() == null || get().equals(number)) {
                return this;
            }

            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }

        public T doesNotEqual(final T number) {

            return doesNotEqualAnd(number, "The reference %s must != %s", get(), number).get();
        }

        public T doesNotEqual(final T number,
                              final String errorMessageTemplate,
                              final Object... errorMessageArgs) {

            return doesNotEqualAnd(number, errorMessageTemplate, errorMessageArgs).get();
        }

        public NumberChecks<T> doesNotEqualAnd(final T number) {

            return doesNotEqualAnd(number, "The reference %s must != %s", get(), number);
        }

        public NumberChecks<T> doesNotEqualAnd(final T number,
                                               final String errorMessageTemplate,
                                               final Object... errorMessageArgs) {

            if (get() == null || !get().equals(number)) {
                return this;
            }

            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }

        public T isLessThan(final T number) {

            return isLessThanAnd(number, "The reference is %s, but must be < %s", get(), number).get();
        }

        public T isLessThan(final T number,
                            final String errorMessageTemplate,
                            final Object... errorMessageArgs) {

            return isLessThanAnd(number, errorMessageTemplate, errorMessageArgs).get();
        }

        public NumberChecks<T> isLessThanAnd(final T number) {

            return isLessThanAnd(number, "The reference is %s, but must be < %s", get(), number);
        }

        public NumberChecks<T> isLessThanAnd(final T number,
                                             final String errorMessageTemplate,
                                             final Object... errorMessageArgs) {

            if (get() == null || get().doubleValue() < number.doubleValue()) {
                return this;
            }

            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }

        public T isLessThanOrEqualTo(final T number) {

            return isLessThanOrEqualToAnd(number, "The reference is %s, but must be <= %s", get(), number).get();
        }

        public T isLessThanOrEqualTo(final T number,
                                     final String errorMessageTemplate,
                                     final Object... errorMessageArgs) {

            return isLessThanOrEqualToAnd(number, errorMessageTemplate, errorMessageArgs).get();
        }

        public NumberChecks<T> isLessThanOrEqualToAnd(final T number) {

            return isLessThanOrEqualToAnd(number, "The reference is %s, but must be <= %s", get(), number);
        }

        public NumberChecks<T> isLessThanOrEqualToAnd(final T number,
                                                      final String errorMessageTemplate,
                                                      final Object... errorMessageArgs) {

            if (get() == null || get().doubleValue() <= number.doubleValue()) {
                return this;
            }

            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }

        public T isGreaterThan(final T number) {

            return isGreaterThanAnd(number, "The reference is %s, but must be > %s", get(), number).get();
        }

        public T isGreaterThan(final T number,
                               final String errorMessageTemplate,
                               final Object... errorMessageArgs) {

            return isGreaterThanAnd(number, errorMessageTemplate, errorMessageArgs).get();
        }

        public NumberChecks<T> isGreaterThanAnd(final T number) {

            return isGreaterThanAnd(number, "The reference is %s, but must be > %s", get(), number);
        }

        public NumberChecks<T> isGreaterThanAnd(T number,
                                                final String errorMessageTemplate,
                                                final Object... errorMessageArgs) {

            if (get() == null || get().doubleValue() > number.doubleValue()) {
                return this;
            }

            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }

        public T isGreaterThanOrEqualTo(final T number) {

            return isGreaterThanOrEqualToAnd(number, "The reference is %s, but must be >= %s", get(), number).get();
        }

        public T isGreaterThanOrEqualTo(final T number,
                                        final String errorMessageTemplate,
                                        final Object... errorMessageArgs) {

            return isGreaterThanOrEqualToAnd(number, errorMessageTemplate, errorMessageArgs).get();
        }

        public NumberChecks<T> isGreaterThanOrEqualToAnd(final T number) {

            return isGreaterThanOrEqualToAnd(number, "The reference is %s, but must be >= %s", get(), number);
        }

        public NumberChecks<T> isGreaterThanOrEqualToAnd(final T number,
                                                         final String errorMessageTemplate,
                                                         final Object... errorMessageArgs) {

            if (get() == null || get().doubleValue() >= number.doubleValue()) {
                return this;
            }

            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }

        public T isBetween(final T lowInclusive,
                           final T highInclusive) {

            return isBetweenAnd(
                    lowInclusive,
                    highInclusive,
                    "The reference is %s, but must be in the range of %s-%s inclusive", get(), lowInclusive, highInclusive
            ).get();
        }

        public T isBetween(final T lowInclusive,
                           final T highInclusive,
                           final String errorMessageTemplate,
                           final Object... errorMessageArgs) {

            return isBetweenAnd(lowInclusive, highInclusive, errorMessageTemplate, errorMessageArgs).get();
        }

        public NumberChecks<T> isBetweenAnd(final T lowInclusive,
                                            final T highInclusive) {

            return isBetweenAnd(
                    lowInclusive,
                    highInclusive,
                    "The reference is %s, but must be in the range of %s-%s inclusive", get(), lowInclusive, highInclusive
            );
        }

        public NumberChecks<T> isBetweenAnd(final T lowInclusive,
                                            final T highInclusive,
                                            final String errorMessageTemplate,
                                            final Object... errorMessageArgs) {

            return isLessThanOrEqualToAnd(highInclusive, errorMessageTemplate, errorMessageArgs)
                    .isGreaterThanOrEqualToAnd(lowInclusive, errorMessageTemplate, errorMessageArgs);
        }
    }

    public static class CollectionChecks<T> {

        private final Checks.StandardChecks<Collection<T>> standardChecks;

        private CollectionChecks(final Checks.StandardChecks<Collection<T>> reference) {

            this.standardChecks = reference;
        }

        public Collection<T> get() {

            return standardChecks.get();
        }

        public <R> R map(final Function<Collection<T>, R> mapperFunction) {

            return get() == null ? null : mapperFunction.apply(get());
        }

        public Collection<T> isValid(final Predicate<Collection<T>> predicate) {

            return isValidAnd(predicate).get();
        }

        public Collection<T> isValid(final Predicate<Collection<T>> predicate,
                                     final String errorMessageTemplate,
                                     final Object... errorMessageArgs) {

            return isValidAnd(predicate, errorMessageTemplate, errorMessageArgs).get();
        }

        public CollectionChecks<T> isValidAnd(final Predicate<Collection<T>> predicate) {

            return isValidAnd(predicate, "Predicate check failed for '%s'", get());
        }

        public CollectionChecks<T> isValidAnd(final Predicate<Collection<T>> predicate,
                                              final String errorMessageTemplate,
                                              final Object... errorMessageArgs) {

            standardChecks.isValidAnd(predicate, errorMessageTemplate, errorMessageArgs);
            return this;
        }

        public Collection<T> isNotEmpty() {

            return isNotEmptyAnd().get();
        }

        public Collection<T> isNotEmpty(final String errorMessageTemplate,
                                        final Object... errorMessageArgs) {

            return isNotEmptyAnd(errorMessageTemplate, errorMessageArgs).get();
        }

        public CollectionChecks<T> isNotEmptyAnd() {

            return isNotEmptyAnd("reference collection must not be empty");
        }

        public CollectionChecks<T> isNotEmptyAnd(final String errorMessageTemplate,
                                                 final Object... errorMessageArgs) {

            if (get() == null || !get().isEmpty()) {
                return this;
            }

            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static class OptionalChecks<T> {

        private final Checks.StandardChecks<Optional<T>> standardChecks;

        private OptionalChecks(final Checks.StandardChecks<Optional<T>> reference) {

            this.standardChecks = reference;
        }

        public Optional<T> get() {

            return standardChecks.get();
        }

        public <R> R map(final Function<Optional<T>, R> mapperFunction) {

            return mapperFunction.apply(get());
        }

        public Optional<T> isValid(final Predicate<Optional<T>> predicate) {

            return isValidAnd(predicate).get();
        }

        public Optional<T> isValid(final Predicate<Optional<T>> predicate,
                                   final String errorMessageTemplate,
                                   final Object... errorMessageArgs) {

            return isValidAnd(predicate, errorMessageTemplate, errorMessageArgs).get();
        }

        public OptionalChecks<T> isValidAnd(final Predicate<Optional<T>> predicate) {

            return isValidAnd(predicate, "Predicate check failed for '%s'", get());
        }

        public OptionalChecks<T> isValidAnd(final Predicate<Optional<T>> predicate,
                                            final String errorMessageTemplate,
                                            final Object... errorMessageArgs) {

            standardChecks.isValidAnd(predicate, errorMessageTemplate, errorMessageArgs);
            return this;
        }

        public Optional<T> isAnyOf(final List<Optional<T>> validValues) {

            return isAnyOfAnd(validValues).get();
        }

        public Optional<T> isAnyOf(final List<Optional<T>> validValues,
                                   final String errorMessageTemplate,
                                   final Object... errorMessageArgs) {

            return isAnyOfAnd(validValues, errorMessageTemplate, errorMessageArgs).get();
        }

        public OptionalChecks<T> isAnyOfAnd(final List<Optional<T>> validValues) {

            return isAnyOfAnd(validValues, "Invalid value: %s, accepted values: %s", get(), Arrays.toString(validValues.toArray()));
        }

        public OptionalChecks<T> isAnyOfAnd(final List<Optional<T>> validValues,
                                            final String errorMessageTemplate,
                                            final Object... errorMessageArgs) {

            if (validValues.contains(get())) {
                return this;
            }

            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }

        public Optional<T> isPresent() {

            return isPresentAnd().get();
        }

        public Optional<T> isPresent(final String errorMessageTemplate,
                                     final Object... errorMessageArgs) {

            return isPresentAnd(errorMessageTemplate, errorMessageArgs).get();
        }

        public OptionalChecks<T> isPresentAnd() {

            return isPresentAnd("reference Optional must not be empty");
        }

        public OptionalChecks<T> isPresentAnd(final String errorMessageTemplate,
                                              final Object... errorMessageArgs) {

            if (get().isPresent()) {
                return this;
            }

            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static class StandardChecks<T> {

        @Nullable
        final T reference;

        private StandardChecks(@Nullable final T reference) {

            this.reference = reference;
        }

        @Nullable
        public T get() {

            return reference;
        }

        @Nullable
        public <R> R map(@NotNull final Function<T, R> mapperFunction) {

            return reference == null ? null : mapperFunction.apply(reference);
        }

        @Nullable
        public T isValid(@NotNull final Predicate<T> predicate) {

            return isValidAnd(predicate).get();
        }

        @Nullable
        public T isValid(@NotNull final Predicate<T> predicate,
                         @NotNull final String errorMessageTemplate,
                         @NotNull final Object... errorMessageArgs) {

            return isValidAnd(predicate, errorMessageTemplate, errorMessageArgs).get();
        }

        @NotNull
        public Checks.StandardChecks<T> isValidAnd(@NotNull final Predicate<T> predicate) {

            return isValidAnd(predicate, "Predicate check failed for '%s'", reference);
        }

        @NotNull
        public Checks.StandardChecks<T> isValidAnd(@NotNull final Predicate<T> predicate,
                                                   @NotNull final String errorMessageTemplate,
                                                   @NotNull final Object... errorMessageArgs) {

            if (reference == null || predicate.test(reference)) {
                return this;
            }

            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }

        @NotNull
        public T isAnyOf(@NotNull final List<T> validValues) {

            return isAnyOfAnd(validValues).get();
        }

        @NotNull
        public T isAnyOf(@NotNull final List<T> validValues,
                         @NotNull final String errorMessageTemplate,
                         @NotNull final Object... errorMessageArgs) {

            return isAnyOfAnd(validValues, errorMessageTemplate, errorMessageArgs).get();
        }

        @NotNull
        public Checks.StandardChecks<T> isAnyOfAnd(@NotNull final List<T> validValues) {

            return isAnyOfAnd(validValues, "Invalid value: %s, accepted values: %s", get(), Arrays.toString(validValues.toArray()));
        }

        @NotNull
        public Checks.StandardChecks<T> isAnyOfAnd(@NotNull final List<T> validValues,
                                                   @NotNull final String errorMessageTemplate,
                                                   @NotNull final Object... errorMessageArgs) {

            if (get() == null || validValues.contains(get())) {
                return this;
            }

            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }
    }
}
