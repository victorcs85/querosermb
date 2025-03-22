package br.com.victorcs.querosermb.presentation.utils

import kotlinx.coroutines.Dispatchers

class IDispatchersProviderImpl : IDispatchersProvider {

    override val io = Dispatchers.IO

    override val main = Dispatchers.Main

    override val default = Dispatchers.Default
}