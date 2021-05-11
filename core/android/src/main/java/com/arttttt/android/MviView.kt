package com.arttttt.android

interface MviView<in Model : Any, Event : Any> : ViewRenderer<Model>, ViewEvents<Event>
