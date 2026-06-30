package com.example.hindupujaa.core.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
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
public final class FirebasePujaRepository_Factory implements Factory<FirebasePujaRepository> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private FirebasePujaRepository_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public FirebasePujaRepository get() {
    return newInstance(firestoreProvider.get());
  }

  public static FirebasePujaRepository_Factory create(
      Provider<FirebaseFirestore> firestoreProvider) {
    return new FirebasePujaRepository_Factory(firestoreProvider);
  }

  public static FirebasePujaRepository newInstance(FirebaseFirestore firestore) {
    return new FirebasePujaRepository(firestore);
  }
}
