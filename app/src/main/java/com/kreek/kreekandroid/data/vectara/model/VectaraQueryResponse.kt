package com.kreek.kreekandroid.data.vectara.model


import com.google.gson.annotations.SerializedName

data class VectaraQueryResponse(
    @SerializedName("responseSet") val responseSet: List<ResponseSet?>?,
    @SerializedName("status") val status: List<Any?>?,
    @SerializedName("metrics") val metrics: Any?
) {
    data class ResponseSet(
        @SerializedName("response") val response: List<Response?>?,
        @SerializedName("status") val status: List<Any?>?,
        @SerializedName("document") val document: List<Document?>?,
        @SerializedName("summary") val summary: List<Summary?>?,
        @SerializedName("futureId") val futureId: Int?
    ) {
        data class Response(
            @SerializedName("text") val text: String?,
            @SerializedName("score") val score: Double?,
            @SerializedName("metadata") val metadata: List<Metadata?>?,
            @SerializedName("documentIndex") val documentIndex: Int?,
            @SerializedName("corpusKey") val corpusKey: CorpusKey?,
            @SerializedName("resultOffset") val resultOffset: Int?,
            @SerializedName("resultLength") val resultLength: Int?
        ) {
            data class Metadata(
                @SerializedName("name") val name: String?,
                @SerializedName("value") val value: String?
            )

            data class CorpusKey(
                @SerializedName("customerId") val customerId: Int?,
                @SerializedName("corpusId") val corpusId: Int?,
                @SerializedName("semantics") val semantics: String?,
                @SerializedName("dim") val dim: List<Any?>?,
                @SerializedName("metadataFilter") val metadataFilter: String?,
                @SerializedName("lexicalInterpolationConfig") val lexicalInterpolationConfig: Any?
            )
        }

        data class Document(
            @SerializedName("id") val id: String?,
            @SerializedName("metadata") val metadata: List<Metadata?>?
        ) {
            data class Metadata(
                @SerializedName("name") val name: String?,
                @SerializedName("value") val value: String?
            )
        }

        data class Summary(
            @SerializedName("text") val text: String?,
            @SerializedName("lang") val lang: String?,
            @SerializedName("prompt") val prompt: String?,
            @SerializedName("chat") val chat: Chat?,
            @SerializedName("factualConsistency") val factualConsistency: FactualConsistency?,
            @SerializedName("done") val done: Boolean?,
            @SerializedName("status") val status: List<Any?>?,
            @SerializedName("futureId") val futureId: Int?
        ) {
            data class Chat(
                @SerializedName("conversationId") val conversationId: String?,
                @SerializedName("turnId") val turnId: String?,
                @SerializedName("rephrasedQuery") val rephrasedQuery: String?,
                @SerializedName("status") val status: Any?
            )

            data class FactualConsistency(
                @SerializedName("score") val score: Double?,
                @SerializedName("status") val status: Status?
            ) {
                data class Status(
                    @SerializedName("code") val code: String?,
                    @SerializedName("statusDetail") val statusDetail: String?,
                    @SerializedName("cause") val cause: Any?
                )
            }
        }
    }
}