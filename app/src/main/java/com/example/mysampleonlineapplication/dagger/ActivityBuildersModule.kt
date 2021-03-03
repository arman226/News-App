package om.example.mysampleonlineapplication.dagger


import com.example.mysampleonlineapplication.ui.main.MainActivity
import com.example.mysampleonlineapplication.ui.main.TeslaActivityBuildersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [TeslaActivityBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}