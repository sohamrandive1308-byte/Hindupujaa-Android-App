package com.example.hindupujaa.feature.puja_detail.presentation;

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
public final class PujaDetailViewModel_Factory implements Factory<PujaDetailViewModel> {
  private final Provider<PujaRepository> pujaRepositoryProvider;

  private final Provider<CartRepository> cartRepositoryProvider;

  private PujaDetailViewModel_Factory(Provider<PujaRepository> pujaRepositoryProvider,
      Provider<CartRepository> cartRepositoryProvider) {
    this.pujaRepositoryProvider = pujaRepositoryProvider;
    this.cartRepositoryProvider = cartRepositoryProvider;
  }

  @Override
  public PujaDetailViewModel get() {
    return newInstance(pujaRepositoryProvider.get(), cartRepositoryProvider.get());
  }

  public static PujaDetailViewModel_Factory create(Provider<PujaRepository> pujaRepositoryProvider,
      Provider<CartRepository> cartRepositoryProvider) {
    return new PujaDetailViewModel_Factory(pujaRepositoryProvider, cartRepositoryProvider);
  }

  public static PujaDetailViewModel newInstance(PujaRepository pujaRepository,
      CartRepository cartRepository) {
    return new PujaDetailViewModel(pujaRepository, cartRepository);
  }
}
