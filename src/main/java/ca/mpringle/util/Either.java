package ca.mpringle.util;

public final class Either<L, R> {

    private final L left;
    private final R right;
    private final boolean isLeft;

    private Either(final L left,
                   final R right,
                   final boolean isLeft) {

        this.left = left;
        this.right = right;
        this.isLeft = isLeft;

        final boolean isLeftSet = this.right == null && this.isLeft;
        final boolean isRightSet = this.left == null && !this.isLeft;
        final String message = "Either elements are mutually exclusive, if left is set, right must be null. If right is set, left must be null.";
        Checks.notNullAnd(isLeft).isValid(p -> isLeftSet || isRightSet, message);
    }

    public static <L, R> Either<L, R> newLeft(final L left) {
        return new Either<>(left, null, true);
    }

    public static <L, R> Either<L, R> newRight(final R right) {
        return new Either<>(null, right, false);
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public boolean isRight() {
        return !isLeft;
    }

    @Override
    public String toString() {

        return isLeft ? "[" + left.toString() + ", -]" : "[-, " + right.toString() + "]";
    }
}
