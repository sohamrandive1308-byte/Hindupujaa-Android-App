package com.example.hindupujaa.feature.home.presentation;

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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<PujaRepository> pujaRepositoryProvider;

  private HomeViewModel_Factory(Provider<PujaRepository> pujaRepositoryProvider) {
    this.pujaRepositoryProvider = pujaRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(pujaRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<PujaRepository> pujaRepositoryProvider) {
    return new HomeViewModel_Factory(pujaRepositoryProvider);
  }

  public static HomeViewModel newInstance(PujaRepository pujaRepository) {
    return new HomeViewModel(pujaRepository);
  }
}
