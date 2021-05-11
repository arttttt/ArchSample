package com.arttttt.module_injector

interface BaseDependencyHolder<D : BaseFeatureDependencies> {
    val dependencies: D
}

abstract class DependencyHolder0<D : BaseFeatureDependencies>(
) : BaseDependencyHolder<D> {

    companion object {
        operator fun<D : BaseFeatureDependencies> invoke(block: (BaseDependencyHolder<D>) -> D): BaseDependencyHolder<D> {
            return object : DependencyHolder0<D>() {
                override val block = block
            }
        }
    }

    abstract val block: (BaseDependencyHolder<D>) -> D

    override val dependencies: D
        get() = block(this)
}

abstract class DependencyHolder1<A1 : BaseFeatureApi, D : BaseFeatureDependencies>(
    private val api1: A1,
) : BaseDependencyHolder<D> {

    companion object {
        operator fun<A1 : BaseFeatureApi, D : BaseFeatureDependencies> invoke(
            api1: A1,
            block: (BaseDependencyHolder<D>, A1) -> D
        ): BaseDependencyHolder<D> {
            return object : DependencyHolder1<A1, D>(
                api1 = api1
            ) {
                override val block = block
            }
        }
    }

    abstract val block: (BaseDependencyHolder<D>, A1) -> D

    override val dependencies: D
        get() = block(this, api1)
}

abstract class DependencyHolder2<A1 : BaseFeatureApi, A2 : BaseFeatureApi, D : BaseFeatureDependencies>(
    private val api1: A1,
    private val api2: A2,
) : BaseDependencyHolder<D> {

    companion object {
        operator fun<A1 : BaseFeatureApi, A2 : BaseFeatureApi, D : BaseFeatureDependencies> invoke(
            api1: A1,
            api2: A2,
            block: (BaseDependencyHolder<D>, A1, A2) -> D
        ): BaseDependencyHolder<D> {
            return object : DependencyHolder2<A1, A2, D>(
                api1 = api1,
                api2 = api2
            ) {
                override val block = block
            }
        }
    }

    abstract val block: (BaseDependencyHolder<D>, A1, A2) -> D

    override val dependencies: D
        get() = block(this, api1, api2)
}

abstract class DependencyHolder3<A1 : BaseFeatureApi, A2 : BaseFeatureApi, A3 : BaseFeatureApi, D : BaseFeatureDependencies>(
    private val api1: A1,
    private val api2: A2,
    private val api3: A3,
) : BaseDependencyHolder<D> {
    abstract val block: (dependencyHolder: BaseDependencyHolder<D>, A1, A2, A3) -> D

    override val dependencies: D
        get() = block(this, api1, api2, api3)
}
