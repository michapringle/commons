package ca.mpringle.util;

import jakarta.annotation.Nullable;

import javax.validation.constraints.NotNull;

public final class Either<L, R> {

    @Nullable
    private final L left;
    @Nullable
    private final R right;
    private final boolean isLeft;

    private Either(@Nullable final L left,
                   @Nullable final R right,
                   final boolean isLeft) {

        this.left = left;
        this.right = right;
        this.isLeft = isLeft;

        final boolean isLeftSet = this.right == null && this.isLeft;
        final boolean isRightSet = this.left == null && !this.isLeft;
        final String message = "Either elements are mutually exclusive, if left is set, right must be null. If right is set, left must be null.";
        Checks.notNullAnd(isLeft).isValid(p -> isLeftSet || isRightSet, message);
    }

    @NotNull
    public static <L, R> Either<L, R> newLeft(@NotNull final L left) {
        return new Either<>(left, null, true);
    }

    @NotNull
    public static <L, R> Either<L, R> newRight(@NotNull final R right) {
        return new Either<>(null, right, false);
    }

    @Nullable
    public L getLeft() {
        return left;
    }

    @Nullable
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
    @NotNull
    public String toString() {

        return isLeft ? "[" + left.toString() + ", -]" : "[-, " + right.toString() + "]";
    }
}
