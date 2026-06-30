package com.example.hindupujaa.core.data.di;

import com.google.firebase.auth.FirebaseAuth;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DataModule_Companion_ProvideAuthFactory implements Factory<FirebaseAuth> {
  @Override
  public FirebaseAuth get() {
    return provideAuth();
  }

  public static DataModule_Companion_ProvideAuthFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FirebaseAuth provideAuth() {
    return Preconditions.checkNotNullFromProvides(DataModule.Companion.provideAuth());
  }

  private static final class InstanceHolder {
    static final DataModule_Companion_ProvideAuthFactory INSTANCE = new DataModule_Companion_ProvideAuthFactory();
  }
}
