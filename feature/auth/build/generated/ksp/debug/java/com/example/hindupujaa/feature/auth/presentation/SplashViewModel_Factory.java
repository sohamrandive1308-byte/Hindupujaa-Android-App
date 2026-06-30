package com.example.hindupujaa.feature.auth.presentation;

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
public final class SplashViewModel_Factory implements Factory<SplashViewModel> {
  private final Provider<FirebaseAuth> authProvider;

  private SplashViewModel_Factory(Provider<FirebaseAuth> authProvider) {
    this.authProvider = authProvider;
  }

  @Override
  public SplashViewModel get() {
    return newInstance(authProvider.get());
  }

  public static SplashViewModel_Factory create(Provider<FirebaseAuth> authProvider) {
    return new SplashViewModel_Factory(authProvider);
  }

  public static SplashViewModel newInstance(FirebaseAuth auth) {
    return new SplashViewModel(auth);
  }
}
