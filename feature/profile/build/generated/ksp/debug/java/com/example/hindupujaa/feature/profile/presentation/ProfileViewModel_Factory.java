package com.example.hindupujaa.feature.profile.presentation;

import com.example.hindupujaa.core.domain.repository.OrderRepository;
import com.google.firebase.auth.FirebaseAuth;
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<OrderRepository> orderRepositoryProvider;

  private final Provider<FirebaseAuth> authProvider;

  private ProfileViewModel_Factory(Provider<OrderRepository> orderRepositoryProvider,
      Provider<FirebaseAuth> authProvider) {
    this.orderRepositoryProvider = orderRepositoryProvider;
    this.authProvider = authProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(orderRepositoryProvider.get(), authProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<OrderRepository> orderRepositoryProvider,
      Provider<FirebaseAuth> authProvider) {
    return new ProfileViewModel_Factory(orderRepositoryProvider, authProvider);
  }

  public static ProfileViewModel newInstance(OrderRepository orderRepository, FirebaseAuth auth) {
    return new ProfileViewModel(orderRepository, auth);
  }
}
