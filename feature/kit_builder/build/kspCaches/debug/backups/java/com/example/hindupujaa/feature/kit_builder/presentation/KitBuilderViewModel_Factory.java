package com.example.hindupujaa.feature.kit_builder.presentation;

import androidx.lifecycle.SavedStateHandle;
import com.example.hindupujaa.core.domain.repository.CartRepository;
import com.example.hindupujaa.core.domain.repository.PujaRepository;
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
public final class KitBuilderViewModel_Factory implements Factory<KitBuilderViewModel> {
  private final Provider<PujaRepository> pujaRepositoryProvider;

  private final Provider<CartRepository> cartRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private KitBuilderViewModel_Factory(Provider<PujaRepository> pujaRepositoryProvider,
      Provider<CartRepository> cartRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.pujaRepositoryProvider = pujaRepositoryProvider;
    this.cartRepositoryProvider = cartRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public KitBuilderViewModel get() {
    return newInstance(pujaRepositoryProvider.get(), cartRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static KitBuilderViewModel_Factory create(Provider<PujaRepository> pujaRepositoryProvider,
      Provider<CartRepository> cartRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new KitBuilderViewModel_Factory(pujaRepositoryProvider, cartRepositoryProvider, savedStateHandleProvider);
  }

  public static KitBuilderViewModel newInstance(PujaRepository pujaRepository,
      CartRepository cartRepository, SavedStateHandle savedStateHandle) {
    return new KitBuilderViewModel(pujaRepository, cartRepository, savedStateHandle);
  }
}
