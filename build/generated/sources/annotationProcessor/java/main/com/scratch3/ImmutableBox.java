package com.scratch3;

import com.google.common.base.MoreObjects;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link Box}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableBox.builder()}.
 */
@Generated(from = "Box", generator = "Immutables")
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
@Immutable
@CheckReturnValue
final class ImmutableBox<T> extends Box<T> {
  private final T obj;

  private ImmutableBox(T obj) {
    this.obj = obj;
  }

  /**
   * @return The value of the {@code obj} attribute
   */
  @Override
  public T getObj() {
    return obj;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Box#getObj() obj} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for obj
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableBox<T> withObj(T value) {
    if (this.obj == value) return this;
    T newValue = Objects.requireNonNull(value, "obj");
    return new ImmutableBox<>(newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableBox} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof ImmutableBox<?>
        && equalTo((ImmutableBox<?>) another);
  }

  private boolean equalTo(ImmutableBox<?> another) {
    return obj.equals(another.obj);
  }

  /**
   * Computes a hash code from attributes: {@code obj}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    @Var int h = 5381;
    h += (h << 5) + obj.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code Box} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("Box")
        .omitNullValues()
        .add("obj", obj)
        .toString();
  }

  /**
   * Creates an immutable copy of a {@link Box} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param <T> generic parameter T
   * @param instance The instance to copy
   * @return A copied immutable Box instance
   */
  public static <T> ImmutableBox<T> copyOf(Box<T> instance) {
    if (instance instanceof ImmutableBox<?>) {
      return (ImmutableBox<T>) instance;
    }
    return ImmutableBox.<T>builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link ImmutableBox ImmutableBox}.
   * <pre>
   * ImmutableBox.&amp;lt;T&amp;gt;builder()
   *    .obj(T) // required {@link Box#getObj() obj}
   *    .build();
   * </pre>
   * @param <T> generic parameter T
   * @return A new ImmutableBox builder
   */
  public static <T> ImmutableBox.Builder<T> builder() {
    return new ImmutableBox.Builder<>();
  }

  /**
   * Builds instances of type {@link ImmutableBox ImmutableBox}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "Box", generator = "Immutables")
  @NotThreadSafe
  public static final class Builder<T> {
    private static final long INIT_BIT_OBJ = 0x1L;
    private long initBits = 0x1L;

    private @Nullable T obj;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code Box} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder<T> from(Box<T> instance) {
      Objects.requireNonNull(instance, "instance");
      obj(instance.getObj());
      return this;
    }

    /**
     * Initializes the value for the {@link Box#getObj() obj} attribute.
     * @param obj The value for obj 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder<T> obj(T obj) {
      this.obj = Objects.requireNonNull(obj, "obj");
      initBits &= ~INIT_BIT_OBJ;
      return this;
    }

    /**
     * Builds a new {@link ImmutableBox ImmutableBox}.
     * @return An immutable instance of Box
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableBox<T> build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableBox<>(obj);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_OBJ) != 0) attributes.add("obj");
      return "Cannot build Box, some of required attributes are not set " + attributes;
    }
  }
}
