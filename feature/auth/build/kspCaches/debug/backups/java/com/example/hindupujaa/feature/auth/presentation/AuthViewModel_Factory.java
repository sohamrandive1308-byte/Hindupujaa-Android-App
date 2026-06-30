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
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<FirebaseAuth> authProvider;

  private AuthViewModel_Factory(Provider<FirebaseAuth> authProvider) {
    this.authProvider = authProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(authProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<FirebaseAuth> authProvider) {
    return new AuthViewModel_Factory(authProvider);
  }

  public static AuthViewModel newInstance(FirebaseAuth auth) {
    return new AuthViewModel(auth);
  }
}
