package com.example.training_ex1;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * ViewModelFactory class. responsible of creating the ContactsViewModel with the ContactsRepository
 * as an argument
 * @author itaychachy
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ContactsRepository repository;

    /**
     * @param repository ContactsRepository object
     */
    public ViewModelFactory(final ContactsRepository repository){
        this.repository = repository;
    }

    /**
     * Creates a new instance of the given Class
     * @param modelClass a Class whose instance is requested
     * @return A newly created ViewModel
     */
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ContactsViewModel(repository);
    }
}
