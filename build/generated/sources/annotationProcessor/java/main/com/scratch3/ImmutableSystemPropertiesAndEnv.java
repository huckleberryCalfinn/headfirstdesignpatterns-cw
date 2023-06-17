package com.scratch3;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link SystemPropertiesAndEnv}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableSystemPropertiesAndEnv.builder()}.
 */
@Generated(from = "SystemPropertiesAndEnv", generator = "Immutables")
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
@Immutable
@CheckReturnValue
final class ImmutableSystemPropertiesAndEnv implements SystemPropertiesAndEnv {
  private final Properties props;
  private final ImmutableMap<String, String> env;

  private ImmutableSystemPropertiesAndEnv(
      Properties props,
      ImmutableMap<String, String> env) {
    this.props = props;
    this.env = env;
  }

  /**
   * @return The value of the {@code props} attribute
   */
  @Override
  public Properties getProps() {
    return props;
  }

  /**
   * @return The value of the {@code env} attribute
   */
  @Override
  public ImmutableMap<String, String> getEnv() {
    return env;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link SystemPropertiesAndEnv#getProps() props} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for props
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableSystemPropertiesAndEnv withProps(Properties value) {
    if (this.props == value) return this;
    Properties newValue = Objects.requireNonNull(value, "props");
    return new ImmutableSystemPropertiesAndEnv(newValue, this.env);
  }

  /**
   * Copy the current immutable object by replacing the {@link SystemPropertiesAndEnv#getEnv() env} map with the specified map.
   * Nulls are not permitted as keys or values.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param entries The entries to be added to the env map
   * @return A modified copy of {@code this} object
   */
  public final ImmutableSystemPropertiesAndEnv withEnv(Map<String, ? extends String> entries) {
    if (this.env == entries) return this;
    ImmutableMap<String, String> newValue = ImmutableMap.copyOf(entries);
    return new ImmutableSystemPropertiesAndEnv(this.props, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableSystemPropertiesAndEnv} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof ImmutableSystemPropertiesAndEnv
        && equalTo((ImmutableSystemPropertiesAndEnv) another);
  }

  private boolean equalTo(ImmutableSystemPropertiesAndEnv another) {
    return props.equals(another.props)
        && env.equals(another.env);
  }

  /**
   * Computes a hash code from attributes: {@code props}, {@code env}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    @Var int h = 5381;
    h += (h << 5) + props.hashCode();
    h += (h << 5) + env.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code SystemPropertiesAndEnv} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("SystemPropertiesAndEnv")
        .omitNullValues()
        .add("props", props)
        .add("env", env)
        .toString();
  }

  /**
   * Creates an immutable copy of a {@link SystemPropertiesAndEnv} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable SystemPropertiesAndEnv instance
   */
  public static ImmutableSystemPropertiesAndEnv copyOf(SystemPropertiesAndEnv instance) {
    if (instance instanceof ImmutableSystemPropertiesAndEnv) {
      return (ImmutableSystemPropertiesAndEnv) instance;
    }
    return ImmutableSystemPropertiesAndEnv.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link ImmutableSystemPropertiesAndEnv ImmutableSystemPropertiesAndEnv}.
   * <pre>
   * ImmutableSystemPropertiesAndEnv.builder()
   *    .props(Properties) // required {@link SystemPropertiesAndEnv#getProps() props}
   *    .putEnv|putAllEnv(String =&gt; String) // {@link SystemPropertiesAndEnv#getEnv() env} mappings
   *    .build();
   * </pre>
   * @return A new ImmutableSystemPropertiesAndEnv builder
   */
  public static ImmutableSystemPropertiesAndEnv.Builder builder() {
    return new ImmutableSystemPropertiesAndEnv.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableSystemPropertiesAndEnv ImmutableSystemPropertiesAndEnv}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "SystemPropertiesAndEnv", generator = "Immutables")
  @NotThreadSafe
  public static final class Builder {
    private static final long INIT_BIT_PROPS = 0x1L;
    private long initBits = 0x1L;

    private @Nullable Properties props;
    private ImmutableMap.Builder<String, String> env = ImmutableMap.builder();

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code SystemPropertiesAndEnv} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * Collection elements and entries will be added, not replaced.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder from(SystemPropertiesAndEnv instance) {
      Objects.requireNonNull(instance, "instance");
      props(instance.getProps());
      putAllEnv(instance.getEnv());
      return this;
    }

    /**
     * Initializes the value for the {@link SystemPropertiesAndEnv#getProps() props} attribute.
     * @param props The value for props 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder props(Properties props) {
      this.props = Objects.requireNonNull(props, "props");
      initBits &= ~INIT_BIT_PROPS;
      return this;
    }

    /**
     * Put one entry to the {@link SystemPropertiesAndEnv#getEnv() env} map.
     * @param key The key in the env map
     * @param value The associated value in the env map
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder putEnv(String key, String value) {
      this.env.put(key, value);
      return this;
    }

    /**
     * Put one entry to the {@link SystemPropertiesAndEnv#getEnv() env} map. Nulls are not permitted
     * @param entry The key and value entry
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder putEnv(Map.Entry<String, ? extends String> entry) {
      this.env.put(entry);
      return this;
    }

    /**
     * Sets or replaces all mappings from the specified map as entries for the {@link SystemPropertiesAndEnv#getEnv() env} map. Nulls are not permitted
     * @param entries The entries that will be added to the env map
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder env(Map<String, ? extends String> entries) {
      this.env = ImmutableMap.builder();
      return putAllEnv(entries);
    }

    /**
     * Put all mappings from the specified map as entries to {@link SystemPropertiesAndEnv#getEnv() env} map. Nulls are not permitted
     * @param entries The entries that will be added to the env map
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder putAllEnv(Map<String, ? extends String> entries) {
      this.env.putAll(entries);
      return this;
    }

    /**
     * Builds a new {@link ImmutableSystemPropertiesAndEnv ImmutableSystemPropertiesAndEnv}.
     * @return An immutable instance of SystemPropertiesAndEnv
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableSystemPropertiesAndEnv build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableSystemPropertiesAndEnv(props, env.build());
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_PROPS) != 0) attributes.add("props");
      return "Cannot build SystemPropertiesAndEnv, some of required attributes are not set " + attributes;
    }
  }
}
