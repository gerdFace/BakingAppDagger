package com.example.android.bakingapplication.dagger.module

import com.example.android.bakingapplication.presentation.DetailListFragmentPresenter
import com.example.android.bakingapplication.presentation.DetailListFragmentPresenterImpl
import com.example.android.bakingapplication.repository.RecipeRepository
import dagger.Module
import dagger.Provides

@Module
class DetailListFragmentModule {

   /*
    * Ideally, since the DetailListFragment is only being used in the
    * DetailListFragment, we could write the code to wire up the fragment's
    * Injector Factory here, with something like:
    *
    * @ContributesAndroidInjector
    * abstract fun bindDetailListFragmentInjector(): DetailListFragment
    *
    * But this would require making the whole module abstract, and converting
    * "all of the instance @Provides methods to static, or including a
    * non-abstract subclass of the module instead."
    *
    * This is definitely an important issue, and one that we will explore in
    * great depth in the near future. For the time being, we're going to look
    * the other way, and dump all of the these methods into a catch-all
    * FragmentBindingModule
    *
    * There is an instance of DetailListFragmentPresenterImpl in the graph, but
    * the AndroidSupportInjection.inject(this) is looking for a an InjectorFactory
    * with the class name "DetailListFragment.class"
    */

    @Provides
    fun provideDetailListFragmentPresenter(recipeRepository: RecipeRepository): DetailListFragmentPresenter {
        return DetailListFragmentPresenterImpl(recipeRepository)
    }
}
