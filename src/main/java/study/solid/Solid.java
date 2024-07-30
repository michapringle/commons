package study.solid;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1 Single-Responsibility Principle -> A class should have only one job.
 * {@link SingleResponsibility}
 * <p/>
 * 2 Open-Closed Principle -> A class should be extendable without modifying the
 * class itself.
 * {@link OpenClosed}
 * <p/>
 * 3 Liskov Substitution Principle -> Every subclass or derived class should be
 * substitutable for their base or parent class.
 * {@link LiskovSubstitution}
 * <p/>
 * 4 Interface Segregation Principle -> A client should never be forced to implement
 * an interface that it does not use, or clients should not be forced to depend on
 * methods they do not use.
 * {@link InterfaceSegregation}
 * <p/>
 * 5 Dependency Inversion Principle -> Entities must depend on abstractions, not
 * on concretions. It states that the high-level module must not depend on the
 * low-level module, but they should depend on abstractions.
 * {@link DependencyInversion}
 * <p/>
 */
final class Solid {

    /**
     * Single-Responsibility Principle -> A class should have only one job.
     */
    static final class SingleResponsibility {

        private SingleResponsibility() {
        }

        /**
         * what does sending mail have to do with an employee?
         */
        static class Violation {

            static class Employee {
                String getDesignation(final int employeeID) {
                    return "";
                }

                void updateSalary(final int employeeID) {
                }

                void sendMail() {
                }
            }
        }

        /**
         * Fixed by introducing a new service
         */
        static class Fix {

            static class Employee {
                String getDesignation(final int employeeID) {
                    return "";
                }

                void updateSalary(final int employeeID) {
                }
            }

            static class MailService {

                void sendMail() {
                }
            }
        }
    }

    /**
     * 2 Open-Closed Principle -> A class should be extendable without
     * modifying the class itself.
     */
    static final class OpenClosed {

        private OpenClosed() {
        }

        /**
         * You need to modify the area method everytime a new shape in introduced
         */
        static class Violation {

            interface Shape {
            }

            static class Square implements Shape {
            }

            static class Circle implements Shape {
            }

            double area(final Shape shape) {
                double areaOfShape;
                if (shape instanceof Square) {
                    // calculate the area of Square
                } else if (shape instanceof Circle) {
                    // calculate the area of Circle
                }
                throw new IllegalStateException("Could find shape");
            }
        }

        /**
         * Fix by moving the calculation to the Shape interface
         */
        static class Fix {

            interface Shape {
                double calculateArea();
            }

            static class Square implements Shape {
                @Override
                public double calculateArea() {
                    return 0D;
                }
            }

            static class Circle implements Shape {
                @Override
                public double calculateArea() {
                    return 0D;
                }
            }

            // this method is not required anymore, but demonstrates the example
            double area(final Shape shape) {
                return shape.calculateArea();
            }
        }

        /**
         * You need to modify the method to add a new discount
         */
        static class Violation2 {

            double calculatePriceViolation(final int price, final String discount) {
                if (discount.equals("10%")) {
                    return price * 0.9;
                }
                if (discount.equals("20%")) {
                    return price * 0.8;
                }
                if (discount.equals("30%")) {
                    return price * 0.7;
                }
                throw new IllegalStateException("Invalid discount");
            }
        }

        /**
         * Fix by making the discount its own class
         */
        static class Fix2 {

            enum Discount {
                TEN(0.9D),
                TWENTY(0.8D),
                THIRTY(0.7D);

                final double multiplier;

                Discount(double multiplier) {
                    this.multiplier = multiplier;
                }
            }

            double calculatePriceFixed(final int price, final Discount discount) {
                return price * discount.multiplier;
            }
        }
    }

    /**
     * 3 Liskov Substitution Principle -> Every subclass or derived class should be
     * substitutable for their base or parent class.
     */
    static final class LiskovSubstitution {

        private LiskovSubstitution() {
        }

        /**
         * A mutable Map and an ImmutableMap do not have the same functionality
         * because an ImmutableMap cannot allow adding elements after construction,
         * so the 2 classes cannot be substituted for each other.
         */
        static class Violation {

            interface Map<T> {

                void add(final T element);
            }

            private static class HashMap<T> implements Map<T> {

                @Override
                public void add(final T element) {
                }
            }

            private static class ImmutableMap<T> implements Map<T> {

                @Override
                public void add(final T element) {
                    throw new UnsupportedOperationException("cannot add to immutable map");
                }
            }
        }

        /**
         * Making 2 different interfaces fixes the problem.
         */
        static class Fix {

            interface Map<T> {

                void add(final T element);
            }

            interface ImmutableMap<T> {
            }

            private static class HashMap<T> implements Map<T> {

                @Override
                public void add(final T element) {
                }
            }

            private static class ImmutableHashMap<T> implements ImmutableMap<T> {
            }
        }
    }

    /**
     * 4 Interface Segregation Principle -> A client should never be forced to implement
     * an interface that it does not use, or clients should not be forced to depend on
     * methods they do not use.
     */
    static final class InterfaceSegregation {

        private InterfaceSegregation() {
        }

        /**
         * Why does a 2D shape need to implement a volume method?
         * Why does a 3D shape need to implement an area?
         */
        static class Violation {
            interface Shape {
                double getArea();

                double getVolume();
            }

            static class Square implements Shape {
                @Override
                public double getArea() {
                    return 0D;
                }

                @Override
                public double getVolume() {
                    return 0D;
                }
            }

            static class Cube implements Shape {
                @Override
                public double getArea() {
                    return 0D;
                }

                @Override
                public double getVolume() {
                    return 0D;
                }
            }
        }

        /**
         * Move the volume method to in interface that makes sense
         */
        static class Fix {
            interface Shape {
            }

            interface Shape2D {
                double getArea();
            }

            interface Shape3D {
                double getVolume();
            }


            static class Square implements Shape2D {
                @Override
                public double getArea() {
                    return 0D;
                }
            }

            static class Cube implements Shape3D {
                @Override
                public double getVolume() {
                    return 0D;
                }
            }
        }
    }


    /**
     * 5 Dependency Inversion Principle -> Entities must depend on abstractions, not
     * on concretions. It states that the high-level module must not depend on the
     * low-level module, but they should depend on abstractions.
     */
    static final class DependencyInversion {

        private DependencyInversion() {
        }

        /**
         * The method left-hand type is a HashSet instead of a Set.
         * It should return the interface type, instead of the concrete type.
         */
        static class Violation {

            public <T> HashSet<T> deduplicate(final T... words) {

                final HashSet<T> result = new HashSet<>(Arrays.asList(words));
                return result;
            }
        }


        /**
         * This now implicitly returns the Set interface instead of a HashSet.
         */
        static class Fix {

            public <T> Set<T> deduplicate(final T... words) {

                return new HashSet<>(Arrays.asList(words));
            }
        }
    }
}
