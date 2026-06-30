package com.example.hindupujaa.core.data.util;

import com.google.firebase.firestore.FirebaseFirestore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
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
public final class FirebaseSeeder_Factory implements Factory<FirebaseSeeder> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private FirebaseSeeder_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public FirebaseSeeder get() {
    return newInstance(firestoreProvider.get());
  }

  public static FirebaseSeeder_Factory create(Provider<FirebaseFirestore> firestoreProvider) {
    return new FirebaseSeeder_Factory(firestoreProvider);
  }

  public static FirebaseSeeder newInstance(FirebaseFirestore firestore) {
    return new FirebaseSeeder(firestore);
  }
}
