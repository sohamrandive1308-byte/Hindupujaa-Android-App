package com.example.hindupujaa.feature.checkout.presentation;

import com.example.hindupujaa.core.domain.repository.CartRepository;
import com.example.hindupujaa.core.domain.repository.OrderRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class CheckoutViewModel_Factory implements Factory<CheckoutViewModel> {
  private final Provider<CartRepository> cartRepositoryProvider;

  private final Provider<OrderRepository> orderRepositoryProvider;

  private CheckoutViewModel_Factory(Provider<CartRepository> cartRepositoryProvider,
      Provider<OrderRepository> orderRepositoryProvider) {
    this.cartRepositoryProvider = cartRepositoryProvider;
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  @Override
  public CheckoutViewModel get() {
    return newInstance(cartRepositoryProvider.get(), orderRepositoryProvider.get());
  }

  public static CheckoutViewModel_Factory create(Provider<CartRepository> cartRepositoryProvider,
      Provider<OrderRepository> orderRepositoryProvider) {
    return new CheckoutViewModel_Factory(cartRepositoryProvider, orderRepositoryProvider);
  }

  public static CheckoutViewModel newInstance(CartRepository cartRepository,
      OrderRepository orderRepository) {
    return new CheckoutViewModel(cartRepository, orderRepository);
  }
}
