package com.ihridoydas.simpleapp.features.timeLineCompose.entities

sealed class MessageSender(open val message: String) {

    data class Candidate(
        val initials: String,
        override val message: String
    ) : MessageSender(message)

    data class HR(
        val initials: String,
        override val message: String
    ) : MessageSender(message)

    data class System(
        override val message: String
    ) : MessageSender(message)
}
